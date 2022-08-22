package com.example.projectjavabatch3.repository;

import com.example.projectjavabatch3.dto.OrderInfo;
import com.example.projectjavabatch3.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

//Unable to locate appropriate constructor on class [com.example.projectjavabatch3.dto.OrderInfo].
// Expected arguments are: long, long, long, java.lang.String, int, java.lang.String, java.lang.String,
// java.util.Date, int
//    @Query("Select new com.example.projectjavabatch3.dto.OrderInfo(" +
//            "s.id, n.name, s.orderId, i.name, i.type," +
//            " c.quantity, s.customerName, s.customerPhone, s.address," +
//            " s.dateCheckIn, s.status) " +
//            "FROM Orders s join OrderItem c on s.orderId = c.id " +
//                          "join Staff n on s.staffId = n.id "+
//                          "join Item i on c.itemId = i.id ")
//    List<OrderInfo> findAllOrdersInfo();
}
