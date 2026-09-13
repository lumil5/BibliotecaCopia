package org.biblioteca.exception;

public class PersonaInvalidDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PersonaInvalidDataException(String message) {
        super(message);
    }
}
