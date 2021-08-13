package com.vodafone.visassessmentsvc.repository.iot.devices.boundary;

import com.vodafone.visassessmentsvc.repository.iot.devices.entity.DeviceEntity;
import com.vodafone.visassessmentsvc.repository.iot.devices.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicesRepository extends JpaRepository<DeviceEntity, Long> {
    Page<DeviceEntity> findByStatus(Status status, Pageable pageable);
    Page<DeviceEntity> findByStatusAndTemperatureBetweenAndSimIsNotNullOrderByIdAsc(Status status, int from, int to, Pageable pageable);
}
