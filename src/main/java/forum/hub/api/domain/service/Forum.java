package forum.hub.api.domain.service;

import forum.hub.api.domain.resposta.DadosCadastroResposta;
import forum.hub.api.domain.resposta.Resposta;
import forum.hub.api.domain.topico.DadosAtualizacaoTopico;
import forum.hub.api.domain.topico.DadosCadastroTopico;
import forum.hub.api.domain.topico.DadosDetalhamentoTopico;
import forum.hub.api.domain.topico.Topico;
import forum.hub.api.domain.topico.enums.Status;
import forum.hub.api.repository.RespostaRepository;
import forum.hub.api.domain.resposta.validacoes.ValidadorCadastroDeResposta;
import forum.hub.api.domain.topico.validacoes.atualizacao.ValidadorAtualizacaoDeTopico;
import forum.hub.api.domain.topico.validacoes.cadastro.ValidadorCadastroDeTopico;
import forum.hub.api.domain.usuario.Usuario;
import forum.hub.api.repository.UsuarioRepository;
import forum.hub.api.infra.exception.ValidacaoException;
import forum.hub.api.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Forum {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private List<ValidadorCadastroDeTopico> validadoresNovoTopico;

    @Autowired
    private List<ValidadorAtualizacaoDeTopico> validadoresAtualizacao;

    @Autowired
    private List<ValidadorCadastroDeResposta> validadoresNovaResposta;

    public Usuario obterUsuarioAutenticado(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var loginUsuarioAutenticado = authentication.getName();
        return usuarioRepository.findByLogin(loginUsuarioAutenticado);
    }

    public Topico cadastrarTopico(DadosCadastroTopico dados){
        validadoresNovoTopico.forEach(v -> v.validar(dados));

        var autor = obterUsuarioAutenticado();
        var topico = new Topico(dados, autor);
        topicoRepository.save(topico);
        return topico;
    }

    public DadosDetalhamentoTopico atualizarTopico(DadosAtualizacaoTopico dados){
        var usuario = obterUsuarioAutenticado();
        var topico = topicoRepository.getReferenceById(dados.id());

        if (!topicoRepository.existsById(dados.id())) {
            throw new ValidacaoException("Tópico não encontrado.");
        }

        if (!usuario.equals(topico.getAutor())) {
            throw new ValidacaoException("Somente o autor pode atualizar esse tópico.");
        }

        validadoresAtualizacao.forEach(v -> v.validar(dados));
        topico.atualizarInformacoes(dados);
        return new DadosDetalhamentoTopico(topico);
    }

    public void excluirTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ValidacaoException("Tópico não encontrado.");
        }

        var usuario = obterUsuarioAutenticado();
        var topico = topicoRepository.getReferenceById(id);

        if (!usuario.equals(topico.getAutor())) {
            throw new ValidacaoException("Você não pode excluir esse tópico.");
        }

        respostaRepository.deleteAllByTopico(topico);
        topicoRepository.deleteById(id);

    }

    public Resposta cadastrarResposta(DadosCadastroResposta dados, Long topicoId) {
        var autor = obterUsuarioAutenticado();
        var topico = topicoRepository.getReferenceById(topicoId);

        validadoresNovaResposta.forEach(v -> v.validar(topicoId, dados));

        if (!topicoRepository.existsById(topicoId)) {
            throw new ValidacaoException("Tópico não encontrado.");
        }

        var resposta = new Resposta(dados, autor, topico);
        respostaRepository.save(resposta);

        topico.setStatus(Status.RESPONDIDO);
        return resposta;
    }

    public void excluirResposta(Long id) {
        if (!respostaRepository.existsById(id)) {
            throw new ValidacaoException("Resposta não encontrada.");
        }

        var usuario = obterUsuarioAutenticado();
        var resposta = respostaRepository.getReferenceById(id);

        if (!usuario.equals(resposta.getAutor())) {
            throw new ValidacaoException("Somente o autor pode excluir essa resposta.");
        }

        var topico = resposta.getTopico();
        topico.getRespostas().remove(resposta);

        respostaRepository.delete(resposta);

        if (topico.getRespostas().isEmpty()) {
            topico.setStatus(Status.NAO_RESPONDIDO);
            topicoRepository.save(topico);
        }
    }
}
