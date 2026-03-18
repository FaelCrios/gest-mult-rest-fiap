package infrastructure.controllers;

import application.usecases.Usuario.*;
import domain.entities.Restaurante;
import domain.entities.Usuario;
import domain.entities.TipoUsuario;
import infrastructure.controllers.dtos.UsuarioRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuário", description = "Gerenciamento de usuários e donos")
public class UsuarioController {

    private final CriaUsuarioUseCase criaUsuarioUseCase;
    private final BuscarUsuariosUseCase buscarUsuariosUseCase;
    private final BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;
    private final AtualizarUsuarioUseCase atualizarUsuarioUseCase;
    private final DeletarUsuarioUseCase deletarUsuarioUseCase;

    public UsuarioController(CriaUsuarioUseCase criaUsuarioUseCase,
                             BuscarUsuariosUseCase buscarUsuariosUseCase, BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase, AtualizarUsuarioUseCase atualizarUsuarioUseCase, DeletarUsuarioUseCase deletarUsuarioUseCase) {
        this.criaUsuarioUseCase = criaUsuarioUseCase;
        this.buscarUsuariosUseCase = buscarUsuariosUseCase;
        this.buscarUsuarioPorIdUseCase = buscarUsuarioPorIdUseCase;
        this.atualizarUsuarioUseCase = atualizarUsuarioUseCase;
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
    }

    @PostMapping
    @Operation(summary = "Cadastrar usuário", description = "Cria um novo usuário vinculado a um tipo")
    public ResponseEntity<Usuario> criar(@RequestBody UsuarioRequest request) {
        Usuario dominio = new Usuario();
        dominio.setNome(request.nome());
        dominio.setEmail(request.email());

        TipoUsuario tipo = new TipoUsuario();
        tipo.setId(request.tipoUsuarioId());
        dominio.setTipo(tipo);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(criaUsuarioUseCase.executar(dominio));
    }

    @GetMapping
    @Operation(summary = "Listar usuários", description = "Retorna todos os usuários cadastrados")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(buscarUsuariosUseCase.executar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarUsuarioPorIdUseCase.executar(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Usuario")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioRequest request) {
        Usuario dominio = new Usuario();
        dominio.setNome(request.nome());
        dominio.setEmail(request.email());

        return ResponseEntity.ok(atualizarUsuarioUseCase.executar(id, dominio));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Usuario")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUsuarioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}