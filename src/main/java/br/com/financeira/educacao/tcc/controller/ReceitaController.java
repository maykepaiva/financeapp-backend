package br.com.financeira.educacao.tcc.controller;

import br.com.financeira.educacao.tcc.domain.dto.ReceitaDTO;
import br.com.financeira.educacao.tcc.service.ReceitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receitas")
@RequiredArgsConstructor
public class ReceitaController {

    private final ReceitaService receitaService;

    @PostMapping
    public ResponseEntity<ReceitaDTO> cadastrar(@RequestBody @Valid ReceitaDTO receitaDTO) {
        ReceitaDTO response = receitaService.cadastrar(receitaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<ReceitaDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(receitaService.buscarReceitasPorUsuario(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerReceita(@PathVariable Long id) {
        receitaService.removerReceita(id);
        return ResponseEntity.noContent().build();
    }
}
