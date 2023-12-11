package com.example.eHospitalServices.Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @OneToOne
    @JoinColumn(nullable = false)
    private ConsumableMD consumableMD;

    @Column
    private Double quantity;

    @Column
    private String location;

    @Column
    private String level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConsumableMD getConsumableMD() {
        return consumableMD;
    }

    public void setConsumableMD(ConsumableMD consumableMD) {
        this.consumableMD = consumableMD;
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
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id) && Objects.equals(name, stock.name) && Objects.equals(consumableMD, stock.consumableMD) && Objects.equals(quantity, stock.quantity) && Objects.equals(location, stock.location) && Objects.equals(level, stock.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, consumableMD, quantity, location, level);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", consumableMD=" + consumableMD +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
