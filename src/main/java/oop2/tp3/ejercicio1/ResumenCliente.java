package oop2.tp3.ejercicio1;

public class ResumenCliente {
    private double montoFinal;
    private int puntosFrecuente;

    public ResumenCliente(double montoFinal, int puntosFrecuente) {
        this.montoFinal = montoFinal;
        this.puntosFrecuente = puntosFrecuente;
    }

    //PARA EL TEST
    public double getMontoFinal() {
        return montoFinal;
    }

    public int getPuntosFrecuente() {
        return puntosFrecuente;
    }

    @Override
    public String toString() {
        return "\nMonto a pagar: " + montoFinal + "\n" +
                "Puntos acumulados: " + puntosFrecuente;
    }
}
