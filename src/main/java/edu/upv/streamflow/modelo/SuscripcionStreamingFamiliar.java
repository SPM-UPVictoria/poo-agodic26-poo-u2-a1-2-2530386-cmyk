package edu.upv.streamflow.modelo;

public class SuscripcionStreamingFamiliar extends SuscripcionStreaming {

    private static final int PERFILES_BONIFICADOS = 1;

    public SuscripcionStreamingFamiliar(String codigoContrato, double tarifaBase,
                                        int perfilesAdicionales, double costoPorPerfilAdicional) {
        super(codigoContrato, tarifaBase, perfilesAdicionales, costoPorPerfilAdicional);
    }

    @Override
    public double calcularMontoFacturable() {
        int perfilesCobrados = Math.max(0, perfilesAdicionales - PERFILES_BONIFICADOS);
        return tarifaBase + perfilesCobrados * costoPorPerfilAdicional;
    }
}
