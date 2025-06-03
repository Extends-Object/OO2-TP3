package oop2.tp3.ejercicio3.correccion;

public abstract class Gasto {

    //protected NombreGasto nombreGasto;
    protected int monto;
    protected String marcaExcesoComidas;

    public Gasto(int monto) {
        this.monto = monto;
        this.marcaExcesoComidas = " ";
    }

    public int obtenerMonto() {
        return monto;
    }

    protected abstract boolean esGastoComida();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "\t" + monto + "\t" + marcaExcesoComidas + "\n";
    }
}
