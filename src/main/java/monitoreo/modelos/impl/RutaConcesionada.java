package monitoreo.modelos.impl;

import monitoreo.modelos.interfaces.IVisitor;

public class RutaConcesionada extends RutaTemplate {

    @Override
    public void recibeEntradas(Punto[] puntos, IVisitor visitor)    {
        this.puntos = puntos;
        this.visitor = visitor;
    }

    @Override
    public void optimizaRuta() {
        
        int total = puntos.length - 2;

        Punto[] nuevosPuntos = new Punto[total];

        for (int i = 0; i<total; i++)  {
            nuevosPuntos[i] = puntos[i];
        }
        
        this.puntos = nuevosPuntos;
    }


}
