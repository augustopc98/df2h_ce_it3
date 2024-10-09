package com.example.demo.services;

import com.example.demo.entities.CustomerOrder;
import com.example.demo.entities.OrderItem;

import java.util.List;

public interface ICustomerOrderService {
    CustomerOrder createOrder(String customerEmail, String customerAddress, List<OrderItem> items);
    CustomerOrder getOrderById(Long id);
    void addOrderItem(Long orderId, OrderItem item);
    void removeOrderItem(Long orderId, OrderItem item);
    void updateDeliveryStatus(Long orderId, String status);
}
