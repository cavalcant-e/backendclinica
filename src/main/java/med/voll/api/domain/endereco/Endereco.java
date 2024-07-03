package med.voll.api.domain.endereco;


import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Endereco {

    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;
    private String logradouro;

    public Endereco(DadosEndereco dados) {
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.logradouro = dados.logradouro();

    }

    public void atualizarInformacoes(DadosEndereco dados) {

        if (dados.bairro() !=null) {
            this.bairro = dados.bairro();
        }
        if (dados.cep() !=null) {
            this.cep = dados.cep();
        }
        if (dados.cidade() !=null) {

            this.cidade = dados.cidade();
        }
        if (dados.uf() !=null) {

            this.uf = dados.uf();
        }
        if (dados.numero() !=null) {
            this.numero = dados.numero();
        }
        if (dados.complemento() !=null) {
            this.complemento = dados.complemento();
        }
        if (dados.cidade() !=null) {
             this.cidade = dados.cidade();
         }
        if (dados.logradouro() != null){
            this.logradouro =dados.logradouro();
        }


    }
}
