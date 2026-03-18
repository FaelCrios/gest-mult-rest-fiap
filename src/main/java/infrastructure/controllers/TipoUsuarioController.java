package infrastructure.controllers;

import application.usecases.TipoUsuario.BuscarTiposUsuarioUseCase;
import application.usecases.TipoUsuario.CriarTipoUsuarioUseCase;
import domain.entities.TipoUsuario;
import infrastructure.controllers.dtos.TipoUsuarioRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-usuarios")
@Tag(name = "Tipo de Usuário", description = "Gerenciamento de perfis de acesso")
public class TipoUsuarioController {

    private final CriarTipoUsuarioUseCase criarTipoUsuarioUseCase;
    private final BuscarTiposUsuarioUseCase buscarTiposUsuarioUseCase;

    public TipoUsuarioController(CriarTipoUsuarioUseCase criarTipoUsuarioUseCase,
                                 BuscarTiposUsuarioUseCase buscarTiposUsuarioUseCase) {
        this.criarTipoUsuarioUseCase = criarTipoUsuarioUseCase;
        this.buscarTiposUsuarioUseCase = buscarTiposUsuarioUseCase;
    }

    @PostMapping
    @Operation(summary = "Cadastrar novo tipo", description = "Cria um perfil de usuário (ex: Dono, Cliente)")
    public ResponseEntity<TipoUsuario> criar(@RequestBody TipoUsuarioRequest request) {
        TipoUsuario dominio = new TipoUsuario();
        dominio.setNome(request.nome());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(criarTipoUsuarioUseCase.executar(dominio));
    }

    @GetMapping
    @Operation(summary = "Listar tipos", description = "Lista todos os perfis disponíveis")
    public ResponseEntity<List<TipoUsuario>> listar() {
        return ResponseEntity.ok(buscarTiposUsuarioUseCase.executar());
    }
}