package br.com.alura.challenge.forumhub.domain.topico;

import br.com.alura.challenge.forumhub.domain.curso.Curso;
import br.com.alura.challenge.forumhub.domain.usuario.Usuario;

import java.time.LocalDateTime;

// Nome do record principal (mantive o seu, mas "DadosListagemTopico" é mais comum)
public record ListagemDadosTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        DadosAutor autor,
        DadosCurso curso
) {

    public ListagemDadosTopico(DetalhamentoDadosTopico topico) {
        this(
                topico.id(),
                topico.titulo(),
                topico.mensagem(),
                topico.dataCriacao(),
                topico.status(),
                new DadosAutor(topico.autor()),
                new DadosCurso(topico.curos())
        );
    }


    private record DadosAutor(String nome) {
        public DadosAutor(Usuario autor) {
            this(autor.getNome());
        }
    }
    private record DadosCurso(String nome) {
        public DadosCurso(Curso curso) {
            this(curso.getNome());
        }
    }
}