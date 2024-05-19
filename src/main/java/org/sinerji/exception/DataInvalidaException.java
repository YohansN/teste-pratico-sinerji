package org.sinerji.exception;

public class DataInvalidaException extends RuntimeException{

    public DataInvalidaException() {
        super("ERRO: A data buscada é invalida. \n Tente novamente.");
    }

}
