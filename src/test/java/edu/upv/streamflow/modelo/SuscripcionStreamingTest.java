package edu.upv.streamflow.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class SuscripcionStreamingTest {

    private static final double DELTA = 0.001;

    @Test
    public void deberiaCalcularTarifaSinPerfilesExtra() {
        SuscripcionStreaming sub = new SuscripcionStreaming("STR-1", 150.0, 0, 50.0);

        double monto = sub.calcularMontoFacturable();

        assertEquals(150.0, monto, DELTA);
    }

    @Test
    public void deberiaCalcularTarifaConPerfilesExtra() {
        SuscripcionStreaming sub = new SuscripcionStreaming("STR-1", 150.0, 2, 50.0);

        double monto = sub.calcularMontoFacturable();

        assertEquals(250.0, monto, DELTA);
    }

    @Test
    public void deberiaIncrementarCostoAlAgregarPerfil() {
        SuscripcionStreaming sub = new SuscripcionStreaming("STR-1", 150.0, 1, 50.0);
        double antes = sub.calcularMontoFacturable();

        sub.agregarPerfil();

        assertEquals(antes + 50.0, sub.calcularMontoFacturable(), DELTA);
        assertEquals(250.0, sub.calcularMontoFacturable(), DELTA);
    }

    @Test
    public void deberiaLanzarExcepcionSiPerfilesInicialesEsNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new SuscripcionStreaming("STR-1", 150.0, -2, 50.0));
    }
}
