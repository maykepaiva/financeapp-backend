package br.com.financeira.educacao.tcc.controller;

import br.com.financeira.educacao.tcc.domain.dto.RelatorioDTO;
import br.com.financeira.educacao.tcc.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/mensal/{usuarioId}")
    public ResponseEntity<List<RelatorioDTO>> getResumoMensal(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(relatorioService.gerarResumoMensal(usuarioId));
    }
}
