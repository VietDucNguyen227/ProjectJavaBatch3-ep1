package com.example.projectjavabatch3.model;

import org.apache.catalina.User;
import org.hibernate.criterion.Order;

import javax.persistence.*;
//import java.sql.Date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Orders implements java.io.Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "date_check_in")
    private Date dateCheckIn;
    @Column(name = "status")
    private int status;
    @Column(name = "username")
    private String username;


    @OneToOne
    @JoinColumn(name = "users_id")
    private Users users;
    @Transient
    private List<OrderItem> orderItem = new ArrayList<OrderItem>();

    public void setUsers(Users users) {
        this.users = users;
    }

    public Users getUsers() {
        return users;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(Date dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Orders orders = (Orders) o;
//        return id == orders.id && status == orders.status && Objects.equals(staffId, orders.staffId) && Objects.equals(orderId, orders.orderId) && Objects.equals(customerName, orders.customerName) && Objects.equals(customerPhone, orders.customerPhone) && Objects.equals(dateCheckIn, orders.dateCheckIn);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCheckIn, status, username);
    }
}
