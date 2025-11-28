package br.unipar.devbackend.trabalhobackend.controller;

import br.unipar.devbackend.trabalhobackend.dto.DisciplinaDTO;
import br.unipar.devbackend.trabalhobackend.model.AlunoDisciplina;
import br.unipar.devbackend.trabalhobackend.repository.AlunoDisciplinaRepository;
import br.unipar.devbackend.trabalhobackend.service.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/disciplina")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DisciplinaController {

    private final DisciplinaService service;
    private final AlunoDisciplinaRepository alunoDisciplinaRepository;

    @PostMapping
    public DisciplinaDTO criar(@RequestBody DisciplinaDTO dto) {
        return service.criar(dto); //cria disciplina
    }

    @PutMapping
    public DisciplinaDTO atualizar(@RequestBody DisciplinaDTO dto) {
        return service.atualizar(dto); //atualiza disciplina
    }

    @GetMapping("/todas")
    public List<DisciplinaDTO> listar() {
        return service.listarTodas(); //lista todas
    }

    @GetMapping("/{id}")
    public DisciplinaDTO buscar(@PathVariable Long id) {
        return service.buscar(id); //busca por ID
    }

    @GetMapping("/nome/{nome}")
    public List<DisciplinaDTO> buscarPorNomeDisciplina(@PathVariable String nome) {
        return service.buscarPorNomeDisciplina(nome); //busca por nome
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id); //remove disciplina
    }

    @GetMapping("/{idDisciplina}/matriculados")
    public List<AlunoDisciplina> listarMatriculados(@PathVariable Long idDisciplina) {
        return alunoDisciplinaRepository.findByDisciplinaId(idDisciplina); //lista alunos
    }
}
