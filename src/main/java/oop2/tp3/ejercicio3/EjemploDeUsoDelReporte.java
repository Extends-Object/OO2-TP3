package oop2.tp3.ejercicio3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EjemploDeUsoDelReporte {
    public static void main(String[] args) {

        String nombreGasto1 = "Desayuno";
        String nombreGasto2 = "Alquiler de Auto";
        String nombreGasto3 = "Cena";

        TipoDeGasto tipoGasto1 = new Desayuno(nombreGasto1);
        TipoDeGasto tipoGasto2 = new AlquilerAuto(nombreGasto2);
        TipoDeGasto tipoGasto3 = new Cena(nombreGasto3);

        var g1 = new Gasto(tipoGasto1, 1500);
        var g2 = new Gasto(tipoGasto2, 3500);
        var g3 = new Gasto(tipoGasto3, 2500);

        var reporte = new ReporteDeGastos();

        LocalDate date = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fecha = date.format(formato);

        //reporte.imprimir(List.of(g1, g2), fecha);

        System.out.println(reporte.imprimir(List.of(g1, g2, g3), fecha));
    }
}

/*
ANTES:

public class EjemploDeUsoDelReporte {
    public static void main(String[] args) {
        var g1 = new Gasto();
        g1.tipoGasto = TipoDeGasto.DESAYUNO;
        g1.monto =1000;
        var reporte = new ReporteDeGastos();
        reporte.imprimir(List.of(g1));
    }
}

*/