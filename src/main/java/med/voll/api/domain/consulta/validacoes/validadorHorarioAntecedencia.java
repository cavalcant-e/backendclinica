package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoExcecao;
import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;


@Component
public class validadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void Validar (DadosAgendamentoConsultaDTO dados){

        LocalDateTime dataConsulta = dados.data();
        LocalDateTime agora = LocalDateTime.now();
        Long diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30 ){

            throw  new ValidacaoExcecao("Não é possivel agendar com menos de 30 min");
        }


    }


}
