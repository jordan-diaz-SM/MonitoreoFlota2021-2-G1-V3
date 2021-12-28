package monitoreo.modelos.impl;


import monitoreo.modelos.interfaces.IVisitor;

import java.util.Map;

public class FormatoImpresoVisitor implements IVisitor {

    @Override
    public Map<String, String> visitRecojo(Recojo recojo) {

        return recojo.imprimirFormato();
    }

    @Override
    public Map<String, String> visitDespacho(Despacho despacho) {

        return despacho.imprimirFormato();
    }

}
