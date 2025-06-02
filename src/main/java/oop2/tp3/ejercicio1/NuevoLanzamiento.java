package oop2.tp3.ejercicio1;

public class NuevoLanzamiento implements GeneroLibro{

    @Override
    public double calcularMonto(int diasAlquilados) {
        return diasAlquilados * 3;
    }

    @Override
    public int calcularPuntosPorAlquilerFrecuente(int diasAlquilados) {
        int puntos = 1;                                                     //Es el punto base
        if(diasAlquilados > 1){
            puntos++;
        }
        return puntos;
    }
}
