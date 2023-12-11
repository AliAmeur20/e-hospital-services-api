package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.DTOs.ReplishementDTO;
import com.example.eHospitalServices.Mappers.ReplishementMapper;
import com.example.eHospitalServices.Models.Replishement;
import com.example.eHospitalServices.Repositories.ReplishementRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReplishementService {
    private final ReplishementRepo replishementRepo;
    private final ReplishementMapper replishementMapper;
    private final Utils utils;
    private final StockService stockService;

    public ReplishementService(ReplishementRepo replishementRepo, ReplishementMapper replishementMapper, Utils utils, StockService stockService) {
        this.replishementRepo = replishementRepo;
        this.replishementMapper = replishementMapper;
        this.utils = utils;
        this.stockService = stockService;
    }

    public ReplishementDTO create(ReplishementDTO replishementDTO){
        utils.checkAndGetCMD(replishementDTO.getConsumableMDId());
        stockService.restockCMD(replishementDTO.getConsumableMDId(), replishementDTO.getQuantity());
        return save(replishementDTO);
    }

    public ReplishementDTO update(ReplishementDTO replishementDTO, Long id){
        Optional<Replishement> replishement = replishementRepo.findById(id);
        stockService.destockCMD(replishement.get().getConsumableMD().getId(), replishement.get().getQuantity());
        replishement = replishement.map(
                repl -> {
                    repl.setSupplier(replishementDTO.getSupplier());
                    repl.setDate(replishementDTO.getDate());
                    repl.setQuantity(replishementDTO.getQuantity());
                    return replishementRepo.save(repl);
                });
        stockService.restockCMD(replishement.get().getConsumableMD().getId(), replishement.get().getQuantity());
        return replishementMapper.toDTO(replishement.get());
    }

    public void delete(Long id){
        Optional<Replishement> replishement = replishementRepo.findById(id);
        stockService.destockCMD(replishement.get().getConsumableMD().getId() , replishement.get().getQuantity());
        replishementRepo.delete(replishement.get());
    }

    public ReplishementDTO save(ReplishementDTO replishementDTO){
        Replishement replishement = replishementMapper.toEntity(replishementDTO);
        Replishement savedReplishement = replishementRepo.save(replishement);
        return replishementMapper.toDTO(savedReplishement);
    }
}
