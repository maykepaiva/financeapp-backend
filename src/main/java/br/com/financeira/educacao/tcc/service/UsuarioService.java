package br.com.financeira.educacao.tcc.service;

import br.com.financeira.educacao.tcc.domain.dto.LoginDTO;
import br.com.financeira.educacao.tcc.domain.dto.UsuarioDTO;
import br.com.financeira.educacao.tcc.domain.entity.Usuario;
import br.com.financeira.educacao.tcc.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public Usuario cadastrar(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado!");
        }


        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public String autenticar(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Senha incorreta!");
        }

        return jwtService.generateToken(usuario.getId(),usuario.getEmail());
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }
}

