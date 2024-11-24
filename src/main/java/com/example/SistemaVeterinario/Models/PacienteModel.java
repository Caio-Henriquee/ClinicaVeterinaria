package com.example.SistemaVeterinario.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
public class PacienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private float peso;

    @Column(name = "tipo_animal", nullable = false)
    private String tipoAnimal;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private String raca;

    @ManyToOne
    @JoinColumn(name = "dono_id", nullable = false)
    private DonoModel dono;

   
}
