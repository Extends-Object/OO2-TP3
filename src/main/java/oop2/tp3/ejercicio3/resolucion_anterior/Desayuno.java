package oop2.tp3.ejercicio3.resolucion_anterior;

public class Desayuno extends TipoDeGasto {

    public Desayuno(String nombreGasto) {
        super(nombreGasto);
    }

    @Override
    public boolean esComida() {
        return true;
    }

    @Override
    public boolean excedeLimite(double monto) {
        if(monto > 1000){
            return true;
        }
        return false;
    }
}
