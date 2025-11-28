package br.unipar.devbackend.trabalhobackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //ID do professor

    @Column(nullable = false, unique = true)
    private String matricula; //matrícula única

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf; //cpf único
}
