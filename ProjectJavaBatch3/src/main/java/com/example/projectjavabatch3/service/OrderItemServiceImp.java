package com.example.projectjavabatch3.service;

import com.example.projectjavabatch3.model.Item;
import com.example.projectjavabatch3.model.OrderItem;
import com.example.projectjavabatch3.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImp implements OrderItemService{
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItem> getAllOrder() {
       return orderItemRepository.findAll();
    }

    @Override
    public OrderItem saveOrder(OrderItem orderItem) {
       return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrder(long id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public Optional<OrderItem> findOrderByID(long id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public OrderItem getOne(long id) {
        return orderItemRepository.findById(id).get();
    }

    @Override
    public Optional<OrderItem> findByItemId(Long id) {
        return orderItemRepository.findByItemId(id);
    }
}
