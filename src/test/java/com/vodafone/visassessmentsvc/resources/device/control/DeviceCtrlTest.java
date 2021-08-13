package com.vodafone.visassessmentsvc.resources.device.control;

import com.vodafone.visassessmentsvc.repository.iot.devices.entity.DeviceEntity;
import com.vodafone.visassessmentsvc.repository.iot.devices.entity.Status;
import com.vodafone.visassessmentsvc.resources.device.entity.DeviceModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class DeviceCtrlTest {

    DeviceCtrl deviceCtrl = new DeviceCtrl();

    @Test
    public void testMapDeviceEntityToModel(){
        DeviceEntity deviceEntity  = new DeviceEntity(1l,null, Status.NOT_READY,15);
        final Page<DeviceEntity> page = new PageImpl<>(List.of(deviceEntity));

        Page<DeviceModel> deviceModelPage = deviceCtrl.mapDeviceEntityToModel(page);
        Assertions.assertEquals(1, deviceModelPage.getSize());
        DeviceModel deviceMapped = deviceModelPage.stream().findFirst().get();
        Assertions.assertEquals(deviceMapped.getId(), deviceEntity.getId());
        Assertions.assertEquals(deviceMapped.getStatus(), deviceEntity.getStatus().name());
        Assertions.assertEquals(deviceMapped.getTemperature(), deviceEntity.getTemperature());

    }
}
