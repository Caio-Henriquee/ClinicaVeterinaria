package com.example.SistemaVeterinario.Controllers;

import com.example.SistemaVeterinario.Models.PacienteModel;
import com.example.SistemaVeterinario.Repositorys.DonoRepository;
import com.example.SistemaVeterinario.Repositorys.PacienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;
    
    @Autowired
    private DonoRepository donoRepository;

    @GetMapping
    public String listarPacientes(Model model) {
        model.addAttribute("pacientes", pacienteRepository.findAll());
        return "pacientes/lista";
    }

    @GetMapping("/novo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("paciente", new PacienteModel());
        model.addAttribute("donos", donoRepository.findAll());
        return "pacientes/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute PacienteModel paciente) {
        pacienteRepository.save(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("paciente", pacienteRepository.findById(id).orElseThrow());
        model.addAttribute("donos", donoRepository.findAll());
        return "pacientes/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
        return "redirect:/pacientes";
    }
}