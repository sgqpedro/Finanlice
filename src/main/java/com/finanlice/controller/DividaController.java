package com.finanlice.controller;


import com.finanlice.model.DividaModel;
import com.finanlice.service.DividaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
