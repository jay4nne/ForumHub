package forum.hub.api.domain.resposta.validacoes;

import forum.hub.api.infra.exception.ValidacaoException;
import forum.hub.api.domain.resposta.DadosCadastroResposta;
import forum.hub.api.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ValidadorRespostaDuplicada")
public class ValidadorRespostaDuplicada implements ValidadorCadastroDeResposta{

    @Autowired
    private RespostaRepository respostaRepository;

    @Override
    public void validar(Long topicoId, DadosCadastroResposta dados) {
        var mensagem = dados.mensagem();
        var respostaDuplicada = respostaRepository.existsByTopicoIdAndMensagem(topicoId, mensagem);

        if(respostaDuplicada){
            throw new ValidacaoException("A resposta a ser cadastrada já existe.");
        }
    }
}
