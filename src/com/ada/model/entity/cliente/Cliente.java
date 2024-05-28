package com.ada.model.entity.cliente;

import com.ada.model.entity.interfaces.conta.Identificador;
import com.ada.model.helpers.enums.Classificacao;

import java.time.LocalDate;

public class Cliente {
    private final Identificador<String> id;
    private final Classificacao classificacao;
    private String nome;
    private final LocalDate dataCadastro;
    private boolean status;

    public Cliente(final Identificador<String> id, final Classificacao classificacao, final String nome) {
        validar(id.getValor(), classificacao, nome);
        this.id = id;
        this.classificacao = classificacao;
        this.nome = nome;
        this.dataCadastro = LocalDate.now();
        this.status = true;
        validar(this.id.getValor(), this.classificacao, this.nome);
    }

    public void alterarNome(final String nome) {
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O Nome não pode ser nulo ou vazio!");
        }
        this.nome = nome;
    }

    public void ativarDesativar(){
        this.status = !this.status;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public String getIdentificador() {
        return id.getValor();
    }

    public String getNome() {
        return nome;
    }

    public Identificador<String> getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    private void validar(final String id, final Classificacao tipo, final String nome) {
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("O ID não pode ser nulo ou vazio!");
        }
        if (tipo == null){
            throw new IllegalArgumentException("O Tipo não pode ser nulo!");
        }
        if (nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O Nome não pode ser nulo ou vazio!");
        }
    }

    @Override
    public String toString() {
        return "Id: " + getIdentificador() + " - Tipo: " + getClassificacao().toString() + " - Nome: " + getNome() +
                " - Data de Cadastro: " + getDataCadastro() + " - Ativo: " + isStatus();
    }
}
