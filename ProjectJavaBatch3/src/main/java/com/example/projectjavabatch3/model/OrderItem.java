package com.example.projectjavabatch3.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "OrderDetail")
public class OrderItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @NotNull
    @Column(name = "item_id")
    private Long itemId;
    @NotNull
    @Column(name = "order_id")
    private Long orderId;
    @NotNull
    @Column(name = "quantity")
    private Integer quantity;
    @NotNull
    @Column(name = "price")
    private int price;

    @Transient
    private Item item;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, itemId);
    }
}
