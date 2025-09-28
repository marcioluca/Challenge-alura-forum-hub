package br.com.alura.challenge.forumhub.services;

import br.com.alura.challenge.forumhub.domain.curso.*;
import br.com.alura.challenge.forumhub.repositories.CursoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public void excluir(Long id) {
        var curso = cursoRepository.getReferenceById(id);
        if(curso.getAtivo() == Boolean.FALSE) {
            throw new IllegalStateException("Curso não já está inativo.");
        }
        curso.Inativar();
    }

    @Transactional
    public DadosCurso cadastrar(CadastroDadosCurso dados) {
        Curso curso = new Curso(dados.nome(), dados.categoria());
        cursoRepository.save(curso);
        return new DadosCurso(curso.getId(), curso.getNome(), curso.getCategoria());
    }

    public Page<ListagemDadosCurso> listar(Pageable paginacao) {
        Page<Curso> paginaDeCursos = cursoRepository.findAllByAtivoTrue(paginacao);
        return paginaDeCursos.map(ListagemDadosCurso::new);
    }

    @Transactional
    public DadosDetalhamentoCurso atualizar(@Valid DadosAtualizacaoCurso dados) {
        var curso = cursoRepository.findById(dados.id())
                .orElseThrow(() -> new RuntimeException("Curso com id não encontrado"));
        curso.atualizarInformacoes(dados);
        return new DadosDetalhamentoCurso(curso);
    }

}
