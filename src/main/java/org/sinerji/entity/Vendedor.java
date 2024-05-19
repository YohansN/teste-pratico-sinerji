package org.sinerji.entity;

import org.sinerji.exception.SemRegistroDeVendaException;

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

    private Double getVendasPorMes(String data) {
        if (vendasPorMes.containsKey(data)) {
            return vendasPorMes.get(data);
        }
        return null; // Retorna null se a chave não for encontrada
    }

    public Double getVendasPorMesOrThrow(String data) {
        Double resultado = getVendasPorMes(data);
        if (resultado == null) {
            throw new SemRegistroDeVendaException(data);
        }
        return resultado;
    }

    public Double getVendasPorMesOrDefaultZero(String data) {
        Double resultado = getVendasPorMes(data);
        if (resultado == null) {
            return 0d;
        }
        return resultado;
    }

    public void setVendasPorMes(Map<String, Double> vendasPorMes) {
        this.vendasPorMes = vendasPorMes;
    }

    @Override
    public String toString() {
        return super.toString() + ", vendasPorMes=" + vendasPorMes +'}';
    }

}

