package oop2.tp3.ejercicio4;

import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PersonaRepositoryTest {
    Jdbi jdbi;
    PersonaRepository repo;
    List<Persona> personas;
    Optional<Persona> persona;

    @BeforeEach
    public void setUp(){

        jdbi = Jdbi.create("jdbc:hsqldb:mem;create=true");
        new SetUpDatabase(jdbi).setUp();
        repo = new PersonaRepository(jdbi);
        //personas = new ArrayList<>();
    }


    @Test
    public void buscarPorNombreTest_encuentra(){

        //Exercise
        personas = repo.buscarPorNombre("Vla");

        //Verify
        assertFalse(personas.isEmpty());
        assertEquals("Vladimir", personas.get(0).nombre());

    }

    @Test
    public void buscarPorNombreTest_no_encuentra(){
        //Exercise
        personas = repo.buscarPorNombre("Vle");     //No existe

        //Verify
        assertTrue(personas.isEmpty());
    }

    @Test
    public void buscarIdTest_existe(){

        //Exercise
        persona = repo.buscarId(1L);

        //Verify
        assertTrue(persona.isPresent());        //Retorna booleano es != de ifPresent
    }

    @Test
    public void buscarIdTest_no_existe(){

        //Exercise
        persona = repo.buscarId(4L);        //No existe, solo hay 3 personas en el setUp

        //Verify
        assertFalse(persona.isPresent());

    }

}
