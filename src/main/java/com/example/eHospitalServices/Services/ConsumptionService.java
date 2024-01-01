package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.DTOs.ConsumptionDTO;
import com.example.eHospitalServices.Mappers.ConsumptionMapper;
import com.example.eHospitalServices.Models.Consumption;
import com.example.eHospitalServices.Repositories.ConsumptionRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ConsumptionService {
    private final ConsumptionRepo consumptionRepo;
    private final ConsumptionMapper consumptionMapper;
    private final Utils utils;
    private final StockService stockService;

    public ConsumptionService(ConsumptionRepo consumptionRepo, ConsumptionMapper consumptionMapper, Utils utils, StockService stockService) {
        this.consumptionRepo = consumptionRepo;
        this.consumptionMapper = consumptionMapper;
        this.utils = utils;
        this.stockService = stockService;
    }

    public ConsumptionDTO create(ConsumptionDTO consumptionDTO){
        utils.checkAndGetCMD(consumptionDTO.getConsumableMDId());
        ConsumptionDTO newConsumption = new ConsumptionDTO();
        newConsumption.setConsumableMDId(consumptionDTO.getConsumableMDId());
        newConsumption.setDate(LocalDate.now());
        newConsumption.setQuantity(consumptionDTO.getQuantity());
        newConsumption.setStaff(consumptionDTO.getStaff());
        stockService.consume(consumptionDTO.getConsumableMDId(), consumptionDTO.getQuantity());
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
}
