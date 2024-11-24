package com.example.SistemaVeterinario.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SistemaVeterinario.Models.ConsultaModel;

public interface ConsultaRepository extends JpaRepository<ConsultaModel,Long> {

}
