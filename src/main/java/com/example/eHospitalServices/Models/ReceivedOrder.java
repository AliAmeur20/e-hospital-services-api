package com.example.eHospitalServices.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "received_order")
public class ReceivedOrder {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Double quantity;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ConsumableMD consumableMD;

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

    public ConsumableMD getConsumableMD() {
        return consumableMD;
    }

    public void setConsumableMD(ConsumableMD consumableMD) {
        this.consumableMD = consumableMD;
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
        ReceivedOrder that = (ReceivedOrder) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ReceivedOrder{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", date=" + date +
                ", consumableMD=" + consumableMD +
                '}';
    }
}
