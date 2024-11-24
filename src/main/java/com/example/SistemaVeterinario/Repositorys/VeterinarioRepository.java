package com.example.SistemaVeterinario.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SistemaVeterinario.Models.VeterinarioModel;

public interface VeterinarioRepository extends JpaRepository<VeterinarioModel,Long> {

}
