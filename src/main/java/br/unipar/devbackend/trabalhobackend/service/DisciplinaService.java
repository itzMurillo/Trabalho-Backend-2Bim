package br.unipar.devbackend.trabalhobackend.service;

import br.unipar.devbackend.trabalhobackend.dto.DisciplinaDTO;
import br.unipar.devbackend.trabalhobackend.model.Disciplina;
import br.unipar.devbackend.trabalhobackend.model.Professor;
import br.unipar.devbackend.trabalhobackend.repository.DisciplinaRepository;
import br.unipar.devbackend.trabalhobackend.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository repo;
    private final ProfessorRepository professorRepo;

    //converte entidade para DTO
    private DisciplinaDTO toDTO(Disciplina d) {
        return new DisciplinaDTO(
                d.getId(),
                d.getCodigo(),
                d.getNome(),
                d.getDescricao(),
                d.getEmenta(),
                d.getProfessor() != null ? d.getProfessor().getId() : null,
                d.getProfessor() != null ? d.getProfessor().getNome() : null
        );
    }

    //cadastrar disciplina
    public DisciplinaDTO criar(DisciplinaDTO dto) {

        //busca o professor informado
        Professor p = professorRepo.findById(dto.professorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        //cria a entidade
        Disciplina d = new Disciplina();
        d.setCodigo(dto.codigo());
        d.setNome(dto.nome());
        d.setDescricao(dto.descricao());
        d.setEmenta(dto.ementa());
        d.setProfessor(p);

        repo.save(d);

        return toDTO(d);
    }

    //atualizar disciplina
    public DisciplinaDTO atualizar(DisciplinaDTO dto) {

        Disciplina d = repo.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        d.setCodigo(dto.codigo());
        d.setNome(dto.nome());
        d.setDescricao(dto.descricao());
        d.setEmenta(dto.ementa());

        Professor p = professorRepo.findById(dto.professorId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        d.setProfessor(p);

        repo.save(d);

        return toDTO(d);
    }

    //listar todas as disciplinas
    public List<DisciplinaDTO> listarTodas() {
        return repo.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    //buscar disciplina por ID
    public DisciplinaDTO buscar(Long id) {
        return repo.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
    }

    //buscar disciplinas pelo nome
    public List<DisciplinaDTO> buscarPorNomeDisciplina(String nome) {
        return repo.findByNomeContaining(nome)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    //excluir disciplina
    public void excluir(Long id) {
        repo.deleteById(id);
    }
}
