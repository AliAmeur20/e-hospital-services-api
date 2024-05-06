package com.example.eHospitalServices.DTOs;

import com.example.eHospitalServices.Enums.CMDState;

import java.time.LocalDate;
import java.util.Objects;

public class DevicePackageDTO {

    private Long id;

    private String reference;

    private Long number;

    private LocalDate date;

    private Long stockId;

    private Long replishementId;
    private LocalDate expDate;

    private CMDState cmdState;

    private Double quantity;

    private String location;

    private String room;

    private String wardrobe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Long getReplishementId() {
        return replishementId;
    }

    public void setReplishementId(Long replishementId) {
        this.replishementId = replishementId;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public CMDState getCmdState() {
        return cmdState;
    }

    public void setCmdState(CMDState cmdState) {
        this.cmdState = cmdState;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getWardrobe() {
        return wardrobe;
    }

    public void setWardrobe(String wardrobe) {
        this.wardrobe = wardrobe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevicePackageDTO that = (DevicePackageDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(reference, that.reference) && Objects.equals(number, that.number) && Objects.equals(date, that.date) && Objects.equals(stockId, that.stockId) && Objects.equals(replishementId, that.replishementId) && Objects.equals(expDate, that.expDate) && cmdState == that.cmdState && Objects.equals(quantity, that.quantity) && Objects.equals(location, that.location) && Objects.equals(room, that.room) && Objects.equals(wardrobe, that.wardrobe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reference, number, date, stockId, replishementId, expDate, cmdState, quantity, location, room, wardrobe);
    }

    @Override
    public String toString() {
        return "DevicePackageDTO{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", number=" + number +
                ", date=" + date +
                ", stockId=" + stockId +
                ", replishementId=" + replishementId +
                ", expDate=" + expDate +
                ", cmdState=" + cmdState +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                ", room='" + room + '\'' +
                ", wardrobe='" + wardrobe + '\'' +
                '}';
    }
}
