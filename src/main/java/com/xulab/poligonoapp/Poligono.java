/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.xulab.poligonoapp;

/**
 *
 * @author jesus
 */
import java.util.ArrayList;
import java.util.List;

public class Poligono {

    // Lista para guardar todos los puntos que forman la figura
    private List<Punto> vertices;

    public Poligono() {
        this.vertices = new ArrayList<>();
    }

    // Método para agregar puntos (vértices) al polígono
    public void agregarVertice(double x, double y) {
        vertices.add(new Punto(x, y));
    }

    public boolean estaPuntoDentro(Punto p) {
        boolean adentro = false;

        // Empezamos con el último vértice para cerrar el ciclo
        int j = vertices.size() - 1;

        for (int i = 0; i < vertices.size(); i++) {
            Punto A = vertices.get(i); // Vértice Actual
            Punto B = vertices.get(j); // Vértice Anterior (forman una pared)


            // Verifica si p.y está entre A.y y B.y
            if ((A.y > p.y) != (B.y > p.y)) {

                double interseccionX = (B.x - A.x) * (p.y - A.y) / (B.y - A.y) + A.x;

                if (p.x < interseccionX) {
                    adentro = !adentro; 
                }
            }

            j = i;
        }

        return adentro;
    }

    public List<Punto> getVertices() {
        return vertices;
    }
}
