// src/controller/InvestimentoController.java

package br.com.financeira.educacao.tcc.controller;

import br.com.financeira.educacao.tcc.domain.dto.InvestimentoDTO;
import br.com.financeira.educacao.tcc.service.InvestimentoService;
import br.com.financeira.educacao.tcc.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/investimentos")
@RequiredArgsConstructor
public class InvestimentoController {

    private final InvestimentoService investimentoService;
    private final JwtService jwtService;

    @PostMapping("/{metaId}/{armazenamentoId}")
    public ResponseEntity<InvestimentoDTO> salvar(HttpServletRequest request, @RequestBody InvestimentoDTO investimento,
                                                  @PathVariable Long metaId,
                                                  @PathVariable Long armazenamentoId) {
        String token = request.getHeader("Authorization").substring(7);
        Long usuarioId = jwtService.getUsuarioIdFromToken(token);
        InvestimentoDTO novo = investimentoService.salvar(investimento, metaId, armazenamentoId);
        return ResponseEntity.ok(novo);
    }

    @GetMapping("/meta/{metaId}")
    public ResponseEntity<List<InvestimentoDTO>> listarPorMeta(@PathVariable Long metaId) {
        return ResponseEntity.ok(investimentoService.listarPorMeta(metaId));
    }

    @GetMapping("/meta")
    public ResponseEntity<List<InvestimentoDTO>> listarTodosInvestimentos() {
        return ResponseEntity.ok(investimentoService.listarTodosInvestimentos());
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<InvestimentoDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(investimentoService.listarPorUsuario(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        investimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
