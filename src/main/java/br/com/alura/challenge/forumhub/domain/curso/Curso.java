package br.com.alura.challenge.forumhub.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;
    private Boolean ativo;

    public Curso(String nome, String categoria) {
        this.nome = nome;
        this.categoria = categoria;
        this.ativo = Boolean.TRUE;
    }

    public void Inativar() {
        this.ativo = Boolean.FALSE;
    }


    public void atualizarInformacoes(DadosAtualizacaoCurso dados) {
        if (dados.nome() != null && !dados.nome().isBlank()) {
            this.nome = dados.nome();
        }
        if (dados.categoria() != null && !dados.categoria().isBlank()) {
            this.categoria = dados.categoria();
        }
    }
}

