package com.example.eHospitalServices.DTOs;

import com.example.eHospitalServices.Enums.CMDType;
import com.example.eHospitalServices.Enums.OrderType;

import java.time.LocalDate;
import java.util.Objects;

public class ConsumableMDDTO {
    private Long id;

    private String name;

    private String reference;

    private Long number;

    private LocalDate date;

    private CMDType type;

    private OrderType orderType;

    private byte[] image;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumableMDDTO that = (ConsumableMDDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(reference, that.reference) && Objects.equals(number, that.number) && Objects.equals(date, that.date) && type == that.type && orderType == that.orderType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, reference, number, date, type, orderType);
    }

    @Override
    public String toString() {
        return "ConsumableMDDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                ", number=" + number +
                ", date=" + date +
                ", type=" + type +
                ", orderType=" + orderType +
                '}';
    }
}
