package oop2.tp3.ejercicio3;

public class Cena extends TipoDeGasto{

    public Cena(String nombreGasto) {
        super(nombreGasto);
    }

    @Override
    public boolean esComida() {
        return true;
    }

    @Override
    public boolean excedeLimite(double monto) {
        if(monto > 5000) {
            return true;
        }
        return false;
    }
}
