package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.DTOs.StockDTO;
import com.example.eHospitalServices.Mappers.StockMapper;
import com.example.eHospitalServices.Models.ConsumableMD;
import com.example.eHospitalServices.Models.Stock;
import com.example.eHospitalServices.Repositories.StockRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {
    private final StockRepo stockRepo;
    private final StockMapper stockMapper;

    private final Utils utils;

    public StockService(StockRepo stockRepo, StockMapper stockMapper, Utils utils) {
        this.stockRepo = stockRepo;
        this.stockMapper = stockMapper;
        this.utils = utils;
    }

    public StockDTO create(StockDTO stockDTO){
        utils.checkAndGetCMD(stockDTO.getConsumableMDId());
        return save(stockDTO);
    }

    public StockDTO update(StockDTO stockDTO, Long id){
        Optional<Stock> updatedStock = stockRepo.findById(id).map(
                stock -> {
                    stock.setName(stockDTO.getName());
                    stock.setLocation(stockDTO.getLocation());
                    stock.setQuantity(stockDTO.getQuantity());
                    stock.setLevel(stockDTO.getLevel());
                    return stockRepo.save(stock);
                });
        return stockMapper.toDTO(updatedStock.get());
    }

    public void delete(Long id){
        stockRepo.deleteById(id);
    }
    public StockDTO save(StockDTO stockDTO){
        Stock stock = stockMapper.toEntity(stockDTO);
        Stock savedStock = stockRepo.save(stock);
        return stockMapper.toDTO(savedStock);
    }

    public void restockCMD (Long id, Double quantity){
        Stock stock = stockRepo.findByConsumableMD_Id(id);
        stock.setQuantity(stock.getQuantity() + quantity);
        stockRepo.save(stock);
    }

    public void destockCMD (Long id, Double quantity){
        Stock stock = stockRepo.findByConsumableMD_Id(id);
        stock.setQuantity(stock.getQuantity() - quantity);
        stockRepo.save(stock);
    }
}
