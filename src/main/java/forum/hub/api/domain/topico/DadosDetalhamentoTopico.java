package forum.hub.api.domain.topico;

import forum.hub.api.domain.resposta.DadosDetalhamentoResposta;
import forum.hub.api.domain.topico.enums.Curso;
import forum.hub.api.domain.topico.enums.Status;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem,
                                      LocalDateTime data, Status status, String autor, Curso curso, List<DadosDetalhamentoResposta> respostas) {

    public DadosDetalhamentoTopico(Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getData(),
                topico.getStatus(),
                topico.getAutor() != null ? topico.getAutor().getLogin() : null,
                topico.getCurso(),
                topico.getRespostas().stream()
                        .map(DadosDetalhamentoResposta::new)
                        .toList()
        );

    }
}

