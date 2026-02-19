package com.xulab.poligonoapp.dos;

import com.xulab.poligonoapp.dos.PoligonoDos;
import com.xulab.poligonoapp.dos.PuntoDos;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class App2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        PoligonoDos miPoligonoLogico = new PoligonoDos();

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
     // Poligono hexágono (CORREGIDO: Sentido Anti-Horario Visual)
        // Esto arregla el conflicto entre Matemáticas y JavaFX
        miPoligonoLogico.agregarVertice(100, 200); // 1. Centro Izq (Empezamos aquí)
        miPoligonoLogico.agregarVertice(150, 300); // 2. Abajo Izq
        miPoligonoLogico.agregarVertice(350, 300); // 3. Abajo Der
        miPoligonoLogico.agregarVertice(400, 200); // 4. Centro Der
        miPoligonoLogico.agregarVertice(350, 100); // 5. Arriba Der
        miPoligonoLogico.agregarVertice(150, 100); // 6. Arriba Izq

        // ¡NO BORRES ESTO! Es vital para el algoritmo 2
        miPoligonoLogico.preparar();
        Polygon poligonoVisual = new Polygon();

        for (PuntoDos p : miPoligonoLogico.getVertices()) {
            poligonoVisual.getPoints().addAll(p.x, p.y);
        }

        poligonoVisual.setFill(Color.LIGHTBLUE);
        poligonoVisual.setStroke(Color.BLUE);

        root.getChildren().add(poligonoVisual);

        root.setOnMouseClicked(event -> {

            double clickX = event.getX();
            double clickY = event.getY();
            PuntoDos puntoPrueba = new PuntoDos(clickX, clickY);

            boolean resultado = miPoligonoLogico.estaEnPoligonoConvexo(puntoPrueba);
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
