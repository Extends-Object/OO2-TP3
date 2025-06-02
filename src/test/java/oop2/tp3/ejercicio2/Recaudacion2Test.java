package oop2.tp3.ejercicio2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Recaudacion2Test {

    @Test
    public void testWhereGivenCompany() throws IOException {
        Map<String, String> options = new HashMap<String, String>();
        options.put("company_name", "Facebook");
        Recaudacion2 r = new Recaudacion2(new LectorCSV("src/main/resources/data.csv"));
        //assertEquals(Recaudacion2.where(options).size(), 7);
        assertEquals(r.where(options).size(), 7);
    }

    @Test
    public void testWhereGivenCity() throws IOException {
        Map<String, String> options = new HashMap<String, String>();
        options.put("city", "Tempe");
        Recaudacion2 r = new Recaudacion2(new LectorCSV("src/main/resources/data.csv"));
        //assertEquals(Recaudacion2.where(options).size(), 3);
        assertEquals(r.where(options).size(), 3);
    }

    @Test
    public void testWhereGivenState() throws IOException {
        Map<String, String> options = new HashMap<String, String>();
        options.put("state", "CA");
        Recaudacion2 r = new Recaudacion2(new LectorCSV("src/main/resources/data.csv"));
        //assertEquals(Recaudacion2.where(options).size(), 873);
        assertEquals(r.where(options).size(), 873);
    }

    @Test
    public void testWhereGivenRound() throws IOException {
        Map<String, String> options = new HashMap<String, String>();
        options.put("round", "a");
        Recaudacion2 r = new Recaudacion2(new LectorCSV("src/main/resources/data.csv"));
        //assertEquals(Recaudacion2.where(options).size(), 582);
        assertEquals(r.where(options).size(), 582);
    }

    @Test
    public void testMultipleOptions() throws IOException {
        Map<String, String> options = new HashMap<String, String>();
        options.put("round", "a");
        options.put("company_name", "Facebook");
        Recaudacion2 r = new Recaudacion2(new LectorCSV("src/main/resources/data.csv"));
        //assertEquals(Recaudacion2.where(options).size(), 1);
        assertEquals(r.where(options).size(), 1);
    }

    @Test
    public void testWhereNotExists() throws IOException {
        Map<String, String> options = new HashMap<String, String>();
        options.put("company_name", "NotFacebook");
        Recaudacion2 r = new Recaudacion2(new LectorCSV("src/main/resources/data.csv"));
        //assertEquals(Recaudacion2.where(options).size(), 0);
        assertEquals(r.where(options).size(), 0);
    }

    @Test
    public void testWhereCorrectKeys() throws IOException {
        Map<String, String> options = new HashMap<String, String>();
        options.put("company_name", "Facebook");
        Recaudacion2 r = new Recaudacion2(new LectorCSV("src/main/resources/data.csv"));
        //Map<String, String> row = Recaudacion2.where(options).get(0);
        Map<String, String> row = r.where(options).get(0);

        assertEquals(row.get("permalink"), "facebook");
        assertEquals(row.get("company_name"), "Facebook");
        assertEquals(row.get("number_employees"), "450");
        assertEquals(row.get("category"), "web");
        assertEquals(row.get("city"), "Palo Alto");
        assertEquals(row.get("state"), "CA");
        assertEquals(row.get("funded_date"), "1-Sep-04");
        assertEquals(row.get("raised_amount"), "500000");
        assertEquals(row.get("round"), "angel");

    }
}