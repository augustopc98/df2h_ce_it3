package com.example.demo.controllers;

import com.example.demo.entities.CustomerOrder;
import com.example.demo.entities.OrderItem;
import com.example.demo.services.ICustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

    @Autowired
    private ICustomerOrderService orderService;

    @PostMapping
    public CustomerOrder createOrder(@RequestBody CustomerOrder order) {
        return orderService.createOrder(order.getCustomerEmail(), order.getCustomerAddress(), order.getItems());
    }

    @GetMapping("/{id}")
    public CustomerOrder getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/{orderId}/items")
    public void addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem item) {
        orderService.addOrderItem(orderId, item);
    }

    @DeleteMapping("/{orderId}/items/{itemId}")
    public void removeOrderItem(@PathVariable Long orderId, @PathVariable Long itemId) {
        CustomerOrder order = orderService.getOrderById(orderId);
        OrderItem itemToRemove = order.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElse(null);
        if (itemToRemove != null) {
            orderService.removeOrderItem(orderId, itemToRemove);
        }
    }

    @PatchMapping("/{orderId}/status")
    public void updateDeliveryStatus(@PathVariable Long orderId, @RequestBody String status) {
        orderService.updateDeliveryStatus(orderId, status);
    }
}
