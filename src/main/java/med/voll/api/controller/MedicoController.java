package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicosDTO dados, UriComponentsBuilder uribuilder){
        var medico = new Medico(dados);
        repository.save(medico);

        //uri para pegar o endereço
        var uri = uribuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medico));
    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);

        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar (@RequestBody @Valid DadosAtualizacaoMedicoDTO dados){
        var medico = repository.getReferenceById(dados.id());

        //metodo para verificar se não está vindo null
        medico.atualizarInformacoes(dados);
        repository.save(medico);

        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));

    }

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity Excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.inativar();
        repository.save(medico);

        return ResponseEntity.noContent().build();

    }

    @GetMapping ("/{id}")
    public ResponseEntity detalhar (@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));



    }


}
