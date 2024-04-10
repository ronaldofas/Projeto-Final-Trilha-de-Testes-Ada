package com.ada.model.entity;

import com.ada.helpers.enums.TipoClienteEnum;

import java.time.LocalDate;

public class Cliente {
    private String id;
    private TipoClienteEnum tipo;
    private String nome;
    private LocalDate dataCadastro;
    private boolean status;

    public Cliente(String id, TipoClienteEnum tipo, String nome) {
        validar(id, tipo, nome);
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.dataCadastro = LocalDate.now();
        this.status = true;
    }

    private void validar(String id, TipoClienteEnum tipo, String nome) {
        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("O ID não pode ser nulo ou vazio!");
        }
        if (tipo == null){
            throw new IllegalArgumentException("O Tipo não pode ser nulo!");
        }
        if (nome == null || nome.trim().equals("")){
            throw new IllegalArgumentException("O Nome não pode ser nulo ou vazio!");
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TipoClienteEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoClienteEnum tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void alterarNome(String nome) {
        if (nome == null || nome.trim().equals("")){
            throw new IllegalArgumentException("O Nome não pode ser nulo ou vazio!");
        }
        this.nome = nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public boolean isStatus() {
        return status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void alterarStatus(){
        this.status = !this.status;
    }

    @Override
    public String toString() {
        return "Id: " + getId() + " - Tipo: " + getTipo().toString() + " - Nome: " + getNome() +
                " - Data de Cadastro: " + getDataCadastro() + " - Ativo: " + isStatus();
    }
}
