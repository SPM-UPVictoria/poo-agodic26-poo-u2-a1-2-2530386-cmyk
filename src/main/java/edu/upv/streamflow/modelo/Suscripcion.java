package edu.upv.streamflow.modelo;

import edu.upv.streamflow.excepciones.SuscripcionInactivaException;
import edu.upv.streamflow.excepciones.TransaccionRechazadaException;
import edu.upv.streamflow.interfaces.Cancelable;
import edu.upv.streamflow.interfaces.Facturable;

public abstract class Suscripcion implements Facturable, Cancelable {

    protected String codigoContrato;
    protected double tarifaBase;
    protected boolean activo;

    public Suscripcion(String codigoContrato, double tarifaBase) {
        if (codigoContrato == null || codigoContrato.isBlank()) {
            throw new IllegalArgumentException("El codigo de contrato no puede estar vacio");
        }
        if (tarifaBase < 0) {
            throw new IllegalArgumentException("La tarifa base no puede ser negativa");
        }
        this.codigoContrato = codigoContrato;
        this.tarifaBase = tarifaBase;
        this.activo = true;
    }

    public String getCodigoContrato() {
        return codigoContrato;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    @Override
    public void cancelarServicio() {
        verificarActiva();
        activo = false;
    }

    @Override
    public boolean estaActivo() {
        return activo;
    }

    @Override
    public void procesarCobro(Cliente cliente) throws TransaccionRechazadaException {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        verificarActiva();
        double monto = calcularMontoFacturable();
        cliente.debitarSaldo(monto);
    }

    @Override
    public abstract double calcularMontoFacturable();

    protected void verificarActiva() {
        if (!activo) {
            throw new SuscripcionInactivaException(
                    "La suscripcion " + codigoContrato + " esta dada de baja");
        }
    }
}
