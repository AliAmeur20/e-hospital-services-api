package com.example.eHospitalServices.DTOs;

import java.time.LocalDate;
import java.util.Objects;

public class ReceivedOrderDTO {

    private Long id;

    private Double quantity;

    private LocalDate date;

    private Long consumableMDId;

    private String consumableMDName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceivedOrderDTO that = (ReceivedOrderDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ReceivedOrderDTO{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", date=" + date +
                ", consumableMDId=" + consumableMDId +
                ", consumableMDName='" + consumableMDName + '\'' +
                '}';
    }
}
