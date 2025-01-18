package forum.hub.api.domain.topico.validacoes.atualizacao;

import forum.hub.api.infra.exception.ValidacaoException;
import forum.hub.api.domain.topico.DadosAtualizacaoTopico;
import forum.hub.api.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorTopicoDuplicadoParaAtualizacao")
public class ValidadorTopicoDuplicado implements ValidadorAtualizacaoDeTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DadosAtualizacaoTopico dados) {
        var titulo = dados.titulo();
        var mensagem = dados.mensagem();
        var topicoDuplicado = topicoRepository.existsByTituloAndMensagem(titulo, mensagem);

        if(topicoDuplicado){
            throw new ValidacaoException("O tópico que você está tentando atualizar já existe.");
        }
    }
}
