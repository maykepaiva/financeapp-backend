package br.com.financeira.educacao.tcc.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoDTO {
    private String mensagem;
    private LocalDateTime dataEnvio;
    private Boolean enviada;
}

