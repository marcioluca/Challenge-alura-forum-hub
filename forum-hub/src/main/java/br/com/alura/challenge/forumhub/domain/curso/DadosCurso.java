package br.com.alura.challenge.forumhub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosCurso(
                         @NotBlank
                         String nome,
                         String categoria) {
}
