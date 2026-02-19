package com.xulab.poligonoapp.dos;

public class PuntoDos {
    public double x, y;

    public PuntoDos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    
    // Resta de vectores (this - p)
    public PuntoDos restar(PuntoDos p) {
        return new PuntoDos(this.x - p.x, this.y - p.y);
    }

    // Producto Cruz (Cross Product)
   public double cruz(PuntoDos p) {
    // Al restar al revés, arreglamos el eje Y invertido
    return this.y * p.x - this.x * p.y; 
}
    
    // Longitud al cuadrado
    public double sqrLen() {
        return this.x * this.x + this.y * this.y;
    }
}