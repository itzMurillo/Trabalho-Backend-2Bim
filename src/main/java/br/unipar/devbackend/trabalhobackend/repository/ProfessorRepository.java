package br.unipar.devbackend.trabalhobackend.repository;

import br.unipar.devbackend.trabalhobackend.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByNome(String nome);
}