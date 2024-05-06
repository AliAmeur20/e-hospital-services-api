package com.example.eHospitalServices.Repositories;

import com.example.eHospitalServices.Models.SecurityStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityStorageRepo extends JpaRepository<SecurityStorage, Long> {
    SecurityStorage findByConsumableMD_Id(Long id);
}
