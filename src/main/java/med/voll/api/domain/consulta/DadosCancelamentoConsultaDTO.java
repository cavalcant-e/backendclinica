package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsultaDTO(
        @NotNull
        Long id,
        @NotNull
        MotivoCancelamento motivo) {
}
