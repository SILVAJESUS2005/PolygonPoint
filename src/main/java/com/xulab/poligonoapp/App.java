package com.xulab.poligonoapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        Poligono miPoligonoLogico = new Poligono();

        // Poligono convexo ramdom
//        miPoligonoLogico.agregarVertice(100, 100);
//        miPoligonoLogico.agregarVertice(300, 50);
//        miPoligonoLogico.agregarVertice(400, 200);
//        miPoligonoLogico.agregarVertice(200, 300);
//        miPoligonoLogico.agregarVertice(50, 200);
        // Poligono concavo C
//        miPoligonoLogico.agregarVertice(100, 100); 
//        miPoligonoLogico.agregarVertice(100, 400); 
//        miPoligonoLogico.agregarVertice(400, 400); 
//        miPoligonoLogico.agregarVertice(400, 300); 
//        miPoligonoLogico.agregarVertice(200, 300); 
//        miPoligonoLogico.agregarVertice(200, 200); 
//        miPoligonoLogico.agregarVertice(400, 200); 
//        miPoligonoLogico.agregarVertice(400, 100);
//        
        // Poligono hexágono
        miPoligonoLogico.agregarVertice(150, 100); 
        miPoligonoLogico.agregarVertice(100, 200); 
        miPoligonoLogico.agregarVertice(150, 300); 
        miPoligonoLogico.agregarVertice(350, 300); 
        miPoligonoLogico.agregarVertice(400, 200); 
        miPoligonoLogico.agregarVertice(350, 100); 

        Polygon poligonoVisual = new Polygon();

        for (Punto p : miPoligonoLogico.getVertices()) {
            poligonoVisual.getPoints().addAll(p.x, p.y);
        }

        poligonoVisual.setFill(Color.LIGHTBLUE);
        poligonoVisual.setStroke(Color.BLUE);

        root.getChildren().add(poligonoVisual);

        root.setOnMouseClicked(event -> {

            double clickX = event.getX();
            double clickY = event.getY();
            Punto puntoPrueba = new Punto(clickX, clickY);

            boolean resultado = miPoligonoLogico.estaPuntoDentro(puntoPrueba);

            Circle circulo = new Circle(clickX, clickY, 5);

            if (resultado) {
                circulo.setFill(Color.GREEN);
                System.out.println("DENTRO");
            } else {
                circulo.setFill(Color.RED);
                System.out.println("FUERA");
            }

            root.getChildren().add(circulo);
        });

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("Prueba de Polígono - Haz Clic");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
