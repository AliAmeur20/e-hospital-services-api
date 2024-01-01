package com.example.eHospitalServices.DTOs;

import com.example.eHospitalServices.Enums.CMDState;

import java.time.LocalDate;
import java.util.Objects;

public class ConsumableMDDTO {
    private Long id;

    private String name;

    private String type;

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
        ConsumableMDDTO that = (ConsumableMDDTO) o;
        return size == that.size && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, size);
    }

    @Override
    public String toString() {
        return "ConsumableMDDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size=" + size +
                '}';
    }
}
