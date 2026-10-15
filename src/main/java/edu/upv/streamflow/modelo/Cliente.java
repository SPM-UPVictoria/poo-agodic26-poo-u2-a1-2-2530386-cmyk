package edu.upv.streamflow.modelo;

import edu.upv.streamflow.excepciones.TransaccionRechazadaException;

public class Cliente {

    private String idCliente;
    private String nombre;
    private double saldoDisponible;

    public Cliente(String idCliente, String nombre, double saldoInicial) {
        if (idCliente == null || idCliente.isBlank()) {
            throw new IllegalArgumentException("El id del cliente no puede estar vacio");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.saldoDisponible = saldoInicial;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void abonarSaldo(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a abonar debe ser mayor a cero");
        }
        saldoDisponible += monto;
    }

    public void debitarSaldo(double monto) throws TransaccionRechazadaException {
        if (monto < 0) {
            throw new IllegalArgumentException("El monto a debitar no puede ser negativo");
        }
        if (saldoDisponible < monto) {
            throw new TransaccionRechazadaException("Saldo insuficiente para el cobro");
        }
        saldoDisponible -= monto;
    }
}
