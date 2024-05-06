package com.example.eHospitalServices.DTOs;

import com.example.eHospitalServices.Enums.StockLevel;
import com.example.eHospitalServices.Models.DevicePackage;

import java.util.List;
import java.util.Objects;

public class StockDTO {
    private Long id;

    private Long consumableMDId;

    private String consumableMDName;

    private List<DevicePackageDTO> devicePackages;
    private Double quantity;

    private StockLevel level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsumableMDId() {
        return consumableMDId;
    }

    public void setConsumableMDId(Long consumableMDId) {
        this.consumableMDId = consumableMDId;
    }

    public String getConsumableMDName() {
        return consumableMDName;
    }

    public void setConsumableMDName(String consumableMDName) {
        this.consumableMDName = consumableMDName;
    }

    public List<DevicePackageDTO> getDevicePackages() {
        return devicePackages;
    }

    public void setDevicePackages(List<DevicePackageDTO> devicePackages) {
        this.devicePackages = devicePackages;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public StockLevel getLevel() {
        return level;
    }

    public void setLevel(StockLevel level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockDTO stockDTO = (StockDTO) o;
        return Objects.equals(id, stockDTO.id) && Objects.equals(consumableMDId, stockDTO.consumableMDId) && Objects.equals(consumableMDName, stockDTO.consumableMDName) && Objects.equals(devicePackages, stockDTO.devicePackages) && Objects.equals(quantity, stockDTO.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, consumableMDId, consumableMDName, devicePackages, quantity);
    }

    @Override
    public String toString() {
        return "StockDTO{" +
                "id=" + id +
                ", consumableMDId=" + consumableMDId +
                ", consumableMDName='" + consumableMDName + '\'' +
                ", devicePackages=" + devicePackages +
                ", quantity=" + quantity +
                '}';
    }
}
