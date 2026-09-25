package com.finanlice.controller;


import com.finanlice.model.ReceitaModel;
import com.finanlice.service.ReceitaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService){
        this.receitaService = receitaService;
    }


    @PostMapping
    public ReceitaModel salvar (@RequestBody ReceitaModel receita){
        return receitaService.salvar(receita);
    }

    @GetMapping
    public List<ReceitaModel> listarTodas(){
        return receitaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ReceitaModel buscarPorId(@PathVariable Long id) {
        return receitaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ReceitaModel atualizar(
            @PathVariable Long id,
            @RequestBody ReceitaModel novaReceita) {

        return receitaService.atualizar(id, novaReceita);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        receitaService.deletar(id);
    }
}
