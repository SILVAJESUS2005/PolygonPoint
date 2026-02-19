package com.xulab.poligonoapp.dos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementación del algoritmo O(log N) para determinar si un punto está dentro
 * de un polígono CONVEXO utilizando Búsqueda Binaria Angular.
 */
public class PoligonoDos {

    private List<PuntoDos> vertices;
    private List<PuntoDos> seq;     // Secuencia de vectores relativos al pivote P0
    private PuntoDos translation;   // Punto pivote P0 (el más inferior-izquierdo)
    private int n;

    public PoligonoDos() {
        this.vertices = new ArrayList<>();
        this.seq = new ArrayList<>();
    }

    public void agregarVertice(double x, double y) {
        vertices.add(new PuntoDos(x, y));
    }

    public List<PuntoDos> getVertices() {
        return vertices;
    }

    /**
     * Prepara el polígono para las consultas O(log N).
     * 1. Encuentra el punto más bajo a la izquierda (P0).
     * 2. Rota la lista para que empiece en P0.
     * 3. Calcula vectores relativos a P0.
     * DEBE LLAMARSE UNA VEZ ANTES DE HACER CONSULTAS.
     */
    public void preparar() {
        n = vertices.size();
        if (n < 3) return;

        // 1. Buscar el punto lexicográficamente menor (menor X, o menor Y en empate)
        int pos = 0;
        for (int i = 1; i < n; i++) {
            if (vertices.get(i).x < vertices.get(pos).x
                    || (vertices.get(i).x == vertices.get(pos).x && vertices.get(i).y < vertices.get(pos).y)) {
                pos = i;
            }
        }

        // 2. Rotar la lista para que ese punto sea el índice 0
        Collections.rotate(vertices, -pos);

        // 3. Guardar P0 y preparar la secuencia de vectores relativos
        translation = vertices.get(0); // P0
        seq.clear();
        
        // Llenamos seq con los puntos trasladados (P_i - P_0)
        for (int i = 1; i < n; i++) {
            seq.add(vertices.get(i).restar(translation));
        }
    }

    /**
     * Determina si un punto está dentro del polígono convexo en tiempo O(log N).
     * @param p El punto a probar.
     * @return true si está dentro o en el borde, false si está fuera.
     */
    public boolean estaEnPoligonoConvexo(PuntoDos p) {
        // 1. Trasladar el punto de prueba relativo a P0
        PuntoDos point = p.restar(translation);

        // 2. Chequeos de límites angulares (¿Está fuera del abanico?)
        // Verifica si está a la derecha del primer lado o a la izquierda del último
        if (seq.get(0).cruz(point) < 0 || seq.get(seq.size() - 1).cruz(point) > 0) {
            return false;
        }

        // 3. Caso especial: ¿Está sobre la línea del primer segmento?
        if (seq.get(0).cruz(point) == 0) {
            return seq.get(0).sqrLen() >= point.sqrLen();
        }

        // 4. BÚSQUEDA BINARIA (O(log n))
        // Buscamos en qué sector angular cae el punto
        int l = 0, r = seq.size() - 1;
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (seq.get(mid).cruz(point) >= 0) {
                l = mid;
            } else {
                r = mid;
            }
        }
        
        // Al terminar, 'l' es el índice del vector inferior del sector.
        // Verificamos si está dentro del triángulo formado por (0,0), seq[l], seq[l+1]
        // (Nota: el tercer parámetro (0,0) es implícito al usar vectores relativos)
        return puntoEnTriangulo(seq.get(l), seq.get(l + 1), point);
    }

    /**
     * Verifica si el punto 'pt' está dentro del triángulo formado por el Origen, 'a' y 'b'.
     * Usa la suma de áreas: ÁreaTotal == Suma(SubÁreas) -> Dentro.
     */
    private boolean puntoEnTriangulo(PuntoDos a, PuntoDos b, PuntoDos pt) {
        // s1: Área total del triángulo (Origen - A - B)
        double s1 = Math.abs(a.cruz(b)); 

        // s2: Suma de las 3 sub-áreas formadas por el punto 'pt'
        double area1 = Math.abs(a.cruz(pt)); // Triángulo Origen - A - pt
        double area2 = Math.abs(b.cruz(pt)); // Triángulo Origen - B - pt
        
        // Triángulo A - B - pt (Restamos vectores para obtener lados relativos)
        double area3 = Math.abs((a.restar(pt)).cruz(b.restar(pt)));

        double s2 = area1 + area2 + area3;

        // Comparamos con tolerancia pequeña por precisión de punto flotante
        return Math.abs(s1 - s2) < 1e-9;
    }
}