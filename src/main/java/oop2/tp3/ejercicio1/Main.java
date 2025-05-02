package oop2.tp3.ejercicio1;

public class Main {
    public static void main(String args[]) {

        //INSTANCIAR LIBROS
        //Libro elTunel = new Libro("El Túnel", Libro.REGULARES);
        //Libro antesDelFin = new Libro("Antes del Fin", Libro.REGULARES);

        GeneroLibro regular = new Regulares();
        //Solo tengo que instanciar uno por cada tipo y usarlo en todos los que sean de ese tipo

        Libro elTunel = new Libro("El Túnel", regular);
        Libro antesDelFin = new Libro("Antes del Fin", regular);


        //INSTANCIAR COPIA DE ESOS LIBROS
        CopiaLibro elTunelCopia = new CopiaLibro(elTunel);
        CopiaLibro antesDelFinCopia = new CopiaLibro(antesDelFin);


        //INSTANCIAR ALQUILER DE ESOS LIBROS
        Alquiler alquilerElTunel = new Alquiler(elTunelCopia, 5);
        Alquiler alquilerAntesDelFin = new Alquiler(antesDelFinCopia, 3);


        //CLIENTE QUE LOS VA A ALQUILAR
        Cliente yo = new Cliente("Javier");


        //ALQUILAR --- Solo agrega los alquileres a una lista
        yo.alquilar(alquilerElTunel);
        yo.alquilar(alquilerAntesDelFin);


        //CALCULO DE LA DEUDA
        Object[] resultado = yo.calcularDeudaYPuntosObtenidos();

        //QUE ES 0 Y 1??
        System.out.println(resultado[0]);
        System.out.println(resultado[1]);
    }
}