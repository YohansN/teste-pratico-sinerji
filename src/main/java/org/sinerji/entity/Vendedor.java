package org.sinerji.entity;

import java.util.Map;

public class Vendedor extends Funcionario{
    private Map<String, Double> vendasPorMes;

    public Vendedor(Map<String, Double> vendasPorMes) {
        this.vendasPorMes = vendasPorMes;
    }

    public Vendedor(String nome, Salario salario, DataContratacao dataContratacao, Map<String, Double> vendasPorMes) {
        super(nome, Cargo.vendedor, salario, dataContratacao);
        this.vendasPorMes = vendasPorMes;
    }

    public Map<String, Double> getVendasPorMes() {
        return vendasPorMes;
    }

    public Double getVendasPorMes(String data) {
        if (vendasPorMes.containsKey(data))
            return vendasPorMes.get(data);
        return 0d;
    }

    public void setVendasPorMes(Map<String, Double> vendasPorMes) {
        this.vendasPorMes = vendasPorMes;
    }

    @Override
    public String toString() {
        return super.toString() + ", vendasPorMes=" + vendasPorMes +'}';
    }

}

