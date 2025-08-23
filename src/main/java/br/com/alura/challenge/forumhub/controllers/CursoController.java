package br.com.alura.challenge.forumhub.controllers;

import br.com.alura.challenge.forumhub.domain.curso.CadastroDadosCurso;
import br.com.alura.challenge.forumhub.domain.curso.DadosCurso;
import br.com.alura.challenge.forumhub.domain.topico.DetalhamentoDadosTopico;
import br.com.alura.challenge.forumhub.services.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
