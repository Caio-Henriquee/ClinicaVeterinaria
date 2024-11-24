package com.example.SistemaVeterinario.Controllers;

import com.example.SistemaVeterinario.Models.PacienteModel;
import com.example.SistemaVeterinario.Repositorys.PacienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;
    

    @GetMapping
    public List<PacienteModel> listarTodos() {
        return pacienteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<PacienteModel> cadastrar(@RequestBody PacienteModel paciente) {
        PacienteModel novoPaciente = pacienteRepository.save(paciente);
        return ResponseEntity.ok(novoPaciente);
    }

    @PutMapping("/{id}")
public ResponseEntity<PacienteModel> editar(@PathVariable Long id, @RequestBody PacienteModel pacienteAtualizado) {
    return pacienteRepository.findById(id)
            .map(paciente -> {
                paciente.setNome(pacienteAtualizado.getNome());
                paciente.setPeso(pacienteAtualizado.getPeso());
                paciente.setTipoAnimal(pacienteAtualizado.getTipoAnimal());
                paciente.setSexo(pacienteAtualizado.getSexo());
                paciente.setRaca(pacienteAtualizado.getRaca());
                PacienteModel pacienteSalvo = pacienteRepository.save(paciente);
                return ResponseEntity.ok(pacienteSalvo);
            }).orElse(ResponseEntity.notFound().build());
}



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
