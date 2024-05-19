package org.sinerji.exception;

public class SemRegistroDeVendaException extends RuntimeException{
    public SemRegistroDeVendaException(String data) {
        super("ERRO: Não há registros de venda na data buscada: " + data);
    }
}
