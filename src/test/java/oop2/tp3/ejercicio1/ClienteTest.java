package oop2.tp3.ejercicio1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {
    GeneroLibro regular;
    GeneroLibro nuevoLanzamiento;
    GeneroLibro infantiles;

    Libro elTunel;
    Libro antesDelFin;
    Libro laOdisea;
    Libro laIliada;
    Libro pinocho;
    Libro caperucita;

    CopiaLibro elTunelCopia;
    CopiaLibro antesDelFinCopia;
    CopiaLibro laOdiseaCopia;
    CopiaLibro laIliadaCopia;
    CopiaLibro pinochoCopia;
    CopiaLibro caperucitaCopia;

    Alquiler alquilerElTunel;
    Alquiler alquilerAntesDelFin;
    Alquiler alquilerLaOdisea;
    Alquiler alquilerLaIliada;
    Alquiler alquilerPinocho;
    Alquiler alquilerCaperucita;

    ResumenCliente resumen;

    @BeforeEach
    public void setUp (){

        regular = new Regulares();
        nuevoLanzamiento = new NuevoLanzamiento();
        infantiles = new Infantiles();

        elTunel = new Libro("El Túnel", regular);
        antesDelFin = new Libro("Antes del Fin", regular);
        laOdisea = new Libro("La Odisea", nuevoLanzamiento);
        laIliada = new Libro("La Iliada", nuevoLanzamiento);
        pinocho = new Libro("Pinocho", infantiles);
        caperucita = new Libro("Caperucita Roja", infantiles);

        elTunelCopia = new CopiaLibro(elTunel);
        antesDelFinCopia = new CopiaLibro(antesDelFin);
        laOdiseaCopia = new CopiaLibro(laOdisea);
        laIliadaCopia = new CopiaLibro(laIliada);
        pinochoCopia = new CopiaLibro(pinocho);
        caperucitaCopia = new CopiaLibro(caperucita);

        alquilerElTunel = new Alquiler(elTunelCopia, 5);            // (5-2)*1.5 = 4.5 ---> MAS 2 DE BASE
        alquilerAntesDelFin = new Alquiler(antesDelFinCopia, 3);    // (3-2)*1.5 = 1.5 ---> MAS 2 DE BASE
        alquilerLaOdisea = new Alquiler(laOdiseaCopia, 1);          // (1*3) = 3       ---> MAS 0 DE BASE
        alquilerLaIliada = new Alquiler(laIliadaCopia, 7);          // (7*3) = 21      ---> MAS 0 DE BASE
        alquilerPinocho = new Alquiler(pinochoCopia, 2);            // 2 < 3 --> 0     ---> MAS 1.5 DE BASE
        alquilerCaperucita = new Alquiler(caperucitaCopia, 4);      // 4 > 3 --> 1.5   ---> MAS 1.5 DE BASE

        /*
        CASO REGULARES
        total --> 4.5 + 1.5 = 6 + 4 = 10
        puntos --> 2

        CASO NUEVO LANZAMIENTO
        total --> 3 + 21 = 24
        puntos --> 2 (alquileres) + 0 (NL pero =< 1 dias) + 1 (NL pero >= 1 dias) = 3

        CASO INFANTILES
        total --> (0 + 1.5) + (1.5 + 1.5) = 4.5
        puntos --> 2

        */
    }

    @Test
    public void calcularDeudaYpuntosObtenidos_REGULARES_Test(){

        Cliente cliente = new Cliente("Clara");

        //Exercise
        cliente.alquilar(alquilerElTunel);
        cliente.alquilar(alquilerAntesDelFin);

        resumen = cliente.calcularDeudaYPuntosObtenidos();

        //Verify
        assertEquals(10, resumen.getMontoFinal(), "El total calculado no es correcto");
        assertEquals(2, resumen.getPuntosFrecuente(), "El total calculado no es correcto");

        //assertEquals(10, cliente.totalAcumulado(), "El total calculado no es correcto");
        //assertEquals(2, cliente.puntosAlquilerFrecuente(), "La cantidad de puntos asignados no coincide");
        //Los puntos del alquiler frecuente son por cada alquiler que se hace
    }

    @Test
    public void calcularDeudaYpuntosObtenidos_NUEVO_LANZAMIENTO_Test(){

        Cliente cliente = new Cliente("Pepe");

        //Exercise
        cliente.alquilar(alquilerLaOdisea);
        cliente.alquilar(alquilerLaIliada);

        resumen = cliente.calcularDeudaYPuntosObtenidos();

        //Verify
        assertEquals(24, resumen.getMontoFinal(), "El total calculado no es correcto");
        assertEquals(3, resumen.getPuntosFrecuente(), "La cantidad de puntos asignados no coincide");
    }

    @Test
    public void calcularDeudaYpuntosObtenidos_INFANTILES_Test(){

        Cliente cliente = new Cliente("Fausto");

        //Exercise
        cliente.alquilar(alquilerPinocho);
        cliente.alquilar(alquilerCaperucita);

        resumen = cliente.calcularDeudaYPuntosObtenidos();

        //Verify
        assertEquals(4.5, resumen.getMontoFinal(), "El total calculado no es correcto");
        assertEquals(2, resumen.getPuntosFrecuente(), "La cantidad de puntos asignados no coincide");
    }

}
