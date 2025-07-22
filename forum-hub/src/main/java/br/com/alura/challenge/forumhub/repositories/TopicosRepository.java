package br.com.alura.challenge.forumhub.repositories;

import br.com.alura.challenge.forumhub.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicosRepository extends JpaRepository<Topico, Long> {
}
