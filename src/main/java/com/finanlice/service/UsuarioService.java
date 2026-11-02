package com.finanlice.service;

import com.finanlice.dto.UsuarioRequestDTO;
import com.finanlice.dto.UsuarioResponseDTO;
import com.finanlice.model.UsuarioModel;
import com.finanlice.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// @Service marca esta classe como componente que concentra as operações de usuários.
@Service
public class UsuarioService {

    // Repositório usado para salvar e consultar usuários no banco.
    private final UsuarioRepository usuarioRepository;

    // O Spring fornece o repositório pelo construtor (injeção de dependência).
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    // Cria um usuário a partir dos dados recebidos e devolve sua versão de resposta.
    public UsuarioResponseDTO salvar(UsuarioRequestDTO request){
        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());

        // Persiste o usuário e converte para DTO, que não inclui a senha na resposta.
        UsuarioModel salvo = usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    // Busca todos os usuários e converte cada entidade para o formato de resposta.
    public List<UsuarioResponseDTO> listarTodos(){
        return usuarioRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    // Busca um usuário pelo identificador ou responde com erro HTTP 404 se não existir.
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

    // Atualiza os dados de um usuário existente e devolve a resposta sem a senha.
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO request){
        // Procura o usuário atual antes de substituir seus dados.
        UsuarioModel usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        usuarioExistente.setNome(request.getNome());
        usuarioExistente.setEmail(request.getEmail());
        usuarioExistente.setSenha(request.getSenha());

        // Salva as alterações no banco.
        UsuarioModel atualizado = usuarioRepository.save(usuarioExistente);

        return converterParaDTO(atualizado);

    }

    // Exclui o usuário; se o identificador não existir, responde com erro HTTP 404.
    public void deletar(Long id){
        if (!usuarioRepository.existsById(id)){
            throw new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Usuário não encontrado");

        }
        
        usuarioRepository.deleteById(id);
    }

    // Converte a entidade para DTO, expondo id, nome e e-mail, mas não a senha.
    private UsuarioResponseDTO converterParaDTO(UsuarioModel usuario){
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
