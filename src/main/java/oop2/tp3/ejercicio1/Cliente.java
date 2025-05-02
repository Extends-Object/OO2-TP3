package oop2.tp3.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private double totalAcumulado = 0;

    private List<Alquiler> alquileres = new ArrayList<Alquiler>();      //Que es esta notacion?
    private String name;

    int puntosAlquilerFrecuente = 0;

    //constructor
    public Cliente(String nombre) {
        this.name = nombre;
    }

    //Rellena la lista de alquileres
    public void alquilar(Alquiler rental) {                 //Agrega los alquileres a la lista del cliente
        alquileres.add(rental);
    }


    //Calculador
    public Object[] calcularDeudaYPuntosObtenidos() {

        Object[] resultado = new Object[2];     //Crea un object con campos para el total y los puntos frecuentes
        double total = 0;                       //Esto esta bien ---- Es el acumulador
        //int puntosAlquilerFrecuente = 0;        //Esto esta bien


        for (Alquiler alquiler : alquileres) {      //Recorre la lista de alquileres

            //Por cada alquiler, le suma el monto que vale
            total += alquiler.copia().libro().genero().calcularMonto(alquiler);
            //Suma el punto base mas un punto extra si es nuevo lanzamiento
            puntosAlquilerFrecuente += alquiler.copia().libro().genero().calcularPuntosPorAlquilerFrecuente(alquiler);

            /*
            double monto = 0;                       //Monto actual
            // determine amounts for each line
            switch (alquiler.copia().libro().codigoPrecio()) {              //Obtiene el precio del libro
                case Libro.REGULARES:                                       //El codigo de precio es la constante
                    monto = Regulares.calcularMonto(alquiler);
                    break;
                case Libro.NUEVO_LANZAMIENTO:                               //No agrega puntos extra al monto
                    monto += NuevoLanzamiento.calcularMonto(alquiler);      //Calcula como esta
                    break;
                case Libro.INFANTILES:
                    monto = Infantiles.calcularMonto(alquiler, monto);
                    break;
            }
            total += monto;                                                 //El total acumula el monto de cada alquiler

            // sumo puntos por alquiler
            puntosAlquilerFrecuente++;                                      //Suma puntos por alquiler

            // bonus por dos días de alquiler de un nuevo lanzamiento
            if ((alquiler.copia().libro().codigoPrecio() == Libro.NUEVO_LANZAMIENTO)
                    && alquiler.diasAlquilados() > 1) {                     //Suma un punto extra por nuevo lanzamiento
                puntosAlquilerFrecuente++;
            }
            */

        }

        totalAcumulado = total;

        resultado[0] = total;                                               //Van al array de Object
        resultado[1] = puntosAlquilerFrecuente;
        return resultado;                                                   //Retorna el Array de Object con los datos
    }


    //SOLO PARA EL TEST
    public double totalAcumulado (){
        return  this.totalAcumulado;
    }

}


/*
PRIMER PASO: Eliminar el switch por Polimorfismo.
    ----->Resuelve: poder agregar nuevos tipos de libros sin tocar la clase Libro y sin tocar el Switch de Cliente
    ----->Principio Abierto/Cerrado — Open/Closed Principle

SEGUNDO PASO: Extraer el codigo de los case del Switch a su metodo abstracto correspondiente
    ----->Extract Method
    ----->Extract Method siempre trabaja localmente. Moverlo a la clase correcta se hace manualmente después
    Alquiler tiene los dias alquilados --> El calculo del monto se hace en base a esa cantidad de dias
    Por eso debe recibir la instancia de Alquiler

    Principio de Responsabilidad Única (SRP): Cada clase de estrategia de precios (Regulares, NuevoLanzamiento, Infantil)
    ahora tiene una única responsabilidad: calcular el precio de alquiler para un tipo específico de libro.

    Principio Abierto/Cerrado (OCP): Si en el futuro se necesita agregar un nuevo tipo de libro con una lógica de precios
    diferente, crear una nueva clase que implemente GeneroLibro.
    No hay que modificar la clase Cliente (que es quien utiliza estas estrategias).

TERCER PASO: Cambiar clase Libro para que tenga una instancia del genero y eliminar las constantes que usaba

CUARTO PASO: Eliminar Switch ---> Aun mantiene una dependencia con las constantes eliminadas
    ----->Tecnica de desacoplamiento

QUINTO PASO: Logica de calculo de puntos frecuentes
    ----->Posible solucion: Transformar interface a clase abstracta y agregar metodo abstracto que calcule esa logica.
                            Las demas clases concretas solo poseen el cuerpo vacio mientras que NuevoLanzamiento
                            posee la logica desarrollada.
    ----->Problema: No respeta el ISP (Principio de segregacion de interface). Si se convierte la interface a clase
                    abstracta y se agrega el metodo abstracto las demas clases se van a ver obligadas a implementarlo
                    aunque tengan cuerpo vacio o retornen 0. Esto les agrega una responsabilidad que no les corresponde
                    tener.
    ----->Solucion: Dejar a la interface como esta, agregar el metodo e implementar la logica diferente para NuevoLanzamiento.
                    Las demas clases retornaran el mismo valor, no cambiara en nada.

SEXTO PASO: Reacomodar el metodo en Cliente.

SEPTIMO PASO: ESTRUCTURA DE LA CLASE TEST
    ----->Ideal: tener un Test por cada Genero ---> Todos aplican diferente logica de calculo


*/

