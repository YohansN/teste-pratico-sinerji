package org.sinerji.exception;

public class EntradaInvalidaException extends RuntimeException{
    public EntradaInvalidaException() {
        super("ERRO: Valor invalido. Tente novamente com uma opção válida: 1 a 7.");
    }
}
