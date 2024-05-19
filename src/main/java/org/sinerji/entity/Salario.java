package org.sinerji.entity;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Salario {

    private Double valor; // Valor fixo.
    private Double bonus; // Valor que varia acordo com a quantidade de anos dentro da empresa.
    private Double salarioTotal; // Salario liquido, sem beneficio aplicado
    private float beneficio; //Porcentagem de beneficio
    private Double valorBeneficio; //Valor de beneficio
    private Double salarioComBeneficio; //Salario somado com beneficio

    public Salario(Cargo cargo, float anosContratado) {

        if(cargo == Cargo.secretario){
            this.valor = 7000d;
            this.bonus = 1000d;
            this.beneficio = 0.2f; //Sobre o salario
            this.salarioTotal = calcularSalarioTotal(valor, bonus, anosContratado);
            this.valorBeneficio = this.salarioTotal * this.beneficio;
            this.salarioComBeneficio = this.salarioTotal + this.valorBeneficio;
        }
        else if(cargo == Cargo.vendedor){
            this.valor = 12000d;
            this.bonus = 1800d;
            this.beneficio = 0.3f; //Sobre o valor vendido no mês
            this.salarioTotal = calcularSalarioTotal(valor, bonus, anosContratado);
        }
        else{
            this.valor = 20000d;
            this.bonus = 3000d;
            this.beneficio = 0f;
            this.valorBeneficio = 0d;
            this.salarioComBeneficio = 0d;
            this.salarioTotal = calcularSalarioTotal(valor, bonus, anosContratado);
        }
    }

    private Double calcularSalarioTotal(double valor, double bonus, float anosContratado){
        if(anosContratado >= 1){ //Já é contratado com 1+ anos de serviço
            return valor + bonus * (int) anosContratado;
        }else if(anosContratado < 0){ //Ainda não foi contratado
            return 0d;
        }
        return valor; //Foi contratado mas ainda não tem 1 ano de serviço.
    }

    public static Double calcularSalarioNaData(Funcionario funcionario, YearMonth dataDoPagamento){
        //dataDoPagamento - data de contratacao.
        //Calcula o total do salario dessa data e retorna.

        YearMonth dataContratacao = funcionario.getDataContratacao().getData();
        long meses = dataContratacao.until(dataDoPagamento, ChronoUnit.MONTHS);
        float anosContratado = meses / 12f;

        if(anosContratado >= 1){ //Já é contratado com 1+ anos de serviço
            return funcionario.getSalario().getValor() + funcionario.getSalario().getBonus() * (int) anosContratado;
        }else if(anosContratado < 0){ //Ainda não foi contratado
            return 0d;
        }
        return funcionario.getSalario().getValor(); //Foi contratado mas ainda não tem 1 ano de serviço.
    }

    public static Double calcularBeneficioNaData(Funcionario funcionario, YearMonth dataDoPagamento){
        //dataDoPagamento - data de contratacao.
        //Calcula o beneficio dessa data e retorna.

        if(funcionario.getCargo() == Cargo.secretario){ //Beneficio em cima do salario
            return calcularSalarioNaData(funcionario, dataDoPagamento) * funcionario.getSalario().getBeneficio(); //Pega o salario na data e multiplica pelo beneficio
        }else if(funcionario.getCargo() == Cargo.vendedor){ //Beneficio em cima das vendas do mes
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
            var dataFormatada = dataDoPagamento.format(formatter);

            double valorVendaDoMes = ((Vendedor) funcionario).getVendasPorMesOrDefaultZero(dataFormatada);
            return funcionario.getSalario().getBeneficio() * valorVendaDoMes;
        }
        else if(funcionario.getCargo() == Cargo.gerente){//Sem beneficio
            return 0d;
        }
        throw new RuntimeException("ERRO: Este cargo não é definido.");
    }

    public double getValor() {
        return valor;
    }

    public double getBonus() {
        return bonus;
    }

    public double getSalarioTotal() {
        return salarioTotal;
    }

    public float getBeneficio() {
        return beneficio;
    }

    @Override
    public String toString() {
        return "Salario{" +
                "valor=" + valor +
                ", bonus=" + bonus +
                ", salarioTotal=" + salarioTotal +
                '}';
    }
}
