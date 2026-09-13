package org.biblioteca.exception;

public class PersonaNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PersonaNotFoundException(String message) {
        super(message);
    }
}
