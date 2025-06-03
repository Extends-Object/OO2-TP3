package oop2.tp3.ejercicio3.correccion;

public class Desayuno extends Gasto{

    public Desayuno(int monto) {
        super(monto);
        marcaExcesoComidas = marcarExcesoComida();
    }

    @Override
    protected boolean esGastoComida() {
        return true;
    }

    private String marcarExcesoComida() {
        return (monto > 1000) ? "X" : " ";
    }
}
