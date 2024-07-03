package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoExcecao;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void Validar(DadosAgendamentoConsultaDTO dados) {
        var idMedico = dados.idMedico();
        var data = dados.data();

        boolean medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(idMedico, data);

        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoExcecao("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }
}