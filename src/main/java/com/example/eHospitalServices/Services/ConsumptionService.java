package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.DTOs.ConsumptionDTO;
import com.example.eHospitalServices.Mappers.ConsumptionMapper;
import com.example.eHospitalServices.Models.Consumption;
import com.example.eHospitalServices.Repositories.ConsumptionRepo;
import org.springframework.stereotype.Service;

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
        stockService.destockCMD(consumptionDTO.getConsumableMDId(), consumptionDTO.getQuantity());
        return save(consumptionDTO);
    }

    public ConsumptionDTO update(ConsumptionDTO consumptionDTO, Long id){
        Optional<Consumption> consumption = consumptionRepo.findById(id);
        stockService.restockCMD(consumption.get().getConsumableMD().getId(), consumption.get().getQuantity());
        consumption = consumption.map(
                cons -> {
                    if(consumptionDTO.getQuantity() != 0) cons.setQuantity(consumptionDTO.getQuantity());
                    if(consumptionDTO.getDate() != null) cons.setDate(consumptionDTO.getDate());
                    if(consumptionDTO.getStaff() != null) cons.setStaff(consumptionDTO.getStaff());
                    return consumptionRepo.save(cons);
                });
        stockService.destockCMD(consumption.get().getConsumableMD().getId(), consumption.get().getQuantity());
        return consumptionMapper.toDTO(consumption.get());
    }

    public void delete(Long id){
        Optional<Consumption> consumption = consumptionRepo.findById(id);
        stockService.restockCMD(consumption.get().getConsumableMD().getId(), consumption.get().getQuantity());
        consumptionRepo.delete(consumption.get());

    }

    public ConsumptionDTO save(ConsumptionDTO consumptionDTO){
        Consumption consumption = consumptionMapper.toEntity(consumptionDTO);
        Consumption savedConsumption = consumptionRepo.save(consumption);
        return consumptionMapper.toDTO(savedConsumption);
    }
}
