package com.example.projectjavabatch3.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "detail")
    private String detail;
    @Basic
    @Column(name = "price")
    private long price;

    public Item(long id, String name, String image, String detail, long price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.detail = detail;
        this.price = price;
    }

    public Item() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && price == item.price && Objects.equals(name, item.name) && Objects.equals(image, item.image) && Objects.equals(detail, item.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, detail, price);
    }

}
