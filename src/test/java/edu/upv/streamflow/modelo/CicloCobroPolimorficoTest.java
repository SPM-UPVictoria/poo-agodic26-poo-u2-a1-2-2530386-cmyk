package edu.upv.streamflow.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import edu.upv.streamflow.excepciones.SuscripcionInactivaException;
import edu.upv.streamflow.excepciones.TransaccionRechazadaException;
import edu.upv.streamflow.interfaces.Facturable;
import org.junit.Test;

public class CicloCobroPolimorficoTest {

    private static final double DELTA = 0.001;

    @Test
    public void deberiaProcesarCobroPolimorficoEnSuscripcionStreaming()
            throws TransaccionRechazadaException {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 1000.0);
        Facturable facturable = new SuscripcionStreaming("STR-1", 150.0, 2, 50.0);

        facturable.procesarCobro(cliente);

        assertEquals(750.0, cliente.getSaldoDisponible(), DELTA);
    }

    @Test
    public void deberiaFallarCobroPorSaldoInsuficiente() {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 50.0);
        Facturable facturable = new SuscripcionStreaming("STR-1", 150.0, 2, 50.0);

        assertThrows(TransaccionRechazadaException.class, () -> facturable.procesarCobro(cliente));

        assertEquals(50.0, cliente.getSaldoDisponible(), DELTA);
    }

    @Test
    public void deberiaLanzarExcepcionNoMarcadaAlCobrarServicioCancelado() {
        Cliente cliente = new Cliente("CLI-1", "Ana Gomez", 1000.0);
        SuscripcionStreaming sub = new SuscripcionStreaming("STR-1", 150.0, 0, 50.0);
        sub.cancelarServicio();

        assertThrows(SuscripcionInactivaException.class, () -> sub.procesarCobro(cliente));
    }

    @Test
    public void deberiaPermitirCancelarServicioActivo() {
        SuscripcionAlmacenamiento sub = new SuscripcionAlmacenamiento("ALM-1", 100.0, 50, 5.0);
        assertTrue(sub.estaActivo());

        sub.cancelarServicio();

        assertFalse(sub.estaActivo());
    }
}
