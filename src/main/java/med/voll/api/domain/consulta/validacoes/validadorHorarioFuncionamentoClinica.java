package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoExcecao;
import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;


@Component
public class validadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {


    public void Validar (DadosAgendamentoConsultaDTO dados){
        LocalDateTime dataConsulta = dados.data();

        boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
//        boolean antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
//        boolean depoisDoEncerramentoDaclinica = dataConsulta.getHour() > 18;
        boolean horarioFuncionamento = dataConsulta.getHour() < 7 || dataConsulta.getHour() > 18;

        if (domingo || horarioFuncionamento){
            throw new ValidacaoExcecao("Consulta fora do horario de atendimento da clinica");
        }
    }



}
