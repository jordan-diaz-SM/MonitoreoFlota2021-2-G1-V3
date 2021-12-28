package monitoreo.modelos.impl;

import monitoreo.modelos.interfaces.IVisitor;

public class RutaPropia extends RutaTemplate {

    @Override
    public void recibeEntradas(Punto[] puntos, IVisitor visitor)    {
        this.puntos = puntos;
        this.visitor = visitor;
    }

    @Override
    public void optimizaRuta() {

        this.visitor = new FormatoImpresoVisitor();
    }


}
