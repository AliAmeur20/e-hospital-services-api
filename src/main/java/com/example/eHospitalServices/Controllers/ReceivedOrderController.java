package com.example.eHospitalServices.Controllers;

import com.example.eHospitalServices.DTOs.OrderDTO;
import com.example.eHospitalServices.DTOs.ReceivedOrderDTO;
import com.example.eHospitalServices.Mappers.ReceivedOrderMapper;
import com.example.eHospitalServices.Models.Order;
import com.example.eHospitalServices.Models.ReceivedOrder;
import com.example.eHospitalServices.Repositories.ReceivedOrderRepository;
import com.example.eHospitalServices.Services.ReceivedOrderService;
import com.example.eHospitalServices.Services.Utils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/received-order")
public class ReceivedOrderController {

    private final ReceivedOrderService receivedOrderService;
    private final ReceivedOrderRepository receivedOrderRepository;
    private final ReceivedOrderMapper receivedOrderMapper;

    public ReceivedOrderController(ReceivedOrderService receivedOrderService, ReceivedOrderRepository receivedOrderRepository, ReceivedOrderMapper receivedOrderMapper) {
        this.receivedOrderService = receivedOrderService;
        this.receivedOrderRepository = receivedOrderRepository;
        this.receivedOrderMapper = receivedOrderMapper;
    }

    @GetMapping
    public ResponseEntity<List<ReceivedOrderDTO>> getAllReceivedOrders(@RequestParam(required = false) String search){
        Specification<ReceivedOrder> specs = null;
        if(search != null) specs = Utils.getLikeCMDSpec("name", search.trim().toLowerCase());
        List<ReceivedOrderDTO> receivedOrderDTOS = receivedOrderRepository.findAll(specs, Sort.by(Sort.Order.asc("id"))).stream().map(receivedOrderMapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok().body(receivedOrderDTOS);
    }
}
