package edu.upv.streamflow.interfaces;

import edu.upv.streamflow.excepciones.TransaccionRechazadaException;
import edu.upv.streamflow.modelo.Cliente;

public interface Facturable {

    double calcularMontoFacturable();

    void procesarCobro(Cliente cliente) throws TransaccionRechazadaException;
}
