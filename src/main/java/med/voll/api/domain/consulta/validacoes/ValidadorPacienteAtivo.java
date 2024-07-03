package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoExcecao;
import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    public void Validar(DadosAgendamentoConsultaDTO dados) {
        var pacienteId = dados.idPaciente();
        boolean pacienteAtivo = repository.findAtivoById(pacienteId);

        if (!pacienteAtivo) {
            throw new ValidacaoExcecao("Consulta não pode ser agendada com paciente inativo");
        }
    }
}