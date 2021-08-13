package com.vodafone.visassessmentsvc.resources.device.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeviceModel {
    private Long id;
    private String status;
    private int temperature;
}
