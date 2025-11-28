package br.unipar.devbackend.trabalhobackend.controller;

import br.unipar.devbackend.trabalhobackend.dto.AulaDTO;
import br.unipar.devbackend.trabalhobackend.dto.PresencaDTO;
import br.unipar.devbackend.trabalhobackend.model.AulasDadas;
import br.unipar.devbackend.trabalhobackend.model.AulasDadasPresencas;
import br.unipar.devbackend.trabalhobackend.service.AulaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/aula")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AulaController {

    private final AulaService service;

    //cadastrar nova aula
    @PostMapping("/disciplina/{idDisciplina}")
    public AulasDadas criarAula(
            @PathVariable Long idDisciplina,
            @RequestBody AulaDTO dto) {

        return service.criarAula(idDisciplina, dto);
    }

    //buscar todas as aulas de uma disciplina
    @GetMapping("/disciplina/{idDisciplina}")
    public List<AulasDadas> listarAulas(@PathVariable Long idDisciplina) {
        return service.listarAulas(idDisciplina);
    }

    //buscar uma aula específica
    @GetMapping("/{idAula}")
    public AulasDadas buscarAula(@PathVariable Long idAula) {
        return service.buscarAula(idAula);
    }

    //listar presenças/faltas de uma aula
    @GetMapping("/{idAula}/presencas")
    public List<AulasDadasPresencas> listarPresencas(@PathVariable Long idAula) {
        return service.listarPresencas(idAula);
    }

    //registrar presenças/faltas
    @PostMapping("/{idAula}/presencas")
    public void registrarPresencas(
            @PathVariable Long idAula,
            @RequestBody List<PresencaDTO> lista) {

        service.registrarPresencas(idAula, lista);
    }
}
