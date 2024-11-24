package com.example.SistemaVeterinario.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SistemaVeterinario.Models.VeterinarioModel;
import com.example.SistemaVeterinario.Repositorys.VeterinarioRepository;

import java.util.List;

@RestController
@RequestMapping("/veterinarios")
public class VeterinarioController {

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @GetMapping
    public List<VeterinarioModel> listarTodos() {
        return veterinarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<VeterinarioModel> cadastrar(@RequestBody VeterinarioModel veterinario) {
        VeterinarioModel novoVeterinario = veterinarioRepository.save(veterinario);
        return ResponseEntity.ok(novoVeterinario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeterinarioModel> editar(@PathVariable Long id, @RequestBody VeterinarioModel veterinarioAtualizado) {
        return veterinarioRepository.findById(id)
                .map(veterinario -> {
                    veterinario.setNome(veterinarioAtualizado.getNome());
                    veterinario.setCpf(veterinarioAtualizado.getCpf());
                    veterinario.setTelefone(veterinarioAtualizado.getTelefone());
                    VeterinarioModel veterinarioSalvo = veterinarioRepository.save(veterinario);
                    return ResponseEntity.ok(veterinarioSalvo);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (veterinarioRepository.existsById(id)) {
            veterinarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
