package model;

import helpers.enums.TipoClienteEnum;

import java.time.LocalDate;

public class Cliente {
    private String id;
    private TipoClienteEnum tipo;
    private String nome;
    private LocalDate dataCadastro;
    private boolean status;

    public Cliente(String id, TipoClienteEnum tipo, String nome) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.dataCadastro = LocalDate.now();
        this.status = true;
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
        this.nome = nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public boolean isStatus() {
        return status;
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
