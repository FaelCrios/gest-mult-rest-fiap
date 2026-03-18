package infrastructure.controllers;

import application.usecases.restaurante.*;
import domain.entities.Restaurante;
import domain.entities.Usuario;
import infrastructure.controllers.dtos.RestauranteRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid; // Para validar o DTO
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
@Tag(name = "Restaurante", description = "CRUD completo de restaurantes")
public class RestauranteController {

    private final CadastrarRestauranteUseCase cadastrarUseCase;
    private final BuscarRestaurantesUseCase listarUseCase;
    private final BuscarRestaurantePorIdUseCase buscarPorIdUseCase;
    private final AtualizarRestauranteUseCase atualizarUseCase;
    private final DeletarRestauranteUseCase deletarUseCase;

    public RestauranteController(CadastrarRestauranteUseCase cadastrarUseCase,
                                 BuscarRestaurantesUseCase listarUseCase,
                                 BuscarRestaurantePorIdUseCase buscarPorIdUseCase,
                                 AtualizarRestauranteUseCase atualizarUseCase,
                                 DeletarRestauranteUseCase deletarUseCase) {
        this.cadastrarUseCase = cadastrarUseCase;
        this.listarUseCase = listarUseCase;
        this.buscarPorIdUseCase = buscarPorIdUseCase;
        this.atualizarUseCase = atualizarUseCase;
        this.deletarUseCase = deletarUseCase;
    }

    @PostMapping
    @Operation(summary = "Criar Restaurante")
    public ResponseEntity<Restaurante> criar(@RequestBody @Valid RestauranteRequest request) {
        Restaurante dominio = new Restaurante();
        dominio.setNome(request.nome());
        dominio.setEndereco(request.endereco());
        dominio.setTipoCozinha(request.tipoCozinha());
        dominio.setHorarioFuncionamento(request.horarioFuncionamento());

        Usuario dono = new Usuario();
        dono.setId(request.donoId());
        dominio.setDono(dono);

        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarUseCase.executar(dominio));
    }

    @GetMapping
    @Operation(summary = "Listar todos")
    public ResponseEntity<List<Restaurante>> listar() {
        return ResponseEntity.ok(listarUseCase.executar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID")
    public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarPorIdUseCase.executar(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Restaurante")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteRequest request) {
        Restaurante dominio = new Restaurante();
        dominio.setNome(request.nome());
        dominio.setEndereco(request.endereco());
        dominio.setTipoCozinha(request.tipoCozinha());
        dominio.setHorarioFuncionamento(request.horarioFuncionamento());

        return ResponseEntity.ok(atualizarUseCase.executar(id, dominio));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Restaurante")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}