package com.example.eHospitalServices.DTOs;

import com.example.eHospitalServices.Enums.OrderStatus;

import java.time.LocalDate;
import java.util.Objects;

public class OrderDTO {

    private Long id;

    private LocalDate date;

    private Long consumableMDId;

    private String consumableMDName;

    private LocalDate deliveryDate;

    private OrderStatus status;

    private Double quantity;

    private Double cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(id, orderDTO.id) && Objects.equals(date, orderDTO.date) && Objects.equals(consumableMDId, orderDTO.consumableMDId) && Objects.equals(consumableMDName, orderDTO.consumableMDName) && Objects.equals(deliveryDate, orderDTO.deliveryDate) && status == orderDTO.status && Objects.equals(quantity, orderDTO.quantity) && Objects.equals(cost, orderDTO.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, consumableMDId, consumableMDName, deliveryDate, status, quantity, cost);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", date=" + date +
                ", consumableMDId=" + consumableMDId +
                ", consumableMDName='" + consumableMDName + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", status=" + status +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
