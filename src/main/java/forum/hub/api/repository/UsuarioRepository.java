package forum.hub.api.repository;

import forum.hub.api.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
   Usuario findByLogin(String login);
}
