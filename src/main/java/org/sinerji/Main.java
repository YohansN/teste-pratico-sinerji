package org.sinerji;

import org.sinerji.builder.FuncionarioBuilder;
import org.sinerji.entity.Cargo;
import org.sinerji.entity.Funcionario;
import org.sinerji.service.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Instanciando funcionarios
        Funcionario jorgeCarvalho = new FuncionarioBuilder()
                .setNome("Jorge Carvalho")
                .setCargo(Cargo.secretario)
                .setMesContratacao(1)
                .setAnoContratacao(2018)
                .build();

        Funcionario mariaSouza = new FuncionarioBuilder()
                .setNome("Maria Souza")
                .setCargo(Cargo.secretario)
                .setMesContratacao(12)
                .setAnoContratacao(2015)
                .build();

        Funcionario anaSilva = new FuncionarioBuilder()
                .setNome("Ana Silva")
                .setCargo(Cargo.vendedor)
                .setMesContratacao(12)
                .setAnoContratacao(2021)
                .setVendasPorMes(new HashMap<String, Double>() {{
                    put("12/2021", 5200.00);
                    put("01/2022", 4000.00);
                    put("02/2022", 4200.00);
                    put("03/2022", 5850.00);
                    put("04/2022", 7000.00);
                }})
                .build();

        Funcionario joaoMendes = new FuncionarioBuilder()
                .setNome("João Mendes")
                .setCargo(Cargo.vendedor)
                .setMesContratacao(12)
                .setAnoContratacao(2021)
                .setVendasPorMes(new HashMap<>() {{
                    put("12/2021", 3400.00);
                    put("01/2022", 7700.00);
                    put("02/2022", 5000.00);
                    put("03/2022", 5900.00);
                    put("04/2022", 6500.00);
                }})
                .build();

        Funcionario julianaAlves = new FuncionarioBuilder()
                .setNome("Juliana Alves")
                .setCargo(Cargo.gerente)
                .setMesContratacao(7)
                .setAnoContratacao(2017)
                .build();

        Funcionario bentoAlbino = new FuncionarioBuilder()
                .setNome("Bento Albino")
                .setCargo(Cargo.gerente)
                .setMesContratacao(3)
                .setAnoContratacao(2014)
                .build();

        // Lista total de funcionarios:
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(jorgeCarvalho);
        funcionarios.add(mariaSouza);
        funcionarios.add(anaSilva);
        funcionarios.add(joaoMendes);
        funcionarios.add(julianaAlves);
        funcionarios.add(bentoAlbino);

        // Lista de vendedores:
        List<Funcionario> vendedores = new ArrayList<Funcionario>();
        vendedores.add(anaSilva);
        vendedores.add(joaoMendes);

        //Lista de funcionarios que recebem benefícios:
        List<Funcionario> funcionariosComBeneficios = new ArrayList<Funcionario>();
        funcionariosComBeneficios.add(jorgeCarvalho);
        funcionariosComBeneficios.add(mariaSouza);
        funcionariosComBeneficios.add(anaSilva);
        funcionariosComBeneficios.add(joaoMendes);


        //Chamando os métodos da service:
        var q1 = Service.valorTotalPagoNoMes(funcionarios, "03", "2015");
        System.out.println(String.format("Valor mensal gasto no pagamento de salario + benefícios R$%.2f", q1 ));

        var q2 = Service.valorSalarialTotalPagoNoMes(funcionarios, "03", "2014");
        System.out.println(String.format("Total pago em salário no mês: R$%.2f", q2));

        var q3 = Service.totalDeBeneficiosMensal(funcionariosComBeneficios, "01", "2022");
        System.out.println(String.format("Valor mensal destinado aos benefícios: R$%.2f", q3));

        var q4 = Service.maiorSalarioDoMes(funcionarios, "03", "2022");
        System.out.println("Maior salário do mês: " + q4);

        var q5 = Service.maiorBeneficiadoDoMes(funcionariosComBeneficios, "03", "2021");
        System.out.println("Funcionarios com a maior quantidade de benefícios: " + q5.getNome());

        var q6 = Service.vendedorDoMes(vendedores, "12", "2021");
        System.out.println("Vendedor(a) do Mês: " + q6);
    }
}