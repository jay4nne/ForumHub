package forum.hub.api.domain.topico.validacoes.atualizacao;

import forum.hub.api.domain.topico.DadosAtualizacaoTopico;

public interface ValidadorAtualizacaoDeTopico {
    void validar(DadosAtualizacaoTopico dadosAtualizacaoTopico);
}
