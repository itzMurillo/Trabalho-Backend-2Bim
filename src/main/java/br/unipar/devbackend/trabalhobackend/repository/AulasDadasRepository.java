package br.unipar.devbackend.trabalhobackend.repository;

import br.unipar.devbackend.trabalhobackend.model.AulasDadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AulasDadasRepository extends JpaRepository<AulasDadas, Long> {
    //busca todas as aulas pertencentes a uma disciplina
    List<AulasDadas> findByDisciplinaId(Long disciplinaId);
}
