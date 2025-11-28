package br.unipar.devbackend.trabalhobackend.dto;

//DTO simples do professor
public record ProfessorDTO(
        Long id,
        String matricula, //matrícula única
        String nome,
        String cpf
) {}