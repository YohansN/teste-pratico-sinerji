package org.sinerji.builder;

import org.sinerji.entity.*;

import java.util.Map;

public class FuncionarioBuilder {
    /*
    Devido a alta dependencia entre as classes (DataContratacao -> Salario -> Funcionario)
    é necessário uma classe builder para facilitar sua construção.
    */
    private String nome;
    private Cargo cargo;
    private int mesContratacao;
    private int anoContratacao;
    private Map<String, Double> vendasPorMes;

    public FuncionarioBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public FuncionarioBuilder setCargo(Cargo cargo) {
        this.cargo = cargo;
        return this;
    }

    public FuncionarioBuilder setMesContratacao(int mes) {
        this.mesContratacao = mes;
        return this;
    }

    public FuncionarioBuilder setAnoContratacao(int ano) {
        this.anoContratacao = ano;
        return this;
    }

    public FuncionarioBuilder setVendasPorMes(Map<String, Double> vendasPorMes) {
        this.vendasPorMes = vendasPorMes;
        return this;
    }

    public Funcionario build() {
        DataContratacao dataContratacao = new DataContratacao(mesContratacao, anoContratacao);
        Salario salario = new Salario(cargo, dataContratacao.getTempoNaEmpresa());

        if (cargo == Cargo.vendedor) {
            return new Vendedor(nome, salario, dataContratacao, vendasPorMes);
        } else {
            return new Funcionario(nome, cargo, salario, dataContratacao);
        }
    }
}
