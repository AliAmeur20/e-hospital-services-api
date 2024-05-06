package com.example.eHospitalServices.Repositories;

import com.example.eHospitalServices.Models.ConsumableMD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ConsumableMDRepo extends JpaRepository<ConsumableMD, Long>, JpaSpecificationExecutor<ConsumableMD> {
    @Query("SELECT c FROM ConsumableMD c WHERE NOT EXISTS (SELECT s FROM Stock s WHERE s.consumableMD = c)")
    List<ConsumableMD> findConsumableMDsNotAttachedToStock();
    @Query("SELECT c FROM ConsumableMD c JOIN Stock s ON c.id = s.consumableMD.id")
    List<ConsumableMD> findConsumableMDsAttachedToStock();

    @Query("SELECT coalesce(max(cmd.number),0) FROM ConsumableMD cmd")
    Long getMaxDeviceNumber();
}
