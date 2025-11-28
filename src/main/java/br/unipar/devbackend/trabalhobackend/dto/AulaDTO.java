package br.unipar.devbackend.trabalhobackend.dto;

import java.util.Date;

//DTO usado ao criar aula
public record AulaDTO(
        Date data,
        String observacoes
) {}
