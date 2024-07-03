package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoExcecao;
import med.voll.api.domain.consulta.validacoes.IvalidaCancelamento;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadoresDeAgendamento;
    @Autowired
    private  List<IvalidaCancelamento> validadoresDeCancelamento;

    public DadosDetalhamentoConsultaDTO agendar(DadosAgendamentoConsultaDTO dados){
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoExcecao("Id do paciente não encontrado");
        }
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoExcecao("Id do médico não encontrado");
        }
        validadoresDeAgendamento.forEach(v -> v.Validar(dados));

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = escolherMedico(dados);
        if (medico == null){
            throw new ValidacaoExcecao("não existe medico disponivel nesta data");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data(), dados.cancelar());
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsultaDTO(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsultaDTO dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null){
            throw new ValidacaoExcecao("Especialidade não encontrada, verifique se está digitando corretamente ORTOPEDIA,\n" +
                    "        CARDIOLOGIA,\n" +
                    "        GINECOLOGIA,\n" +
                    "        DERMATOLOGIA.");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());
    }

    public void cancelarConsulta(DadosCancelamentoConsultaDTO dados) {
       if (!consultaRepository.existsById(dados.id())) {
            throw new ValidacaoExcecao("Não existe consulta para este iD " + dados.id());
        }
       validadoresDeCancelamento.forEach(v->  v.ValidarCancelamento(dados));

        var consulta = consultaRepository.getReferenceById(dados.id());

        consulta.cancelar(dados.motivo());


    }
}
