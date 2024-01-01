package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.DTOs.ConsumableMDDTO;
import com.example.eHospitalServices.Mappers.ConsumableMDMapper;
import com.example.eHospitalServices.Models.ConsumableMD;
import com.example.eHospitalServices.Models.Consumption;
import com.example.eHospitalServices.Repositories.ConsumableMDRepo;
import com.example.eHospitalServices.Repositories.ConsumptionRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumableMDService {

    private final ConsumableMDRepo consumableMDRepo;
    private final StockService stockService;
    private final ConsumableMDMapper consumableMDMapper;

    private final ConsumptionRepo consumptionRepo;

    private static final Double CS = 2.33D;
    private static final Double DM = 7D;
    private static final Double FR = 10D;
    public ConsumableMDService(ConsumableMDRepo consumableMDRepo, StockService stockService, ConsumableMDMapper consumableMDMapper, ConsumptionRepo consumptionRepo){
        this.consumableMDRepo = consumableMDRepo;
        this.stockService = stockService;
        this.consumableMDMapper = consumableMDMapper;
        this.consumptionRepo = consumptionRepo;
    }

    public ConsumableMDDTO create(ConsumableMDDTO consumableMDDTO){
        ConsumableMDDTO savedCMD = save(consumableMDDTO);
        stockService.create(savedCMD.getId());
        return savedCMD;
    }

    public ConsumableMDDTO update(ConsumableMDDTO consumableMDDTO, Long id){
        Optional<ConsumableMD> updatedCMD = consumableMDRepo.findById(id).map(
                cmd -> {
                    if(consumableMDDTO.getName() != null) cmd.setName(consumableMDDTO.getName());
                    if(consumableMDDTO.getType() != null) cmd.setType(consumableMDDTO.getType());
                    if(consumableMDDTO.getSize() != 0) cmd.setSize(consumableMDDTO.getSize());
                    return consumableMDRepo.save(cmd);
                });
        return consumableMDMapper.toDTO(updatedCMD.get());
    }

    public void delete(Long id) {
        consumableMDRepo.deleteById(id);
    }

    public ConsumableMDDTO save(ConsumableMDDTO consumableMDDTO){
        ConsumableMD consumableMD = consumableMDMapper.toEntity(consumableMDDTO);
        ConsumableMD savedCMD = consumableMDRepo.save(consumableMD);
        return consumableMDMapper.toDTO(savedCMD);
    }

    public Double countSecurityStorage(Long deviceId){
        List<Consumption> consumptions = consumptionRepo.findByConsumableMD_Id(deviceId);
        Double EC = consumptions.stream().mapToDouble(Consumption::getQuantity).average().orElse(0.0);
        Double variance = consumptions.stream()
                .mapToDouble(consumption -> Math.pow(consumption.getQuantity() - EC, 2))
                .sum() / consumptions.size();
        Double standardDeviation = Math.sqrt(variance);
        return CS * standardDeviation * Math.sqrt(DM) ;
    }
}
