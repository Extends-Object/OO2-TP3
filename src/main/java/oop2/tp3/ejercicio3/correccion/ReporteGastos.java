package oop2.tp3.ejercicio3.correccion;

import java.util.List;

public class ReporteGastos {

    private final List<Gasto> gastos;
    private final Formateador formateador;
    private int total;
    private int gastosComida;

    public ReporteGastos(List<Gasto> gastos, Formateador formateador) {
        this.formateador = formateador;
        this.total = 0;
        this.gastosComida = 0;
        this.gastos = gastos;
    }

    public String generarReporte() {

        for (Gasto gasto : gastos) {
            if(gasto.esGastoComida()) {
                this.gastosComida += gasto.obtenerMonto();
            }
            this.total += gasto.obtenerMonto();
        }
        return this.formateador.formatear(this);
    }

    public int total() {
        return total;
    }

    public int gastosComida() {
        return gastosComida;
    }

    public List<Gasto> gastos() {
        return gastos;
    }
}