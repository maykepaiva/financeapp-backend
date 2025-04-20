package br.com.financeira.educacao.tcc.controller;

import br.com.financeira.educacao.tcc.domain.entity.Notificacao;
import br.com.financeira.educacao.tcc.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notificacoes")
@RequiredArgsConstructor
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Notificacao>> listarNotificacoes(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(notificacaoService.listarNotificacoes(usuarioId));
    }
}

