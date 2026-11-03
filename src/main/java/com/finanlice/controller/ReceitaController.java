package com.finanlice.controller;
import com.finanlice.dto.ReceitaRequestDTO;
import com.finanlice.dto.ReceitaResponseDTO;
import com.finanlice.service.ReceitaService;
import jakarta.validation.Valid;
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

    // POST /receitas: recebe e valida os dados da receita pelo DTO de requisição.
    @PostMapping
    public ReceitaResponseDTO salvar(@Valid @RequestBody ReceitaRequestDTO request){
        return receitaService.salvar(request);
    }

    // GET /receitas: retorna a lista de receitas no formato do DTO de resposta.
    @GetMapping
    public List<ReceitaResponseDTO> listarTodas(){
        return receitaService.listarTodas();
    }

    // GET /receitas/{id}: busca uma receita pelo identificador e devolve o DTO de resposta.
    @GetMapping("/{id}")
    public ReceitaResponseDTO buscarPorId(@PathVariable Long id) {
        return receitaService.buscarPorId(id);
    }

    // PUT /receitas/{id}: recebe e valida os novos dados pelo DTO de requisição.
    @PutMapping("/{id}")
    public ReceitaResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ReceitaRequestDTO request) {

        return receitaService.atualizar(id, request);
    }

    // DELETE /receitas/{id}: remove a receita indicada pelo identificador da URL.
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        receitaService.deletar(id);
    }
}
