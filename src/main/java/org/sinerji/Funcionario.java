package org.sinerji;

public class Funcionario{
    private String nome;
    private Cargo cargo;
    private Salario salario;
    private DataContratacao dataContratacao;

    public Funcionario() {}

    public Funcionario(String nome, Cargo cargo, Salario salario, DataContratacao dataContratacao) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
    }

    public String getNome() {
        return nome;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Salario getSalario() {
        return salario;
    }

    public DataContratacao getDataContratacao() {
        return dataContratacao;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", cargo=" + cargo +
                ", salario=" + salario +
                ", dataContratacao=" + dataContratacao +
                '}';
    }
}
