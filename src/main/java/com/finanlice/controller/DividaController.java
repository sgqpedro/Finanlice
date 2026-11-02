package com.finanlice.controller;


import com.finanlice.dto.DividaRequestDTO;
import com.finanlice.dto.DividaResponseDTO;
import com.finanlice.model.DividaModel;
import com.finanlice.service.DividaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController define esta classe como controller REST e converte retornos em respostas HTTP.
@RestController
// Todas as rotas deste controller começam com /dividas.
@RequestMapping("/dividas")
public class DividaController {

    // Serviço que contém as operações e regras de negócio relacionadas às dívidas.
    private final DividaService dividaService;

    // O Spring fornece o serviço pelo construtor (injeção de dependência).
    public DividaController(DividaService dividaService){
        this.dividaService = dividaService;
    }

    // POST /dividas: valida os dados recebidos e cria uma dívida.
    @PostMapping
    public DividaResponseDTO salvar(@Valid @RequestBody DividaRequestDTO request ) {
        return dividaService.salvar(request);
    }

    // GET /dividas: retorna a lista de dívidas.
    @GetMapping
    public List<DividaResponseDTO> listarTodas(){
        return dividaService.listarTodas();
    }

    // GET /dividas/{id}: busca uma dívida pelo identificador informado na URL.
    @GetMapping("/{id}")
    public DividaResponseDTO buscarPorId(@PathVariable Long id){
        return dividaService.buscarPorId(id);
    }

    // PUT /dividas/{id}: valida os dados recebidos e atualiza a dívida indicada.
    @PutMapping("/{id}")
    public DividaResponseDTO atualizar(@PathVariable Long id,@Valid @RequestBody DividaRequestDTO request){
        return dividaService.atualizar(id, request);
    }

    // DELETE /dividas/{id}: remove a dívida indicada pelo identificador.
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        dividaService.deletar(id);
    }



}
