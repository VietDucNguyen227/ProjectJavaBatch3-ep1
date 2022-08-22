package com.example.projectjavabatch3.service;

import com.example.projectjavabatch3.model.Item;
import com.example.projectjavabatch3.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    List<OrderItem> getAllOrder();

    OrderItem saveOrder(OrderItem orderItem);

    void deleteOrder(long id);

    Optional<OrderItem> findOrderByID(long id);

    OrderItem getOne(long id);

    Optional<OrderItem> findByItemId(Long id);
}
