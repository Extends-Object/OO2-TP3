package oop2.tp3.ejercicio1;

public class CopiaLibro {

    //SE LE ASIGNA EL LIBRO ORIGINAL
    private Libro libro;


    //CONSTRUCTOR
    public CopiaLibro(Libro libro) {
        this.libro = libro;
    }


    //RETORNA EL LIBRO ORIGINAL
    public Libro libro() {
        return libro;
    }
}