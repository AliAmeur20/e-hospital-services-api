package com.example.eHospitalServices.Models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "security_storage")
public class SecurityStorage {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ConsumableMD consumableMD;

    @Column(name = "security_storage")
    private Long securityStorage;

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

    public Long getSecurityStorage() {
        return securityStorage;
    }

    public void setSecurityStorage(Long securityStorage) {
        this.securityStorage = securityStorage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityStorage that = (SecurityStorage) o;
        return Objects.equals(id, that.id) && Objects.equals(consumableMD, that.consumableMD) && Objects.equals(securityStorage, that.securityStorage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, consumableMD, securityStorage);
    }

    @Override
    public String toString() {
        return "SecurityStorage{" +
                "id=" + id +
                ", consumableMD=" + consumableMD +
                ", securityStorage=" + securityStorage +
                '}';
    }
}
