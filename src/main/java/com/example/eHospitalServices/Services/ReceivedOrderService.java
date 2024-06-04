package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.DTOs.OrderDTO;
import com.example.eHospitalServices.DTOs.ReceivedOrderDTO;
import com.example.eHospitalServices.Mappers.ReceivedOrderMapper;
import com.example.eHospitalServices.Models.ReceivedOrder;
import com.example.eHospitalServices.Repositories.ReceivedOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class ReceivedOrderService {

    private final ReceivedOrderRepository receivedOrderRepository;
    private final ReceivedOrderMapper receivedOrderMapper;

    public ReceivedOrderService(ReceivedOrderRepository receivedOrderRepository, ReceivedOrderMapper receivedOrderMapper) {
        this.receivedOrderRepository = receivedOrderRepository;
        this.receivedOrderMapper = receivedOrderMapper;
    }

    public void create(OrderDTO orderDTO){
        ReceivedOrderDTO receivedOrderDTO = new ReceivedOrderDTO();
        receivedOrderDTO.setConsumableMDId(orderDTO.getConsumableMDId());
        receivedOrderDTO.setQuantity(orderDTO.getQuantity());
        receivedOrderDTO.setDate(orderDTO.getDate());
        save(receivedOrderDTO);
    }

    public void save(ReceivedOrderDTO receivedOrderDTO){
        ReceivedOrder receivedOrderToSave = receivedOrderMapper.toEntity(receivedOrderDTO);
        receivedOrderRepository.save(receivedOrderToSave);
    }

    public void delete(Long id){
        receivedOrderRepository.deleteById(id);
    }
}
