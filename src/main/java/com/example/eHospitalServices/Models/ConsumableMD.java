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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumableMD that = (ConsumableMD) o;
        return size == that.size && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, size);
    }

    @Override
    public String toString() {
        return "ConsumableMD{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                '}';
    }
}
