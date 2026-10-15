package edu.upv.streamflow.excepciones;

public class SuscripcionInactivaException extends RuntimeException {

    public SuscripcionInactivaException(String mensaje) {
        super(mensaje);
    }
}
