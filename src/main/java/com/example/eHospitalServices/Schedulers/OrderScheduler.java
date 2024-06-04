package com.example.eHospitalServices.Schedulers;

import com.example.eHospitalServices.DTOs.OrderDTO;
import com.example.eHospitalServices.Enums.OrderStatus;
import com.example.eHospitalServices.Models.Order;
import com.example.eHospitalServices.Services.OrderService;
import com.example.eHospitalServices.Services.ReceivedOrderService;
import com.example.eHospitalServices.Services.Utils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Component
public class OrderScheduler {

    private final OrderService orderService;
    private final ReceivedOrderService receivedOrderService;

    public OrderScheduler(OrderService orderService, ReceivedOrderService receivedOrderService) {
        this.orderService = orderService;
        this.receivedOrderService = receivedOrderService;
    }


//    @Scheduled(cron = "0 0 0 * * ?")  // This cron expression schedules the task to run at midnight every day
    @Scheduled(fixedDelay = 120000) // this will trigger every 2 min (just for test) ---- to replace later
    @Transactional
    public void updateDeviceQuantities() {
        List<OrderDTO> orders = orderService.findAll(Utils.<Order>getEqualSpec("date", LocalDate.now()).and(Utils.getEqualSpec("status", OrderStatus.NEW)));
        orders.forEach(order -> {
            receivedOrderService.create(order);
            orderService.setOrderDelivered(order.getId());
        });
    }
}
