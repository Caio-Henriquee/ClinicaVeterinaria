package com.example.SistemaVeterinario.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SistemaVeterinario.Models.DonoModel;


public interface DonoRepository extends JpaRepository<DonoModel,Long> {

}