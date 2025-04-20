package br.com.financeira.educacao.tcc.controller;

import br.com.financeira.educacao.tcc.domain.dto.ArmazenamentoDTO;
import br.com.financeira.educacao.tcc.domain.entity.Armazenamento;
import br.com.financeira.educacao.tcc.service.ArmazenamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/armazenamento")
@RequiredArgsConstructor
public class ArmazenamentoController {

    @Autowired
    private ArmazenamentoService service;

    @PostMapping
    public ResponseEntity<Armazenamento> criar(@RequestBody @Valid ArmazenamentoDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Armazenamento>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Armazenamento> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
