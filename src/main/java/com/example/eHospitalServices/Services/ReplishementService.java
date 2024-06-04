package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.DTOs.CreateReplishementDTO;
import com.example.eHospitalServices.DTOs.ReplishementDTO;
import com.example.eHospitalServices.Mappers.ReplishementMapper;
import com.example.eHospitalServices.Models.Replishement;
import com.example.eHospitalServices.Repositories.ReplishementRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReplishementService {
    private final ReplishementRepo replishementRepo;
    private final ReplishementMapper replishementMapper;
    private final Utils utils;
    private final StockService stockService;

    private final DevicePackageService devicePackageService;

    public ReplishementService(ReplishementRepo replishementRepo, ReplishementMapper replishementMapper, Utils utils, StockService stockService, DevicePackageService devicePackageService) {
        this.replishementRepo = replishementRepo;
        this.replishementMapper = replishementMapper;
        this.utils = utils;
        this.stockService = stockService;
        this.devicePackageService = devicePackageService;
    }

    public ReplishementDTO create(CreateReplishementDTO createReplishementDTO){
        utils.checkAndGetCMD(createReplishementDTO.getCmdId());
        ReplishementDTO newReplishement = new ReplishementDTO();
        newReplishement.setDate(LocalDate.now());
        newReplishement.setConsumableMDId(createReplishementDTO.getCmdId());
        newReplishement.setQuantity(createReplishementDTO.getQuantity());
        ReplishementDTO savedReplishement = save(newReplishement);
        devicePackageService.create(createReplishementDTO, savedReplishement.getId());
        stockService.restockCMD(savedReplishement.getConsumableMDId(), savedReplishement.getQuantity());
        return savedReplishement;
    }

    public ReplishementDTO update(ReplishementDTO replishementDTO, Long id){
        Optional<Replishement> replishement = replishementRepo.findById(id);
        stockService.destockCMD(replishement.get().getConsumableMD().getId(), replishement.get().getQuantity());
        replishement = replishement.map(
                repl -> {
                    if(replishementDTO.getQuantity() != 0) repl.setQuantity(replishementDTO.getQuantity());
                    return replishementRepo.save(repl);
                });
        devicePackageService.updateQuantity(replishement.get().getId(), replishement.get().getQuantity());
        stockService.restockCMD(replishement.get().getConsumableMD().getId(), replishement.get().getQuantity());
        return replishementMapper.toDTO(replishement.get());
    }

    public void delete(Long id){
        Optional<Replishement> replishement = replishementRepo.findById(id);
        stockService.destockCMD(replishement.get().getConsumableMD().getId() , replishement.get().getQuantity());
        devicePackageService.deleteByReplishement(replishement.get().getId());
        replishementRepo.delete(replishement.get());
    }

    public ReplishementDTO save(ReplishementDTO replishementDTO){
        Replishement replishement = replishementMapper.toEntity(replishementDTO);
        Replishement savedReplishement = replishementRepo.save(replishement);
        return replishementMapper.toDTO(savedReplishement);
    }
}
