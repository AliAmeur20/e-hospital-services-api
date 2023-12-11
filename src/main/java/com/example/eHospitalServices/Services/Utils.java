package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.Models.ConsumableMD;
import com.example.eHospitalServices.Repositories.ConsumableMDRepo;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class Utils {
    private final ConsumableMDRepo consumableMDRepo;

    public Utils(ConsumableMDRepo consumableMDRepo) {
        this.consumableMDRepo = consumableMDRepo;
    }

    public ConsumableMD checkAndGetCMD (Long id){
        Optional<ConsumableMD> consumableMD = consumableMDRepo.findById(id);
        if (consumableMD.isPresent()){
            return consumableMD.get();
        }else{
            throw new NoSuchElementException();
        }
    }
}
