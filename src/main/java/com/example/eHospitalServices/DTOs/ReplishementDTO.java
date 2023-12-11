package com.example.eHospitalServices.DTOs;

import java.time.LocalDate;
import java.util.Objects;

public class ReplishementDTO {
    private Long id;

    private Long consumableMDId;

    private String supplier;

    private LocalDate date;

    private Double quantity;

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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReplishementDTO that = (ReplishementDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(consumableMDId, that.consumableMDId) && Objects.equals(supplier, that.supplier) && Objects.equals(date, that.date) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, consumableMDId, supplier, date, quantity);
    }

    @Override
    public String toString() {
        return "ReplishementDTO{" +
                "id=" + id +
                ", consumableMDId=" + consumableMDId +
                ", supplier='" + supplier + '\'' +
                ", date=" + date +
                ", quantity=" + quantity +
                '}';
    }
}
