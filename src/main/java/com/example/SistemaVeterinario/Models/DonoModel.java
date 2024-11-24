package com.example.SistemaVeterinario.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "donos")
@Getter
@Setter
public class DonoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String telefone;

    //@OneToMany(mappedBy = "dono", cascade = CascadeType.ALL)
    //private List<PacienteModel> pacientes;
}
