package oop2.tp3.ejercicio1;

public class Alquiler {


    private CopiaLibro copia;
    private int diasAlquilados;


    //CONSTRUCTOR
    public Alquiler(CopiaLibro copia, int diasAlquilados) { //RECIBE UNA COPIA Y LA CANTIDAD DE DIAS
        this.copia = copia;
        this.diasAlquilados = diasAlquilados;
    }


    //RETORNA LOS DIAS
    public int diasAlquilados() {
        return this.diasAlquilados;
    }

    //RETORNA LA COPIA
    public CopiaLibro copia() {
        return this.copia;
    }
}
