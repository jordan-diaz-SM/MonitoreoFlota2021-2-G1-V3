package monitoreo.modelos.impl;

import monitoreo.modelos.interfaces.ITipoServicio;

public class EntregaTipoServicio implements ITipoServicio {

    private int color = 0xffffff00;
    
    @Override
    public void ejecutarServicio() {
        System.out.println("[EntregaTipoServicio] Ejecutando entrega");
    }

    public int getColor() {
        return color;
    }

}
