package com.example.SistemaVeterinario.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.SistemaVeterinario.Models.VeterinarioModel;
import com.example.SistemaVeterinario.Repositorys.VeterinarioRepository;


@Controller
@RequestMapping("/veterinarios")
public class VeterinarioController {
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @GetMapping
    public String listarVeterinarios(Model model) {
        model.addAttribute("veterinarios", veterinarioRepository.findAll());
        return "veterinarios/lista";
    }

    @GetMapping("/novo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("veterinario", new VeterinarioModel());
        return "veterinarios/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute VeterinarioModel veterinario) {
        veterinarioRepository.save(veterinario);
        return "redirect:/veterinarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("veterinario", veterinarioRepository.findById(id).orElseThrow());
        return "veterinarios/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        veterinarioRepository.deleteById(id);
        return "redirect:/veterinarios";
    }
}