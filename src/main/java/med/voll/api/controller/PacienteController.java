package med.voll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

     @Autowired
     private PacienteRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPacientesDTO dados, UriComponentsBuilder uribuilder){

        var paciente = new Paciente(dados);

        repository.save(paciente);

        //uri para pegar o endereço
        var uri = uribuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPacienteDTO(paciente));
    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemPacienteDTO>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPacienteDTO::new);

        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarPacienteDTO dados){
        var paciente = repository.getReferenceById(dados.id());

        //verificar se os campos nao estão null
        paciente.atualizarInformacoesPacientes(dados);
        repository.save(paciente);

        return ResponseEntity.ok(new DadosListagemPacienteDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inativarPaciente(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.inativarpaciente();
        repository.save(paciente);

        return ResponseEntity.noContent().build();


    }

}
