package com.finanlice.service;

import com.finanlice.model.UsuarioModel;
import com.finanlice.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioModel salvar(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioModel> listarTodos(){
        return usuarioRepository.findAll();
    }

    public UsuarioModel buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public UsuarioModel atualizar(Long id, UsuarioModel novoUsuario){
        UsuarioModel usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuarioExistente.setNome(novoUsuario.getNome());
        usuarioExistente.setEmail(novoUsuario.getEmail());
        usuarioExistente.setSenha(novoUsuario.getSenha());

        return usuarioRepository.save(usuarioExistente);
    }

}
