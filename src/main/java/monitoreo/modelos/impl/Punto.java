package monitoreo.modelos.impl;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import monitoreo.modelos.interfaces.IGrafico;
import monitoreo.modelos.interfaces.IPuntoMonitoreo;

public class Punto implements IGrafico {

    private String tipoPunto;
    private Graphic punto;
    private static final SpatialReference SPATIAL_REFERENCE = SpatialReferences.getWgs84();
    private SimpleMarkerSymbol simbolo;

    private Double latitud;
    private Double longitud;
    private SimpleMarkerSymbol.Style estilo;
    private int color;
    private int tamano;

    public Punto() {
    }
    
    public void generaPunto()   {
        simbolo = new SimpleMarkerSymbol(estilo, color, tamano);
        punto = new Graphic(new Point(longitud, latitud, SPATIAL_REFERENCE), simbolo);
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public SimpleMarkerSymbol.Style getEstilo() {
        return estilo;
    }

    public void setEstilo(SimpleMarkerSymbol.Style estilo) {
        this.estilo = estilo;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public String getTipoPunto() {
        return tipoPunto;
    }

    public void setTipoPunto(String tipoPunto) {
        this.tipoPunto = tipoPunto;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Graphic getGrafico(){
        return getPunto();
    }

    public Graphic getPunto() {
        // alertas
        System.out.println("[Punto] Obteniendo Punto para agregarlo al mapa");
        return punto;
    }

    @Override
    public void mover(Integer x, Integer Y) {

    }


}
