// src/main/java/com/example/mealbooking/controllers/HomeController.java
package com.example.mealbooking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // Aqui "index" é o nome do ficheiro index.html
    }
}
