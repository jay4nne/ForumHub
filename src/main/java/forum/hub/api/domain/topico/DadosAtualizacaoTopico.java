package forum.hub.api.domain.topico;

import forum.hub.api.domain.topico.enums.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
        @NotNull
        Long id,

        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        @NotNull
        Curso curso
) {
}
