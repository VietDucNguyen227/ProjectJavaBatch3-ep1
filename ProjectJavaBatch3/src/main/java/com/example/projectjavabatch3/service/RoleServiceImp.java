package com.example.projectjavabatch3.service;

import com.example.projectjavabatch3.model.Roles;
import com.example.projectjavabatch3.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Roles> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
