package edu.upv.streamflow.excepciones;

public class TransaccionRechazadaException extends Exception {

    public TransaccionRechazadaException(String mensaje) {
        super(mensaje);
    }

    public TransaccionRechazadaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
