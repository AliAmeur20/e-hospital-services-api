package com.example.eHospitalServices.DTOs;

import java.time.LocalDate;
import java.util.Objects;

public class CreateReplishementDTO {

    private Long cmdId;

    private String supplier;

    private Double quantity;

    private LocalDate expDate;

    private String location;

    private String level;

    public Long getCmdId() {
        return cmdId;
    }

    public void setCmdId(Long cmdId) {
        this.cmdId = cmdId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
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
        CreateReplishementDTO that = (CreateReplishementDTO) o;
        return Objects.equals(cmdId, that.cmdId) && Objects.equals(supplier, that.supplier) && Objects.equals(quantity, that.quantity) && Objects.equals(expDate, that.expDate) && Objects.equals(location, that.location) && Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cmdId, supplier, quantity, expDate, location, level);
    }

    @Override
    public String toString() {
        return "createReplishementDTO{" +
                "cmdId=" + cmdId +
                ", supplier='" + supplier + '\'' +
                ", quantity=" + quantity +
                ", expDate=" + expDate +
                ", location='" + location + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
