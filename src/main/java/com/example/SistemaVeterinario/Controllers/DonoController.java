package com.example.SistemaVeterinario.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.SistemaVeterinario.Models.DonoModel;
import com.example.SistemaVeterinario.Repositorys.DonoRepository;


import java.util.List;

@Controller
@RequestMapping("/donos")
public class DonoController {
    @Autowired
    private DonoRepository donoRepository;

    @GetMapping
    public String listarDonos(Model model) {
        model.addAttribute("donos", donoRepository.findAll());
        return "donos/lista";
    }

    @GetMapping("/novo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("dono", new DonoModel());
        return "donos/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute DonoModel dono) {
        donoRepository.save(dono);
        return "redirect:/donos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("dono", donoRepository.findById(id).orElseThrow());
        return "donos/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        donoRepository.deleteById(id);
        return "redirect:/donos";
    }
}