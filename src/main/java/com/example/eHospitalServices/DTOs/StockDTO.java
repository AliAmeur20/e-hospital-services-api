package com.example.eHospitalServices.DTOs;

import java.util.Objects;

public class StockDTO {
    private Long id;

    private String name;

    private Long consumableMDId;

    private Double quantity;

    private String location;

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

    public Long getConsumableMDId() {
        return consumableMDId;
    }

    public void setConsumableMDId(Long consumableMDId) {
        this.consumableMDId = consumableMDId;
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
        StockDTO stockDTO = (StockDTO) o;
        return Objects.equals(id, stockDTO.id) && Objects.equals(name, stockDTO.name) && Objects.equals(consumableMDId, stockDTO.consumableMDId) && Objects.equals(quantity, stockDTO.quantity) && Objects.equals(location, stockDTO.location) && Objects.equals(level, stockDTO.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, consumableMDId, quantity, location, level);
    }

    @Override
    public String toString() {
        return "StockDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", consumableMDId=" + consumableMDId +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
