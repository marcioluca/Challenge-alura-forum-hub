package br.com.alura.challenge.forumhub.services;

import br.com.alura.challenge.forumhub.domain.curso.Curso;
import br.com.alura.challenge.forumhub.domain.topico.*;
import br.com.alura.challenge.forumhub.domain.usuario.Usuario;
import br.com.alura.challenge.forumhub.repositories.CursoRepository;
import br.com.alura.challenge.forumhub.repositories.TopicosRepository;
import br.com.alura.challenge.forumhub.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {

    @Autowired
    private TopicosRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public DetalhamentoDadosTopico cadastrar(DadosCadastroTopico dados) {

        Usuario autor = usuarioRepository.findById(dados.idAutor())
                .orElseThrow(() -> new EntityNotFoundException("Autor com id " + dados.idAutor() + " não encontrado."));

        Curso curso = cursoRepository.findById(dados.idCurso())
                .orElseThrow(() -> new EntityNotFoundException("Curso com id " + dados.idCurso() + " não encontrado."));


        Topico topico = new Topico(dados.titulo(), dados.mensagem(), autor, curso);


        topicoRepository.save(topico);


        return new DetalhamentoDadosTopico(topico);
    }
}