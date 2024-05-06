package com.example.eHospitalServices.Models;

import com.example.eHospitalServices.Enums.CMDType;
import com.example.eHospitalServices.Enums.OrderType;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "consumable_m_d")
public class ConsumableMD {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String reference;

    @Column
    private Long number;

    @Column
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private CMDType type;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Column
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CMDType getType() {
        return type;
    }

    public void setType(CMDType type) {
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
        ConsumableMD that = (ConsumableMD) o;
        return size == that.size && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(reference, that.reference) && Objects.equals(number, that.number) && Objects.equals(date, that.date) && type == that.type && orderType == that.orderType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, reference, number, date, type, orderType, size);
    }

    @Override
    public String toString() {
        return "ConsumableMD{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                ", number=" + number +
                ", date=" + date +
                ", type=" + type +
                ", orderType=" + orderType +
                ", size=" + size +
                '}';
    }
}
