package com.example.projectjavabatch3.controller.view.admin;

import com.example.projectjavabatch3.model.Staff;
import com.example.projectjavabatch3.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class StaffController {
    @Autowired
    StaffService staffService;

    @GetMapping("/admin/listStaff")
    public String showStaffList(Model model){
        List<Staff> list = staffService.getAllStaff();
        model.addAttribute("list", list);
        return "listStaff";
    }

    @GetMapping("/admin/showNewStaffForm")
    public String showNewStaffForm(Model model){
        Staff staff = new Staff();
        model.addAttribute("staff", staff);
        return "new_staff";
    }

    @PostMapping("/admin/saveStaff")
    public String saveStaff(@Valid @ModelAttribute("staff") Staff staff,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "new_staff";
        } else {
            staffService.saveStaff(staff);
            return "redirect:/admin/listStaff";
        }
    }

    @GetMapping("/admin/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Optional<Staff> staff = staffService.findStaffByID(id);
        model.addAttribute("staff", staff);
        return "update_staff";
    }

    @GetMapping("/admin/deleteStaff/{id}")
    public String deleteStaff(@PathVariable (value = "id") long id) {
        this.staffService.deleteStaff(id);
        return "redirect:/admin/listStaff";
    }
}
