package com.vodafone.visassessmentsvc.resources.management.boundary;

import com.vodafone.visassessmentsvc.resources.management.control.ManagementCtrl;
import com.vodafone.visassessmentsvc.resources.management.entity.DeviceConfigurationModel;
import com.vodafone.visassessmentsvc.repository.iot.devices.boundary.DevicesRepository;
import com.vodafone.visassessmentsvc.repository.iot.devices.entity.DeviceEntity;
import com.vodafone.visassessmentsvc.repository.iot.devices.entity.Status;
import com.vodafone.visassessmentsvc.repository.sim.boundary.SimsRepository;
import com.vodafone.visassessmentsvc.repository.sim.entity.SimEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/devices")
public class ManagementController {

    private final DevicesRepository devicesRepository;
    private final SimsRepository simRepository;
    private final ManagementCtrl managementCtrl;

    public ManagementController(ManagementCtrl managementCtrl, DevicesRepository devicesRepository, SimsRepository simRepository) {
        this.managementCtrl = managementCtrl;
        this.devicesRepository = devicesRepository;
        this.simRepository = simRepository;
    }


    @PutMapping
    public ResponseEntity<DeviceConfigurationModel> configureADevice(@RequestBody DeviceConfigurationModel deviceConfigurationModel) {
        Optional<DeviceEntity> deviceEntity = devicesRepository.findById(deviceConfigurationModel.getDeviceId());
        Optional<SimEntity> simEntityOptional = simRepository.findById(deviceConfigurationModel.getSimId());

        if (deviceEntity.isPresent() && deviceConfigurationModel.getStatus().equals(Status.READY) &&
                deviceConfigurationModel.getTemperature() <= 85 && deviceConfigurationModel.getTemperature() >= -25 &&
                deviceConfigurationModel.getSimId() != null && simEntityOptional.isPresent()) {
            managementCtrl.configureDevice(deviceEntity.get(), deviceConfigurationModel, simEntityOptional.get());
            devicesRepository.save(deviceEntity.get());
            managementCtrl.activateSim(simEntityOptional.get());
            simRepository.save(simEntityOptional.get());
            return ResponseEntity.ok(deviceConfigurationModel);
        } else if (deviceEntity.isPresent() && deviceConfigurationModel.getStatus().equals(Status.NOT_READY)) {
            managementCtrl.unconfigureDevice(deviceEntity.get());
            devicesRepository.save(deviceEntity.get());
            return ResponseEntity.ok(deviceConfigurationModel);
        }
        return ResponseEntity.badRequest().build();
    }
}
