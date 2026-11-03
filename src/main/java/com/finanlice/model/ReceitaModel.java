package com.finanlice.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// @Entity informa ao JPA que esta classe representa dados persistidos no banco.
@Entity
// @Table define o nome da tabela que armazenará as receitas.
@Table(name = "receitas")
public class ReceitaModel {

    // @Id marca a chave primária; o banco gera seu valor ao inserir uma receita.
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome ou descrição que identifica a receita.
    private String nome;
    // Valor recebido. BigDecimal ajuda a representar valores monetários com precisão.
    private BigDecimal valorReceita;
    // Tipo ou categoria da receita, como salário ou investimento.
    private String tipo;
    // Data e horário em que o valor foi ou será recebido.
    private LocalDateTime dataRecebimento;

    // Várias receitas podem pertencer ao mesmo usuário.
    @ManyToOne
    // A relação é armazenada na tabela receitas pela coluna usuario_id.
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;

    // Construtor vazio usado pelo JPA ao carregar uma receita do banco.
    public ReceitaModel(){}


    // Getters e Setters.

        public Long getId(){
            return id;
        }

        public void setId(Long Id){
            this.id = id;
        }
        public String getNome(){
            return nome;
        }

        public void setNome(String nome){
            this.nome = nome;
        }

        public BigDecimal getValorReceita(){
            return valorReceita;
        }

        public void setValorReceita(BigDecimal valorReceita){
            this.valorReceita = valorReceita;
        }

        public String getTipo(){
            return tipo;
        }

        public void setTipo(String tipo){
            this.tipo = tipo;
        }

        public LocalDateTime getDataRecebimento(){
            return dataRecebimento;
        }

        public void setDataRecebimento(LocalDateTime dataRecebimento){
            this.dataRecebimento = dataRecebimento;
        }

        public UsuarioModel getUsuario(){
            return usuario;
        }

        public void setUsuario(UsuarioModel usuario){
            this.usuario = usuario;
        }


}
