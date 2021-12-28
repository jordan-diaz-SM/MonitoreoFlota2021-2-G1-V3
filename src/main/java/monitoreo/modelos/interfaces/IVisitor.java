package monitoreo.modelos.interfaces;

import monitoreo.modelos.impl.Despacho;
import monitoreo.modelos.impl.Recojo;

import java.util.Map;

public interface IVisitor {

    Map<String, String> visitRecojo(Recojo recojo);
    Map<String, String> visitDespacho(Despacho despacho);

}
