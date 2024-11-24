package com.example.SistemaVeterinario.Controllers;

import com.example.SistemaVeterinario.Models.ConsultaModel;
import com.example.SistemaVeterinario.Repositorys.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaRepository consultaRepository;
    
    @GetMapping
    public String listarConsultas(Model model) {
        model.addAttribute("consultas", consultaRepository.findAll());
        return "consultas/lista";
    }

    @GetMapping("/nova")
    public String mostrarFormulario(Model model) {
        model.addAttribute("consulta", new ConsultaModel());
        //model.addAttribute("pacientes", pacienteRepository.findAll());
        //model.addAttribute("veterinarios", veterinarioRepository.findAll());
        return "consultas/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute ConsultaModel consulta) {
        consultaRepository.save(consulta);
        return "redirect:/consultas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("consulta", consultaRepository.findById(id).orElseThrow());
       // model.addAttribute("pacientes", pacienteRepository.findAll());
        //model.addAttribute("veterinarios", veterinarioRepository.findAll());
        return "consultas/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id) {
        consultaRepository.deleteById(id);
        return "redirect:/consultas";
    }
}
