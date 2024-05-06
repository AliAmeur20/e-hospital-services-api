package com.example.eHospitalServices.Controllers;

import com.example.eHospitalServices.DTOs.OrderDTO;
import com.example.eHospitalServices.Mappers.OrderMapper;
import com.example.eHospitalServices.Models.Order;
import com.example.eHospitalServices.Repositories.OrderRepo;
import com.example.eHospitalServices.Services.OrderService;
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
@RequestMapping("api/order")
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderRepo orderRepo;
    private final OrderService orderService;


    public OrderController(OrderMapper orderMapper, OrderRepo orderRepo, OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderRepo = orderRepo;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws URISyntaxException {
        try{
            OrderDTO order = orderService.create(orderDTO);
            return ResponseEntity.created(new URI("/api/order/" + order.getId())).body(order);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id){
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()){
            OrderDTO orderDTO = orderMapper.toDTO(order.get());
            return ResponseEntity.ok().body(orderDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders(@RequestParam(required = false) String search) {
        Specification<Order> specs = null;
        if(search != null) specs = Utils.getLikeCMDSpec("name", search.trim().toLowerCase());
        List<OrderDTO> orderDTOS = orderRepo.findAll(specs, Sort.by(Sort.Order.asc("id"))).stream().map(orderMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok().body(orderDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }
}
