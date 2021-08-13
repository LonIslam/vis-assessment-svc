package com.vodafone.visassessmentsvc.resources.management.control;


import com.vodafone.visassessmentsvc.resources.management.entity.DeviceConfigurationModel;
import com.vodafone.visassessmentsvc.repository.iot.devices.entity.DeviceEntity;
import com.vodafone.visassessmentsvc.repository.iot.devices.entity.Status;
import com.vodafone.visassessmentsvc.repository.sim.entity.SimEntity;
import com.vodafone.visassessmentsvc.repository.sim.entity.SimStatus;
import org.springframework.stereotype.Component;

@Component
public class ManagementCtrl {


    public void configureDevice(DeviceEntity deviceEntity, DeviceConfigurationModel deviceConfigurationModel,
                                SimEntity simEntity) {
        deviceEntity.setStatus(deviceConfigurationModel.getStatus());
        deviceEntity.setTemperature(deviceConfigurationModel.getTemperature());
        deviceEntity.setSim(simEntity);
    }

    public void unconfigureDevice(DeviceEntity deviceEntity) {
        deviceEntity.setStatus(Status.NOT_READY);
    }

    public void activateSim(SimEntity simEntity) {
        simEntity.setStatus(SimStatus.ACTIVE);
    }
}
