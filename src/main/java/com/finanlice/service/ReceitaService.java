package com.finanlice.service;

import com.finanlice.dto.ReceitaRequestDTO;
import com.finanlice.dto.ReceitaResponseDTO;
import com.finanlice.model.ReceitaModel;
import com.finanlice.model.UsuarioModel;
import com.finanlice.repository.ReceitaRepository;
import com.finanlice.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final UsuarioRepository usuarioRepository;

    public ReceitaService(ReceitaRepository receitaRepository, UsuarioRepository usuarioRepository) {
        this.receitaRepository = receitaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ReceitaResponseDTO salvar(ReceitaRequestDTO request) {
        UsuarioModel usuario = buscarUsuario(request.getUsuarioId());

        ReceitaModel receita = new ReceitaModel();
        preencherReceita(receita, request, usuario);

        return converterParaDTO(receitaRepository.save(receita));
    }

    public List<ReceitaResponseDTO> listarTodas() {
        return receitaRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public ReceitaResponseDTO buscarPorId(Long id) {
        ReceitaModel receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));
        return converterParaDTO(receita);
    }

    public ReceitaResponseDTO atualizar(Long id, ReceitaRequestDTO request) {
        ReceitaModel receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));
        UsuarioModel usuario = buscarUsuario(request.getUsuarioId());

        preencherReceita(receita, request, usuario);
        return converterParaDTO(receitaRepository.save(receita));
    }

    public void deletar(Long id) {
        if (!receitaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada");
        }
        receitaRepository.deleteById(id);
    }

    private UsuarioModel buscarUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    private void preencherReceita(ReceitaModel receita, ReceitaRequestDTO request, UsuarioModel usuario) {
        receita.setNome(request.getNome());
        receita.setValorReceita(request.getValorReceita());
        receita.setTipo(request.getTipo());
        receita.setDataRecebimento(request.getDataRecebimento());
        receita.setUsuario(usuario);
    }

    private ReceitaResponseDTO converterParaDTO(ReceitaModel receita) {
        return new ReceitaResponseDTO(
                receita.getId(),
                receita.getNome(),
                receita.getValorReceita(),
                receita.getTipo(),
                receita.getDataRecebimento(),
                receita.getUsuario() == null ? null : receita.getUsuario().getId()
        );
    }
}
