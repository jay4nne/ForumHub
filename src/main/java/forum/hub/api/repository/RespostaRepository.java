package forum.hub.api.repository;

import forum.hub.api.domain.resposta.Resposta;
import forum.hub.api.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    List<Resposta> findByTopico(Topico topico);

    void deleteAllByTopico(Topico topico);

    @Query("SELECT COUNT(r) > 0 FROM Resposta r WHERE r.topico.id = :topicoId AND r.mensagem = :mensagem")
    Boolean existsByTopicoIdAndMensagem(Long topicoId, String mensagem);
}
