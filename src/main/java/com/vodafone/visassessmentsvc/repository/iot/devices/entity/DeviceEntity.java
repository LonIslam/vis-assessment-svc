package com.vodafone.visassessmentsvc.repository.iot.devices.entity;

import com.vodafone.visassessmentsvc.repository.sim.entity.SimEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author islam-gamal
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "devices")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "sim_id")
    private SimEntity sim;
    @Enumerated(EnumType.STRING)
    private Status status;
    private int temperature;
}
