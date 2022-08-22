package com.example.projectjavabatch3.dto;

import java.util.Date;


public class OrderInfo {

    private long id;

    private String staffName;

    private long orderId;

    private String itemName;

    private String type;

    private Integer quantity;

    private String customerName;

    private String customerPhone;

    private String address;

    private Date dateCheckIn;

    private int status;

    public OrderInfo(long id, String staffName, long orderId, String itemName, String type, Integer quantity,
                     String customerName, String customerPhone, String address, Date dateCheckIn, int status) {
        this.id = id;
        this.staffName = staffName;
        this.orderId = orderId;
        this.itemName = itemName;
        this.type = type;
        this.quantity = quantity;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.address = address;
        this.dateCheckIn = dateCheckIn;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
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
}
