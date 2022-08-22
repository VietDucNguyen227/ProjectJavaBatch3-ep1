package com.example.projectjavabatch3.repository;

import com.example.projectjavabatch3.model.Users;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository <Users, Long> {

    Users findByUsername(String name);
    Boolean existsByUsername(String username);
}
