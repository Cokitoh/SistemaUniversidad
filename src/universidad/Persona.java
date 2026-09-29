/**
 * UNIVERSIDAD DE LA CUENCA DEL PLATA
 * INGENIERIA EN SISTEMAS DE INFORMACION
 * MATERIA: PARADIGMAS Y LENGUAJES DE PROGRAMACION II
 * TRABAJO PRACTICO AE1 EVALUATIVO
 * INTEGRANTES: MORINIGO SERGIO, BENITEZ MAXIMO
 * CURSO: 2° "B"
 */
package universidad;

/**
 * Clase abstracta que representa a una persona dentro del sistema.
 * Coordinador, Profesor y Alumno heredan de esta clase (generalización).
 */
public abstract class Persona {

    protected String nombre;
    protected String apellido;
    protected String dni;

    public Persona(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    @Override
    public String toString() {
        return getNombreCompleto() + " (DNI: " + dni + ")";
    }
}
