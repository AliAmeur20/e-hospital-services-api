package com.example.eHospitalServices.Repositories;

import com.example.eHospitalServices.Models.ConsumableMD;
import com.example.eHospitalServices.Models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepo extends JpaRepository<Stock, Long> {

    Stock findByConsumableMD_Id(Long id);

}
