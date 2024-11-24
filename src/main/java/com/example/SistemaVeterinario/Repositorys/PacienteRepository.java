package com.example.SistemaVeterinario.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SistemaVeterinario.Models.PacienteModel;

public interface PacienteRepository extends JpaRepository<PacienteModel,Long> {

}
