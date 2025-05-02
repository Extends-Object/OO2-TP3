package oop2.tp3.ejercicio1;

public class NuevoLanzamiento implements GeneroLibro{

    @Override
    public double calcularMonto(Alquiler alquiler) {
        double monto = 0;
        return monto = alquiler.diasAlquilados() * 3;
    }

    @Override
    public int calcularPuntosPorAlquilerFrecuente(Alquiler alquiler) {
        int puntos = 1;                                                     //Es el punto base
        if(alquiler.diasAlquilados() > 1){
            puntos++;
        }
        return puntos;
    }
}
