package com.finanlice.controller;

import com.finanlice.dto.UsuarioRequestDTO;
import com.finanlice.dto.UsuarioResponseDTO;

import com.finanlice.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// @RestController define esta classe como controller REST e converte retornos em respostas HTTP.
@RestController
// Todas as rotas deste controller começam com /usuarios.
@RequestMapping("/usuarios")
public class UsuarioController {

    // Serviço que executa as operações relacionadas aos usuários.
    private final UsuarioService usuarioService;

    // O Spring fornece o serviço pelo construtor (injeção de dependência).
    public UsuarioController (UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // POST /usuarios: valida os dados recebidos e cria um usuário.
    @PostMapping
    public UsuarioResponseDTO salvar(@Valid @RequestBody UsuarioRequestDTO request){
        return usuarioService.salvar(request);
    }

    // GET /usuarios: retorna a lista de usuários.
    @GetMapping
    public List<UsuarioResponseDTO> listarTodos(){
        return usuarioService.listarTodos();
    }

    // GET /usuarios/{id}: busca um usuário pelo identificador informado na URL.
    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id);
    }

    // PUT /usuarios/{id}: valida os dados recebidos e atualiza o usuário indicado.
    @PutMapping("/{id}")
    public UsuarioResponseDTO atualizar(@PathVariable Long id,@Valid @RequestBody UsuarioRequestDTO request){

        return usuarioService.atualizar(id,request);
    }

    // DELETE /usuarios/{id}: remove o usuário indicado pelo identificador.
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        usuarioService.deletar(id);
    }



}
