package br.com.alura.challenge.forumhub.controllers;

import br.com.alura.challenge.forumhub.domain.curso.*;
import br.com.alura.challenge.forumhub.services.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<DadosCurso> cadastrar(@RequestBody @Valid CadastroDadosCurso dados, UriComponentsBuilder uriBuilder) {

        DadosCurso detalhamentoDto = cursoService.cadastrar(dados);
        URI uri = uriBuilder.path("/curso/{id}").buildAndExpand(detalhamentoDto.id()).toUri();

        return ResponseEntity.created(uri).body(detalhamentoDto);
    }

    @GetMapping
    public ResponseEntity<Page<ListagemDadosCurso>> listar(@PageableDefault(size = 10, sort = {"nome"}, direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = cursoService.listar(paginacao);
        return ResponseEntity.ok(page);

    }
    @PutMapping
    public ResponseEntity<DadosDetalhamentoCurso> atualizar(@RequestBody @Valid DadosAtualizacaoCurso dados) {
        var topicoAtualizado = cursoService.atualizar(dados);
        return ResponseEntity.ok(topicoAtualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id) {
        cursoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

