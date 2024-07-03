package med.voll.api.domain.paciente;
import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.DadosAtualizacaoMedicoDTO;

@Entity
@Table (name = "Pacientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private boolean ativo;

   @Embedded
    private Endereco endereco;

   public Paciente(DadosCadastroPacientesDTO dados){
       this.ativo = true;
       this.nome = dados.nome();
       this.email = dados.email();
       this.telefone = dados.telefone();
       this.cpf = dados.cpf();
       this.endereco = new Endereco(dados.endereco());

   }

    public void atualizarInformacoesPacientes(DadosAtualizarPacienteDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }


    public void inativarpaciente() {
       this.ativo = false;

    }
}
