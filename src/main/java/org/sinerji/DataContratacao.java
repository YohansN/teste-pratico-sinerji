package org.example;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class DataContratacao {
    private int mes;
    private int ano;
    private YearMonth data;
    private int tempoNaEmpresa;

    public DataContratacao(int mes, int ano) {
        this.mes = mes;
        this.ano = ano;
        this.data = YearMonth.of(ano, mes);
        this.tempoNaEmpresa = tempoTotalDeContratacao(this.data);
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public YearMonth getData() {
        return data;
    }

    public int getTempoNaEmpresa() {
        return tempoNaEmpresa;
    }

    private int tempoTotalDeContratacao(YearMonth dataContratacao){ //Calcula quantos anos o funcionario tem contratado.
        YearMonth dataAtual = YearMonth.now();
        long anosContratado = dataContratacao.until(dataAtual, ChronoUnit.YEARS);
        return (int) anosContratado;
    }

    @Override
    public String toString() {
        return mes+"/"+ano;
    }
}
