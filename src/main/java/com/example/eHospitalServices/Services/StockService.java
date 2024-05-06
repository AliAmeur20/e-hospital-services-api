package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.DTOs.StockDTO;
import com.example.eHospitalServices.Mappers.StockMapper;
import com.example.eHospitalServices.Models.Stock;
import com.example.eHospitalServices.Repositories.StockRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockService {
    private final StockRepo stockRepo;
    private final StockMapper stockMapper;
    private final DevicePackageService devicePackageService;

    private final Utils utils;

    public StockService(StockRepo stockRepo, StockMapper stockMapper, DevicePackageService devicePackageService, Utils utils) {
        this.stockRepo = stockRepo;
        this.stockMapper = stockMapper;
        this.devicePackageService = devicePackageService;
        this.utils = utils;
    }

    public void create(Long cmdId){
        StockDTO stockDTO = new StockDTO();
        stockDTO.setConsumableMDId(cmdId);
        stockDTO.setQuantity(0D);
        save(stockDTO);
    }

    public void delete(Long id){
        stockRepo.deleteById(id);
    }

    public void deleteByConsumableId(Long id) {
        stockRepo.deleteByConsumableMD_Id(id);
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

    public Double destockCMD (Long id, Double quantity){
        Stock stock = stockRepo.findByConsumableMD_Id(id);
        stock.setQuantity(stock.getQuantity() - quantity);
        stockRepo.save(stock);
        return stock.getQuantity();
    }

    public Double consume (Long id, Double quantity){
        devicePackageService.consume(id, quantity);
        return destockCMD(id, quantity);
    }
}
