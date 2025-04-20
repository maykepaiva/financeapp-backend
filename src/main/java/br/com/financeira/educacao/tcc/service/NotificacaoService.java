package br.com.financeira.educacao.tcc.service;

import br.com.financeira.educacao.tcc.domain.entity.Notificacao;
import br.com.financeira.educacao.tcc.domain.entity.Usuario;
import br.com.financeira.educacao.tcc.repository.NotificacaoRepository;
import br.com.financeira.educacao.tcc.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;
    private final UsuarioRepository usuarioRepository;

    // 📌 Criar uma nova notificação
    public Notificacao criarNotificacao(Long usuarioId, String mensagem) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        Notificacao notificacao = new Notificacao();
        notificacao.setMensagem(mensagem);
        notificacao.setDataEnvio(LocalDateTime.now());
        notificacao.setEnviada(false);
        notificacao.setUsuario(usuario);

        return notificacaoRepository.save(notificacao);
    }

    // 📌 Listar todas as notificações de um usuário
    public List<Notificacao> listarNotificacoes(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        return notificacaoRepository.findByUsuarioId(usuarioId);
    }

    // 📌 Marcar notificação como enviada
    public void marcarComoEnviada(Long notificacaoId) {
        Notificacao notificacao = notificacaoRepository.findById(notificacaoId)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada!"));

        notificacao.setEnviada(true);
        notificacaoRepository.save(notificacao);
    }
}

