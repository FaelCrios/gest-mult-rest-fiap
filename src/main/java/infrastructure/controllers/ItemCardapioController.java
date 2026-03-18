package infrastructure.controllers;

import application.usecases.ItemCardapio.*;
import domain.entities.ItemCardapio;
import domain.entities.Restaurante;
import infrastructure.controllers.dtos.ItemCardapioAtualizarRequest;
import infrastructure.controllers.dtos.ItemCardapioRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens-cardapio")
@Tag(name = "Cardápio", description = "Gestão de itens do cardápio")
public class ItemCardapioController {

    private final AdicionarItemCardapioUseCase adicionarItemCardapioUseCase;
    private final AtualizarItemCardapioUseCase atualizarItemCardapioUseCase;
    private final BuscarItemCardapioPoIdUseCase buscarItemCardapioPoIdUseCase;
    private final DeletarItemCardapioUseCase deletarItemCardapioUseCase;
    private final ListarTodosItemCardapioUseCase listarTodosItemCardapioUseCase;

    public ItemCardapioController(AdicionarItemCardapioUseCase adicionarItemCardapioUseCase, AtualizarItemCardapioUseCase atualizarItemCardapioUseCase, BuscarItemCardapioPoIdUseCase buscarItemCardapioPoIdUseCase, DeletarItemCardapioUseCase deletarItemCardapioUseCase, ListarTodosItemCardapioUseCase listarTodosItemCardapioUseCase) {
        this.adicionarItemCardapioUseCase = adicionarItemCardapioUseCase;
        this.atualizarItemCardapioUseCase = atualizarItemCardapioUseCase;
        this.buscarItemCardapioPoIdUseCase = buscarItemCardapioPoIdUseCase;
        this.deletarItemCardapioUseCase = deletarItemCardapioUseCase;
        this.listarTodosItemCardapioUseCase = listarTodosItemCardapioUseCase;
    }

    @PostMapping
    @Operation(summary = "Adicionar item", description = "Cadastra um prato em um restaurante específico")
    public ResponseEntity<ItemCardapio> adicionar(@RequestBody ItemCardapioRequest request) {
        ItemCardapio dominio = new ItemCardapio();
        dominio.setNome(request.nome());
        dominio.setDescricao(request.descricao());
        dominio.setPreco(request.preco());
        dominio.setDisponivelApenasNoRestaurante(request.disponivelApenasNoRestaurante());
        dominio.setCaminhoFoto(request.caminhoFoto());

        Restaurante restaurante = new Restaurante();
        restaurante.setId(request.restauranteId());
        dominio.setRestauranteId(restaurante.getId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adicionarItemCardapioUseCase.executar(dominio, request.restauranteId()));
    }

    @GetMapping
    @Operation(summary = "Listar todos")
    public ResponseEntity<List<ItemCardapio>> listar() {
        return ResponseEntity.ok(listarTodosItemCardapioUseCase.executar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID")
    public ResponseEntity<ItemCardapio> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarItemCardapioPoIdUseCase.executar(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar item no cardápio")
    public ResponseEntity<ItemCardapio> atualizar(@PathVariable Long id, @RequestBody @Valid ItemCardapioAtualizarRequest request) {
        ItemCardapio dominio = new ItemCardapio();
        dominio.setNome(request.nome());
        dominio.setDescricao(request.descricao());
        dominio.setPreco(request.preco());
        dominio.setDisponivelApenasNoRestaurante(request.disponivelApenasNoRestaurante());
        dominio.setCaminhoFoto(request.caminhoFoto());

        return ResponseEntity.ok(atualizarItemCardapioUseCase.executar(id, dominio));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar item no cardápio")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarItemCardapioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}