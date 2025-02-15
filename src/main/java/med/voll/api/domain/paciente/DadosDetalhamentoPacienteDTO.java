package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoPacienteDTO(String nome, String email, String cpf, String telefone, Endereco endereco) {

public DadosDetalhamentoPacienteDTO(Paciente paciente){

   this(paciente.getNome(),
           paciente.getEmail(),
           paciente.getCpf(),
           paciente.getTelefone(),
           paciente.getEndereco());
}

}
