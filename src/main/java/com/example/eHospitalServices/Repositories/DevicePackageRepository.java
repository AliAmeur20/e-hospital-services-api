package com.example.eHospitalServices.Repositories;

import com.example.eHospitalServices.Models.DevicePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DevicePackageRepository extends JpaRepository<DevicePackage, Long>, JpaSpecificationExecutor<DevicePackage> {
    DevicePackage findByReplishement_Id (Long id);

    List<DevicePackage> findByStock_Id (Long id);

    @Query("SELECT d FROM DevicePackage d WHERE d.stock.id = :stockId ORDER BY d.expDate ASC, d.quantity ASC")
    List<DevicePackage> findDevicesByStockIdOrderByExpiryDateAndQuantity(@Param("stockId") Long stockId);

    @Query("SELECT coalesce(max(dp.number),0) FROM DevicePackage dp")
    Long getMaxDevicePackageNumber();
}
