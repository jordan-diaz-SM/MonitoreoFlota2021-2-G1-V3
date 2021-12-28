package monitoreo.modelos.impl;

import java.util.HashMap;
import java.util.Map;

import monitoreo.modelos.interfaces.ITipoServicio;
import monitoreo.modelos.interfaces.IVisitor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Despacho extends Punto {

    private String direccion;
    private String comentarios;
    private String documento;

    public Despacho(double lon, double lat) {
        super(lon, lat);
    }

    public Despacho(double lon, double lat, String direccion, String comentarios, String documento) {
        super(lon, lat);
        this.direccion = direccion;
        this.comentarios = comentarios;
        this.documento = documento;
    }

    public Despacho(ITipoServicio servicio, Double latitud, Double longitud)  {
        super(servicio, latitud, longitud);
    }

    public String getDireccion() {
        return direccion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public String getDocumento() {
        return documento;
    }

    public void acceptImprimir(IVisitor visitor) {
        visitor.visitDespacho(this);
    }

    public Map<String, String> imprimirFormato() {

        System.out.println("\n[Visitor]-[Despacho] *********** FORNATO DESPACHO XXXX ***************");
        Map<String, String> salida = new HashMap<>();
        salida.put("comentarios", getComentarios());
        salida.put("direccion", getDireccion());
        salida.put("documento", getDocumento());
        Gson gson = new GsonBuilder().create();
        String jsonParse = gson.toJson(salida);
        System.out.println("[Visitor]-[Despacho] " + jsonParse);
        System.out.println("[Visitor]-[Despacho] *********** FORNATO DESPACHO XXXX ***************");
        return salida;
    }

}
