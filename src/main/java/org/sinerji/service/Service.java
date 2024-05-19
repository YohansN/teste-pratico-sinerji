package org.sinerji.service;

import org.sinerji.entity.Funcionario;
import org.sinerji.entity.Salario;
import org.sinerji.entity.Vendedor;
import org.sinerji.exception.DataInvalidaException;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Service {
    // Classe destinada a implementar os métodos requisitados pelo teste prático.

    /*
        Método 01: Um método que receba uma lista de funcionários, mês e ano e retorne o valor total
        pago (salário e benefício) a esses funcionários no mês.
    */
    static public double valorTotalPagoNoMes(List<Funcionario> funcionarios, String mes, String ano){
        YearMonth dataDoPagamento = null;
        try {
            dataDoPagamento = YearMonth.of(Integer.parseInt(ano), Integer.parseInt(mes));
        }catch (Exception ex){
            throw new DataInvalidaException();
        }

        double valorDeSalariosEBeneficiosTotais = 0d;
        double beneficiosTotais = 0d;
        double salariosTotais = 0d;

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
    static public double valorSalarialTotalPagoNoMes(List<Funcionario> funcionarios, String mes, String ano){
        YearMonth dataDoPagamento = null;
        try {
            dataDoPagamento = YearMonth.of(Integer.parseInt(ano), Integer.parseInt(mes));
        }catch (Exception ex){
            throw new DataInvalidaException();
        }

        double totalPagoNoMes = 0d;

        for(Funcionario funcionario : funcionarios){
            double totalPagoNaData = Salario.calcularSalarioNaData(funcionario, dataDoPagamento);
            totalPagoNoMes+= totalPagoNaData;
        }
        return totalPagoNoMes;
    }

    /*
        Método 03: Um método que receba uma lista somente com os funcionários que recebem
        benefícios, mês e ano e retorne o total pago em benefícios no mês
    */
    static public double totalDeBeneficiosMensal(List<Funcionario> funcionarios, String mes, String ano){
        YearMonth dataDoPagamento = null;
        try {
            dataDoPagamento = YearMonth.of(Integer.parseInt(ano), Integer.parseInt(mes));
        }catch (Exception ex){
            throw new DataInvalidaException();
        }
        double valorTotalEmBonificacaoMensal = 0d;

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
        YearMonth dataDoPagamento = null;
        try {
            dataDoPagamento = YearMonth.of(Integer.parseInt(ano), Integer.parseInt(mes));
        }catch (Exception ex){
            throw new DataInvalidaException();
        }

        double valorInicial = 0d;
        String funcionarioComMaiorPagamentoNoMes = "";

        for(Funcionario funcionario : funcionarios){
            double totalEmSalario = Salario.calcularSalarioNaData(funcionario, dataDoPagamento);
            double totalEmBeneficio = Salario.calcularBeneficioNaData(funcionario, dataDoPagamento);
            double ValorTotal = totalEmSalario + totalEmBeneficio;
            if(ValorTotal >= valorInicial){
                funcionarioComMaiorPagamentoNoMes = funcionario.getNome();
                valorInicial = ValorTotal;
            }
        }
        return funcionarioComMaiorPagamentoNoMes + " - Pagamento: R$:" + valorInicial;
    }

    /*
        Método 05: Um método que receba uma lista somente com os funcionários que recebem
        benefícios, mês e ano e retorne o nome do funcionário que recebeu o valor mais
        alto em benefícios no mês
    */
    static public Funcionario maiorBeneficiadoDoMes(List<Funcionario> funcionarios, String mes, String ano){
        YearMonth dataDoPagamento = null;
        try {
            dataDoPagamento = YearMonth.of(Integer.parseInt(ano), Integer.parseInt(mes));
        }catch (Exception ex){
            throw new DataInvalidaException();
        }

        double valorInicial = 0d;

        Funcionario funcionarioComMaisBeneficios = new Funcionario();
        for(Funcionario funcionario : funcionarios){
            double aux = Salario.calcularBeneficioNaData(funcionario, dataDoPagamento);
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
        YearMonth data = null;
        try{
            data = YearMonth.of(Integer.parseInt(ano), Integer.parseInt(mes));
        }catch (Exception ex){
            throw new DataInvalidaException();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        String dataFormatada = data.format(formatter);

        double valorInicial = 0d;
        String vendedorDoMes = "";

        for(Funcionario vendedor : vendedores){
            double valorVenda = ((Vendedor) vendedor).getVendasPorMesOrThrow(dataFormatada);
            if(valorVenda >= valorInicial){
                vendedorDoMes = vendedor.getNome();
                valorInicial = valorVenda;
            }
        }
        return vendedorDoMes;
    }
}
