package oop2.tp3.ejercicio1;

public class Libro {

    /*
    //CONSTANTES
    public static final int INFANTILES = 2;
    public static final int REGULARES = 0;
    public static final int NUEVO_LANZAMIENTO = 1;
    */

    private GeneroLibro genero;

    private String nombre;
    //private int codigoPrecio;

    //CONSTRUCTOR
    public Libro(String nombre, GeneroLibro genero) {
        this.nombre = nombre;
        //this.codigoPrecio = priceCode;
        this.genero = genero;
    }

    public GeneroLibro genero(){
        return genero;
    }

    /*
    //GET CODIGO
    public int codigoPrecio() {
        return codigoPrecio;
    }
    */


    //GET NOMBRE LIBRO
    public String nombre() {
        return nombre;
    }
}