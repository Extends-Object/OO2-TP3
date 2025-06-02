package oop2.tp3.ejercicio1;

public class Infantiles implements GeneroLibro{

    @Override
    public double calcularMonto(int diasAlquilados) {
        double monto = 1.5;
        if (diasAlquilados > 3)
            monto += (diasAlquilados - 3) * 1.5;
        return monto;
    }

    @Override
    public int calcularPuntosPorAlquilerFrecuente(int diasAlquilados) {
        return 1;
    }

}
