package oop2.tp3.ejercicio4;

import org.jdbi.v3.core.Jdbi;

public class SetUpDatabase {

    private Jdbi jdbi;

    public SetUpDatabase(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public void setUp() {
        jdbi.useTransaction(handle -> {

            //LIMPIA BD
            handle.execute("DROP SCHEMA PUBLIC CASCADE");
            //Limpia lo que haya en la BD para arrancar con la BD vacia cada que se llama a SET UP


            //CREA TABLA
            handle.execute("CREATE TABLE persona (id_persona INT NOT NULL "
                    + "primary key generated always as identity (start with 1,increment by 1), "
                    + "nombre VARCHAR(255), apellido VARCHAR(255))");
            //Crea una tabla Persona y la configura


            //INSERTS
            handle.createUpdate("INSERT INTO persona (nombre, apellido) VALUES (?, ?)")
                    .bind(0, "José").bind(1, "Laurenti").execute();
            //Inserta un registro de Persona

            handle.createUpdate("INSERT INTO persona (nombre, apellido) VALUES (?, ?)")
                    .bind(0, "Esteban").bind(1, "Otermon").execute();
            //Inserta otra Persona

            handle.createUpdate("INSERT INTO persona (nombre, apellido) VALUES (?, ?)")
                    .bind(0, "Vladimir").bind(1, "Varkov").execute();
            //Inserta otra persona

        });
    }
}
