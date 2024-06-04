package com.example.eHospitalServices.Models;

import com.example.eHospitalServices.Enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "md_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDate date;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ConsumableMD consumableMD;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    private Double quantity;

    @Column
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

    public ConsumableMD getConsumableMD() {
        return consumableMD;
    }

    public void setConsumableMD(ConsumableMD consumableMD) {
        this.consumableMD = consumableMD;
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
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(date, order.date) && Objects.equals(consumableMD, order.consumableMD) && Objects.equals(deliveryDate, order.deliveryDate) && status == order.status && Objects.equals(quantity, order.quantity) && Objects.equals(cost, order.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, consumableMD, deliveryDate, status, quantity, cost);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", consumableMD=" + consumableMD +
                ", deliveryDate=" + deliveryDate +
                ", status=" + status +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
