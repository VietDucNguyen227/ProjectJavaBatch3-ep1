package com.example.projectjavabatch3.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Staff {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @NotEmpty(message = "Name Can't be Null! Please enter your Name")
    @Column(name = "name")
    private String name;
    @NotNull(message = "Please enter your Age")
    @Column(name = "age")
    private int age;
    @NotEmpty
    @Column(name = "address")
    private String address;
    @NotEmpty
    @Email
    @Column(name = "email")
    private String email;
    @NotEmpty(message = "Please enter your Phone")
    @Column(name = "phone")
    private String phone;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id == staff.id && Objects.equals(name, staff.name) && Objects.equals(age, staff.age) && Objects.equals(address, staff.address) && Objects.equals(email, staff.email) && Objects.equals(phone, staff.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, address, email, phone);
    }
}
