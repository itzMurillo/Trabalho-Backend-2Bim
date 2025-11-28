package br.unipar.devbackend.trabalhobackend.service;

import br.unipar.devbackend.trabalhobackend.dto.AulaDTO;
import br.unipar.devbackend.trabalhobackend.dto.PresencaDTO;
import br.unipar.devbackend.trabalhobackend.model.*;
import br.unipar.devbackend.trabalhobackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AulaService {

    private final AulasDadasRepository aulasRepo;
    private final AulasDadasPresencasRepository presencasRepo;
    private final DisciplinaRepository disciplinaRepo;
    private final AlunoDisciplinaRepository alunoDisciplinaRepo;

    //criar nova aula
    public AulasDadas criarAula(Long idDisciplina, AulaDTO dto) {

        Disciplina d = disciplinaRepo.findById(idDisciplina)
                .orElseThrow();

        AulasDadas aula = new AulasDadas();
        aula.setDisciplina(d);
        aula.setData(dto.data());
        aula.setObservacoes(dto.observacoes());

        aula = aulasRepo.save(aula);

        //ao criar a aula, gerar registros de presença para cada aluno matriculado
        List<AlunoDisciplina> alunos = alunoDisciplinaRepo.findByDisciplinaId(idDisciplina);

        for (AlunoDisciplina ad : alunos) {
            AulasDadasPresencas p = new AulasDadasPresencas();
            p.setAulaDada(aula);
            p.setAluno(ad.getAluno());
            p.setFalta(false);

            presencasRepo.save(p);
        }
        return aula;
    }

    //buscar aulas da disciplina
    public List<AulasDadas> listarAulas(Long idDisciplina) {
        return aulasRepo.findByDisciplinaId(idDisciplina);
    }

    //buscar uma única aula
    public AulasDadas buscarAula(Long idAula) {
        return aulasRepo.findById(idAula).orElseThrow();
    }

    //listar presenças/faltas
    public List<AulasDadasPresencas> listarPresencas(Long idAula) {
        return presencasRepo.findByAulaDadaId(idAula);
    }

    //registrar presenças
    public void registrarPresencas(Long idAula, List<PresencaDTO> lista) {

        for (PresencaDTO dto : lista) {

            AulasDadasPresencas p = presencasRepo
                    .findByAulaDadaIdAndAlunoId(idAula, dto.alunoId());

            p.setFalta(dto.falta());
            presencasRepo.save(p);
        }
    }
}
