package oop2.tp3.ejercicio3;

import oop2.tp3.ejercicio3.resolucion_anterior.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReporteTest {

    String nombreDesayuno, nombreCena, nombreAlquiler;
    TipoDeGasto desayuno1, desayuno2, cena1, cena2, alquilerAuto;
    Gasto gasto1a, gasto1b, gasto2a, gasto2b, gasto3;

    ReporteDeGastos reporte;

    LocalDate date;
    DateTimeFormatter formato;
    String fecha;

    List<Gasto> listaGastos;


    @BeforeEach
    public void setUp (){

        nombreDesayuno = "Desayuno";
        nombreCena = "Cena";
        nombreAlquiler = "Alquiler de auto";

        desayuno1 = new Desayuno(nombreDesayuno);
        desayuno2 = new Desayuno(nombreDesayuno);
        cena1 = new Cena(nombreCena);
        cena2 = new Cena(nombreCena);
        alquilerAuto = new AlquilerAuto(nombreAlquiler);

        gasto1a = new Gasto(desayuno1, 500);
        gasto1b = new Gasto(desayuno2, 1500);
        gasto2a = new Gasto(cena1, 6000);
        gasto2b = new Gasto(cena2, 3150);
        gasto3 = new Gasto(alquilerAuto, 3500);

        reporte = new ReporteDeGastos();

        date = LocalDate.of(2025, 04, 20);
        formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fecha = date.format(formato);

        listaGastos = new ArrayList<>();
        listaGastos.add(gasto1a);
        listaGastos.add(gasto1b);
        listaGastos.add(gasto2a);
        listaGastos.add(gasto2b);
        listaGastos.add(gasto3);
    }


    @Test
    public void imprimirTest () {

        //Exercise
        String result = reporte.imprimir(listaGastos, fecha);

        //Verify
        assertEquals("Expenses 20/04/2025\nDesayuno\t500\t \nDesayuno\t1500\tX\nCena\t6000\tX\nCena\t3150\t \nAlquiler de auto\t3500\t \nGastos de comida: 11150\nTotal de gastos: 14650\n", result);

    }

    @Test
    public void asignarMarcaTest(){

        //Exercise
        reporte.asignarMarca(gasto1a);
        reporte.asignarMarca(gasto1b);
        reporte.asignarMarca(gasto2a);
        reporte.asignarMarca(gasto2b);
        reporte.asignarMarca(gasto3);

        //Verify
        assertEquals(" ", gasto1a.marcaExcesoComida());
        assertEquals("X", gasto1b.marcaExcesoComida());
        assertEquals("X", gasto2a.marcaExcesoComida());
        assertEquals(" ", gasto2b.marcaExcesoComida());
        assertEquals(" ", gasto3.marcaExcesoComida());
    }


    @Test
    public void calcularMontoComidasTest(){
        int monto = 0;
        //Exercise
        monto = (int) reporte.calcularMontoComidas(listaGastos);

        //Verify
        assertEquals(11150, monto);
    }

    @Test
    public void calcularTotalTest(){
        int monto = 0;

        //Exercise
        monto = (int) reporte.calcularTotal(listaGastos);

        //Verify
        assertEquals(14650, monto);
    }

}
