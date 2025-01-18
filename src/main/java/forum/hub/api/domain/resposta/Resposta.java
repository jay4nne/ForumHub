package forum.hub.api.domain.resposta;

import forum.hub.api.domain.topico.Topico;
import forum.hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;

@Entity(name = "Resposta")
@Table(name = "resposta")
@EqualsAndHashCode(of = "id")
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;

    private LocalDateTime data;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "topico_id")
    private Topico topico;


    public Resposta(DadosCadastroResposta dados, Usuario usuario, Topico topico) {
        this.mensagem = dados.mensagem();
        this.autor = usuario;
        this.data = LocalDateTime.now();
        this.topico = topico;
    }

    public Resposta(DadosCadastroResposta dados, Usuario usuario){
        this.mensagem = dados.mensagem();
        this.autor = usuario;
        this.data = LocalDateTime.now();
    }

    public Resposta(){}

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public Usuario getAutor() {
        return autor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Topico getTopico() {
        return topico;
    }

}

