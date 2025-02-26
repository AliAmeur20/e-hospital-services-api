package com.example.eHospitalServices.Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "replishement")
public class Replishement {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ConsumableMD consumableMD;

    @Column
    private LocalDate date;

    @Column
    private Double quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        Replishement that = (Replishement) o;
        return Objects.equals(id, that.id) && Objects.equals(consumableMD, that.consumableMD) && Objects.equals(date, that.date) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, consumableMD, date, quantity);
    }

    @Override
    public String toString() {
        return "Replishement{" +
                "id=" + id +
                ", consumableMD=" + consumableMD +
                ", date=" + date +
                ", quantity=" + quantity +
                '}';
    }
}
