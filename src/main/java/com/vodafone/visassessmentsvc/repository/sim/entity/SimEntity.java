package com.vodafone.visassessmentsvc.repository.sim.entity;

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
@Table(name = "sims")
public class SimEntity {

    @Id
    private Long simId;
    private String country;
    @Enumerated(EnumType.STRING)
    private SimStatus status;
    private String operatorCode;

}
