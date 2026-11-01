package com.finanlice.controller;


import com.finanlice.dto.DividaRequestDTO;
import com.finanlice.dto.DividaResponseDTO;
import com.finanlice.model.DividaModel;
import com.finanlice.service.DividaService;
import jakarta.validation.Valid;
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
    public DividaResponseDTO salvar(@Valid @RequestBody DividaRequestDTO request ) {
        return dividaService.salvar(request);
    }

    @GetMapping
    public List<DividaResponseDTO> listarTodas(){
        return dividaService.listarTodas();
    }

    @GetMapping("/{id}")
    public DividaResponseDTO buscarPorId(@PathVariable Long id){
        return dividaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public DividaResponseDTO atualizar(@PathVariable Long id,@Valid @RequestBody DividaRequestDTO request){
        return dividaService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        dividaService.deletar(id);
    }



}
