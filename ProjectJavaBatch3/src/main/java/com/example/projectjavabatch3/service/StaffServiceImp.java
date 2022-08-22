package com.example.projectjavabatch3.service;

import com.example.projectjavabatch3.model.Staff;
import com.example.projectjavabatch3.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImp implements StaffService {
    @Autowired
    StaffRepository staffRepository;

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Staff saveStaff(Staff staff) {
       return staffRepository.save(staff);
    }

    @Override
    public void deleteStaff(long id) {
        staffRepository.deleteById(id);
    }

    @Override
    public Optional<Staff> findStaffByID(long id) {
        return staffRepository.findById(id);
    }

    @Override
    public Staff getOne(long id) {
        return staffRepository.findById(id).get();
    }
}
