package com.example.eHospitalServices.Controllers;

import com.example.eHospitalServices.DTOs.ConsumableMDDTO;
import com.example.eHospitalServices.Mappers.ConsumableMDMapper;
import com.example.eHospitalServices.Models.ConsumableMD;
import com.example.eHospitalServices.Repositories.ConsumableMDRepo;
import com.example.eHospitalServices.Services.ConsumableMDService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/consumable-md")
public class ConsumableMDController {

    private final ConsumableMDService consumableMDService;
    private final ConsumableMDRepo consumableMDRepo;
    private final ConsumableMDMapper consumableMDMapper;

    public ConsumableMDController(ConsumableMDService consumableMDService, ConsumableMDRepo consumableMDRepo, ConsumableMDMapper consumableMDMapper) {
        this.consumableMDService = consumableMDService;
        this.consumableMDRepo = consumableMDRepo;
        this.consumableMDMapper = consumableMDMapper;
    }

    @PostMapping
    public ResponseEntity<ConsumableMDDTO> createCMDevice (@RequestBody ConsumableMDDTO consumableMDDTO) throws URISyntaxException {
        ConsumableMDDTO consumableMD = consumableMDService.create(consumableMDDTO);
        return ResponseEntity.created(new URI("/api/consumable-md/" + consumableMD.getId())).body(consumableMD);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumableMDDTO> updateCMDevice (@RequestBody ConsumableMDDTO consumableMDDTO, @PathVariable Long id) {
        ConsumableMDDTO consumableMD = consumableMDService.update(consumableMDDTO, id);
        return ResponseEntity.ok().body(consumableMD);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumableMDDTO> getCMDevice (@PathVariable Long id) {
        Optional<ConsumableMD> consumableMD = consumableMDRepo.findById(id);
        if (consumableMD.isPresent()) {
            ConsumableMDDTO consumableMDDTO = consumableMDMapper.toDTO(consumableMD.get());
            return ResponseEntity.ok().body(consumableMDDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ConsumableMDDTO>> getAllCMDevices () {
        List<ConsumableMDDTO> consumableMDDTOS = consumableMDRepo.findAll(Sort.by(Sort.Order.asc("id"))).stream().map(consumableMDMapper::toDTO).collect(Collectors.toList());
            return ResponseEntity.ok().body(consumableMDDTOS);
    }

    @GetMapping("/stock")
    public ResponseEntity<List<ConsumableMDDTO>> getAStockCMDevices () {
        List<ConsumableMDDTO> consumableMDDTOS = consumableMDRepo.findConsumableMDsNotAttachedToStock().stream().map(consumableMDMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(consumableMDDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCMDevice (@PathVariable Long id) {
        consumableMDService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
