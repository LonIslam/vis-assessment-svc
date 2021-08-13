package com.vodafone.visassessmentsvc.resources.device.boundary;

import com.vodafone.visassessmentsvc.resources.device.control.DeviceCtrl;
import com.vodafone.visassessmentsvc.resources.device.entity.DeviceModel;
import com.vodafone.visassessmentsvc.repository.iot.devices.boundary.DevicesRepository;
import com.vodafone.visassessmentsvc.repository.iot.devices.entity.DeviceEntity;
import com.vodafone.visassessmentsvc.repository.iot.devices.entity.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/iot-device")
public class DeviceController {

    private final DevicesRepository devicesRepository;
    private final DeviceCtrl deviceCtrl;

    public DeviceController(DeviceCtrl deviceCtrl, DevicesRepository devicesRepository) {
        this.deviceCtrl = deviceCtrl;
        this.devicesRepository = devicesRepository;
    }


    @GetMapping("/waiting-activation")
    public ResponseEntity<Page<DeviceModel>> getAllDevicesWaitingActivation(@RequestParam("page") int page,
                                                                            @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<DeviceEntity> deviceEntities = devicesRepository.findByStatus(Status.NOT_READY, pageRequest);
        Page<DeviceModel> deviceModels = deviceCtrl.mapDeviceEntityToModel(deviceEntities);
        return new ResponseEntity<>(deviceModels, HttpStatus.OK);
    }

    @GetMapping("/available-sale")
    public ResponseEntity<Page<DeviceModel>> getAllDevicesAvailableForSale(@RequestParam("page") int page,
                                                                           @RequestParam("size") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<DeviceEntity> deviceEntities = devicesRepository.findByStatusAndTemperatureBetweenAndSimIsNotNullOrderByIdAsc(Status.READY, -25, 85, pageRequest);
        Page<DeviceModel> deviceModels = deviceCtrl.mapDeviceEntityToModel(deviceEntities);
        return new ResponseEntity<>(deviceModels, HttpStatus.OK);
    }
}
