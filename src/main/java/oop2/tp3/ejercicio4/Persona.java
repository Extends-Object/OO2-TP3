package oop2.tp3.ejercicio4;

public record Persona(String nombre, String apellido) {

}

/*

    Un RECORD es una forma compacta de declarar una clase inmutable que SOLO CONTIENE DATOS (desde java 16)

    Automáticamente genera:
    - Los campos private final
    - Constructor
    - Métodos equals, hashCode y toString
    - Métodos getters con los mismos nombres que los campos


*/