package oop2.tp3.ejercicio3;

public class Gasto {

    private TipoDeGasto tipoGasto;
    private double monto;

    private String marcaExcesoComida;


    public Gasto(TipoDeGasto tipoGasto, double monto) {
        this.tipoGasto = tipoGasto;
        this.monto = monto;
        this.marcaExcesoComida = " ";
    }


    public void marcarPorExcesoComidas (){
        this.marcaExcesoComida = verificarExceso() ? "X" : " ";
    }

    private boolean verificarExceso(){
        return tipoGasto.excedeLimite(this.monto);
    }

    public TipoDeGasto tipoGasto(){
        return tipoGasto;
    }


    public double monto() {
        return monto;
    }

    public String marcaExcesoComida() {
        return marcaExcesoComida;
    }

    @Override
    public String toString() {
        return tipoGasto.toString() + "\t" + (int) monto + "\t" + marcaExcesoComida;
    }
}
