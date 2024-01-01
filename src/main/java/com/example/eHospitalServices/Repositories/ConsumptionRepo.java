package com.example.eHospitalServices.Repositories;

import com.example.eHospitalServices.Models.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ConsumptionRepo extends JpaRepository<Consumption, Long>, JpaSpecificationExecutor<Consumption> {
    List<Consumption> findByConsumableMD_Id(Long id);
}
