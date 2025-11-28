package br.unipar.devbackend.trabalhobackend.controller;

import br.unipar.devbackend.trabalhobackend.dto.ProfessorDTO;
import br.unipar.devbackend.trabalhobackend.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/professor")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProfessorController {

    private final ProfessorService service;

    @PostMapping
    //endpoint para cadastrar um professor
    public ProfessorDTO criar(@RequestBody ProfessorDTO dto) {
        return service.criar(dto); //chama o service para salvar
    }

    @PutMapping
    //endpoint para atualizar os dados de um professor
    public ProfessorDTO atualizar(@RequestBody ProfessorDTO dto) {
        return service.atualizar(dto); //chama o service para atualizar
    }

    @GetMapping("/todos")
    //retorna a lista de todos os professores cadastrados
    public List<ProfessorDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    //busca um professor específico pelo ID
    public ProfessorDTO buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @DeleteMapping("/{id}")
    //exclui um professor pelo ID
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
