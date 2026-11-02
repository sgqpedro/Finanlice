
package com.finanlice.dto;

// DTO (Data Transfer Object) que organiza os dados do usuário enviados na resposta da API.
// A resposta inclui o identificador, o nome e o e-mail do usuário.
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;

    // Construtor vazio disponível para frameworks que precisam criar o DTO sem argumentos.
    public UsuarioResponseDTO() {
    }

    // Construtor para montar a resposta com os dados do usuário.
    public UsuarioResponseDTO (Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
