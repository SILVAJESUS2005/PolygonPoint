module com.xulab.poligonoapp {
    requires javafx.controls;
   

    // Paquete del Ejercicio 1
    opens com.xulab.poligonoapp;
    exports com.xulab.poligonoapp;

    // Paquete del Ejercicio 2
    opens com.xulab.poligonoapp.dos;
    exports com.xulab.poligonoapp.dos;
}