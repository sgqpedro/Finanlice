package com.finanlice.service;


import com.finanlice.dto.DividaRequestDTO;
import com.finanlice.dto.DividaResponseDTO;
import com.finanlice.model.DividaModel;
import com.finanlice.model.UsuarioModel;
import com.finanlice.repository.DividaRepository;
import com.finanlice.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

// @Service marca esta classe como componente que concentra as regras de negócio das dívidas.
@Service
public class DividaService {

    // Repositórios usados para salvar e consultar dívidas e usuários no banco.
    private final DividaRepository dividaRepository;
    private final UsuarioRepository usuarioRepository;

    // O Spring fornece os repositórios pelo construtor (injeção de dependência).
    public DividaService
            (DividaRepository dividaRepository,
                UsuarioRepository usuarioRepository) {
        this.dividaRepository = dividaRepository;
        this.usuarioRepository = usuarioRepository;
    }


    // Cria uma dívida após conferir o usuário associado e validar o valor pago.
    public DividaResponseDTO salvar(DividaRequestDTO request) {

        // Procura o usuário informado; se não existir, responde com erro HTTP 404.
        UsuarioModel usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));

        // Quando o valor pago não é informado, considera que ainda não houve pagamento.
        BigDecimal valorPago = request.getValorPago() == null
                ? BigDecimal.ZERO : request.getValorPago();

        // Impede que o total pago seja maior que o valor da dívida.
        validarPagamento(request.getValor(), valorPago);

        DividaModel divida = new DividaModel();

        divida.setDescricao(request.getDescricao());
        divida.setValor(request.getValor());
        divida.setValorPago(valorPago);
        divida.setCategoria(request.getCategoria());
        divida.setVencimento(request.getVencimento());
        divida.setUsuario(usuario);

        // Define a situação com base no pagamento e na data de vencimento.
        divida.setStatus(calcularStatus(request.getValor(), valorPago, request.getVencimento()));

        // Persiste a dívida e transforma o resultado no DTO usado na resposta.
        DividaModel salva = dividaRepository.save(divida);

        return converteParaDTO(salva);

    }

    // Busca todas as dívidas e converte cada uma para o formato de resposta.
    public List<DividaResponseDTO> listarTodas(){
        return dividaRepository.findAll()
                .stream()
                .map(this::converteParaDTO)
                .toList();
    }

    // Busca uma dívida pelo identificador ou responde com erro HTTP 404 se não existir.
    public DividaResponseDTO buscarPorId(Long id){

        DividaModel divida = dividaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Dívida não encontrada"));

            return converteParaDTO(divida);
    }

    // Atualiza os dados de uma dívida existente depois de validar pagamento e usuário.
    public DividaResponseDTO atualizar(Long id, DividaRequestDTO request) {

        DividaModel dividaExistente = dividaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "Dívida não encontrada"));

        // Se o pagamento não vier na requisição, considera o valor como zero.
        BigDecimal valorPago = request.getValorPago() == null
                ? BigDecimal.ZERO : request.getValorPago();


        // Garante que o valor pago não ultrapasse o valor total da dívida.
        validarPagamento(request.getValor(), valorPago);

        UsuarioModel usuario = usuarioRepository.findById(request.getUsuarioId())
                        .orElseThrow(() -> new ResponseStatusException
                                (HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        dividaExistente.setUsuario(usuario);
        dividaExistente.setDescricao(request.getDescricao());
        dividaExistente.setValor(request.getValor());
        dividaExistente.setValorPago(valorPago);
        dividaExistente.setCategoria(request.getCategoria());
        dividaExistente.setVencimento(request.getVencimento());

        dividaExistente.setStatus(calcularStatus(
                request.getValor(),
                valorPago,
                request.getVencimento()
        ));

        // Salva as alterações e converte o resultado para o DTO de resposta.
        DividaModel atualizada = dividaRepository.save(dividaExistente);

        return converteParaDTO(atualizada);
    }

    // Exclui a dívida; se o identificador não existir, responde com erro HTTP 404.
    public void deletar (Long id){
        if (!dividaRepository.existsById(id)){
            throw new ResponseStatusException
                    (HttpStatus.NOT_FOUND, "Dívida não encontrada");
        }

        dividaRepository.deleteById(id);
    }


    // Valida que o valor já pago não seja maior que o valor total da dívida.
    private void validarPagamento(BigDecimal valor, BigDecimal valorPago){
         if (valorPago.compareTo(valor) > 0 ){
             throw new ResponseStatusException (HttpStatus.BAD_REQUEST, "O valor não pode ser maior que a dívida"
             );
         }
    }

    // Calcula a situação da dívida: paga, atrasada, parcial ou pendente.
    private String calcularStatus(BigDecimal valor, BigDecimal valorPago, LocalDate vencimento){
        if (valorPago.compareTo(valor) == 0 ){
            return "PAGA";
        }

        if (vencimento.isBefore(LocalDate.now())){
            return "ATRASADA";
        }

        if (valorPago.compareTo(BigDecimal.ZERO) > 0){
            return "PARCIAL";
        }

        return "PENDENTE";
    }

    // Copia os dados da entidade para o DTO e calcula o valor que ainda falta pagar.
    private DividaResponseDTO converteParaDTO (DividaModel divida) {
        return new DividaResponseDTO(
                divida.getId(),
                divida.getDescricao(),
                divida.getValor(),
                divida.getValorPago(),
                divida.getValor().subtract(divida.getValorPago()),
                divida.getCategoria(),
                divida.getVencimento(),
                divida.getStatus(),
                divida.getUsuario().getId()
               // divida.getValorRestante()
        );
    }


}
