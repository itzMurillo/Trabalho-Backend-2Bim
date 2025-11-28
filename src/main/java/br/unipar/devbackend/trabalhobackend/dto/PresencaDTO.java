package br.unipar.devbackend.trabalhobackend.dto;

//DTO usado para registrar presenças/faltas
public record PresencaDTO(
        Long alunoId,
        boolean falta
) {}