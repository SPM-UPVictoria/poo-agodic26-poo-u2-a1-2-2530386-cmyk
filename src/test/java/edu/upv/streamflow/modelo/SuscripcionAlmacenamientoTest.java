package edu.upv.streamflow.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class SuscripcionAlmacenamientoTest {

    private static final double DELTA = 0.001;

    @Test
    public void deberiaCobrarTarifaBaseDentroDelLimiteGratuito() {
        SuscripcionAlmacenamiento sub = new SuscripcionAlmacenamiento("ALM-1", 100.0, 50, 5.0);
        sub.registrarConsumo(30);

        double monto = sub.calcularMontoFacturable();

        assertEquals(100.0, monto, DELTA);
    }

    @Test
    public void deberiaCobrarTarifaBaseEnElLimiteExacto() {
        SuscripcionAlmacenamiento sub = new SuscripcionAlmacenamiento("ALM-1", 100.0, 50, 5.0);
        sub.registrarConsumo(50);

        double monto = sub.calcularMontoFacturable();

        assertEquals(100.0, monto, DELTA);
    }

    @Test
    public void deberiaCalcularExcedenteDeAlmacenamiento() {
        SuscripcionAlmacenamiento sub = new SuscripcionAlmacenamiento("ALM-1", 100.0, 50, 5.0);
        sub.registrarConsumo(70);

        double monto = sub.calcularMontoFacturable();

        assertEquals(200.0, monto, DELTA);
    }

    @Test
    public void deberiaLanzarExcepcionAlRegistrarConsumoNegativo() {
        SuscripcionAlmacenamiento sub = new SuscripcionAlmacenamiento("ALM-1", 100.0, 50, 5.0);

        assertThrows(IllegalArgumentException.class, () -> sub.registrarConsumo(-15));
    }
}
