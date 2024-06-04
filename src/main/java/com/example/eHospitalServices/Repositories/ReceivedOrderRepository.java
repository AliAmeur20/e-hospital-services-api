package com.example.eHospitalServices.Repositories;

import com.example.eHospitalServices.Models.ReceivedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReceivedOrderRepository extends JpaRepository<ReceivedOrder, Long>, JpaSpecificationExecutor<ReceivedOrder> {
}
