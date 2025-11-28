package br.unipar.devbackend.trabalhobackend.repository;

import br.unipar.devbackend.trabalhobackend.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    //busca disciplinas onde o nome tem parte do texto digitado
    //usado na tela de busca de disciplinas por nome
    List<Disciplina> findByNomeContaining(String nome);
}