package com.example.projectjavabatch3.service;

import com.example.projectjavabatch3.model.Roles;

import java.util.Optional;

public interface RoleService {
    Optional<Roles> findByName(String name);
}
