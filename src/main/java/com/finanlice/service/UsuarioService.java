package com.finanlice.service;

import com.finanlice.dto.UsuarioRequestDTO;
import com.finanlice.dto.UsuarioResponseDTO;
import com.finanlice.model.UsuarioModel;
import com.finanlice.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDTO salvar(UsuarioRequestDTO request){
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());

        UsuarioModel salvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    public List<UsuarioResponseDTO> listarTodos(){
        return usuarioRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }



    public UsuarioResponseDTO buscarPorId(Long id){

        UsuarioModel usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Usuário não encontrado"));

                return new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail()
                );

    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO request){
        UsuarioModel usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        usuarioExistente.setNome(request.getNome());
        usuarioExistente.setEmail(request.getEmail());
        usuarioExistente.setSenha(request.getSenha());

            UsuarioModel atualizado = usuarioRepository.save(usuarioExistente);

        return converterParaDTO(atualizado);

    }

    public void deletar(Long id){
        if (!usuarioRepository.existsById(id)){
            throw new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Usuário não encontrado");

        }
        
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDTO converterParaDTO(UsuarioModel usuario){
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
