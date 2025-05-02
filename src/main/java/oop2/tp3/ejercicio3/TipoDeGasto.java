package oop2.tp3.ejercicio3;

public abstract class TipoDeGasto {
    //La interfaz define el comportamiento y las clases concretas almacenan el estado

    private String nombreGasto;

    public TipoDeGasto(String nombreGasto) {
        this.nombreGasto = nombreGasto;
    }

    public abstract boolean esComida ();

    public abstract boolean excedeLimite(double monto);

    @Override
    public String toString() {
        return nombreGasto;
    }
}
