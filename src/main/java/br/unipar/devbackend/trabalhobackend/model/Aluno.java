package br.unipar.devbackend.trabalhobackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //PK

    @Column(nullable = false, unique = true)
    private Long ra;  //ra único

    private String nome;         //nome completo
    private String dtNascimento; //data de nascimento
    private String curso;        //curso atual
}