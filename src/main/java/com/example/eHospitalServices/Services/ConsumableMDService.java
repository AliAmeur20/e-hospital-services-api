package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.DTOs.ConsumableMDDTO;
import com.example.eHospitalServices.Enums.OrderType;
import com.example.eHospitalServices.Mappers.ConsumableMDMapper;
import com.example.eHospitalServices.Models.ConsumableMD;
import com.example.eHospitalServices.Models.Consumption;
import com.example.eHospitalServices.Models.SecurityStorage;
import com.example.eHospitalServices.Repositories.ConsumableMDRepo;
import com.example.eHospitalServices.Repositories.ConsumptionRepo;
import com.example.eHospitalServices.Repositories.SecurityStorageRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ConsumableMDService {

    private final ConsumableMDRepo consumableMDRepo;
    private final StockService stockService;
    private final ConsumableMDMapper consumableMDMapper;
    private final ConsumptionRepo consumptionRepo;
    private final SecurityStorageRepo securityStorageRepo;

    private static final Double CS = 2.33D;
    private static final Double DM = 7D;
    private static final Double FR = 10D;
    public ConsumableMDService(ConsumableMDRepo consumableMDRepo, StockService stockService, ConsumableMDMapper consumableMDMapper, ConsumptionRepo consumptionRepo, SecurityStorageRepo securityStorageRepo){
        this.consumableMDRepo = consumableMDRepo;
        this.stockService = stockService;
        this.consumableMDMapper = consumableMDMapper;
        this.consumptionRepo = consumptionRepo;
        this.securityStorageRepo = securityStorageRepo;
    }

    public ConsumableMDDTO create(ConsumableMDDTO consumableMDDTO){
        ConsumableMDDTO consumableMD = new ConsumableMDDTO();
        consumableMD.setDate(LocalDate.now());
        consumableMD.setName(consumableMDDTO.getName());
        consumableMD.setType(consumableMDDTO.getType());
        consumableMD.setNumber(consumableMDRepo.getMaxDeviceNumber() + 1);
        consumableMD.setReference("cmd-" + consumableMD.getNumber().toString());
        consumableMD.setOrderType(consumableMDDTO.getOrderType());
        consumableMD.setSize(consumableMDDTO.getSize());
        ConsumableMDDTO savedCMD = save(consumableMD);
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
        stockService.deleteByConsumableId(id);
        consumableMDRepo.deleteById(id);
    }

    public ConsumableMDDTO save(ConsumableMDDTO consumableMDDTO){
        ConsumableMD consumableMD = consumableMDMapper.toEntity(consumableMDDTO);
        ConsumableMD savedCMD = consumableMDRepo.save(consumableMD);
        return consumableMDMapper.toDTO(savedCMD);
    }

    public ConsumableMDDTO findOne (Long id) {
        ConsumableMD consumableMD = consumableMDRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ConsumableMD with id " + id + " not found"));
        return consumableMDMapper.toDTO(consumableMD);
    }

    public long countSecurityStorage(Long deviceId){
        ConsumableMDDTO consumableMDDTO = findOne(deviceId);
        List<Consumption> consumptions = consumptionRepo.findByConsumableMD_Id(deviceId);
        Double EC = consumptions.stream().mapToDouble(Consumption::getQuantity).average().orElse(0.0);
        Double variance = consumptions.stream()
                .mapToDouble(consumption -> Math.pow(consumption.getQuantity() - EC, 2))
                .sum() / consumptions.size();
        Double standardDeviation = Math.sqrt(variance);
        if (consumableMDDTO.getOrderType() == OrderType.ORDER_POINT){
            return Math.round(CS * standardDeviation * Math.sqrt(DM)) ;
        }else {
            return Math.round(CS * standardDeviation * Math.sqrt(DM + FR)) ;
        }
    }

    public void saveSecurityStorage(Long securityStorageValue, Long deviceId) {
        SecurityStorage existingSecurityStorage = securityStorageRepo.findByConsumableMD_Id(deviceId);
        if (existingSecurityStorage != null) {
            existingSecurityStorage.setSecurityStorage(securityStorageValue);
        } else {
            consumableMDRepo.findById(deviceId).ifPresent(device -> {
                SecurityStorage newSecurityStorage = new SecurityStorage();
                newSecurityStorage.setConsumableMD(device);
                newSecurityStorage.setSecurityStorage(securityStorageValue);
                securityStorageRepo.save(newSecurityStorage);
            });
            return;
        }
        securityStorageRepo.save(existingSecurityStorage);
    }

    public Boolean checkQuantity(Long deviceId, Double quantity){
        SecurityStorage existingSecurityStorage = securityStorageRepo.findByConsumableMD_Id(deviceId);
        if (existingSecurityStorage == null || existingSecurityStorage.getSecurityStorage() == 0) {
            return false;
        }
        return (quantity <= existingSecurityStorage.getSecurityStorage());
    }
}
