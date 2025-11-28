package br.unipar.devbackend.trabalhobackend.service;

import br.unipar.devbackend.trabalhobackend.dto.Lancamento1BimDTO;
import br.unipar.devbackend.trabalhobackend.dto.Lancamento2BimDTO;
import br.unipar.devbackend.trabalhobackend.model.AlunoDisciplina;
import br.unipar.devbackend.trabalhobackend.repository.AlunoDisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlunoDisciplinaService {

    private final AlunoDisciplinaRepository repo;

    //atualiza nota e faltas do 1° bimestre
    public void atualizar1Bim(Long idAluno, Long idDisc, Lancamento1BimDTO req) {

        AlunoDisciplina ad = repo.findByAlunoIdAndDisciplinaId(idAluno, idDisc);

        if (ad == null) {
            throw new RuntimeException("Matrícula não encontrada para o 1º bimestre.");
        }
        ad.setNota1Bim(req.nota1Bim());
        ad.setFaltas1Bim(req.faltas1Bim());

        repo.save(ad);
    }

    //atualiza nota e faltas do 2° bimestre + calcula situação final
    public void atualizar2Bim(Long idAluno, Long idDisc, Lancamento2BimDTO req) {

        AlunoDisciplina ad = repo.findByAlunoIdAndDisciplinaId(idAluno, idDisc);

        if (ad == null) {
            throw new RuntimeException("Matrícula não encontrada para o 2º bimestre.");
        }

        ad.setNota2Bim(req.nota2Bim());
        ad.setFaltas2Bim(req.faltas2Bim());

        //calculo de media
        double nota1 = ad.getNota1Bim() != null ? ad.getNota1Bim() : 0.0;
        double nota2 = ad.getNota2Bim() != null ? ad.getNota2Bim() : 0.0;
        double media = (nota1 + nota2) / 2.0;

        //calculo de faltas
        int faltas1 = ad.getFaltas1Bim() != null ? ad.getFaltas1Bim() : 0;
        int faltas2 = ad.getFaltas2Bim() != null ? ad.getFaltas2Bim() : 0;
        int totalFaltas = faltas1 + faltas2;

        int limiteFaltas = 25;

        //regra final
        boolean aprovado = media >= 60 && totalFaltas <= limiteFaltas;

        ad.setSituacao(aprovado ? "APROVADO" : "REPROVADO");
        ad.setMatriculado(!aprovado);

        repo.save(ad);
    }
}

