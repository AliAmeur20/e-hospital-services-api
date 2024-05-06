package com.example.eHospitalServices.Models;

import com.example.eHospitalServices.Enums.StockLevel;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false)
    private ConsumableMD consumableMD;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<DevicePackage> devicePackages;

    @Column
    private Double quantity;

    @Formula("CASE WHEN quantity >= 100 AND quantity <= 200  THEN 'Moyen' " +
            "WHEN quantity > 200 THEN 'Haut' " +
            "ELSE 'Bas' END")
    @Enumerated(EnumType.STRING)
    private StockLevel level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<DevicePackage> getDevicePackages() {
        return devicePackages;
    }

    public void setDevicePackages(List<DevicePackage> devicePackages) {
        this.devicePackages = devicePackages;
    }

    public StockLevel getLevel() {
        return level;
    }

    public void setLevel(StockLevel level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(id, stock.id) && Objects.equals(consumableMD, stock.consumableMD) && Objects.equals(devicePackages, stock.devicePackages) && Objects.equals(quantity, stock.quantity) && Objects.equals(level, stock.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, consumableMD, devicePackages, quantity, level);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", consumableMD=" + consumableMD +
                ", devicePackages=" + devicePackages +
                ", quantity=" + quantity +
                ", level='" + level + '\'' +
                '}';
    }
}
