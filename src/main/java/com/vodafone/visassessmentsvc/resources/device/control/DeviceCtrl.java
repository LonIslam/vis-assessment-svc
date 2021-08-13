package com.vodafone.visassessmentsvc.resources.device.control;

import com.vodafone.visassessmentsvc.resources.device.entity.DeviceModel;
import com.vodafone.visassessmentsvc.repository.iot.devices.entity.DeviceEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class DeviceCtrl {
    public Page<DeviceModel> mapDeviceEntityToModel(Page<DeviceEntity> deviceEntities) {
        return deviceEntities.map(deviceEntity ->
                new DeviceModel(deviceEntity.getId(), deviceEntity.getStatus().name(), deviceEntity.getTemperature()));
    }
}
