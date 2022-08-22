package com.example.projectjavabatch3.service;

import com.example.projectjavabatch3.model.Users;
import com.example.projectjavabatch3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public Users getOne(String username) {
        return null;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void saveUser(Users users) {
        userRepository.save(users);
    }

    @Override
    public Users login(String username, String password) {
        Users users = this.getOne(username);
        if (users != null){
            if (BCrypt.checkpw(password, users.getPassword())){
                return users;
            }
        }
        return null;
    }

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
