package org.sinerji;

import org.sinerji.builder.FuncionarioBuilder;
import org.sinerji.entity.Cargo;
import org.sinerji.entity.Funcionario;
import org.sinerji.exception.EntradaInvalidaException;
import org.sinerji.service.Service;

import java.util.*;

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

        menu(funcionarios, funcionariosComBeneficios, vendedores);
    }

    public static void menu(List<Funcionario> funcionarios, List<Funcionario> funcionariosComBeneficios, List<Funcionario> vendedores){
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        System.out.println(
                "  ___ ___ _  _ ___ ___    _ ___ \n" +
                " / __|_ _| \\| | __| _ \\_ | |_ _|\n" +
                " \\__ \\| || .` | _||   / || || | \n" +
                " |___/___|_|\\_|___|_|_\\\\__/|___|\n" +
                " +===========================================+\n" +
                " | SOFTWARE DE GESTÃO FINANCEIRA EMPRESARIAL |\n" +
                " +===========================================+");
        while(continuar){
            System.out.println("\nOpções de busca:\n" +
                    "1 - Valor total pago (salário + Beneficio) de todos os funcionários em determinada data.\n" +
                    "2 - Total pago em salário a todos os funcionários em determinada data.\n" +
                    "3 - Total pago em benefícios a todos os funcionários em determinada data.\n" +
                    "4 - Funcionário com maior pagamento em determinada data.\n" +
                    "5 - Funcionário com maior valor em benefício em determinada data.\n" +
                    "6 - Vendedor com maior valor em vendas em determinada data.\n" +
                    "7 - SAIR.\n" +
                    "Digite um número de 1 a 7 para escolher.");
            int option = 0;
            try {
                option = scanner.nextInt();
            }catch (InputMismatchException ex){
                throw new EntradaInvalidaException();
            }

            switch (option){
                case 1:
                    System.out.print("Opção escolhida: \nValor total pago (salário + Beneficio) de todos os funcionários em determinada data.\nDigite o mês: ");
                    int mes = scanner.nextInt();
                    System.out.print("Digite o ano: ");
                    int ano = scanner.nextInt();
                    var q1 = Service.valorTotalPagoNoMes(funcionarios, mes, ano);
                    System.out.printf("Valor mensal gasto no pagamento de salario + benefícios: R$%.2f%n", q1 );
                    break;
                case 2:
                    System.out.print("Opção escolhida: \nTotal pago em salário a todos os funcionários em determinada data. \nDigite o mês: ");
                    mes = scanner.nextInt();
                    System.out.print("Digite o ano: ");
                    ano = scanner.nextInt();
                    var q2 = Service.valorSalarialTotalPagoNoMes(funcionarios, mes, ano);
                    System.out.printf("Total pago em salário no mês: R$%.2f%n", q2);
                    break;
                case 3:
                    System.out.print("Opção escolhida: \nTotal pago em benefícios a todos os funcionários em determinada data. \nDigite o mês: ");
                    mes = scanner.nextInt();
                    System.out.print("Digite o ano: ");
                    ano = scanner.nextInt();
                    var q3 = Service.totalDeBeneficiosMensal(funcionariosComBeneficios, mes, ano);
                    System.out.printf("Valor mensal destinado aos benefícios: R$%.2f%n", q3);
                    break;
                case 4:
                    System.out.print("Opção escolhida: \nFuncionário com maior pagamento em determinada data. \nDigite o mês: ");
                    mes = scanner.nextInt();
                    System.out.print("Digite o ano: ");
                    ano = scanner.nextInt();
                    var q4 = Service.maiorSalarioDoMes(funcionarios, mes, ano);
                    System.out.printf("Funcionário: " + q4);
                    break;
                case 5:
                    System.out.print("Opção escolhida: \nFuncionário com maior valor em benefício em determinada data. \nDigite o mês: ");
                    mes = scanner.nextInt();
                    System.out.print("Digite o ano: ");
                    ano = scanner.nextInt();
                    var q5 = Service.maiorBeneficiadoDoMes(funcionariosComBeneficios, mes, ano);
                    System.out.printf("Funcionarios com a maior quantidade de benefícios: " + q5.getNome());
                    break;
                case 6:
                    System.out.println("Opção escolhida: \nVendedor com maior valor em vendas em determinada data.\n" +
                            "* Datas disponìveis para consulta: 12/2021 à 04/2022\nDigite o mês: ");
                    mes = scanner.nextInt();
                    System.out.print("Digite o ano: ");
                    ano = scanner.nextInt();
                    var q6 = Service.vendedorDoMes(vendedores, mes, ano);
                    System.out.printf("Vendedor(a) do Mês: " + q6);
                    break;
                case 7:
                    continuar = false;
                    System.out.println("SAINDO DA APLICAÇÃO");
                    break;
                default:
                    System.out.println("Valor invalido. Tente novamente.\n");
            }
        }

    }

}