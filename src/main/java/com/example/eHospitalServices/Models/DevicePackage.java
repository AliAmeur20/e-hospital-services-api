package com.example.eHospitalServices.Models;

import com.example.eHospitalServices.Enums.CMDState;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "device_package")
public class DevicePackage {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String reference;

    @Column
    private Long number;

    @Column
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @OneToOne
    private Replishement replishement;

    @Column(nullable = false)
    private LocalDate expDate;

    @Formula("CASE WHEN expDate >= CURRENT_DATE + 5 THEN 'SAFE' " +
            "WHEN expDate >= CURRENT_DATE THEN 'CLOSE_TO_DATE' " +
            "ELSE 'EXPIRED' END")
    @Enumerated(EnumType.STRING)
    private CMDState cmdState;

    @Column
    private Double quantity;

    @Column
    private String location;

    @Column
    private String room;

    @Column
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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Replishement getReplishement() {
        return replishement;
    }

    public void setReplishement(Replishement replishement) {
        this.replishement = replishement;
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
        DevicePackage that = (DevicePackage) o;
        return Objects.equals(id, that.id) && Objects.equals(reference, that.reference) && Objects.equals(number, that.number) && Objects.equals(date, that.date) && Objects.equals(stock, that.stock) && Objects.equals(replishement, that.replishement) && Objects.equals(expDate, that.expDate) && cmdState == that.cmdState && Objects.equals(quantity, that.quantity) && Objects.equals(location, that.location) && Objects.equals(room, that.room) && Objects.equals(wardrobe, that.wardrobe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reference, number, date, stock, replishement, expDate, cmdState, quantity, location, room, wardrobe);
    }

    @Override
    public String toString() {
        return "DevicePackage{" +
                "id=" + id +
                ", reference='" + reference + '\'' +
                ", number=" + number +
                ", date=" + date +
                ", stock=" + stock +
                ", replishement=" + replishement +
                ", expDate=" + expDate +
                ", cmdState=" + cmdState +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                ", room='" + room + '\'' +
                ", wardrobe='" + wardrobe + '\'' +
                '}';
    }
}
