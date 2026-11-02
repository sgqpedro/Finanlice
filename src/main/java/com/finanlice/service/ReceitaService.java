package com.finanlice.service;


import com.finanlice.model.ReceitaModel;
import com.finanlice.repository.ReceitaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// @Service marca esta classe como componente que executa operações relacionadas às receitas.
@Service
public class ReceitaService {

    // Repositório usado para salvar, buscar e excluir receitas no banco.
    private final ReceitaRepository receitaRepository;

    // O Spring fornece o repositório pelo construtor (injeção de dependência).
    public ReceitaService(ReceitaRepository receitaRepository){
        this.receitaRepository = receitaRepository;}

    // Salva uma nova receita no banco e retorna a entidade salva.
    public ReceitaModel salvar(ReceitaModel receita){
        return receitaRepository.save(receita);
    }

    // Busca e retorna todas as receitas armazenadas.
    public List<ReceitaModel> listarTodas(){
        return receitaRepository.findAll();
    }

    // Busca uma receita pelo identificador; lança uma exceção se não for encontrada.
    public ReceitaModel buscarPorId(Long id){
        return receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

    }

    // Atualiza os dados de uma receita existente e salva as alterações.
    public ReceitaModel atualizar(Long id, ReceitaModel novaReceita) {

        // Procura o registro atual antes de alterar seus dados.
        ReceitaModel receitaExistente = receitaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));

        // Copia para a entidade existente os valores recebidos na atualização.
        receitaExistente.setNome(novaReceita.getNome());
        receitaExistente.setValorReceita(novaReceita.getValorReceita());
        receitaExistente.setTipo(novaReceita.getTipo());
        receitaExistente.setDataRecebimento(novaReceita.getDataRecebimento());

        // Persiste a entidade atualizada e devolve o resultado.
        return receitaRepository.save(receitaExistente);
    }


    // Exclui a receita indicada; se ela não existir, responde com erro HTTP 404.
    public void deletar(Long id){
        if (!receitaRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada");
        }

        receitaRepository.deleteById(id);
    }


}
