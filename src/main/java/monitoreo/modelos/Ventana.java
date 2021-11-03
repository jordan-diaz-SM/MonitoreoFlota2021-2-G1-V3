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
import monitoreo.modelos.impl.Punto;
import monitoreo.modelos.impl.PuntoMonitoreoBuilder;

import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

public class Ventana extends Application {

    private Mapa mapaBase;
    private GraphicsOverlay graphicsOverlay;

    @Override
    public void start(Stage stage) throws Exception {

        // set the title and size of the stage and show it
        stage.setTitle("Sistema de Monitoreo de Vehiculos");
        stage.setWidth(800);
        stage.setHeight(700);

        // create a JavaFX scene with a stack pane as the root node and add it to the scene
        StackPane stackPane = new StackPane();

        // create a MapView to display the map and add it to the stack pane
        mapaBase = new Mapa();
        mapaBase.imprimeCoordenadasActual();
        stackPane.getChildren().add(mapaBase.getMapView());

        // Crea la imagen para el botón
        Image img = new Image("https://upload-icon.s3.us-east-2.amazonaws.com/uploads/icons/png/4498062351543238871-512.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(40);
        view.setPreserveRatio(true);

        // Crea el botón de Ventana nueva
        Button btnNuevo = new Button();
        btnNuevo.setGraphic( view );
        btnNuevo.setText("Nuevo");
        stackPane.getChildren().add(btnNuevo);
        StackPane.setAlignment(btnNuevo, Pos.BOTTOM_CENTER);
        StackPane.setMargin(btnNuevo, new Insets(10, 10, 10, 10));
        btnNuevo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                muestraNuevaVentana();
            }
        });

        // Agrega el punto
        PuntoMonitoreoBuilder puntoBuilder = new PuntoMonitoreoBuilder("Inicio del día");
        puntoBuilder.withSimbolo(SimpleMarkerSymbol.Style.CIRCLE, 0xFFFF0000, 10);
        puntoBuilder.withUbicacion(-12.054901, -77.085470);
        Punto puntoInicial = puntoBuilder.build();

        GraphicsOverlay graphicsOverlay = new GraphicsOverlay();
        graphicsOverlay.getGraphics().add(puntoInicial.getPunto());
        mapaBase.getMapView().getGraphicsOverlays().add(graphicsOverlay);

        // Muestra la ventana
        Scene scene = new Scene(stackPane);
        stage.setScene(scene);
        stage.show();
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
        Mapa mapaBase2 = (Mapa)mapaBase.copiar();

        mapaBase2.imprimeCoordenadasActual();
        stackPane.getChildren().add(mapaBase2.getMapView());

        stage.show();
    }


}
