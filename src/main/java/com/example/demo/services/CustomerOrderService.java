package com.example.demo.services;

import com.example.demo.entities.CustomerOrder;
import com.example.demo.entities.OrderItem;
import com.example.demo.repositories.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService implements ICustomerOrderService {

    @Autowired
    private CustomerOrderRepository orderRepository;

    @Override
    public CustomerOrder createOrder(String customerEmail, String customerAddress, List<OrderItem> items) {
        CustomerOrder order = new CustomerOrder(null, customerEmail, customerAddress, new Date(), items);
        return orderRepository.save(order);
    }

    @Override
    public CustomerOrder getOrderById(Long id) {
        Optional<CustomerOrder> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public void addOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = getOrderById(orderId);
        order.addOrderItem(item);
        orderRepository.save(order);
    }

    @Override
    public void removeOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = getOrderById(orderId);
        order.removeOrderItem(item);
        orderRepository.save(order);
    }

    @Override
    public void updateDeliveryStatus(Long orderId, String status) {
        CustomerOrder order = getOrderById(orderId);
        order.updateDeliveryStatus(status);
        orderRepository.save(order);
    }
}
