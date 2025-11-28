package br.unipar.devbackend.trabalhobackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AlunoDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //registro da relação

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno; //aluno vinculado

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina; //disciplina vinculada

    private Double nota1Bim; //nota do 1 bimestre
    private Double nota2Bim; //nota do 2 bimestre

    private Integer faltas1Bim; //total de faltas no 1 bim
    private Integer faltas2Bim; //total de faltas no 2 bim

    private Boolean matriculado; //se o aluno ainda está cursando
    private String situacao;     //APROVADO / REPROVADO / EM_CURSO
}
