package com.vodafone.visassessmentsvc.repository.sim.boundary;

import com.vodafone.visassessmentsvc.repository.sim.entity.SimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimsRepository extends JpaRepository<SimEntity, Long> {

}
