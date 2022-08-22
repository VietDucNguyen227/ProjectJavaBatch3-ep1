package com.example.projectjavabatch3.dto;

import org.springframework.web.multipart.MultipartFile;

public class ItemDto {
    private long id;
    private String name;
    private MultipartFile image;
    private String detail;
    private long price;

    public ItemDto(long id, String name, MultipartFile image, String detail, long price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.detail = detail;
        this.price = price;
    }

    public ItemDto() {
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
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
}
