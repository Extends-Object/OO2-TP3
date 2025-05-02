package oop2.tp3.ejercicio1;

public class Infantiles implements GeneroLibro{

    @Override
    public double calcularMonto(Alquiler alquiler) {
        double monto = 1.5;
        if (alquiler.diasAlquilados() > 3)
            monto += (alquiler.diasAlquilados() - 3) * 1.5;
        return monto;
    }

    @Override
    public int calcularPuntosPorAlquilerFrecuente(Alquiler alquiler) {
        return 1;
    }

}
