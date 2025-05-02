package oop2.tp3.ejercicio3;

public class AlquilerAuto extends TipoDeGasto{

    public AlquilerAuto(String nombreGasto) {
        super(nombreGasto);
    }

    @Override
    public boolean esComida() {
        return false;
    }

    @Override
    public boolean excedeLimite(double monto) {
        return false;
    }
}
