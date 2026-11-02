package com.finanlice.controller;


import com.finanlice.model.ReceitaModel;
import com.finanlice.service.ReceitaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController define esta classe como controller REST e converte retornos em respostas HTTP.
@RestController
// Todas as rotas deste controller começam com /receitas.
@RequestMapping("/receitas")
public class ReceitaController {

    // Serviço que executa as operações relacionadas às receitas.
    private final ReceitaService receitaService;

    // O Spring fornece o serviço pelo construtor (injeção de dependência).
    public ReceitaController(ReceitaService receitaService){
        this.receitaService = receitaService;
    }

    // POST /receitas: recebe os dados de uma receita e solicita sua criação.
    @PostMapping
    public ReceitaModel salvar (@RequestBody ReceitaModel receita){
        return receitaService.salvar(receita);
    }

    // GET /receitas: retorna a lista de receitas.
    @GetMapping
    public List<ReceitaModel> listarTodas(){
        return receitaService.listarTodas();
    }

    // GET /receitas/{id}: busca uma receita pelo identificador informado na URL.
    @GetMapping("/{id}")
    public ReceitaModel buscarPorId(@PathVariable Long id) {
        return receitaService.buscarPorId(id);
    }

    // PUT /receitas/{id}: recebe os novos dados e atualiza a receita indicada.
    @PutMapping("/{id}")
    public ReceitaModel atualizar(
            @PathVariable Long id,
            @RequestBody ReceitaModel novaReceita) {

        return receitaService.atualizar(id, novaReceita);
    }

    // DELETE /receitas/{id}: remove a receita indicada pelo identificador.
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        receitaService.deletar(id);
    }
}
