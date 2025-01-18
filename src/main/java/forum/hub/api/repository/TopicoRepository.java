package forum.hub.api.repository;

import forum.hub.api.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Boolean existsByTituloAndMensagem(String titulo, String mensagem);
}
