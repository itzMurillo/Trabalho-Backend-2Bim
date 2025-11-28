package br.unipar.devbackend.trabalhobackend.dto;

//DTO da Disciplina, usado no envio e retorno das requisições
public record DisciplinaDTO(
        Long id,            //ID da disciplina
        String codigo,      //código da matéria
        String nome,        //nome da disciplina
        String descricao,   //descrição resumida
        String ementa,      //ementa completa
        Long professorId,   //ID do professor
        String professorNome //nome do professor (pra exibir no front)
) {}