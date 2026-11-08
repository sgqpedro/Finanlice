package com.finanlice.model;

import jakarta.persistence.*;

import java.util.List;

// @Entity informa ao JPA que esta classe representa usuários armazenados no banco.
@Entity
// @Table define o nome da tabela associada aos usuários.
@Table(name = "usuarios")

public class UsuarioModel {

    // @Id marca a chave primária; o banco gera o identificador ao inserir o usuário.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    // Senha usada para autenticação. A aplicação deve armazená-la de forma segura.
    @Column(nullable = false)
    private String senha;

    // Nome do usuário.
    private String nome;

    // Endereço de e-mail do usuário.
    @Column (nullable = false, unique = true)
    private String email;

    // Lista de dívidas deste usuário. mappedBy indica que a relação é definida
    // pelo campo "usuario" em DividaModel.
    @OneToMany(mappedBy = "usuario")
    private List<DividaModel> dividas;

    // Lista de receitas deste usuário. A relação é definida pelo campo
    // "usuario" em ReceitaModel.
    @OneToMany(mappedBy = "usuario")
    private List<ReceitaModel> receitas;

    // Construtor vazio usado pelo JPA ao carregar um usuário do banco.
    public UsuarioModel(){}

    // Construtor para criar um usuário informando seus dados principais.
    public UsuarioModel(Long id, String nome, String email, String senha){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
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

    public void setEmail (String email){
        this.email = email;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }



}
