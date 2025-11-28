package br.unipar.devbackend.trabalhobackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AulasDadas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //relação com a disciplina da aula ministrada
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    //data em que a aula foi dada
    private Date data;

    //campo opcional para anotações do professor
    private String observacoes;
}
