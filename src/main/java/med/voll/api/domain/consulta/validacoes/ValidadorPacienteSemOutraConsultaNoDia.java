package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoExcecao;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void Validar(DadosAgendamentoConsultaDTO dados) {
        var pacienteId = dados.idPaciente();
        var data = dados.data();
        var dataInicio = data.toLocalDate().atStartOfDay();
        var dataFim = dataInicio.plusDays(1).minusSeconds(1);

        boolean pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(pacienteId, dataInicio, dataFim);

        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoExcecao("Paciente já possui uma consulta agendada neste dia");
        }
    }
}