package forum.hub.api.domain.topico;

import forum.hub.api.domain.topico.enums.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(
        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A mensagem é obrigatória.")
        String mensagem,

        @NotNull(message = "O curso é obrigatório")
        Curso curso
) {
}
