package oop2.tp3.ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String name;

    private double totalAcumulado;
    private int puntosAlquilerFrecuente;
    private List<Alquiler> alquileres;


    public Cliente(String nombre) {
        this.name = nombre;
        this.totalAcumulado = 0;
        this.puntosAlquilerFrecuente = 0;
        this.alquileres = new ArrayList<Alquiler>();
    }


    public void alquilar(Alquiler rental) {
        alquileres.add(rental);
    }


    public ResumenCliente calcularDeudaYPuntosObtenidos() {

        //Object[] resultado = new Object[2];
        //Podria reemplazarla con una clase tipo Resumen o ResumenCliente con esos datos para evitar el uso de Object

        for (Alquiler alquiler : alquileres) {

            //Por cada alquiler le suma el monto que vale ---> Puedo aplicar Extract Method?
            //totalAcumulado += calcularTotalAcumulado(alquiler);
            //totalAcumulado += alquiler.copia().libro().genero().calcularMonto(alquiler);

            totalAcumulado += alquiler.calcularTotal();

            //Suma el punto base mas el punto extra si es nuevo lanzamiento ---> Aplico Extract Method :)
            //NO SIRVE DE NADA ----> Igualmente rompe Ley de DEMETER
            //puntosAlquilerFrecuente += calcularPuntosPorAlquilerFrecuente(alquiler);
            //puntosAlquilerFrecuente += alquiler.copia().libro().genero().calcularPuntosPorAlquilerFrecuente(alquiler);

            puntosAlquilerFrecuente += alquiler.calcularPuntosAlquilerFrecuente();
        }

        //resultado[0] = totalAcumulado;
        //resultado[1] = puntosAlquilerFrecuente;

        ResumenCliente resumen = new ResumenCliente(totalAcumulado, puntosAlquilerFrecuente);

        return resumen;
    }

//    private static int calcularPuntosPorAlquilerFrecuente(Alquiler alquiler) {
//        return alquiler.copia().libro().genero().calcularPuntosPorAlquilerFrecuente(alquiler);
//    }
//
//    private static double calcularTotalAcumulado(Alquiler alquiler) {
//        return alquiler.copia().libro().genero().calcularMonto(alquiler);
//    }


    //SOLO PARA EL TEST
    public double totalAcumulado (){
        return  this.totalAcumulado;
    }

    public double puntosAlquilerFrecuente(){ return this.puntosAlquilerFrecuente; }
}


/*
            --- Heuristica del Extract Method? ---> Si el metodo no necesita nada de this entonces puede
                                                          (o debería) ser static
            --- El ide por defecto propone que el metodo extraído sea static si no accede a atributos de instancia

PRIMER PASO: Eliminar el switch con Polimorfismo
    ----->Resuelve: poder agregar nuevos tipos de libros sin tocar la clase Libro
    ----->Principio Abierto/Cerrado — Open/Closed Principle

SEGUNDO PASO: Extraer el codigo de los case del Switch a su metodo abstracto correspondiente
    ----->Extract Method
    ----->Extract Method siempre trabaja localmente. Moverlo a la clase correcta se hace manualmente después
    Alquiler tiene los dias alquilados --> El calculo del monto se hace en base a esa cantidad de dias
    Por eso debe recibir la instancia de Alquiler

    Principio de Responsabilidad Única (SRP): Cada clase de estrategia de precios (Regulares, NuevoLanzamiento, Infantil)
    ahora tiene su propia única responsabilidad que es calcular el precio de alquiler para ese tipo específico de libro.

    Principio Abierto/Cerrado (OCP): Si en el futuro quiero agregar un nuevo tipo de libro con una lógica de precios
    diferente solamente tengo qye crear una nueva clase que implemente GeneroLibro
    No hay que modificar la clase Cliente (que es el que usa esa logica)

TERCER PASO: Cambiar clase Libro para que tenga una instancia del genero y eliminar las constantes que usaba

CUARTO PASO: Eliminar Switch --->
    ----->Tecnica de desacoplamiento

QUINTO PASO: Logica de calculo de puntos frecuentes
    ----->Posible solucion: Transformar interface a clase abstracta y agregar metodo abstracto que calcule esa logica.
                            Las demas clases concretas solo tienen el cuerpo vacio mientras que NuevoLanzamiento
                            tiene la logica desarrollada
    ----->Problema: No respeta el ISP (Principio de segregacion de interface). Si se convierte la interface a clase
                    abstracta y se agrega el metodo abstracto las demas clases se van a ver obligadas a implementarlo
                    aunque tengan cuerpo vacio o retornen 0. (esto les agrega una responsabilidad que no les corresponde
                    tener ?¿).
    ----->Solucion: Dejar a la interface como esta, agregar el metodo e implementar la logica diferente para NuevoLanzamiento.
                    Las demas clases retornaran el mismo valor, (no cambia nada))

SEXTO PASO: Reacomodar el metodo en Cliente.

SEPTIMO PASO: ESTRUCTURA DE LA CLASE TEST
    ----->Ideal: tener un Test por cada Genero ---> Todos aplican diferente logica de calculo
                                               ---> HAY QUE TESTEAR TOODO MAR


*/

