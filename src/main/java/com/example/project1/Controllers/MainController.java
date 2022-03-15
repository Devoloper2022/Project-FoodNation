package com.example.project1.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestParam("/FoodNation")
public class MainController {

    @GetMapping("/")
    public String home(@ModelAttribute Model model){
        return "home";
    }


    @GetMapping("/greeting")
    public String greeting (@RequestParam(name="name",required = false,defaultValue = "World")
                                        String name ,Model model){
        model.addAttribute("name",name);
        return "hello";
    }
}
