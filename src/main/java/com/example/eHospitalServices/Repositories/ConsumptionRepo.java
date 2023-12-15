package com.example.eHospitalServices.Repositories;

import com.example.eHospitalServices.Models.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConsumptionRepo extends JpaRepository<Consumption, Long>, JpaSpecificationExecutor<Consumption> {
}
