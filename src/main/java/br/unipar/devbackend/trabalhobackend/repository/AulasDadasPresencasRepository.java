package br.unipar.devbackend.trabalhobackend.repository;

import br.unipar.devbackend.trabalhobackend.model.AulasDadasPresencas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AulasDadasPresencasRepository extends JpaRepository<AulasDadasPresencas, Long> {
    //lista todos os registros de presença de uma aula
    List<AulasDadasPresencas> findByAulaDadaId(Long aulaId);
    //busca um registro específico (um aluno em uma aula)
    AulasDadasPresencas findByAulaDadaIdAndAlunoId(Long aulaId, Long alunoId);
}