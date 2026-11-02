package com.finanlice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {



    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "É necessário ter um email")
    @Email(message = "O Email deve ser válido")
    @Size(max = 250, message = "O Email deve ter no máximo 250 caracteres")
    private String email;

    @NotBlank(message = "É necessário ter uma senha")
    @Size(min = 8, max = 70, message = "A senha deve ter entre 8 e 70 caracteres")
    private String senha;


    public UsuarioRequestDTO(){
    }

    public UsuarioRequestDTO(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }
}
