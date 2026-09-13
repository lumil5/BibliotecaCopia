package org.biblioteca.exception;

public class PersonaAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PersonaAlreadyExistsException(String message) {
        super(message);
    }
}
