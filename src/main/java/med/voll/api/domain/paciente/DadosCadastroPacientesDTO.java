package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroPacientesDTO(

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank (message = "email obrigatorio")
        @Email (message = "formato invalido")
        String email,

        @NotBlank (message = "telefone obrigatorio")
        String telefone,

        @NotBlank (message = "CPF obrigatorio")
        String cpf,

        @NotNull(message = "Dados do endereço são obrigatórios")
        @Valid
        DadosEndereco endereco
        ) {
}
