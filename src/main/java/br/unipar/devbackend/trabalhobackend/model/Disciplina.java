package br.unipar.devbackend.trabalhobackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;     //código único da disciplina
    private String nome;       //nome da matéria
    private String descricao;  //descrição curta
    private String ementa;     //conteúdo programático

    @ManyToOne
    private Professor professor; //professor responsável
}