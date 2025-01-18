package forum.hub.api.controller;

import forum.hub.api.domain.resposta.DadosCadastroResposta;
import forum.hub.api.domain.resposta.DadosDetalhamentoResposta;
import forum.hub.api.repository.RespostaRepository;
import forum.hub.api.domain.topico.DadosDetalhamentoTopico;
import forum.hub.api.domain.service.Forum;
import forum.hub.api.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos/{id}/respostas")
public class RespostaController {

    @Autowired
    private Forum forum;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@PathVariable Long id, @RequestBody @Valid DadosCadastroResposta dados, UriComponentsBuilder uriComponentsBuilder) {
        var resposta = forum.cadastrarResposta(dados, id);

        var uri = uriComponentsBuilder.path("/topicos/{id}/respostas/{respostaId}")
                .buildAndExpand(id, resposta.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoResposta(resposta));
    }

    @GetMapping
    public ResponseEntity <DadosDetalhamentoTopico>listar(@PathVariable("id") Long topicoId){
        var topico = topicoRepository.getReferenceById(topicoId);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{respostaId}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long respostaId){
        forum.excluirResposta(respostaId);
        return ResponseEntity.noContent().build();
    }
}
