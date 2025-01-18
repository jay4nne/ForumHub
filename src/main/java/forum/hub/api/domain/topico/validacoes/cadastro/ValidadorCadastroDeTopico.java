package forum.hub.api.domain.topico.validacoes.cadastro;

import forum.hub.api.domain.topico.DadosCadastroTopico;

public interface ValidadorCadastroDeTopico {
    void validar(DadosCadastroTopico dadosCadastroTopico);
}
