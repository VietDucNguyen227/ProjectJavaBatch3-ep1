package com.example.projectjavabatch3.service;

import com.example.projectjavabatch3.model.Item;
import com.example.projectjavabatch3.model.Users;

import java.util.Optional;

public interface UserService {

    Users getOne(String username);

    Boolean existsByUsername(String username);

    void saveUser(Users users);

    public Users login (String username, String password);

    Users findByUsername(String username);
}
