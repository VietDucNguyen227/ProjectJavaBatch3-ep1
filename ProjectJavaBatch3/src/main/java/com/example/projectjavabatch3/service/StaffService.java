package com.example.projectjavabatch3.service;

import com.example.projectjavabatch3.model.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<Staff> getAllStaff();

    Staff saveStaff(Staff staff);

    void deleteStaff(long id);

    Optional<Staff> findStaffByID(long id);

    Staff getOne(long id);
}
