package br.unipar.devbackend.trabalhobackend.service;

import br.unipar.devbackend.trabalhobackend.dto.ProfessorDTO;
import br.unipar.devbackend.trabalhobackend.model.Professor;
import br.unipar.devbackend.trabalhobackend.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repo;

    //cadastra um novo professor
    public ProfessorDTO criar(ProfessorDTO dto) {
        Professor p = new Professor(
                null,
                dto.matricula(),
                dto.nome(),
                dto.cpf()
        );

        p = repo.save(p);

        return new ProfessorDTO(
                p.getId(),
                p.getMatricula(),
                p.getNome(),
                p.getCpf()
        );
    }

    //atualiza professor existente
    public ProfessorDTO atualizar(ProfessorDTO dto) {
        Professor p = repo.findById(dto.id()).orElseThrow();

        p.setMatricula(dto.matricula());
        p.setNome(dto.nome());
        p.setCpf(dto.cpf());

        repo.save(p);

        return dto;
    }

    //busca professor por ID
    public ProfessorDTO buscar(Long id) {
        Professor p = repo.findById(id).orElseThrow();

        return new ProfessorDTO(
                p.getId(),
                p.getMatricula(),
                p.getNome(),
                p.getCpf()
        );
    }

    //lista todos os professores
    public List<ProfessorDTO> listarTodos() {
        return repo.findAll()
                .stream()
                .map(p -> new ProfessorDTO(
                        p.getId(),
                        p.getMatricula(),
                        p.getNome(),
                        p.getCpf()
                ))
                .toList();
    }

    //exclui professor
    public void excluir(Long id) {
        repo.deleteById(id);
    }
}
