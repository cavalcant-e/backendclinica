package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoExcecao;
import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private MedicoRepository repository;

    public void Validar (DadosAgendamentoConsultaDTO dados){

        if (dados.idMedico()==null){
            return;
        }
        Long medicoId =  dados.idMedico();
        boolean medicoAtivo = repository.findAtivoById(medicoId);

        if (!medicoAtivo){
            throw new ValidacaoExcecao("Medico nao está ativo");
        }



    }

}
