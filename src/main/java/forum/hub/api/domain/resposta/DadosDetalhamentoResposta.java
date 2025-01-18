package forum.hub.api.domain.resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(Long id, String mensagem, String autor, LocalDateTime data) {

    public DadosDetalhamentoResposta(Resposta resposta) {
        this(resposta.getId(),
             resposta.getMensagem(),
             resposta.getAutor().getLogin(),
             resposta.getData());
    }
}

