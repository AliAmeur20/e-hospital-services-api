package com.example.eHospitalServices.Controllers;

import com.example.eHospitalServices.DTOs.CreateReplishementDTO;
import com.example.eHospitalServices.DTOs.ReplishementDTO;
import com.example.eHospitalServices.Mappers.ReplishementMapper;
import com.example.eHospitalServices.Models.Replishement;
import com.example.eHospitalServices.Repositories.ReplishementRepo;
import com.example.eHospitalServices.Services.ReceivedOrderService;
import com.example.eHospitalServices.Services.ReplishementService;
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
@RequestMapping("api/replishement")
public class ReplishementController {
    private final ReplishementService replishementService;
    private final ReplishementRepo replishementRepo;
    private final ReplishementMapper replishementMapper;
    private final ReceivedOrderService receivedOrderService;

    public ReplishementController(ReplishementService replishementService, ReplishementRepo replishementRepo, ReplishementMapper replishementMapper, ReceivedOrderService receivedOrderService) {
        this.replishementService = replishementService;
        this.replishementRepo = replishementRepo;
        this.replishementMapper = replishementMapper;
        this.receivedOrderService = receivedOrderService;
    }

    @PostMapping
    public ResponseEntity<ReplishementDTO> createReplishement(@RequestBody CreateReplishementDTO createReplishementDTO){
        try{
            ReplishementDTO replishement = replishementService.create(createReplishementDTO);
            return ResponseEntity.created(new URI("/api/replishement/" + replishement.getId())).body(replishement);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReplishementDTO> updateReplishement(@RequestBody ReplishementDTO replishementDTO, @PathVariable Long id){
        ReplishementDTO replishemen = replishementService.update(replishementDTO, id);
        return ResponseEntity.ok().body(replishemen);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReplishementDTO> getReplishement(@PathVariable Long id){
        Optional<Replishement> replishement = replishementRepo.findById(id);
        if (replishement.isPresent()){
            ReplishementDTO replishementDTO = replishementMapper.toDTO(replishement.get());
            return ResponseEntity.ok().body(replishementDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<ReplishementDTO>> getAllReplishement(@RequestParam(required = false) String search) {
        Specification<Replishement> specs = null;
        if(search != null) specs = Utils.getLikeCMDSpec("name", search.trim().toLowerCase());
        List<ReplishementDTO> replishementDTOS = replishementRepo.findAll(specs, Sort.by(Sort.Order.asc("id"))).stream().map(replishementMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(replishementDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletReplishement(@PathVariable Long id){
        replishementService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> createReplishementFromReceivedOrder(@RequestBody CreateReplishementDTO createReplishementDTO, @PathVariable Long id){
            replishementService.create(createReplishementDTO);
            receivedOrderService.delete(id);
            return ResponseEntity.ok().build();
    }
}
