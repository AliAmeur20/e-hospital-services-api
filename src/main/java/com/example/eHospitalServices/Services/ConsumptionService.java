package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.DTOs.ConsumptionAverageDTO;
import com.example.eHospitalServices.DTOs.ConsumptionDTO;
import com.example.eHospitalServices.Enums.CMDType;
import com.example.eHospitalServices.Enums.OrderType;
import com.example.eHospitalServices.Mappers.ConsumptionMapper;
import com.example.eHospitalServices.Models.ConsumableMD;
import com.example.eHospitalServices.Models.Consumption;
import com.example.eHospitalServices.Repositories.ConsumptionRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;
import java.util.Optional;

@Service
public class ConsumptionService {
    private final ConsumptionRepo consumptionRepo;
    private final ConsumptionMapper consumptionMapper;
    private final Utils utils;
    private final StockService stockService;
    private final ConsumableMDService consumableMDService;

    @PersistenceContext
    private EntityManager entityManager;

    public ConsumptionService(ConsumptionRepo consumptionRepo, ConsumptionMapper consumptionMapper, Utils utils, StockService stockService, ConsumableMDService consumableMDService) {
        this.consumptionRepo = consumptionRepo;
        this.consumptionMapper = consumptionMapper;
        this.utils = utils;
        this.stockService = stockService;
        this.consumableMDService = consumableMDService;
    }

    public ConsumptionDTO create(ConsumptionDTO consumptionDTO){
        ConsumableMD consumableMD = utils.checkAndGetCMD(consumptionDTO.getConsumableMDId());
        ConsumptionDTO newConsumption = new ConsumptionDTO();
        newConsumption.setConsumableMDId(consumptionDTO.getConsumableMDId());
        newConsumption.setDate(LocalDate.now());
        newConsumption.setQuantity(consumptionDTO.getQuantity());
        newConsumption.setStaff(consumptionDTO.getStaff());
        Double newQuantity = stockService.consume(consumptionDTO.getConsumableMDId(), consumptionDTO.getQuantity());
        newConsumption.setLower(Objects.equals(consumableMD.getOrderType(), OrderType.ORDER_POINT) && consumableMDService.checkQuantity(newConsumption.getConsumableMDId(), newQuantity));
        return save(newConsumption);
    }

    public void delete(Long id){
        Optional<Consumption> consumption = consumptionRepo.findById(id);
        consumptionRepo.delete(consumption.get());
    }

    public ConsumptionDTO save(ConsumptionDTO consumptionDTO){
        Consumption consumption = consumptionMapper.toEntity(consumptionDTO);
        Consumption savedConsumption = consumptionRepo.save(consumption);
        return consumptionMapper.toDTO(savedConsumption);
    }

    public ConsumptionAverageDTO calculateConsumptionAverage(Long consumableId){

        YearMonth currentMonth = YearMonth.now();
        LocalDate startOfCurrentMonth = currentMonth.atDay(1);
        LocalDate endOfCurrentMonth = currentMonth.atEndOfMonth();

        YearMonth previousMonth = currentMonth.minusMonths(1);
        LocalDate startOfPreviousMonth = previousMonth.atDay(1);
        LocalDate endOfPreviousMonth = previousMonth.atEndOfMonth();

        Object[] result = (Object[]) entityManager.createQuery(
                        "SELECT AVG(CASE WHEN c.date BETWEEN :startOfCurrentMonth AND :endOfCurrentMonth THEN c.quantity ELSE NULL END), " +
                                "AVG(CASE WHEN c.date BETWEEN :startOfPreviousMonth AND :endOfPreviousMonth THEN c.quantity ELSE NULL END) " +
                                "FROM Consumption c " +
                                "WHERE c.consumableMD.id = :consumableId")
                .setParameter("startOfCurrentMonth", startOfCurrentMonth)
                .setParameter("endOfCurrentMonth", endOfCurrentMonth)
                .setParameter("startOfPreviousMonth", startOfPreviousMonth)
                .setParameter("endOfPreviousMonth", endOfPreviousMonth)
                .setParameter("consumableId", consumableId)
                .getSingleResult();

        long currentMonthAverage = result[0] != null ? ((Number) result[0]).longValue() : 0;
        long previousMonthAverage = result[1] != null ? ((Number) result[1]).longValue() : 0;

        ConsumptionAverageDTO consumptionAverage = new ConsumptionAverageDTO();
        consumptionAverage.setCurrentMonthAverage(Math.round(currentMonthAverage));
        consumptionAverage.setPreviousMonthAverage(Math.round(previousMonthAverage));

        return consumptionAverage;
    }
}
