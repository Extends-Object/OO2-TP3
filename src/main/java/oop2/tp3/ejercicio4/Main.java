package oop2.tp3.ejercicio4;

import org.jdbi.v3.core.Jdbi;

public class Main {

    public static void main(String[] args) {

        Jdbi jdbi = Jdbi.create("jdbc:hsqldb:mem;create=true");
        //crea una instancia de Jdbi conectada a una base de datos en memoria usando HSQLDB (HyperSQL DataBase)

        new SetUpDatabase(jdbi).setUp();                    //Llama a setUp que limpia, crea tabla e inserta 3 registros

        var repo = new PersonaRepository(jdbi);             //Tiene un metodo que busca por nombre

        var personas = repo.buscarPorNombre("Vla");  //Ejecuta metodo que busca una persona por fragmento de texto

        /*
        if (personas != null) {                                  //Si retorna algo el resultset imprime lo que encontró
            for (Persona persona : personas) {
                System.out.println(persona.nombre() + " " + persona.apellido());
            }
        }
        */

        for (Persona persona : personas) {
            System.out.println(persona.nombre() + " " + persona.apellido());
        }


        var persona = repo.buscarId(1L);                                        //Llama a buscar por el ID
        persona.ifPresent(p -> System.out.println(p.nombre() + " " + p.apellido()));
        //ifPresent() recibe un CONSUMER
        //SI EL OPTIONAL ESTA VACIO NO HACE NADA


        /*
        if (persona != null) {                                                  //Si encuentra algo lo imprime
            System.out.println(persona.nombre() + " " + persona.apellido());
        }
        */

        //System.out.println(persona.get().nombre() + " " + persona.get().apellido());
        //SI USO Optional.get() SIN VERIFICAR SI EL OPTIONAL TIENE VALOR ---> Si no se encuentra la persona con ID 1
        // get() lanza una excepción (NoSuchElementException)
        /*
        SOLUCION ---> ifPresent(), orElse(), orElseThrow()

        System.out.println(persona.orElse(new Persona("Desconocido", "Desconocido")).nombre() + " " +
                           persona.orElse(new Persona("Desconocido", "Desconocido")).apellido());

        Persona p = persona.orElseThrow(() -> new RuntimeException("Persona no encontrada"));
                    System.out.println(p.nombre() + " " + p.apellido());

             IF PRESENT != IS PRESENT -----> NO CONFUNDIR
                     - En este caso: isPresent() solo devuelve un boolean que indica si hay un valor, verdadero si hay
                       algo y falso si el Optional esta vacio, no permite hacer acciones directas sobre el Optional
                       porque se estaria intentando hacer cosas sobre algo que puede estar vacio
        */


    }

    /*

    -----------------------------------------------  NOTAS  ------------------------------------------------------------

    ¿Que es HSQLDB? ---> HSQLDB (HyperSQL DataBase) es un sistema de gestión de bases de datos relacional (RDBMS)
    escrito en Java

    ¿Que es JDBI? ---> Una librería de Java que facilita el acceso a bases de datos relacionales mediante JDBC,
    simplificando el código respecto al uso directo de Connection, PreparedStatement, etc

    Jdbi.create (URL) --> Crea la instancia usando una URL de conexion JDBC

    URL = "jdbc:hsqldb:mem;create=true"
        -   jdbc:hsqldb:mem = indica que es una base de datos HSQLDB que vive en memoria (NO SE GUARDA EN DISCO)
        -   create=true = si la BD no existe se debe crear automaticamente

    En resumen ----> Se inicializa una BD en memoria con HSQLDB. El objeto jdbi permite hacer consultas SQL
    Ejemplo:
    jdbi.useHandle(handle -> {
        handle.execute("CREATE TABLE ejemplo (id INT PRIMARY KEY, nombre VARCHAR(50))");
    });

    --------------------------------------------  RESOLUCION  ----------------------------------------------------------

    PRIMER PASO ---> RECONOCER LO QUE RETORNA NULL
    SEGUNDO PASO --> Soluciones:
                        - Para COLECCIONES: retornar una coleccion vacia
                        - Para OBJETOS: uso de optional


    */
}

