package com.example.eHospitalServices.DTOs;

import java.util.Objects;

public class ConsumptionAverageDTO {

    long currentMonthAverage;

    long previousMonthAverage;

    public long getCurrentMonthAverage() {
        return currentMonthAverage;
    }

    public void setCurrentMonthAverage(long currentMonthAverage) {
        this.currentMonthAverage = currentMonthAverage;
    }

    public long getPreviousMonthAverage() {
        return previousMonthAverage;
    }

    public void setPreviousMonthAverage(long previousMonthAverage) {
        this.previousMonthAverage = previousMonthAverage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumptionAverageDTO that = (ConsumptionAverageDTO) o;
        return currentMonthAverage == that.currentMonthAverage && previousMonthAverage == that.previousMonthAverage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentMonthAverage, previousMonthAverage);
    }

    @Override
    public String toString() {
        return "ConsumptionAverageDTO{" +
                "currentMonthAverage=" + currentMonthAverage +
                ", previousMonthAverage=" + previousMonthAverage +
                '}';
    }
}
