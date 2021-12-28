package monitoreo.modelos.impl;

import java.util.HashMap;
import java.util.Map;

import monitoreo.modelos.interfaces.ITipoServicio;
import monitoreo.modelos.interfaces.IVisitor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Recojo extends Punto {

    private String direccion;
    private String comentarios;

    public Recojo(double lon, double lat) {
        super(lon, lat);
    }

    public Recojo(double lon, double lat, String direccion, String comentarios) {
        super(lon, lat);
        this.direccion = direccion;
        this.comentarios = comentarios;
    }

    public Recojo(ITipoServicio servicio, Double latitud, Double longitud)  {
        super(servicio, latitud, longitud);
    }

    public String getDireccion(){
        return direccion;
    }

    public String getComentarios(){
        return comentarios;
    }

    public void acceptImprimir(IVisitor visitor) {
        visitor.visitRecojo(this);
    }

    public Map<String, String> imprimirFormato()   {
        
        System.out.println("\n[Visitor]-[Recojo] --------------- FORNATO RECOJO YYYYY --------------");
        Map<String, String> cadena = new HashMap<>();
        cadena.put("comentarios", getComentarios());
        cadena.put("direccion", getDireccion());
        //Gson gson = new GsonBuilder().create();
        //String jsonParse = gson.toJson(json);
        System.out.println("[Visitor]-[Recojo] "+cadena);
        System.out.println("[Visitor]-[Recojo] --------------- FORNATO RECOJO YYYYY --------------");
        return cadena;
    }
}
