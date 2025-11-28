package br.unipar.devbackend.trabalhobackend.service;

import br.unipar.devbackend.trabalhobackend.dto.AlunoDTO;
import br.unipar.devbackend.trabalhobackend.model.Aluno;
import br.unipar.devbackend.trabalhobackend.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repo;

    //cadastra um novo aluno no banco
    public AlunoDTO criar(AlunoDTO dto) {

        //converte o DTO para a entidade
        Aluno a = new Aluno(
                null,
                dto.ra(),
                dto.nome(),
                dto.dtNascimento(),
                dto.curso()
        );
        //salva no banco
        a = repo.save(a);
        //retorna novamente como DTO
        return new AlunoDTO(
                a.getId(),
                a.getRa(),
                a.getNome(),
                a.getDtNascimento(),
                a.getCurso()
        );
    }

    //atualiza um aluno já existente
    public AlunoDTO atualizar(AlunoDTO dto) {
        Aluno a = repo.findById(dto.id()).orElseThrow();

        a.setRa(dto.ra());
        a.setNome(dto.nome());
        a.setDtNascimento(dto.dtNascimento());
        a.setCurso(dto.curso());

        repo.save(a);

        return dto;
    }

    //lista todos os alunos cadastrados
    public List<AlunoDTO> listarTodos() {
        return repo.findAll()
                .stream()
                .map(a -> new AlunoDTO(
                        a.getId(),
                        a.getRa(),
                        a.getNome(),
                        a.getDtNascimento(),
                        a.getCurso()
                ))
                .toList();
    }

    //busca um aluno pelo ID
    public AlunoDTO buscar(Long id) {
        Aluno a = repo.findById(id).orElseThrow();

        return new AlunoDTO(
                a.getId(),
                a.getRa(),
                a.getNome(),
                a.getDtNascimento(),
                a.getCurso()
        );
    }

    //remove o aluno pelo ID
    public void excluir(Long id) {
        repo.deleteById(id);
    }
}