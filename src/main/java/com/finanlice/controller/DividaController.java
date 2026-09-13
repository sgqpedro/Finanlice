package com.finanlice.controller;


import com.finanlice.model.DividaModel;
import com.finanlice.service.DividaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/dividas")
public class DividaController {


    private final DividaService dividaService;

    public DividaController(DividaService dividaService){
        this.dividaService = dividaService;
    }

    @PostMapping
    public DividaModel salvar(@RequestBody DividaModel divida ) {
        return dividaService.salvar(divida);
    }

    @GetMapping
    public List<DividaModel> listarTodas(){
        return dividaService.listarTodas();
    }

    @GetMapping("/{id}")
    public DividaModel buscarPorId(@PathVariable Long id){
        return dividaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public DividaModel atualizar(@PathVariable Long id, @RequestBody DividaModel novaDivida){
        return dividaService.atualizar(id, novaDivida);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        dividaService.deletar(id);
    }



}
