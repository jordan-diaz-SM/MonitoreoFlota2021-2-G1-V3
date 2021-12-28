package monitoreo.modelos.impl;

import monitoreo.modelos.interfaces.ITransporteStrategy;

public class Transporte {

    private ITransporteStrategy strategy;

    public void setStrategy(ITransporteStrategy strategy) {
        this.strategy = strategy;
    }

    public Double[][] crearPuntos(Double[][] puntos)    {
        return this.strategy.crearPuntos(puntos);
    }

}
