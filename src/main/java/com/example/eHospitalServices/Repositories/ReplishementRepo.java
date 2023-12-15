package com.example.eHospitalServices.Repositories;

import com.example.eHospitalServices.Models.Replishement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReplishementRepo extends JpaRepository<Replishement, Long>, JpaSpecificationExecutor<Replishement> {
}
