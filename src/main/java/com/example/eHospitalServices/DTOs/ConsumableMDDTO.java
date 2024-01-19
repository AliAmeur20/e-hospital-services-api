package com.example.eHospitalServices.DTOs;

import com.example.eHospitalServices.Enums.OrderType;
import java.util.Objects;

public class ConsumableMDDTO {
    private Long id;

    private String name;

    private String type;

    private OrderType orderType;

    private int size;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumableMDDTO that = (ConsumableMDDTO) o;
        return size == that.size && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && orderType == that.orderType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, orderType, size);
    }

    @Override
    public String toString() {
        return "ConsumableMDDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", orderType=" + orderType +
                ", size=" + size +
                '}';
    }
}
