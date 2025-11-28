package br.unipar.devbackend.trabalhobackend.controller;

import br.unipar.devbackend.trabalhobackend.dto.Lancamento1BimDTO;
import br.unipar.devbackend.trabalhobackend.dto.Lancamento2BimDTO;
import br.unipar.devbackend.trabalhobackend.model.Aluno;
import br.unipar.devbackend.trabalhobackend.model.AlunoDisciplina;
import br.unipar.devbackend.trabalhobackend.model.Disciplina;
import br.unipar.devbackend.trabalhobackend.repository.AlunoDisciplinaRepository;
import br.unipar.devbackend.trabalhobackend.repository.AlunoRepository;
import br.unipar.devbackend.trabalhobackend.repository.DisciplinaRepository;
import br.unipar.devbackend.trabalhobackend.service.AlunoDisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/lancamento")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AlunoDisciplinaController {

    private final AlunoDisciplinaService service;
    private final AlunoDisciplinaRepository repo;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    // Lista alunos matriculados na disciplina
    @GetMapping("/disciplina/{id}")
    public List<AlunoDisciplina> listarAlunosDisciplina(@PathVariable Long id) {
        return repo.findByDisciplinaId(id);
    }

    // Atualiza 1º bimestre
    @PutMapping("/{alunoId}/{disciplinaId}/1bim")
    public void atualizar1(
            @PathVariable Long alunoId,
            @PathVariable Long disciplinaId,
            @RequestBody Lancamento1BimDTO req
    ) {
        service.atualizar1Bim(alunoId, disciplinaId, req);
    }

    // Atualiza 2º bimestre e calcula situação
    @PutMapping("/{alunoId}/{disciplinaId}/2bim")
    public void atualizar2(
            @PathVariable Long alunoId,
            @PathVariable Long disciplinaId,
            @RequestBody Lancamento2BimDTO req
    ) {
        service.atualizar2Bim(alunoId, disciplinaId, req);
    }

    // Matricular aluno em disciplina
    @PostMapping("/matricular")
    public AlunoDisciplina matricular(@RequestBody Map<String, Long> req) {
        Long alunoId = req.get("alunoId");
        Long disciplinaId = req.get("disciplinaId");

        Aluno aluno = alunoRepository.findById(alunoId).orElseThrow();
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId).orElseThrow();

        AlunoDisciplina ad = new AlunoDisciplina();
        ad.setAluno(aluno);
        ad.setDisciplina(disciplina);
        ad.setNota1Bim(0.0);
        ad.setNota2Bim(0.0);
        ad.setFaltas1Bim(0);
        ad.setFaltas2Bim(0);
        ad.setMatriculado(true);
        ad.setSituacao("EM_CURSO");

        return repo.save(ad);
    }
}