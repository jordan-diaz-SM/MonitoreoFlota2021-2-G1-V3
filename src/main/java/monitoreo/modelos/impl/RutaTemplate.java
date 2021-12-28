package monitoreo.modelos.impl;

import monitoreo.modelos.interfaces.IVisitor;

public abstract class RutaTemplate {

    //protected PoliLinea rutaLinea;
    protected Punto[] puntos;
    protected IVisitor visitor;

    public void generaRuta(Punto[] puntos, IVisitor visitor)    {

        recibeEntradas(puntos, visitor);
        optimizaRuta();
        imprimeRuta();
    }

    public abstract void recibeEntradas(Punto[] puntos, IVisitor visitor);

    public abstract void optimizaRuta();

    public void imprimeRuta()  {
    
        if (this.visitor != null)   {
            for (Punto punto : this.puntos) {
                punto.acceptImprimir(this.visitor);
            }
        }
        else{
            for (Punto punto : this.puntos) {
                System.out.println("Descripcion: " + punto.getDireccion() + " - " + punto.getComentarios());
            }
        }
    }

}
