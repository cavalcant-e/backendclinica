package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoExcecao;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorCancelamento implements IvalidaCancelamento{

    @Autowired
    private ConsultaRepository repository;

    public  void ValidarCancelamento(DadosCancelamentoConsultaDTO dados){
        var consulta = repository.getReferenceById(dados.id());
        LocalDateTime dataDaConsulta = consulta.getData();
        LocalDateTime agora = LocalDateTime.now();
        Long diferenca = Duration.between(agora, dataDaConsulta).toHours();

        if (diferenca < 24){
            throw new ValidacaoExcecao("Não é possivel cancelar consulta com menos de 24hrs");
        }


    }
}
