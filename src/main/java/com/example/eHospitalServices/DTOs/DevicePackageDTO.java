package com.example.eHospitalServices.DTOs;

import com.example.eHospitalServices.Enums.CMDState;

import java.time.LocalDate;
import java.util.Objects;

public class DevicePackageDTO {

    private Long id;

    private Long stockId;

    private Long replishementId;
    private LocalDate expDate;

    private CMDState cmdState;

    private Double quantity;

    private String location;

    private String level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getReplishementId() {
        return replishementId;
    }

    public void setReplishementId(Long replishementId) {
        this.replishementId = replishementId;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public CMDState getCmdState() {
        return cmdState;
    }

    public void setCmdState(CMDState cmdState) {
        this.cmdState = cmdState;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevicePackageDTO that = (DevicePackageDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(stockId, that.stockId) && Objects.equals(replishementId, that.replishementId) && Objects.equals(expDate, that.expDate) && cmdState == that.cmdState && Objects.equals(quantity, that.quantity) && Objects.equals(location, that.location) && Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stockId, replishementId, expDate, cmdState, quantity, location, level);
    }

    @Override
    public String toString() {
        return "DevicePackageDTO{" +
                "id=" + id +
                ", stockId=" + stockId +
                ", replishementId=" + replishementId +
                ", expDate=" + expDate +
                ", cmdState=" + cmdState +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
