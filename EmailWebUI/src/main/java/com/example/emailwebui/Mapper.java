package com.example.emailwebui;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Mapper {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}