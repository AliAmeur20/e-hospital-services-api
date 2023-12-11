package com.example.eHospitalServices.Models;

import com.example.eHospitalServices.Enums.CMDState;
import jakarta.persistence.*;
import org.hibernate.annotations.Formula;

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
    private String type;

    @Column
    private int size;

    @Column(nullable = false)
    private LocalDate expDate;

    @Formula("CASE WHEN expDate >= CURRENT_DATE + 5 THEN 'SAFE' " +
            "WHEN expDate >= CURRENT_DATE THEN 'CLOSE_TO_DATE' " +
            "ELSE 'EXPIRED' END")
    @Enumerated(EnumType.STRING)
    private CMDState cmdState;

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumableMD that = (ConsumableMD) o;
        return size == that.size && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(type, that.type) && Objects.equals(expDate, that.expDate) && cmdState == that.cmdState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, size, expDate, cmdState);
    }

    @Override
    public String toString() {
        return "ConsumableMD{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                ", expDate=" + expDate +
                ", cmdState=" + cmdState +
                '}';
    }
}
