package com.example.projectjavabatch3.controller.view;

import com.example.projectjavabatch3.model.Roles;
import com.example.projectjavabatch3.model.Users;
import com.example.projectjavabatch3.common.Constants;
import com.example.projectjavabatch3.payload.RegisterUser;
import com.example.projectjavabatch3.service.RoleService;
import com.example.projectjavabatch3.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    UserService userServices;

    @Autowired
    RoleService roleServices;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping(value = {"/", "index","/home"})
    public String showIndex(){
        return "index";
    }

    @GetMapping(value={"/403" })
    public String showPermisionDenied() {
        return "403";//tên view = tên file html
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model, HttpSession session) {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage(Model model) {
        return "register";
    }

    @RequestMapping(value = "/registerSubmit", method = RequestMethod.POST)
    public String registerSubmit(@ModelAttribute RegisterUser registerUser) {
        if(userServices.existsByUsername(registerUser.getUsername())) {
            return "403";
        } else {
            //Chua ton tai username
            Users u = new Users();
            u.setUsername(registerUser.getUsername());
            String password = encoder.encode(registerUser.getPassword());
            u.setPassword(password);

            Set<Roles> roles = new HashSet<Roles>();
            Roles r = roleServices.findByName(Constants.ROLE_USER).get();
            roles.add(r);

            u.setRoles(roles);

            u.setEmail(registerUser.getEmail());
            u.setAddress(registerUser.getAddress());

            userServices.saveUser(u);
        }
        return "login";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "logoutSuccessful";
    }
}
