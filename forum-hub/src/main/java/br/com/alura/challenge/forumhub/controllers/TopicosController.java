package br.com.alura.challenge.forumhub.controllers;

import br.com.alura.challenge.forumhub.domain.topico.DadosCadastroTopico;
import br.com.alura.challenge.forumhub.domain.topico.DetalhamentoDadosTopico;
import br.com.alura.challenge.forumhub.services.TopicoService;
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
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DetalhamentoDadosTopico> cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {

        DetalhamentoDadosTopico detalhamentoDto = topicoService.cadastrar(dados);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(detalhamentoDto.id()).toUri();

        return ResponseEntity.created(uri).body(detalhamentoDto);
    }
}