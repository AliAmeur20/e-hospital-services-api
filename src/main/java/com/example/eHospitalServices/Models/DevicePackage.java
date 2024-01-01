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
    private String level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        DevicePackage that = (DevicePackage) o;
        return Objects.equals(id, that.id) && Objects.equals(stock, that.stock) && Objects.equals(replishement, that.replishement) && Objects.equals(expDate, that.expDate) && cmdState == that.cmdState && Objects.equals(quantity, that.quantity) && Objects.equals(location, that.location) && Objects.equals(level, that.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stock, replishement, expDate, cmdState, quantity, location, level);
    }

    @Override
    public String toString() {
        return "DevicePackage{" +
                "id=" + id +
                ", stock=" + stock +
                ", replishement=" + replishement +
                ", expDate=" + expDate +
                ", cmdState=" + cmdState +
                ", quantity=" + quantity +
                ", location='" + location + '\'' +
                ", level='" + level + '\'' +
                '}';
    }
}
