package br.com.financeira.educacao.tcc.controller;

import br.com.financeira.educacao.tcc.domain.dto.DespesaDTO;
import br.com.financeira.educacao.tcc.domain.entity.Despesa;
import br.com.financeira.educacao.tcc.service.DespesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/despesas")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService despesaService;

    @PostMapping
    public ResponseEntity<DespesaDTO> cadastrar(@RequestBody @Valid DespesaDTO despesaDTO) {
        DespesaDTO despesa = despesaService.cadastrar(despesaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<DespesaDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(despesaService.buscarDespesasPorUsuario(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerReceita(@PathVariable Long id) {
        despesaService.removerReceita(id);
        return ResponseEntity.noContent().build();
    }
}

