package com.example.eHospitalServices.Repositories;

import com.example.eHospitalServices.Models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StockRepo extends JpaRepository<Stock, Long>, JpaSpecificationExecutor<Stock> {

    Stock findByConsumableMD_Id(Long id);

}
