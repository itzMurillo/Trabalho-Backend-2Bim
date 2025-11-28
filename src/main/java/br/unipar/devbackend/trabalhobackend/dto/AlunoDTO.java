package br.unipar.devbackend.trabalhobackend.dto;

//DTO usado para enviar e receber dados do aluno
public record AlunoDTO(
        Long id,
        Long ra,
        String nome,
        String dtNascimento,
        String curso
) {}
