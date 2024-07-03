package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosCancelamentoConsultaDTO;

public interface IvalidaCancelamento {

    void ValidarCancelamento(DadosCancelamentoConsultaDTO dados);
}
