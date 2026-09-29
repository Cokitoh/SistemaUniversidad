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
 * Coordinador de una Carrera. Hereda de Persona.
 */
public class Coordinador extends Persona {

    public Coordinador(String nombre, String apellido, String dni) {
        super(nombre, apellido, dni);
    }

    @Override
    public String toString() {
        return "Coordinador: " + super.toString();
    }
}
