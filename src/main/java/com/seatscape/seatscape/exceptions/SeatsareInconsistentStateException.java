package com.seatscape.seatscape.exceptions;

public class SeatsareInconsistentStateException extends Exception {
    public SeatsareInconsistentStateException() {
        super();
    }

    public SeatsareInconsistentStateException(String message) {
        super(message);
    }

    public SeatsareInconsistentStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeatsareInconsistentStateException(Throwable cause) {
        super(cause);
    }
}
