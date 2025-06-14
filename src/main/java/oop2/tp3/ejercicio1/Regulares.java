package oop2.tp3.ejercicio1;

public class Regulares implements GeneroLibro{

    @Override
    public double calcularMonto(int diasAlquilados) {
        double monto = 2;
        if (diasAlquilados > 2)
            monto += (diasAlquilados - 2) * 1.5;
        return monto;
    }

    @Override
    public int calcularPuntosPorAlquilerFrecuente(int diasAlquilados) {
        return 1;
    }

    /*
    public double calcularMonto(Alquiler alquiler, double monto) {
        monto += 2;                                             //Que es ese 2? --- Agrega 2 al monto - COSTO BASE
        if (alquiler.diasAlquilados() > 2)
            monto += (alquiler.diasAlquilados() - 2) * 1.5;     //Calcula el monto a partir de esos 2
        return monto;
    }
    */

}
