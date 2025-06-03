package oop2.tp3.ejercicio3;

import oop2.tp3.ejercicio3.correccion.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Reporte2Test {

    Formateador formateador;

    oop2.tp3.ejercicio3.correccion.Gasto g1;
    oop2.tp3.ejercicio3.correccion.Gasto g2;
    oop2.tp3.ejercicio3.correccion.Gasto g3;

    ReporteGastos reporte;

    @BeforeEach
    public void setUp(){
        formateador = new FormateadorReporte();

        g1 = new Cena(2500);
        g2 = new Desayuno(1500);
        g3 = new AlquilerAuto(3500);

    }

    @Test
    void imprimirReporteSinGastos() {

        reporte = new ReporteGastos(List.of(), formateador);

        String esperado = "Expenses " + LocalDate.now() + "\n" +
                "Gastos de comida: 0\n" +
                "Total de gastos: 0";
        assertEquals(esperado, reporte.generarReporte());
    }

    @Test
    public void imprimirReporteTodosLosGastos(){

        reporte = new ReporteGastos(List.of(g1, g2, g3), formateador);

        String esperado = "Expenses " + LocalDate.now() + "\n" +
                "Cena\t2500\t \n" +
                "Desayuno\t1500\tX\n" +
                "AlquilerAuto\t3500\t \n" +
                "Gastos de comida: 4000\n" +
                "Total de gastos: 7500";
        assertEquals(esperado, reporte.generarReporte());
    }

    @Test
    public void imprimirReporteSoloComidas(){

        reporte = new ReporteGastos(List.of(g1, g2), formateador);

        String esperado = "Expenses " + LocalDate.now() + "\n" +
                "Cena\t2500\t \n" +
                "Desayuno\t1500\tX\n" +
                "Gastos de comida: 4000\n" +
                "Total de gastos: 4000";
        assertEquals(esperado, reporte.generarReporte());
    }

    @Test
    public void imprimirReporteCena(){

        reporte = new ReporteGastos(List.of(g1), formateador);

        String esperado = "Expenses " + LocalDate.now() + "\n" +
                "Cena\t2500\t \n" +
                "Gastos de comida: 2500\n" +
                "Total de gastos: 2500";
        assertEquals(esperado, reporte.generarReporte());
    }

    @Test
    public void imprimirReporteAlquilerAuto(){

        reporte = new ReporteGastos(List.of(g3), formateador);

        String esperado = "Expenses " + LocalDate.now() + "\n" +
                "AlquilerAuto\t3500\t \n" +
                "Gastos de comida: 0\n" +
                "Total de gastos: 3500";
        assertEquals(esperado, reporte.generarReporte());
    }

}
