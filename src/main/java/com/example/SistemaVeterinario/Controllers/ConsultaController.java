package com.example.SistemaVeterinario.Controllers;

import com.example.SistemaVeterinario.Models.ConsultaModel;
import com.example.SistemaVeterinario.Repositorys.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    // Listar todas as consultas
    @GetMapping
    public List<ConsultaModel> listarTodas() {
        return consultaRepository.findAll();
    }

    // Cadastrar uma nova consulta
    @PostMapping
    public ResponseEntity<ConsultaModel> cadastrarConsulta(@RequestBody ConsultaModel consulta) {
        ConsultaModel novaConsulta = consultaRepository.save(consulta);
        return ResponseEntity.ok(novaConsulta);
    }

    // Editar uma consulta existente
    @PutMapping("/{id}")
    public ResponseEntity<ConsultaModel> editarConsulta(@PathVariable Long id, @RequestBody ConsultaModel consultaDetalhes) {
        Optional<ConsultaModel> consultaExistente = consultaRepository.findById(id);

        if (consultaExistente.isPresent()) {
            ConsultaModel consulta = consultaExistente.get();
            consulta.setDataConsulta(consultaDetalhes.getDataConsulta());
            consulta.setHoraConsulta(consultaDetalhes.getHoraConsulta());
            consulta.setPaciente(consultaDetalhes.getPaciente());
            consulta.setVeterinario(consultaDetalhes.getVeterinario());
            ConsultaModel consultaAtualizada = consultaRepository.save(consulta);
            return ResponseEntity.ok(consultaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar uma consulta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConsulta(@PathVariable Long id) {
        Optional<ConsultaModel> consultaExistente = consultaRepository.findById(id);

        if (consultaExistente.isPresent()) {
            consultaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // Deleta e retorna status 204
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
