package br.com.financeira.educacao.tcc.controller;

import br.com.financeira.educacao.tcc.domain.dto.MetaFinanceiraDTO;
import br.com.financeira.educacao.tcc.domain.entity.MetaFinanceira;
import br.com.financeira.educacao.tcc.service.MetaFinanceiraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metas")
@RequiredArgsConstructor
public class MetaFinanceiraController {

    private final MetaFinanceiraService metaService;

    @PostMapping
    public ResponseEntity<MetaFinanceira> cadastrar(@RequestBody @Valid MetaFinanceiraDTO metaDTO) {
        MetaFinanceira meta = metaService.cadastrar(metaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(meta);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<MetaFinanceiraDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(metaService.listarPorUsuario(usuarioId));
    }

    @GetMapping("buscar/{usuarioId}")
    public ResponseEntity<List<MetaFinanceira>> listarPorMetasUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(metaService.listarPorMetasUsuario(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        metaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

