package edu.upv.streamflow.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import edu.upv.streamflow.excepciones.TransaccionRechazadaException;
import org.junit.Test;

public class ClienteTest {

    private static final double DELTA = 0.001;

    @Test
    public void deberiaInstanciarClienteValido() {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 1500.0);

        assertEquals("CLI-1", cliente.getIdCliente());
        assertEquals("Ana Gomez", cliente.getNombre());
        assertEquals(1500.0, cliente.getSaldoDisponible(), DELTA);
    }

    @Test
    public void deberiaLanzarExcepcionPorNombreInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Cliente("CLI-1", null, 100.0));
        assertThrows(IllegalArgumentException.class, () -> new Cliente("CLI-1", "   ", 100.0));
    }

    @Test
    public void deberiaLanzarExcepcionPorSaldoInicialNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Cliente("CLI-1", "Ana Gomez", -1.0));
    }

    @Test
    public void deberiaAbonarSaldoCorrectamente() {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 1000.0);

        cliente.abonarSaldo(500.0);

        assertEquals(1500.0, cliente.getSaldoDisponible(), DELTA);
    }

    @Test
    public void deberiaLanzarExcepcionAlAbonarMontoNegativo() {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 1000.0);

        assertThrows(IllegalArgumentException.class, () -> cliente.abonarSaldo(-50.0));
    }

    @Test
    public void deberiaDebitarSaldoExitosamente() throws TransaccionRechazadaException {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 800.0);

        cliente.debitarSaldo(300.0);

        assertEquals(500.0, cliente.getSaldoDisponible(), DELTA);
    }

    @Test
    public void deberiaLanzarExcepcionPropiaAlDebitarMontoMayorAlSaldo() {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 200.0);

        assertThrows(TransaccionRechazadaException.class, () -> cliente.debitarSaldo(500.0));
    }
}
