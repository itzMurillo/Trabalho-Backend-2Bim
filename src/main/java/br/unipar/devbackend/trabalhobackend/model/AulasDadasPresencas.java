package br.unipar.devbackend.trabalhobackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AulasDadasPresencas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //aula que pertence esta presença/falta
    @ManyToOne
    @JoinColumn(name = "aula_dada_id")
    private AulasDadas aulaDada;

    //aluno registrado na presença/falta
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    //true = faltou, false = presente
    private Boolean falta;
}
