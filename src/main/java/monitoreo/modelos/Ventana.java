package monitoreo.modelos;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import monitoreo.modelos.impl.*;
import monitoreo.modelos.interfaces.ITipoServicio;

//import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

public class Ventana extends Application {

    private Mapa mapa;
    //private GraphicsOverlay graphicsOverlay;

    @Override
    public void start(Stage stage) throws Exception {

        // Crea una fachada par el Mapa
        FachadaMapa facade = new FachadaMapa(stage);
        //facade.getMapaBase().imprimeCoordenadasActual();
        this.mapa = facade.getMapa();

        // Crea la imagen para el botón
        Image img = new Image("https://upload-icon.s3.us-east-2.amazonaws.com/uploads/icons/png/4498062351543238871-512.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(40);
        view.setPreserveRatio(true);

        // Crea el botón de Ventana nueva
        Button btnNuevo = new Button();
        btnNuevo.setGraphic( view );
        btnNuevo.setText("Nuevo");
        facade.getStackPane().getChildren().add(btnNuevo);
        facade.getStackPane().setAlignment(btnNuevo, Pos.BOTTOM_CENTER);
        facade.getStackPane().setMargin(btnNuevo, new Insets(10, 10, 10, 10));
        btnNuevo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                muestraNuevaVentana();
            }
        });

        // Agrega el punto
        PuntoMonitoreoBuilder puntoBuilder = new PuntoMonitoreoBuilder("Inicio del día");
        puntoBuilder.withSimbolo(SimpleMarkerSymbol.Style.DIAMOND, 0xFF00FF00, 10);
        puntoBuilder.withUbicacion(-12.05310, -77.08552);
        Punto puntoInicial = puntoBuilder.build();
        facade.addGraphicsOverlay(puntoInicial.getGrafico());

        // Entregas programadas para una misma ruta
        GuiaEntrega guia = new GuiaEntrega();
        guia.agregarEntrega(new EntregaProgramada("09:00-10:00", "23/11/2021"));
        guia.agregarEntrega(new EntregaProgramada("10:00-11:00", "23/11/2021"));
        guia.agregarEntrega(new EntregaProgramada("12:00-13:00", "23/11/2021"));
        //guia.listarEntrega();

        GuiaEntrega guiaGeneral = new GuiaEntrega();
        guiaGeneral.agregarEntrega(guia);
        guiaGeneral.agregarEntrega(new EntregaProgramada("13:00-14:00", "24/11/2021"));
        guiaGeneral.agregarEntrega(new EntregaProgramada("15:00-16:00", "24/11/2021"));
        guiaGeneral.listarEntrega();

        System.out.println("[Cliente][Guia General] Costo total "+guiaGeneral.calcularCosto());

        // Crear ruta con entrega y recojo
        ITipoServicio recojo = new RecojoTipoServicio();
        ITipoServicio entrega = new EntregaTipoServicio();

        Punto puntoRecojo = new Recojo(recojo, -12.054901, -77.085470);
        facade.addGraphicsOverlay(puntoRecojo.getGrafico());
        puntoRecojo.ejecutarServicio();

        Punto puntoEntrega = new Despacho(entrega,-12.072936, -77.083132);
        facade.addGraphicsOverlay(puntoEntrega.getGrafico());
        puntoEntrega.ejecutarServicio();
        
        // Agrega la ruta entre los puntos
        Double[][] puntosEntrega = {
                {-12.05492, -77.08531},
                {-12.05531, -77.08472},
                {-12.05792, -77.08575},
                {-12.05878, -77.08474},
                {-12.06108, -77.08424},
                {-12.06087, -77.08266},
                {-12.06759, -77.08168},
                {-12.07293, -77.08313}
        };
        PoliLinea poliEntrega = new PoliLinea(entrega, puntosEntrega);
        facade.addGraphicsOverlay(poliEntrega.getGrafico());


        // Strategy, algoritmos de creacion de ruta
        Double[][] puntos = {
            {-12.054456, -77.083491},
            {-12.059279, -77.075558}
        };
        Transporte transporteContext = new Transporte();
        //transporteContext.setStrategy(new CamionStrategy());
        transporteContext.setStrategy(new MotoStrategy());
        Double[][] puntosEntregaOptimizado = transporteContext.crearPuntos(puntos);
        PoliLinea rutaLinea = new PoliLinea(new RecojoTipoServicio(), puntosEntregaOptimizado);
        facade.addGraphicsOverlay(rutaLinea.getGrafico());

        // Visitor: generar puntos de recojo y entrega 
        Punto pRecojo = new Recojo(puntos[0][0], puntos[0][1], "UNMSM", "Recoger en la entrada");
        Punto pEntrega = new Despacho(puntos[1][0], puntos[1][1], "Terapias infantiles", "Despachar libros", "123456798");
        Punto[] puntosRuta = { pRecojo, pEntrega };

        // Imprimir la informacion visitando cada punto
        FormatoImpresoVisitor impresorVisitor = new FormatoImpresoVisitor();
        for (Punto punto : puntosRuta) {
            punto.acceptImprimir(impresorVisitor);
        }


        // Creacion de una Ruta Concesionada a partir de un Template Method
        Punto p1 = new Recojo(puntosEntrega[0][0], puntosEntrega[0][1], "Sitio 1", "Recoger del sitio 1");
        Punto p2 = new Recojo(puntosEntrega[1][0], puntosEntrega[1][1], "Sitio 2", "Recoger del sitio 2");
        Punto p3 = new Recojo(puntosEntrega[2][0], puntosEntrega[2][1], "Sitio 3", "Recoger del sitio 3");
        Punto p4 = new Recojo(puntosEntrega[3][0], puntosEntrega[3][1], "Sitio 4", "Recoger del sitio 4");
        Punto[] puntosTemplate = { p1, p2, p3, p4 };

        //RutaTemplate ruta = new RutaPropia();
        RutaTemplate ruta = new RutaConcesionada();
        ruta.generaRuta(puntosTemplate, null);


        // Dibuja el mapa en la ventana
        facade.stackAddMapView();
        facade.mostrarVentana();

    }

    public void muestraNuevaVentana() {
        Stage stage = new Stage();
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.setTitle("Sistema de Monitoreo de Vehiculos NUEVO");
        stage.setWidth(800);
        stage.setHeight(700);

        //  Clonacion de MapaBase
        Mapa mapa2 = (Mapa)this.mapa.copiar();

        mapa2.imprimeCoordenadasActual();
        stackPane.getChildren().add(mapa2.getMapView());

        stage.show();
    }

}
