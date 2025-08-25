package br.com.alura.challenge.forumhub.repositories;

import br.com.alura.challenge.forumhub.domain.curso.Curso;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
