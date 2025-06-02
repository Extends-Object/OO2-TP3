package oop2.tp3.ejercicio2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recaudacion2 {

    //CONSTANTES PARA NOMBRES DE COLUMNAS
    public static final String COMPANY_NAME = "company_name";
    public static final String CITY = "city";
    public static final String STATE = "state";
    public static final String ROUND = "round";
    public static final String PERMALINK = "permalink";
    public static final String NUMBER_EMPLOYEES = "number_employees";
    public static final String CATEGORY = "category";
    public static final String FUNDED_DATE = "funded_date";
    public static final String RAISED_AMOUNT = "raised_amount";
    public static final String RAISED_CURRENCY = "raised_currency";

    //CONSTANTES PARA INDICES DE COLUMNAS
    public static final int INDICE_PERMALINK = 0;
    public static final int INDICE_COMPANY_NAME = 1;
    public static final int INDICE_NUMBER_EMPLOYEES = 2;
    public static final int INDICE_CATEGORY = 3;
    public static final int INDICE_CITY = 4;
    public static final int INDICE_STATE = 5;
    public static final int INDICE_FUNDED_DATE = 6;
    public static final int INDICE_RAISED_AMOUNT = 7;
    public static final int INDICE_RAISED_CURRENCY = 8;
    public static final int INDICE_ROUND = 9;

    //private LectorCSV lector;
    private List<String[]> csvData;
    Map<String, String> opcionesFiltrado;
    Map<String, Integer> indicesColumnas;

    public Recaudacion2(Lector lector) {
        this.csvData = lector.recuperarContenido();
        this.indicesColumnas = Map.of(
                PERMALINK, INDICE_PERMALINK,
                COMPANY_NAME, INDICE_COMPANY_NAME,
                NUMBER_EMPLOYEES, INDICE_NUMBER_EMPLOYEES,
                CATEGORY, INDICE_CATEGORY,
                CITY, INDICE_CITY,
                STATE, INDICE_STATE,
                FUNDED_DATE, INDICE_FUNDED_DATE,
                RAISED_AMOUNT, INDICE_RAISED_AMOUNT,
                RAISED_CURRENCY, INDICE_RAISED_CURRENCY,
                ROUND, INDICE_ROUND);
    }

    public List<Map<String, String>> where (Map<String, String> opcionesFiltrado) {

        inicializarOpcionesFiltrado(opcionesFiltrado);

        for (String columna : this.opcionesFiltrado.keySet()){
            List<String[]> results = new ArrayList<String[]>();     //filtra por una columna especifica
                for (String[] csvDatum : csvData) {
                    if (csvDatum[this.indicesColumnas.get(columna)].equals(opcionesFiltrado.get(columna))) {        //antes era un indice concreto
                        results.add(csvDatum);
                    }
                }
            csvData = results;
        }


//        filtrar(COMPANY_NAME);
//        filtrar(CITY);
//        filtrar(STATE);
//        filtrar(ROUND);

        return formatearResultado(csvData);  //salida
    }

    private void inicializarOpcionesFiltrado(Map<String, String> options){
        this.opcionesFiltrado = options;
    }

    private void filtrar(String columna) {
        if (opcionesFiltrado.containsKey(columna)) {
            List<String[]> results = new ArrayList<String[]>();     //filtra por una columna especifica

            for (String[] csvDatum : csvData) {
                if (csvDatum[this.indicesColumnas.get(columna)].equals(opcionesFiltrado.get(columna))) {        //antes era un indice concreto
                    results.add(csvDatum);
                }
            }
            csvData = results;
        }
    }

    private List<Map<String, String>> formatearResultado(List<String[]> csvData){
        List<Map<String, String>> output = new ArrayList<Map<String, String>>();

        for (int i = 0; i < csvData.size(); i++) {
            Map<String, String> mapped = new HashMap<String, String>();
            mapped.put(PERMALINK, csvData.get(i)[INDICE_PERMALINK]);
            mapped.put(COMPANY_NAME, csvData.get(i)[INDICE_COMPANY_NAME]);
            mapped.put(NUMBER_EMPLOYEES, csvData.get(i)[INDICE_NUMBER_EMPLOYEES]);
            mapped.put(CATEGORY, csvData.get(i)[INDICE_CATEGORY]);
            mapped.put(CITY, csvData.get(i)[INDICE_CITY]);
            mapped.put(STATE, csvData.get(i)[INDICE_STATE]);
            mapped.put(FUNDED_DATE, csvData.get(i)[INDICE_FUNDED_DATE]);
            mapped.put(RAISED_AMOUNT, csvData.get(i)[INDICE_RAISED_AMOUNT]);
            mapped.put(RAISED_CURRENCY, csvData.get(i)[INDICE_RAISED_CURRENCY]);
            mapped.put(ROUND, csvData.get(i)[INDICE_ROUND]);
            output.add(mapped);
        }
        return output;
    }

    /*
    PROBLEMAS:
    ---> Where es un metodo "Dios" o "God Object" ---> Se encarga de hacer todo
    ---> Principio de responsabilidad unica X
    ---> Acoplamiento y numeros magicos ---> Acceso a elementos por indice genera numeros
         magicos, si el orden de las columnas cambia en el CSV el codigo se rompe
    ---> Mucho codigo repetido
    ---> Acoplamiento a una ruta especifica de archivo
    ---> Lista mutable?? csvData
    ---> TODOS METODOS ESTATICOS ---> NO PERMITEN HACER USO DE POLIMORFISMO (una de las joyas del paradigma
    ---> Hacer los metodos estaticos como metodos de la instancia para aplicar polimorfismo
    ---> Hacer uso de constantes


LO QUE HICE EN LA SOLUCION ANTERIOR-------------------------------------- LO QUE ARREGLE: lamentablemente todo
    PRIMER PASO: Separar responsabilidades
        ---->Lectura del archivo
        ---->Filtrados
        ---->Conversion de vectores a Mapas

        Empiezo por aplicar Extract Method y extraer en un metodo privado la logica de apertura y lectura del archivo
        ----> ARREGLADO: se pasa esa logica a una clase nueva que implementa una interface para desacoplo e insert de
                         inyecciones por dependencia

    SEGUNDO PASO: Conversion de vectores de String[] a Map<String, String>
        ---->Aplico Extract Method y extraigo esa logica a otro metodo privado

    TERCER PASO: MANEJO DE LA LOGICA DE FILTRADO
        ---->Todos los metodos van a recibir y retornar lo mismo, nada mas que cada filtrado se va a hacer sobre
             el anterior
        ---->Entonces, hago un metodo que use terminos generales:
                -csvData, que es la lista que va a filtrar sucesivamente
                -Clave por la que filtra
                -Ubicacion dentro de los vectores
                -options, que es el mapa que va a filtrar

    RESULTADO DEL REFACTORING:
        - Separar las responsabilidades
        - Reducir repeticiones con el filtrado
        - Mejorar legibilidad (estaba bien feo)
        - Evitar codigo repetido

    SOLUCION QUE PLANTEE MAL:
        csvData = filtrar(csvData, "company_name", 1, options);
        csvData = filtrar(csvData, "city", 4, options);
        csvData = filtrar(csvData, "state", 5, options);
        csvData = filtrar(csvData, "round", 9, options);

        POR QUE ESTA MAL? --> Porque cada vez que llamo al metodo WHERE este llama al metodo FILTRAR, filtrando TODOS
                              los campos, entonces cuando se filtra por segunda vez, tercera vez, ..., aparecen campos
                              nulos, y de esta forma intenta filtrar un conjunto de datos vacio.

        SOLUCION CORRECTA --> SOLO APLICAR EL FILTRO SI EL DATO EXISTE, SINO DA NULO Y SE BORRAN LOS REGISTROS
                              De esta forma se filtra lo que "va quedando"


    ANALISIS DE LA FIRMA DEL METODO
    public static List<Map<String, String>> where (Map<String, String> options) throws IOException
    ---> List<Map<String, String>> ----Tipo de dato que va a retornar "where" (una lista de mapas donde clave y valor
         seran de tipo String)
    ---> WHERE ----NOMBRE DEL METODO (yo confundir yo no entender)
    ---> (Map<String, String> options) ----RECIBE UN MAP LLAMADO OPTIONS


La IA dice this
    CSVReader es una clase (probablemente de una librería externa como com.opencsv) que facilita la lectura de archivos CSV
    (Comma Separated Values). Su función principal es parsear cada línea del archivo y dividirla en un array de strings,
    donde cada string representa un valor separado por comas.

    FileReader es una clase de Java que se utiliza para abrir un archivo en modo lectura, tomando como argumento la ruta
    del archivo que se desea leer. En este caso, se está abriendo el archivo llamado data.csv que se encuentra en la
    ruta "src/main/resources/data.csv".


     CON STREAM:
     private void filtrar(String columna) {
        if (opcionesFiltrado.containsKey(columna)) {
            List<String[]> results = csvData.stream().filter(csvDatum -> csvDatum[this.indicesColumnas.get(columna)].equals(opcionesFiltrado.get(columna))).collect(Collectors.toList());     //filtra por una columna especifica

            //antes era un indice concreto
            csvData = results;
        }
    }

    //var m = new HashMap <String, Integer> ();
    //m.put("", "");
    */
}