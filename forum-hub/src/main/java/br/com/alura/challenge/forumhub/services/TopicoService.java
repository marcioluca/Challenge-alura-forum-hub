package br.com.alura.challenge.forumhub.services;

import br.com.alura.challenge.forumhub.domain.curso.Curso;
import br.com.alura.challenge.forumhub.domain.topico.*;
import br.com.alura.challenge.forumhub.domain.usuario.Usuario;
import br.com.alura.challenge.forumhub.repositories.CursoRepository;
import br.com.alura.challenge.forumhub.repositories.TopicosRepository;
import br.com.alura.challenge.forumhub.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public DetalhamentoDadosTopico cadastrar(CadastroDadosTopico dados) {

        if(topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            throw new IllegalStateException("Já existe um tópico com o mesmo título e mensagem.");
        }

        Usuario autor = usuarioRepository.findById(dados.idAutor())
                .orElseThrow(() -> new EntityNotFoundException("Autor com id " + dados.idAutor() + " não encontrado."));

        Curso curso = cursoRepository.findById(dados.idCurso())
                .orElseThrow(() -> new EntityNotFoundException("Curso com id " + dados.idCurso() + " não encontrado."));


        Topico topico = new Topico(dados.titulo(), dados.mensagem(), autor, curso);

        topicoRepository.save(topico);

        return new DetalhamentoDadosTopico(topico);
    }

    public Page<ListagemDadosTopico> listar(Pageable paginacao) {
        var pagina = topicoRepository.findAll(paginacao).map(DetalhamentoDadosTopico::new);

        return pagina.map(ListagemDadosTopico::new);
    }
}