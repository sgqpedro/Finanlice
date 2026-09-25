package com.finanlice.service;


import com.finanlice.model.ReceitaModel;
import com.finanlice.repository.ReceitaRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    public ReceitaService(ReceitaRepository receitaRepository){
        this.receitaRepository = receitaRepository;}

    public ReceitaModel salvar(ReceitaModel receita){
        return receitaRepository.save(receita);
    }

    public List<ReceitaModel> listarTodas(){
        return receitaRepository.findAll();
    }

    public ReceitaModel buscarPorId(Long id){
        return receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

    }

    public ReceitaModel atualizar(Long id, ReceitaModel novaReceita) {

        ReceitaModel receitaExistente = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        receitaExistente.setNome(novaReceita.getNome());
        receitaExistente.setValorReceita(novaReceita.getValorReceita());
        receitaExistente.setTipo(novaReceita.getTipo());
        receitaExistente.setDataRecebimento(novaReceita.getDataRecebimento());

        return receitaRepository.save(receitaExistente);
    }


    public void deletar(Long id){
        if (!receitaRepository.existsById(id)){
            throw new RuntimeException("Receita não encontrada");
        }
    }


}
