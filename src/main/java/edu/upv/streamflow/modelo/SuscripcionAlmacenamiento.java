package edu.upv.streamflow.modelo;

public class SuscripcionAlmacenamiento extends Suscripcion {

    private int gigabytesConsumidos;
    private int limiteGratuitoGB;
    private double costoPorGBExtra;

    public SuscripcionAlmacenamiento(String codigoContrato, double tarifaBase,
                                     int limiteGratuitoGB, double costoPorGBExtra) {
        super(codigoContrato, tarifaBase);
        if (limiteGratuitoGB < 0) {
            throw new IllegalArgumentException("El limite gratuito no puede ser negativo");
        }
        if (costoPorGBExtra < 0) {
            throw new IllegalArgumentException("El costo por GB extra no puede ser negativo");
        }
        this.limiteGratuitoGB = limiteGratuitoGB;
        this.costoPorGBExtra = costoPorGBExtra;
        this.gigabytesConsumidos = 0;
    }

    public int getGigabytesConsumidos() {
        return gigabytesConsumidos;
    }

    public void registrarConsumo(int totalGB) {
        if (totalGB < 0) {
            throw new IllegalArgumentException("El consumo no puede ser negativo");
        }
        verificarActiva();
        this.gigabytesConsumidos = totalGB;
    }

    @Override
    public double calcularMontoFacturable() {
        int excedente = Math.max(0, gigabytesConsumidos - limiteGratuitoGB);
        return tarifaBase + excedente * costoPorGBExtra;
    }
}
