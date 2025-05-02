package oop2.tp3.ejercicio3;
import java.util.List;

public class ReporteDeGastos {

    private int gastosDeComida;
    private int total;

    public ReporteDeGastos() {
        this.total = 0;
        this.gastosDeComida = 0;
    }

    public String imprimir(List<Gasto> gastos, String fecha) {  //Falta formatear

        this.gastosDeComida = (int) calcularMontoComidas(gastos);
        this.total = (int) calcularTotal(gastos);

        for (Gasto gasto : gastos) {
            asignarMarca(gasto);
        }

        return formatearReporte(gastos, fecha);
    }

    public void asignarMarca (Gasto gasto){
            gasto.marcarPorExcesoComidas();
    }


    private String formatearReporte(List<Gasto> listaGastos, String fecha) {
        StringBuilder detalle = new StringBuilder();

        detalle.append("Expenses ").append(fecha).append("\n");

        for(Gasto gasto : listaGastos){
            detalle.append(gasto).append("\n");
        }

        detalle.append("Gastos de comida: ").append(gastosDeComida).append("\n");
        detalle.append("Total de gastos: ").append(total).append("\n");

        return detalle.toString();
    }

    public double calcularMontoComidas (List<Gasto> gastos){
        double gastoComida = 0;
        for(Gasto gasto : gastos){
            if(gasto.tipoGasto().esComida()){
                gastoComida += gasto.monto();
            }
        }
        return gastoComida;
    }

    public double calcularTotal(List<Gasto> gastos) {
        double total = 0;
        for (Gasto gasto : gastos) {
            total += gasto.monto();
        }
        return total;
    }


      /*
      ANTES:

      enum TipoDeGasto {
    CENA, DESAYUNO, ALQUILER_AUTO
}

class Gasto {
    TipoDeGasto tipoGasto;
    int monto;
}

public class ReporteDeGastos {
    public void imprimir(List<Gasto> gastos) {
        int total = 0;
        int gastosDeComida = 0;

        System.out.println("Expenses " + LocalDate.now());

        for (Gasto gasto : gastos) {
            if (gasto.tipoGasto == TipoDeGasto.CENA || gasto.tipoGasto == TipoDeGasto.DESAYUNO) {
                gastosDeComida += gasto.monto;
            }

            String nombreGasto = "";
            switch (gasto.tipoGasto) {
                case CENA:
                    nombreGasto = "Cena";
                    break;
                case DESAYUNO:
                    nombreGasto = "Desayuno";
                    break;
                case ALQUILER_AUTO:
                    nombreGasto = "Alquiler de Autos";
                    break;
            }

            String marcaExcesoComidas = gasto.tipoGasto == TipoDeGasto.CENA && gasto.monto > 5000
                    || gasto.tipoGasto == TipoDeGasto.DESAYUNO && gasto.monto > 1000 ? "X" : " ";

            System.out.println(nombreGasto + "\t" + gasto.monto + "\t" + marcaExcesoComidas);

            total += gasto.monto;
        }

        System.out.println("Gastos de comida: " + gastosDeComida);
        System.out.println("Total de gastos: " + total);
    }
}



      */
}
