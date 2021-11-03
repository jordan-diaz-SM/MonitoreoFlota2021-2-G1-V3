package monitoreo.modelos.impl;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import monitoreo.modelos.interfaces.IGrafico;

public class Punto implements IGrafico {

    private String tipoPunto;
    private Graphic punto;
    private static final SpatialReference SPATIAL_REFERENCE = SpatialReferences.getWgs84();
    SimpleMarkerSymbol circleSymbol;

    public Punto(String tipoPunto, SimpleMarkerSymbol.Style estilo, int color, int tamano, Double latitud, Double longitud){
        this.tipoPunto = tipoPunto;

        circleSymbol = new SimpleMarkerSymbol(estilo, color, tamano);
        punto = new Graphic(new Point(longitud, latitud, SPATIAL_REFERENCE), circleSymbol);
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
