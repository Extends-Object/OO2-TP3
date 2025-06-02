package oop2.tp3.ejercicio2;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV implements Lector{

    //"src/main/resources/data.csv"
    private String ruta;

    public LectorCSV(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public List<String[]> recuperarContenido() {
        List<String[]> csvData = new ArrayList<String[]>();

        try {

            CSVReader reader = new CSVReader(new FileReader(this.ruta));
            String[] row = null;

            while ((row = reader.readNext()) != null) {
                csvData.add(row);           //lee el csv
            }
            reader.close();
            csvData.remove(0);      //remueve el primero
            return csvData;

        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }
}
