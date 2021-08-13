package com.vodafone.visassessmentsvc.resources.management.control;

import com.vodafone.visassessmentsvc.repository.iot.devices.entity.DeviceEntity;
import com.vodafone.visassessmentsvc.repository.iot.devices.entity.Status;
import com.vodafone.visassessmentsvc.repository.sim.entity.SimEntity;
import com.vodafone.visassessmentsvc.repository.sim.entity.SimStatus;
import com.vodafone.visassessmentsvc.resources.management.entity.DeviceConfigurationModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagementCtrlTest {

    ManagementCtrl managementCtrl = new ManagementCtrl();

    @Test
    public void testConfigureDevice() {
        DeviceEntity deviceEntity = new DeviceEntity(1l, null, Status.NOT_READY, 100);
        SimEntity simEntity = new SimEntity(1l, "EGYPT", SimStatus.WAITING_FOR_ACTIVATION, "0100");
        DeviceConfigurationModel deviceConfigurationModel = new DeviceConfigurationModel(deviceEntity.getId(), Status.READY, deviceEntity.getTemperature(), simEntity.getSimId());
        managementCtrl.configureDevice(deviceEntity, deviceConfigurationModel, simEntity);

        Assertions.assertEquals(deviceConfigurationModel.getStatus(), deviceEntity.getStatus());
        Assertions.assertEquals(deviceConfigurationModel.getTemperature(), deviceEntity.getTemperature());
        Assertions.assertEquals(deviceConfigurationModel.getSimId(), deviceEntity.getSim().getSimId());

    }

    @Test
    public void testUnconfigureDevice() {
        DeviceEntity deviceEntity = new DeviceEntity(1l, null, Status.READY, 15);

        managementCtrl.unconfigureDevice(deviceEntity);
        Assertions.assertNotEquals(Status.READY, deviceEntity.getStatus());
        Assertions.assertEquals(Status.NOT_READY, deviceEntity.getStatus());
    }

    @Test
    public void testActivateSim() {
        SimEntity simEntity = new SimEntity(1l, "EGYPT", SimStatus.WAITING_FOR_ACTIVATION, "012");

        managementCtrl.activateSim(simEntity);
        Assertions.assertNotEquals(SimStatus.WAITING_FOR_ACTIVATION, simEntity.getStatus());
        Assertions.assertEquals(SimStatus.ACTIVE, simEntity.getStatus());

    }
}
