package oop2.tp3.ejercicio4;

import org.jdbi.v3.core.Jdbi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PersonaRepository {

    private Jdbi jdbi;

    //CONSTRUCTOR
    public PersonaRepository(Jdbi jdbi) {               //Esta clase cumple el rol de un DAO
        this.jdbi = jdbi;
    }

    /*
    Jdbi ---> Clase del Framework JDBI
         ---> Tiene un metodo WITHHANDLE

    HandleCallback  ---> Es una interface funcional
                    ---> Tiene un unico metodo abstracto withHandle(Handle handle)
                    ---> Puede recibir lambdas o referencias a metodos

    jdbi.withHandle(Handle handle) ---> Este metodo recibe una implementacion de la interface HandleCallback
                                   ---> Abre una conexion (handle), se pasa como lambda, se ejecuta lo que indica el
                                        lambda, cierra el handle automaticamente
    */

    /**
     * Busca por nombre a parte
     */
    public List<Persona> buscarPorNombre(String nombreOParte) {

        /*
        -----------------------------------OPCION 1: RETORNAR EL ARRAY VACIO antes inicializado-------------------------
        return jdbi.withHandle(handle -> {


            //Ejecuta una sentencia SQL para buscar
            var rs = handle.select("select nombre, apellido from persona where nombre like ?")    //EJECUTA UN SELECT
                    .bind(0, "%" + nombreOParte + "%").mapToMap(String.class).list();   //BUSCA LOS QUE TIENEN ESA CADENA

            //.bind(0, valor) ---> En JDBI se usa para reemplazar el marcador de posición ? en la consulta SQL
            // con un valor específico


            var personas = new ArrayList<Persona>();


            if (rs.size() == 0) {           //ANALIZA EL RESULTSET QUE NO SEA NULO
                //return null;              //Resultset es una List<Map<String, String>> osea una lista de filas donde
                return personas;
            }                               //cada una es un Map


            for (Map<String, String> map : rs) {                        //Por cada Map que lee de la lista del Resultset
                personas.add(new Persona(map.get("nombre"), map.get("apellido")));
                //Toma los datos del Map, crea una Persona y la agrega al ArrayList de personas
            }
            //

            return personas;

        });


        --------------------------------------------OPCION 2: USAR STREAM PARA RETORNAR UNA LISTA VACIA-----------------
        */
        return jdbi.withHandle(handle -> {

            var rs = handle.select("select nombre, apellido from persona where nombre like ?")
                    .bind(0, "%" + nombreOParte + "%")
                    .mapToMap(String.class)
                    .list();

            return rs.stream()
                    .map(map -> new Persona(map.get("nombre"), map.get("apellido")))
                    .toList();

            //esto es stream().map().toList() y retorna la lista vacia si no lee nada el resultset
            //.stream() = transforma el resultset a flujo stream
            //.map() = agarra un map del resultset y lo transforma en Persona
            //.toList() = recoge todos esos resultados y los colecta en una lista
            //si no colecta nada, retorna la lista vacia
        });
    }


    /**
     * Dado un id, retorna:
     * - null si el id no se encuentra en la BD
     * - la instancia de Persona encontrada
     */


    public Optional<Persona> buscarId(Long id) {

        /*
        -------------------------------------------------------USO DE OPTIONAL -----------------------------------------
        return jdbi.withHandle(handle -> {          //Recibe el HandleCallback

            var rs = handle.select("select nombre, apellido from persona where id_persona = ?")
                    .bind(0, id).mapToMap(String.class).list();
            //Ejecuta un select que busca por ID

            if (rs.size() == 0) {           //Verifica que encontró algo
                return null;
            }

            return new Persona(rs.get(0).get("nombre"), rs.get(0).get("apellido"));
            //Instancia la persona con los datos del result set y la retorna

        });

        OPTIONAL ---> EVITA NULL POINTER EXCEPTION

        */

        return jdbi.withHandle(handle -> {

            var rs = handle.select("select nombre, apellido from persona where id_persona = ?")
                    .bind(0, id).mapToMap(String.class).list();

            if (rs.size() == 0) {
                return Optional.empty();            //Retorna el Optional vacio
            }

            var map = rs.get(0);     //Toma el map (una linea del resultset) CERO porque el ID ES UNICO y se espera UNICO resultado
            return Optional.of(new Persona(map.get("nombre"), map.get("apellido")));
            //Envuelve a Persona en un Optional porque indica que esta presente el objeto pero podria no estarlo

        });

    }

}
