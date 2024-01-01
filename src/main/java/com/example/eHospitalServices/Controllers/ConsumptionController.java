package com.example.eHospitalServices.Controllers;

import com.example.eHospitalServices.DTOs.ConsumptionDTO;
import com.example.eHospitalServices.Mappers.ConsumptionMapper;
import com.example.eHospitalServices.Models.Consumption;
import com.example.eHospitalServices.Repositories.ConsumptionRepo;
import com.example.eHospitalServices.Services.ConsumptionService;
import com.example.eHospitalServices.Services.Utils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/consumption")
public class ConsumptionController {
    private final ConsumptionRepo consumptionRepo;
    private final ConsumptionService consumptionService;
    private final ConsumptionMapper consumptionMapper;

    public ConsumptionController(ConsumptionRepo consumptionRepo, ConsumptionService consumptionService, ConsumptionMapper consumptionMapper) {
        this.consumptionRepo = consumptionRepo;
        this.consumptionService = consumptionService;
        this.consumptionMapper = consumptionMapper;
    }

    @PostMapping
    public ResponseEntity<ConsumptionDTO> createConsumption(@RequestBody ConsumptionDTO consumptionDTO) throws URISyntaxException {
        try{
            ConsumptionDTO consumption = consumptionService.create(consumptionDTO);
            return ResponseEntity.created(new URI("/api/consumption/" + consumption.getId())).body(consumption);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumptionDTO> getConsumption(@PathVariable Long id){
        Optional<Consumption> consumption = consumptionRepo.findById(id);
        if (consumption.isPresent()){
            ConsumptionDTO consumptionDTO = consumptionMapper.toDTO(consumption.get());
            return ResponseEntity.ok().body(consumptionDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ConsumptionDTO>> getAllConsumptions(@RequestParam(required = false) String search) {
        Specification<Consumption> specs = null;
        if(search != null) specs = Utils.getLikeCMDSpec("name", search.trim().toLowerCase());
        List<ConsumptionDTO> consumptionDTOS = consumptionRepo.findAll(specs, Sort.by(Sort.Order.asc("id"))).stream().map(consumptionMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(consumptionDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsumption(@PathVariable Long id){
        consumptionService.delete(id);
        return ResponseEntity.ok().build();
    }

}
