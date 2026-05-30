package com.empresa.members.config.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String tipo, Object id) {
        super("%s com id %s não encontrado.".formatted(tipo, id));
    }
}
