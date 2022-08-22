package com.example.projectjavabatch3.service;


import com.example.projectjavabatch3.dto.OrderInfo;
import com.example.projectjavabatch3.model.Item;
import com.example.projectjavabatch3.model.OrderItem;
import com.example.projectjavabatch3.model.Orders;
import com.example.projectjavabatch3.model.Staff;
import com.example.projectjavabatch3.repository.ItemRepository;
import com.example.projectjavabatch3.repository.OrderItemRepository;
import com.example.projectjavabatch3.repository.OrdersRepository;
import com.example.projectjavabatch3.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImp implements OrdersService {
    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    StaffRepository staffRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public void saveOrders(Orders bill) {
        ordersRepository.save(bill);
    }

    @Override
    public void deleteOrders(long id) {
        ordersRepository.deleteById(id);
    }

    @Override
    public Optional<Orders> findOrdersByID(long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public Orders getOne(long id) {
        return ordersRepository.findById(id).get();
    }

//    @Override
//    public List<OrderInfo> findAllOrdersInfo() {
//        return ordersRepository.findAllOrdersInfo();
//    }

//    @Transactional
//    @Override
//    public void saveOrderInfo(String staffName, Long orderId, String itemName, String type, Integer quantity, String customerName, String customerPhone, String address, Date dateCheckIn, Integer status) {
//        OrderItem _oderItem = new OrderItem();
//        _oderItem.setQuantity(quantity);
//        _oderItem = orderItemRepository.save(_oderItem);
//
//        Item _item = new Item();
//        _item.setName(itemName);
//        _item.setType(type);
//        _item = itemRepository.save(_item);
//
//        Staff _staff = new Staff();
//        _staff.setName(staffName);
//        _staff = staffRepository.save(_staff);
//
//        Orders ord = new Orders();
//        ord.setAddress(address);
//        ord.setOrderId(orderId);
//        ord.setCustomerName(customerName);
//        ord.setCustomerPhone(customerPhone);
//        ord.setStatus(status);
//        ord.setDateCheckIn(dateCheckIn);
//
//        ordersRepository.save(ord);
//    }
}
