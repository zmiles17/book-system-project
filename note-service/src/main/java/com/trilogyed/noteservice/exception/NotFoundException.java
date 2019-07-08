package com.trilogyed.noteservice.exception;

/**
 * Constructs a new NotFoundException with the specified message.
 */
public class NotFoundException extends RuntimeException {

    /**
     * Constructs a new NotFoundException exception
     */
    public NotFoundException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified message.
     * @param message
     */
    public NotFoundException(String message) {
        super(message);
    }
}

