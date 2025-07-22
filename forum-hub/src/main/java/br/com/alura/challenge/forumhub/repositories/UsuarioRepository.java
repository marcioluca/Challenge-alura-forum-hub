package br.com.alura.challenge.forumhub.repositories;

import br.com.alura.challenge.forumhub.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}
