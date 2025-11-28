package br.unipar.devbackend.trabalhobackend.controller;

import br.unipar.devbackend.trabalhobackend.dto.Lancamento1BimDTO;
import br.unipar.devbackend.trabalhobackend.dto.Lancamento2BimDTO;
import br.unipar.devbackend.trabalhobackend.service.AlunoDisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lancamento")
@RequiredArgsConstructor
public class LancamentoController {

    private final AlunoDisciplinaService service;

    @PutMapping("/1bim/{idAluno}/{idDisciplina}")
    public void atualizar1Bim(
            @PathVariable Long idAluno,          //ID do aluno que receberá o lançamento
            @PathVariable Long idDisciplina,     //ID da disciplina referente ao lançamento
            @RequestBody Lancamento1BimDTO req   //dados enviados no corpo da requisição
    ) {
        service.atualizar1Bim(idAluno, idDisciplina, req);
    }

    @PutMapping("/2bim/{idAluno}/{idDisciplina}")
    public void atualizar2Bim(
            @PathVariable Long idAluno,          //ID do aluno
            @PathVariable Long idDisciplina,     //ID da disciplina
            @RequestBody Lancamento2BimDTO req   //dados enviados (nota e faltas do 2 bimestre)
    ) {
        service.atualizar2Bim(idAluno, idDisciplina, req);
    }
}

