package com.finanlice.service;


import com.finanlice.dto.DividaRequestDTO;
import com.finanlice.dto.DividaResponseDTO;
import com.finanlice.model.DividaModel;
import com.finanlice.model.UsuarioModel;
import com.finanlice.repository.DividaRepository;
import com.finanlice.repository.UsuarioRepository;
import org.apache.coyote.Response;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
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


        BigDecimal valorPago = request.getValorPago() == null
                ? BigDecimal.ZERO : request.getValorPago();

        validarPagamento(request.getValor(), valorPago);

        DividaModel divida = new DividaModel();

        divida.setDescricao(request.getDescricao());
        divida.setValor(request.getValor());
        divida.setValorPago(request.getValorPago());
        divida.setCategoria(request.getCategoria());
        divida.setVencimento(request.getVencimento());
        divida.setUsuario(usuario);

        divida.setStatus(calcularStatus(request.getValor(), valorPago, request.getVencimento()));

        DividaModel salva = dividaRepository.save(divida);

        return converteParaDTO(salva);

    }

    public List<DividaResponseDTO> listarTodas(){
        return dividaRepository.findAll()
                .stream()
                .map(this::converteParaDTO)
                .toList();
    }

    public DividaResponseDTO buscarPorId(Long id){
        DividaModel divida = dividaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Dívida não encontrada"));

            return converteParaDTO(divida);
    }

    public DividaResponseDTO atualizar(Long id, DividaRequestDTO request) {

        DividaModel dividaExistente = dividaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Dívida não encontrada"));

        BigDecimal valorPago = request.getValorPago() == null
                ? BigDecimal.ZERO : request.getValorPago();


        validarPagamento(request.getValor(), valorPago);

        UsuarioModel usuario = usuarioRepository.findById(request.getUsuarioId())
                        .orElseThrow(() -> new ResponseStatusException
                                (HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        dividaExistente.setDescricao(request.getDescricao());
        dividaExistente.setValor(request.getValor());
        dividaExistente.setValorPago(request.getValorPago());
        dividaExistente.setCategoria(request.getCategoria());
        dividaExistente.setVencimento(request.getVencimento());

        dividaExistente.setStatus(calcularStatus(
                request.getValor(),
                valorPago,
                request.getVencimento()
        ));

        DividaModel atualizada = dividaRepository.save(dividaExistente);

        return converteParaDTO(atualizada);
    }

    public void deletar (Long id){
        if (!dividaRepository.existsById(id)){
            throw new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Dívida não encontrada");
        }

        dividaRepository.deleteById(id);
    }


    private void validarPagamento(BigDecimal valor, BigDecimal valorPago){
         if (valorPago.compareTo(valor) > 0 ){
             throw new ResponseStatusException (HttpStatus.BAD_REQUEST, "O valor não pode ser maior que a dívida"
             );
         }
    }

    private String calcularStatus(BigDecimal valor, BigDecimal valorPago, LocalDate vencimento){
        if (valorPago.compareTo(valor) == 0 ){
            return "PAGA";
        }

        if (vencimento.isBefore(LocalDate.now())){
            return "ATRASADA";
        }

        if (valor.compareTo(valor) > 0){
            return "PARCIAL";
        }

        return "PENDENTE";
    }

    private DividaResponseDTO converteParaDTO(DividaModel divida){
        return new DividaResponseDTO(
                divida.getId(), divida.getDescricao(), divida.getValor(),
                divida.getValorPago(), divida.getCategoria(), divida.getVencimento(), divida.getStatus(), divida.getUsuario().getId()
        );
    }


}
