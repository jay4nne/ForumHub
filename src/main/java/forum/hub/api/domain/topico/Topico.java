package forum.hub.api.domain.topico;

import forum.hub.api.domain.resposta.Resposta;
import forum.hub.api.domain.topico.enums.Curso;
import forum.hub.api.domain.topico.enums.Status;
import forum.hub.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Curso curso;

    @OneToMany(mappedBy = "topico", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Resposta> respostas = new ArrayList<>();

    public Topico(DadosCadastroTopico dados, Usuario usuario) {
        this.data = LocalDateTime.now();
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.status = Status.NAO_RESPONDIDO;
        this.curso = dados.curso();
        this.autor = usuario;
    }

    public Topico(){}

    public DadosAtualizacaoTopico atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.curso() != null) {
            this.curso = dados.curso();
        }

        return new DadosAtualizacaoTopico(dados.id(), dados.titulo(), dados.mensagem(), dados.curso());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Status getStatus() {
        return status;
    }

    public Curso getCurso() {
        return curso;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
