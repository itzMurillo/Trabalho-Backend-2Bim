package br.unipar.devbackend.trabalhobackend.repository;

import br.unipar.devbackend.trabalhobackend.model.AlunoDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoDisciplinaRepository extends JpaRepository<AlunoDisciplina, Long> {
    //lista todos os alunos matriculados em uma disciplina
    List<AlunoDisciplina> findByDisciplinaId(Long id);
    //busca o relacionamento específico entre aluno e disciplina
    //usado para atualizar notas e faltas
    AlunoDisciplina findByAlunoIdAndDisciplinaId(Long alunoId, Long disciplinaId);
}
