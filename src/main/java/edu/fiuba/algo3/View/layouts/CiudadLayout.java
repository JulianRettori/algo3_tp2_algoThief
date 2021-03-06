package edu.fiuba.algo3.View.layouts;

import edu.fiuba.algo3.Controller.App;
import edu.fiuba.algo3.View.BarraAcciones;
import edu.fiuba.algo3.View.scenes.ImagenPortada;
import edu.fiuba.algo3.View.scenes.SeleccionEdificiosBox;
import edu.fiuba.algo3.View.scenes.SeleccionViajeBox;
import edu.fiuba.algo3.Model.AlgoThief;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javafx.geometry.Insets;

public class CiudadLayout extends BorderPane {
    public CiudadLayout(Stage window, App app, AlgoThief algoThief){

        //Detalles
        DropShadow dropShadow = new DropShadow(1, 3, 3, Color.BLACK);
        BorderStroke borderStroke =
                new BorderStroke(
                        Color.BLACK,
                        BorderStrokeStyle.SOLID,
                        new CornerRadii(1),
                        new BorderWidths(4)
                );
        Border border = new Border(borderStroke);

        BorderPane ladoIzquierdo= new BorderPane();
        BorderPane ladoDerecho= new BorderPane();

        BorderPane cajaGenerica = new BorderPane();
        cajaGenerica.setBorder(border);
        cajaGenerica.setPadding(new Insets(3));
        //cajaGenerica.setStyle("-fx-background-radius: 6;" +
        //        "-fx-background-color: rgb(45, 45, 50), rgb(60, 60, 65);" +
        //        "-fx-background-insets: 0, 0 1 1 0;");
        cajaGenerica.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(5))));
        cajaGenerica.setMaxSize(300,140);
        cajaGenerica.setVisible(false);
        ladoDerecho.setCenter(cajaGenerica);

        Label horaUbicacion = new Label(algoThief.ciudadActual() + "\n" + algoThief.obtenerHorario());
        HBox boxHorario = new HBox(horaUbicacion);
        horaUbicacion.setMinSize(300, 50);
        boxHorario.setBorder(border);
        horaUbicacion.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 24));

        Label descripcionCiudad = new Label(algoThief.obtenerInformacionCiudad());
        descripcionCiudad.setBorder(border);
        descripcionCiudad.setPrefWidth(320);
        descripcionCiudad.setPrefHeight(240);
        descripcionCiudad.setWrapText(true);
        descripcionCiudad.setFont(Font.font("OCR A Extended", FontWeight.EXTRA_BOLD, 16));

        //Visitar edificio
        Button botonVisitar = new Button();
        botonVisitar.setOnAction(i -> {
            cajaGenerica.getChildren().clear();
            SeleccionEdificiosBox.agregarOpcionesEdificio(cajaGenerica,app);
            cajaGenerica.setVisible(!cajaGenerica.isVisible());
        });

        //Opciones de viaje
        Button botonViajar = new Button();
        botonViajar.setOnAction(i -> {
            cajaGenerica.getChildren().clear();
            SeleccionViajeBox.agregarOpcionesViaje(app,algoThief,cajaGenerica);
            cajaGenerica.setVisible(!cajaGenerica.isVisible());
        });

        //Computar
        Button botonComputadora = new Button();
        botonComputadora.setOnAction(e -> {
            app.abrirLaComputadora();
        });

        //Ver Opciones de Viaje
        Button botonOpciones = new Button();
        botonOpciones.setOnAction(i -> {
            cajaGenerica.getChildren().clear();
            SeleccionViajeBox.mostrarOpcionesViaje(app,algoThief,cajaGenerica);
            cajaGenerica.setVisible(!cajaGenerica.isVisible());
        });


        //Ordenar Layout
        BarraAcciones barraAcciones = new BarraAcciones(botonOpciones, botonViajar, botonVisitar, botonComputadora);
        ladoDerecho.setBottom(barraAcciones);
        ladoDerecho.setTop(descripcionCiudad);



        VBox imagenCiudad = new VBox();
        imagenCiudad.setBackground(ImagenPortada.crearFondo("rsc/images/fotosciudades/"+algoThief.ciudadActual()+".png"));
        imagenCiudad.setMinSize(300, 400);
        imagenCiudad.setMaxSize(300,400);
        imagenCiudad.setBorder(border);
        imagenCiudad.setEffect(dropShadow);
        setPadding(new Insets(4));

        ladoIzquierdo.setTop(boxHorario);
        ladoIzquierdo.setBottom(imagenCiudad);

        this.setLeft(ladoIzquierdo);
        this.setRight(ladoDerecho);
    }
}