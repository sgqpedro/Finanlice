package com.finanlice.service;


import com.finanlice.model.DividaModel;
import com.finanlice.repository.DividaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DividaService {


    private final DividaRepository dividaRepository;

    public DividaService(DividaRepository dividaRepository) {
        this.dividaRepository = dividaRepository;
    }

    public DividaModel salvar(DividaModel divida) {
        return dividaRepository.save(divida);
    }

    public List<DividaModel> listarTodas(){
        return dividaRepository.findAll();
    }

    public DividaModel buscarPorId(Long id){return dividaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Dívida não encontrada"));
    }

    public DividaModel atualizar(Long id, @NonNull DividaModel novaDivida) {
        DividaModel dividaExistente = dividaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dívida não encontrada"));

        dividaExistente.setDescricao(novaDivida.getDescricao());
        dividaExistente.setValor(novaDivida.getValor());
        dividaExistente.setValorPago(novaDivida.getValorPago());
        dividaExistente.setCategoria(novaDivida.getCategoria());
        dividaExistente.setVencimento(novaDivida.getCategoria());
        dividaExistente.setStatus(novaDivida.getStatus());

        return dividaRepository.save(dividaExistente);
    }

    public void deletar (Long id){
        if (!dividaRepository.existsById(id)){
            throw new RuntimeException("Dívida não encontrada");
        }

        dividaRepository.deleteById(id);
    }
}
