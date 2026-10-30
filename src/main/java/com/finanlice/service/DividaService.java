package com.finanlice.service;


import com.finanlice.dto.DividaRequestDTO;
import com.finanlice.dto.DividaResponseDTO;
import com.finanlice.model.DividaModel;
import com.finanlice.model.UsuarioModel;
import com.finanlice.repository.DividaRepository;
import com.finanlice.repository.UsuarioRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DividaService {


    private final DividaRepository dividaRepository;
    private final UsuarioRepository usuarioRepository;

    public DividaService
            (DividaRepository dividaRepository,
                UsuarioRepository usuarioRepository) {
        this.dividaRepository = dividaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public DividaResponseDTO salvar(DividaRequestDTO request) {
        UsuarioModel usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));


        DividaModel divida = new DividaModel();
        divida.setDescricao(request.getDescricao());
        divida.setValor(request.getValor());
        divida.setValorPago(request.getValorPago());
        divida.setCategoria(request.getCategoria());
        divida.setVencimento(request.getVencimento());
        divida.setStatus(request.getStatus());
        divida.setUsuario(usuario);

        DividaModel salva = dividaRepository.save(divida);

        return new DividaResponseDTO(
                salva.getId(),
                salva.getDescricao(),
                salva.getValor(),
                salva.getValorPago(),
                salva.getCategoria(),
                salva.getVencimento(),
                salva.getStatus(),
                salva.getUsuario().getId()
        );

    }

    public List<DividaModel> listarTodas(){
        return dividaRepository.findAll();
    }

    public DividaModel buscarPorId(Long id){return dividaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Dívida não encontrada"));
    }

    public DividaModel atualizar(Long id, @NonNull DividaModel novaDivida) {
        DividaModel dividaExistente = dividaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Dívida não encontrada"));

        dividaExistente.setDescricao(novaDivida.getDescricao());
        dividaExistente.setValor(novaDivida.getValor());
        dividaExistente.setValorPago(novaDivida.getValorPago());
        dividaExistente.setCategoria(novaDivida.getCategoria());
        dividaExistente.setVencimento(novaDivida.getVencimento());
        dividaExistente.setStatus(novaDivida.getStatus());

        return dividaRepository.save(dividaExistente);
    }

    public void deletar (Long id){
        if (!dividaRepository.existsById(id)){
            throw new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Dívida não encontrada");
        }

        dividaRepository.deleteById(id);
    }
}
