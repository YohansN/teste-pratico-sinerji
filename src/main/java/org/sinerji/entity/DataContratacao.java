package org.sinerji.entity;

import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class DataContratacao {
    private int mes;
    private int ano;
    private YearMonth data;
    private float tempoNaEmpresa;

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

    public float getTempoNaEmpresa() {
        return tempoNaEmpresa;
    }

    private float tempoTotalDeContratacao(YearMonth dataContratacao){ //Calcula quantos anos o funcionario tem contratado.
        YearMonth dataAtual = YearMonth.now();
        long mesesContratado = dataContratacao.until(dataAtual, ChronoUnit.MONTHS);
        float anosContratado = mesesContratado / 12f;
        return anosContratado;
    }

    @Override
    public String toString() {
        return mes+"/"+ano;
    }
}
