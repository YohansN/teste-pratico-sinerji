package org.sinerji;

import java.time.YearMonth;
import java.util.List;

public class Service {
    // Classe destinada a implementar os métodos requisitados pelo teste prático.

    /*
        Método 01: Um método que receba uma lista de funcionários, mês e ano e retorne o valor total
        pago (salário e benefício) a esses funcionários no mês.
    */
    static public Double valorTotalPagoNoMes(List<Funcionario> funcionarios, String mes, String ano){

        YearMonth dataDoPagamento = YearMonth.of(Integer.parseInt(ano), Integer.parseInt(mes));
        Double valorDeSalariosEBeneficiosTotais = 0d;
        Double beneficiosTotais = 0d;
        Double salariosTotais = 0d;

        for(Funcionario funcionario : funcionarios){
            var salarioLiquido = Salario.calcularSalarioNaData(funcionario, dataDoPagamento);
            if(salarioLiquido > 0){ //Pular essa etapa caso o funcionario ainda não tenha entrado na empresa.
                salariosTotais += salarioLiquido;
                beneficiosTotais += Salario.calcularBeneficioNaData(funcionario, dataDoPagamento);
            }
        }
        valorDeSalariosEBeneficiosTotais = salariosTotais + beneficiosTotais;
        return valorDeSalariosEBeneficiosTotais;
    }


    /*
        Método 02: Um método que receba uma lista de funcionários,
        mês e ano e retorne o total pago somente em salários no mês
    */
    static public Double valorSalarialTotalPagoNoMes(List<Funcionario> funcionarios, String mes, String ano){
        YearMonth dataDoPagamento = YearMonth.of(Integer.parseInt(ano), Integer.parseInt(mes));

        Double totalPagoNoMes = 0d;
        for(Funcionario funcionario : funcionarios){
            Double totalPagoNaData = Salario.calcularSalarioNaData(funcionario, dataDoPagamento);
            totalPagoNoMes+= totalPagoNaData;
        }
        return totalPagoNoMes;
    }

    /*
        Método 03: Um método que receba uma lista somente com os funcionários que recebem
        benefícios, mês e ano e retorne o total pago em benefícios no mês
    */
    static public Double totalDeBeneficiosMensal(List<Funcionario> funcionarios, String mes, String ano){
        YearMonth dataDoPagamento = YearMonth.of(Integer.parseInt(ano), Integer.parseInt(mes));
        Double valorTotalEmBonificacaoMensal = 0d;

        for(Funcionario funcionario : funcionarios){
            valorTotalEmBonificacaoMensal += Salario.calcularBeneficioNaData(funcionario, dataDoPagamento);
        }

        return valorTotalEmBonificacaoMensal;
    }

    /*
        Método 04: Um método que receba uma lista de funcionários,
        mês e ano e retorne o que recebeu o valor mais alto no mês.
    */
    static public String maiorSalarioDoMes(List<Funcionario> funcionarios, String mes, String ano){
        YearMonth dataDoPagamento = YearMonth.of(Integer.parseInt(ano), Integer.parseInt(mes));
        double valorInicial = 0d;
        String funcionarioComMaiorSalarioDoMes = "";

        for(Funcionario funcionario : funcionarios){
            Double salarioDoFuncionarioNaqueleMes = Salario.calcularSalarioNaData(funcionario, dataDoPagamento);
            if(salarioDoFuncionarioNaqueleMes >= valorInicial){
                funcionarioComMaiorSalarioDoMes = funcionario.getNome();
                valorInicial = salarioDoFuncionarioNaqueleMes;
            }
        }
        return funcionarioComMaiorSalarioDoMes + " - Salário: R$:" + valorInicial;
    }

    /*
        Método 05: Um método que receba uma lista somente com os funcionários que recebem
        benefícios, mês e ano e retorne o nome do funcionário que recebeu o valor mais
        alto em benefícios no mês
    */
    static public Funcionario maiorBeneficiadoDoMes(List<Funcionario> funcionarios, String mes, String ano){
        YearMonth dataDoPagamento = YearMonth.of(Integer.parseInt(ano), Integer.parseInt(mes));
        Double valorInicial = 0d;
        Funcionario funcionarioComMaisBeneficios = new Funcionario();
        for(Funcionario funcionario : funcionarios){
            Double aux = Salario.calcularBeneficioNaData(funcionario, dataDoPagamento);
            if(valorInicial < aux){
                funcionarioComMaisBeneficios = funcionario;
                valorInicial = aux;
            }
        }
        return funcionarioComMaisBeneficios;
    }


    /*
        Método 06: Um método que receba uma lista de vendedores, mês e ano e retorne o que mais vendeu no mês.
    */
    static public String vendedorDoMes(List<Funcionario> vendedores, String mes, String ano){
        double valorInicial = 0d;
        String vendedorDoMes = "";
        String dataFormatada = mes + "/" + ano;

        for(Funcionario vendedor : vendedores){
            double valorVenda = ((Vendedor) vendedor).getVendasPorMes(dataFormatada);
            if(valorVenda >= valorInicial){
                vendedorDoMes = vendedor.getNome();
                valorInicial = valorVenda;
            }
        }
        return vendedorDoMes;
    }
}
