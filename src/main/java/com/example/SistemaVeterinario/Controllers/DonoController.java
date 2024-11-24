package com.example.SistemaVeterinario.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.SistemaVeterinario.Models.DonoModel;
import com.example.SistemaVeterinario.Repositorys.DonoRepository;

import java.util.List;

@RestController
@RequestMapping("/donos")
public class DonoController {

    @Autowired
    private DonoRepository donoRepository;

    @GetMapping
    public List<DonoModel> listarTodos() {
        return donoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<DonoModel> cadastrar(@RequestBody DonoModel dono) {
        DonoModel novoDono = donoRepository.save(dono);
        return ResponseEntity.ok(novoDono);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonoModel> editar(@PathVariable Long id, @RequestBody DonoModel donoAtualizado) {
        return donoRepository.findById(id)
                .map(dono -> {
                    dono.setNome(donoAtualizado.getNome());
                    dono.setCpf(donoAtualizado.getCpf());
                    dono.setTelefone(donoAtualizado.getTelefone());
                    DonoModel donoSalvo = donoRepository.save(dono);
                    return ResponseEntity.ok(donoSalvo);
                }).orElse(ResponseEntity.notFound().build());
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (donoRepository.existsById(id)) {
            donoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}