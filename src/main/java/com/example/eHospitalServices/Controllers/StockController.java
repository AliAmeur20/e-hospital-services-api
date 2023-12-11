package com.example.eHospitalServices.Controllers;

import com.example.eHospitalServices.DTOs.StockDTO;
import com.example.eHospitalServices.Mappers.StockMapper;
import com.example.eHospitalServices.Models.Stock;
import com.example.eHospitalServices.Repositories.StockRepo;
import com.example.eHospitalServices.Services.StockService;
import com.example.eHospitalServices.Services.Utils;
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
@RequestMapping("api/stock")
public class StockController {
    private final StockService stockService;
    private final StockRepo stockRepo;
    private final StockMapper stockMapper;

    public StockController(StockService stockService, StockRepo stockRepo, StockMapper stockMapper) {
        this.stockService = stockService;
        this.stockRepo = stockRepo;
        this.stockMapper = stockMapper;
    }

    @PostMapping
    public ResponseEntity<StockDTO> createStock(@RequestBody StockDTO stockDTO) throws URISyntaxException {
        try {
            StockDTO stock = stockService.create(stockDTO);
            return ResponseEntity.created(new URI("/api/stock/" + stock.getId())).body(stock);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDTO> updateStock(@RequestBody StockDTO stockDTO, @PathVariable Long id){
        StockDTO stock = stockService.update(stockDTO, id);
        return ResponseEntity.ok().body(stock);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDTO> getStock(@PathVariable Long id) {
        Optional<Stock> stock = stockRepo.findById(id);
        if (stock.isPresent()){
            StockDTO stockDTO = stockMapper.toDTO(stock.get());
            return ResponseEntity.ok().body(stockDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<StockDTO>> getAllStocks() {
        List<StockDTO> stockDTOS = stockRepo.findAll().stream().map(stockMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(stockDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        stockRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
