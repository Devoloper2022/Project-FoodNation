package com.example.project1.Controllers;


import com.example.project1.Domain.Staff;
import com.example.project1.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;

    @GetMapping("/register")
    public String addStaff(@ModelAttribute("staff")Staff staff){

        return "registrationStaff";
    }
}
