package oop2.tp3.ejercicio3.correccion;

public class Cena extends Gasto{

    public Cena(int monto) {
        super(monto);
        marcaExcesoComidas = marcarExcesoComida();
    }

    @Override
    protected boolean esGastoComida() {
        return true;
    }

    private String marcarExcesoComida() {
        return (monto > 5000) ? "X" : " ";
    }
}
