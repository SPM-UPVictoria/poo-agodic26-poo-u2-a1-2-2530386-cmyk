package edu.upv.streamflow.modelo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SuscripcionStreamingFamiliarTest {

    private static final double DELTA = 0.001;

    @Test
    public void deberiaBonificarUnicoPerfilAdicional() {
        SuscripcionStreamingFamiliar sub = new SuscripcionStreamingFamiliar("FAM-1", 200.0, 1, 60.0);

        double monto = sub.calcularMontoFacturable();

        assertEquals(200.0, monto, DELTA);
    }

    @Test
    public void deberiaCobrarPerfilesExcedentesDespuesDeBonificar() {
        SuscripcionStreamingFamiliar sub = new SuscripcionStreamingFamiliar("FAM-1", 200.0, 3, 60.0);

        double monto = sub.calcularMontoFacturable();

        assertEquals(320.0, monto, DELTA);
    }

    @Test
    public void deberiaMantenerTarifaBaseSiNoHayPerfilesExtra() {
        SuscripcionStreamingFamiliar sub = new SuscripcionStreamingFamiliar("FAM-1", 200.0, 0, 60.0);

        double monto = sub.calcularMontoFacturable();

        assertEquals(200.0, monto, DELTA);
    }
}
