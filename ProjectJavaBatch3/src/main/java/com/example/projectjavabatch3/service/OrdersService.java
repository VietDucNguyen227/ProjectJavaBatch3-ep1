package com.example.projectjavabatch3.service;


import com.example.projectjavabatch3.dto.OrderInfo;
import com.example.projectjavabatch3.model.Orders;


import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrdersService {
    List<Orders> getAllOrders();

    void saveOrders(Orders bill);

    void deleteOrders(long id);

    Optional<Orders> findOrdersByID(long id);

    Orders getOne(long id);

//    List<OrderInfo> findAllOrdersInfo();

//    void saveOrderInfo(String staffName,
//                       Long orderId,
//                       String itemName,
//                       String type,
//                       Integer quantity,
//                       String customerName,
//                       String customerPhone,
//                       String address,
//                       Date dateCheckIn,
//                       Integer status);


}
