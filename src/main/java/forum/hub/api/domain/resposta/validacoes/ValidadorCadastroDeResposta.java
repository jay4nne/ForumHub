package forum.hub.api.domain.resposta.validacoes;

import forum.hub.api.domain.resposta.DadosCadastroResposta;

public interface ValidadorCadastroDeResposta {
    void validar(Long topicoId, DadosCadastroResposta dadosCadastroResposta);
}
