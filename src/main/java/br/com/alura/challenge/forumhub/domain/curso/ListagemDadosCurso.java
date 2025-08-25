package br.com.alura.challenge.forumhub.domain.curso;

public record ListagemDadosCurso(Long id, String nome, String categoria) {
    public ListagemDadosCurso(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
