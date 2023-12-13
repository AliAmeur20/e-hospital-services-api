package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.DTOs.ConsumableMDDTO;
import com.example.eHospitalServices.Mappers.ConsumableMDMapper;
import com.example.eHospitalServices.Models.ConsumableMD;
import com.example.eHospitalServices.Repositories.ConsumableMDRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumableMDService {

    private final ConsumableMDRepo consumableMDRepo;
    private final ConsumableMDMapper consumableMDMapper;

    public ConsumableMDService(ConsumableMDRepo consumableMDRepo, ConsumableMDMapper consumableMDMapper){
        this.consumableMDRepo = consumableMDRepo;
        this.consumableMDMapper = consumableMDMapper;
    }

    public ConsumableMDDTO create(ConsumableMDDTO consumableMDDTO){
        return save(consumableMDDTO);
    }

    public ConsumableMDDTO update(ConsumableMDDTO consumableMDDTO, Long id){
        Optional<ConsumableMD> updatedCMD = consumableMDRepo.findById(id).map(
                cmd -> {
                    if(consumableMDDTO.getName() != null) cmd.setName(consumableMDDTO.getName());
                    if(consumableMDDTO.getType() != null) cmd.setType(consumableMDDTO.getType());
                    if(consumableMDDTO.getSize() != 0) cmd.setSize(consumableMDDTO.getSize());
                    if(consumableMDDTO.getExpDate() != null) cmd.setExpDate(consumableMDDTO.getExpDate());
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
}
