package forum.hub.api.domain.topico.validacoes.cadastro;

import forum.hub.api.infra.exception.ValidacaoException;
import forum.hub.api.domain.topico.DadosCadastroTopico;
import forum.hub.api.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorTopicoDuplicado")
public class ValidadorTopicoDuplicado implements ValidadorCadastroDeTopico{

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DadosCadastroTopico dados){
        var titulo = dados.titulo();
        var mensagem = dados.mensagem();
        var topicoDuplicado = topicoRepository.existsByTituloAndMensagem(titulo, mensagem);

        if(topicoDuplicado){
            throw new ValidacaoException("O tópico a ser cadastrado já existe.");
        }
    }
}
