package oop2.tp3.ejercicio3.correccion;

import java.time.LocalDate;

public class FormateadorReporte implements Formateador {

    @Override
    public String formatear(ReporteGastos reporte) {
        StringBuilder reporteString = new StringBuilder();
        StringBuilder gastosString = new StringBuilder();

        for (Gasto gasto : reporte.gastos()){
            gastosString.append(gasto);
        }

        reporteString.append("Expenses " + LocalDate.now() + "\n").append(gastosString).append(
                "Gastos de comida: " + reporte.gastosComida() + "\n").append(
                        "Total de gastos: " + reporte.total());

        return String.valueOf(reporteString);
    }
}
