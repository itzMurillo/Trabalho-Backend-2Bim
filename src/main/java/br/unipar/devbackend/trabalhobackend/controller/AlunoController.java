package br.unipar.devbackend.trabalhobackend.controller;

import br.unipar.devbackend.trabalhobackend.dto.AlunoDTO;
import br.unipar.devbackend.trabalhobackend.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/aluno")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService alunoService;

    //cadastrar aluno
    //recebe um AlunoDTO e retorna o aluno criado
    @PostMapping
    public AlunoDTO criar(@RequestBody AlunoDTO dto) {
        return alunoService.criar(dto);
    }

    //atualizar aluno
    //recebe um DTO com ID incluído e salva no banco
    @PutMapping
    public AlunoDTO atualizar(@RequestBody AlunoDTO dto) {
        return alunoService.atualizar(dto);
    }

    //listar todos os alunos
    @GetMapping("/todos")
    public List<AlunoDTO> listar() {
        return alunoService.listarTodos();
    }

    //buscar aluno por ID
    @GetMapping("/{id}")
    public AlunoDTO buscar(@PathVariable Long id) {
        return alunoService.buscar(id);
    }

    //excluir aluno
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        alunoService.excluir(id);
    }
}