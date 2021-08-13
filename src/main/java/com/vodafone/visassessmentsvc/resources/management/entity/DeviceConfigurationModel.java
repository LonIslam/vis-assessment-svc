package com.vodafone.visassessmentsvc.resources.management.entity;

import com.vodafone.visassessmentsvc.repository.iot.devices.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceConfigurationModel {
    @NotNull
    private Long deviceId;
    @NotNull
    private Status status;
    private int temperature;
    private Long simId;
}
