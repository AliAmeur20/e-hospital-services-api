package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.DTOs.CreateReplishementDTO;
import com.example.eHospitalServices.DTOs.DevicePackageDTO;
import com.example.eHospitalServices.Mappers.DevicePackageMapper;
import com.example.eHospitalServices.Models.DevicePackage;
import com.example.eHospitalServices.Models.Stock;
import com.example.eHospitalServices.Repositories.DevicePackageRepository;
import com.example.eHospitalServices.Repositories.StockRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DevicePackageService {

    private final DevicePackageRepository devicePackageRepository;
    private final DevicePackageMapper devicePackageMapper;
    private final StockRepo stockRepo;

    public DevicePackageService(DevicePackageRepository devicePackageRepository, DevicePackageMapper devicePackageMapper, StockRepo stockRepo) {
        this.devicePackageRepository = devicePackageRepository;
        this.devicePackageMapper = devicePackageMapper;
        this.stockRepo = stockRepo;
    }

    public DevicePackageDTO create (CreateReplishementDTO createReplishementDTO, Long replishementId){
        Stock stock = stockRepo.findByConsumableMD_Id(createReplishementDTO.getCmdId());
        DevicePackageDTO devicePackageDTO = new DevicePackageDTO();
        devicePackageDTO.setStockId(stock.getId());
        devicePackageDTO.setReplishementId(replishementId);
        devicePackageDTO.setExpDate(createReplishementDTO.getExpDate());
        devicePackageDTO.setQuantity(createReplishementDTO.getQuantity());
        devicePackageDTO.setLocation(createReplishementDTO.getLocation());
        devicePackageDTO.setLevel(createReplishementDTO.getLevel());
        return save(devicePackageDTO);
    }

    public void updateQuantity(Long replishementId, Double quantity){
        DevicePackage devicePackage = devicePackageRepository.findByReplishement_Id(replishementId);
        devicePackage.setQuantity(quantity);
        devicePackageRepository.save(devicePackage);
    }

    public DevicePackageDTO save (DevicePackageDTO devicePackageDTO){
        DevicePackage devicePackage = devicePackageMapper.toEntity(devicePackageDTO);
        DevicePackage savedPackage = devicePackageRepository.save(devicePackage);
        return devicePackageMapper.toDTO(savedPackage);
    }

    public void deleteByReplishement(Long replishementId){
        DevicePackage devicePackage = devicePackageRepository.findByReplishement_Id(replishementId);
        devicePackageRepository.deleteById(devicePackage.getId());
    }

    public List<DevicePackageDTO> getAllByStock(Long stockId){
        List<DevicePackage> devicePackages = devicePackageRepository.findByStock_Id(stockId);
        return devicePackages.stream().map(devicePackageMapper::toDTO).collect(Collectors.toList());
    }

    public void consume (Long stockId, Double quantityToConsume){
        List<DevicePackage> devicePackages = devicePackageRepository.findDevicesByStockIdOrderByExpiryDateAndQuantity(stockId);
        Double totalQuantity = devicePackages.stream().map(DevicePackage::getQuantity).reduce(0.0, Double::sum);
        if (totalQuantity < quantityToConsume){
            throw new RuntimeException();
        }else {
            for (DevicePackage devicePackage : devicePackages){
                if (devicePackage.getQuantity() >= quantityToConsume){
                    devicePackage.setQuantity(devicePackage.getQuantity() - quantityToConsume);
                    if (devicePackage.getQuantity() == 0){
                        devicePackageRepository.delete(devicePackage);
                    }else{
                        devicePackageRepository.save(devicePackage);
                    }
                    break;
                }else {
                    quantityToConsume = quantityToConsume - devicePackage.getQuantity();
                    devicePackageRepository.delete(devicePackage);
                }
            }
        }
    }
}
