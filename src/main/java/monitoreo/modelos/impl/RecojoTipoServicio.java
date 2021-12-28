package monitoreo.modelos.impl;

import monitoreo.modelos.interfaces.ITipoServicio;

public class RecojoTipoServicio implements ITipoServicio {

    private int color = 0X6072c300;

    @Override
    public void ejecutarServicio() {
        System.out.println("[RecojoTipoServicio] Ejecutando recojo");
    }

    public int getColor() {
        return color;
    }

}
