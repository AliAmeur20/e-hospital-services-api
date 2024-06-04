package com.example.eHospitalServices.Services;

import com.example.eHospitalServices.DTOs.OrderDTO;
import com.example.eHospitalServices.Enums.OrderStatus;
import com.example.eHospitalServices.Mappers.OrderMapper;
import com.example.eHospitalServices.Models.Order;
import com.example.eHospitalServices.Repositories.OrderRepo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;
    private final Utils utils;

    public OrderService(OrderRepo orderRepo, OrderMapper orderMapper, Utils utils) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
        this.utils = utils;
    }

    public OrderDTO create(OrderDTO orderDTO){
        utils.checkAndGetCMD(orderDTO.getConsumableMDId());
        OrderDTO newOrderDTO = new OrderDTO();
        newOrderDTO.setConsumableMDId(orderDTO.getConsumableMDId());
        newOrderDTO.setDate(LocalDate.now());
        newOrderDTO.setDeliveryDate(orderDTO.getDeliveryDate());
        newOrderDTO.setQuantity(orderDTO.getQuantity());
        newOrderDTO.setCost(orderDTO.getCost());
        newOrderDTO.setStatus(OrderStatus.NEW);
        return save(newOrderDTO);
    }

    public void delete(Long id){
        orderRepo.findById(id).ifPresent(order -> orderRepo.delete(order));
    }

    private OrderDTO save(OrderDTO orderDTO){
        Order orderToSave = orderMapper.toEntity(orderDTO);
        Order savedOrder = orderRepo.save(orderToSave);
        return orderMapper.toDTO(savedOrder);
    }

    public OrderDTO findOne(Long id){
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order with id " + id + " not found"));
        return orderMapper.toDTO(order);
    }

    public List<OrderDTO> findAll(Specification<Order> specs){
        return orderRepo.findAll(specs).stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    public void setOrderDelivered(Long orderId){
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order not found"));
        order.setStatus(OrderStatus.DELIVERED);
        orderRepo.save(order);
    }
}
