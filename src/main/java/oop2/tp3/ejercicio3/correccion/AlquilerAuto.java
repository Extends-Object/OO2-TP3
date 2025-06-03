package oop2.tp3.ejercicio3.correccion;

public class AlquilerAuto extends Gasto{

    public AlquilerAuto(int monto) {
        super(monto);
    }

    @Override
    protected boolean esGastoComida() {
        return false;
    }
}
