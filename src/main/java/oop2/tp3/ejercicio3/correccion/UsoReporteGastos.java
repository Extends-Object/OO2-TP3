package oop2.tp3.ejercicio3.correccion;

import java.util.List;

public class UsoReporteGastos {
    public static void main(String[] args) {

        Formateador formateador = new FormateadorReporte();

        var g1 = new Cena(1000);
        var g2 = new Desayuno(2000);
        var g3 = new AlquilerAuto(3500);

        List<Gasto> gastos = List.of(g1, g2, g3);

        var reporte = new ReporteGastos(gastos, formateador);
        System.out.printf(reporte.generarReporte());
    }
}