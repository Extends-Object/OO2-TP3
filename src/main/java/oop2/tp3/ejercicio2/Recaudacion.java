package oop2.tp3.ejercicio2;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recaudacion {

    public static List<Map<String, String>> where(Map<String, String> options) throws IOException {

        List<String[]> csvData = obtenerContenidoArchivo();         //Extract Method...

        /*
        csvData = filtrar(csvData, "company_name", 1, options);
        csvData = filtrar(csvData, "city", 4, options);
        csvData = filtrar(csvData, "state", 5, options);
        csvData = filtrar(csvData, "round", 9, options);

        */


        //Aplico filtros solo si la opción está presente ---> PARA NO FILTRAR DE MAS
        if (options.containsKey("company_name")) {
            csvData = filtrar(csvData, "company_name", 1, options);
        }
        if (options.containsKey("city")) {
            csvData = filtrar(csvData, "city", 4, options);
        }
        if (options.containsKey("state")) {
            csvData = filtrar(csvData, "state", 5, options);
        }
        if (options.containsKey("round")) {
            csvData = filtrar(csvData, "round", 9, options);
        }



        /*
------------------------------------------------------------------------------------------------------------------------
        if (options.containsKey("company_name")) {          //Busca en el Map "options" una clave "company_name"
            List<String[]> results = new ArrayList<String[]>();    //Crea una coleccion de String[]

            for (int i = 0; i < csvData.size(); i++) {      //Lee todas las filas leidas y guardadas en el csv

                if (csvData.get(i)[1].equals(options.get("company_name"))) {
                    //Esta condicion lee el Vector de Strings en la posicion "i", y toma el elemento [1] del mismo
                    //Compara si el contenido de la Key del elemento es ese que pregunta
                    //Si coincide, agrega el vector a la lista de vectores que se creo afuera del For
                    results.add(csvData.get(i));
                }
            }                                               //Results ahora solo posee los elementos que coinciden
                                                            //con esa Key
            csvData = results;                              //Ahora, csvData apunta a Results
            //Resumen: este bloque de codigo filtra por una Key especifica
        }

        //El map "options" sigue teniendo los mismos elementos, solo filtro algunos por clave
------------------------------------------------------------------------------------------------------------------------
        if (options.containsKey("city")) {                  //Ahora pregunta si options tiene elementos con clave "city"
            List<String[]> results = new ArrayList<String[]>();    //Crea un array vacio de vectores

            for (int i = 0; i < csvData.size(); i++) {      //Recorre el csvData actual, que ya esta filtrado

                if (csvData.get(i)[4].equals(options.get("city"))) {
                    //Aca recorre por cada vector de posicion (i) del csv y pregunta si en la posicion [4]
                    //del vector leido el contenido coincide con la Key "city"
                    //Si cpincide, agrega al vector al array de vectores creado antes del For
                    results.add(csvData.get(i));
                }
            }
            csvData = results;              //Ahora csvData esta filtrado por dos claves diferentes
        }
------------------------------------------------------------------------------------------------------------------------
        if (options.containsKey("state")) {                      //Aplica la misma modalidad de filtro
            List<String[]> results = new ArrayList<String[]>();

            for (int i = 0; i < csvData.size(); i++) {
                if (csvData.get(i)[5].equals(options.get("state"))) {
                    results.add(csvData.get(i));
                }
            }
            csvData = results;
            //csvData posee elementos filtrados con "company_name, city, state"
        }
------------------------------------------------------------------------------------------------------------------------
        if (options.containsKey("round")) {                      //Aplica la misma modalidad de filtro
            List<String[]> results = new ArrayList<String[]>();

            for (int i = 0; i < csvData.size(); i++) {
                if (csvData.get(i)[9].equals(options.get("round"))) {
                    results.add(csvData.get(i));
                }
            }
            csvData = results;
            //csvData posee elementos filtrados con "company_name, city, state, round"
        }
------------------------------------------------------------------------------------------------------------------------
         */

        //FINALIZADOS LOS FILTRADOS, SE CREA UNA NUEVA COLECCION PARA ALMACENAR EL RESULTADO FINAL
        return convertirAcoleccionMapas(csvData);
    }



    private static List<String[]> filtrar (List<String[]> csvData, String clave, int posicionVector, Map<String, String> options){
        List<String[]> results = new ArrayList<String[]>();

        for (int i = 0; i < csvData.size(); i++) {
            if (csvData.get(i)[posicionVector].equalsIgnoreCase(options.get(clave))) {
                results.add(csvData.get(i));
            }
        }
        return results;
    }




    private static List<Map<String, String>> convertirAcoleccionMapas(List<String[]> csvData) {
        List<Map<String, String>> output = new ArrayList<Map<String, String>>();

        for (int i = 0; i < csvData.size(); i++) {                 //Recorre el csvData full filtrado

            Map<String, String> mapped = new HashMap<String, String>();
            //Instancia un HashMap para transformar los String[] de csv a colecciones Map<String, String>
            //Por cada vector de la posicion (i) toma el elemento de [0] como valor y le asigna esa clave

            //           Key       |      Value
            mapped.put("permalink", csvData.get(i)[0]);
            mapped.put("company_name", csvData.get(i)[1]);
            mapped.put("number_employees", csvData.get(i)[2]);
            mapped.put("category", csvData.get(i)[3]);
            mapped.put("city", csvData.get(i)[4]);
            mapped.put("state", csvData.get(i)[5]);
            mapped.put("funded_date", csvData.get(i)[6]);
            mapped.put("raised_amount", csvData.get(i)[7]);
            mapped.put("raised_currency", csvData.get(i)[8]);
            mapped.put("round", csvData.get(i)[9]);

            output.add(mapped);         //Asigna la coleccion de MAPs a la lista de MAPs creada antes
        }
        return output;
    }



    private static List<String[]> obtenerContenidoArchivo() throws IOException {
        List<String[]> csvData = new ArrayList<String[]>();                     //CREA UNA COLECCION DE STRING
        CSVReader reader = new CSVReader(new FileReader("src/main/resources/data.csv"));    //ABRE EL ARCH EN LECTURA
        String[] row = null;                    //Son las lineas que va a leer del archivo


        // Lee el contenido del archivo
        while ((row = reader.readNext()) != null) {         //Lee una fila y la graba en la lista de strings
            csvData.add(row);
        }
        reader.close();                                     //Cierra el flujo del reader
        csvData.remove(0);                            //Elimina el elemento del indice 0 de lo que leyó
        return csvData;
    }

    /*

    PRIMER PASO: Separar responsabilidades
        ---->Lectura del archivo
        ---->Filtrados
        ---->Conversion de vectores a Mapas

        Empiezo por aplicar Extract Method y extraer en un metodo privado la logica de apertura y lectura del archivo

    SEGUNDO PASO: Conversion de vectores de String[] a Map<String, String>
        ---->Aplico Extract Method y extraigo esa logica a otro metodo privado

    TERCER PASO: MANEJO DE LA LOGICA DE FILTRADO
        ---->Todos los metodos van a recibir y retornar lo mismo, nada mas que cada filtrado se va a hacer sobre
             el anterior
        ---->Entonces, hago un tercer metodo que use terminos generales:
                -csvData, que es la lista que va a filtrar sucesivamente
                -Clave por la que filtra
                -Ubicacion dentro de los vectores
                -options, que es el mapa que va a filtrar

    RESULTADO DEL REFACTORING:
        - Separar las responsabilidades
        - Reducir repeticiones con el filtrado
        - Mejorar la legibilidad

    SOLUCION QUE PLANTEE MAL:
        csvData = filtrar(csvData, "company_name", 1, options);
        csvData = filtrar(csvData, "city", 4, options);
        csvData = filtrar(csvData, "state", 5, options);
        csvData = filtrar(csvData, "round", 9, options);

        POR QUE ESTA MAL? --> Porque cada vez que llamo al metodo WHERE este llama al metodo FILTRAR, filtrando TODOS
                              los campos, entonces cuando se filtra por segunda vez, tercera vez, ..., aparecen campos
                              nulos, y de esta forma intenta filtrar un conjunto de datos vacio.

        PROBLEMA: cuando se ejecutan todos los tests juntos los datos se filtran de forma acumulativa,

        SOLUCION CORRECTA --> SOLO APLICAR EL FILTRO SI EL DATO EXISTE, SINO DA NULO Y SE BORRAN LOS REGISTROS
                              De esta forma se filtra lo que "va quedando"


    ANALISIS DE LA FIRMA DEL METODO
    public static List<Map<String, String>> where (Map<String, String> options) throws IOException
    ---> List<Map<String, String>> ----Tipo de dato que va a retornar "where" (una lista de mapas donde clave y valor
         seran de tipo String)
    ---> WHERE ----NOMBRE DEL METODO
    ---> (Map<String, String> options) ----RECIBE UN MAP LLAMADO OPTIONS


    CSVReader es una clase (probablemente de una librería externa como com.opencsv) que facilita la lectura de archivos CSV
    (Comma Separated Values). Su función principal es parsear cada línea del archivo y dividirla en un array de strings,
    donde cada string representa un valor separado por comas.

    FileReader es una clase de Java que se utiliza para abrir un archivo en modo lectura, tomando como argumento la ruta
    del archivo que se desea leer. En este caso, se está abriendo el archivo llamado data.csv que se encuentra en la
    ruta "src/main/resources/data.csv".





    */



}
