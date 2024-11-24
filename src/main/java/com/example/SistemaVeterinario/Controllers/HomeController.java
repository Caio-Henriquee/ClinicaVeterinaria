package com.example.SistemaVeterinario.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("")
    public String home() {
        System.out.println("Carregando página index.html");
        return "index"; // Nome do arquivo HTML sem extensão
    }
}
