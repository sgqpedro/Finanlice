package com.finanlice.controller;

import com.finanlice.model.UsuarioModel;
import com.finanlice.service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController (UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioModel salvar(@RequestBody UsuarioModel usuario){
        return usuarioService.salvar(usuario);
    }


    @GetMapping
    public List<UsuarioModel> listarTodos(){
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public UsuarioModel buscarPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public UsuarioModel atualizar(@PathVariable Long id, @RequestBody UsuarioModel novoUsuario){

        return usuarioService.atualizar(id,novoUsuario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        usuarioService.deletar(id);
    }



}
