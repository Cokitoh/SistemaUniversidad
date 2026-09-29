/**
 * UNIVERSIDAD DE LA CUENCA DEL PLATA
 * INGENIERIA EN SISTEMAS DE INFORMACION
 * MATERIA: PARADIGMAS Y LENGUAJES DE PROGRAMACION II
 * ACTIVIDAD DE EVALUACION A2 (INDIVIDUAL) - Extension de la solucion grupal AE1
 * ALUMNO: BENITEZ MAXIMO
 * CURSO: 2° "B"
 */
package universidad;

/**
 * Patrón Chain of Responsibility - rol Handler.
 * Cada eslabón verifica una regla de inscripción. Si la regla falla, la cadena
 * se corta y se devuelve el motivo; si se cumple, se delega en el siguiente
 * eslabón. Agregar una regla nueva no exige modificar las existentes.
 */
public abstract class ValidadorInscripcion {

    private ValidadorInscripcion siguiente;

    /**
     * Enlaza el siguiente eslabón y lo devuelve, lo que permite armar la
     * cadena de forma fluida: a.setSiguiente(b).setSiguiente(c).
     */
    public ValidadorInscripcion setSiguiente(ValidadorInscripcion siguiente) {
        this.siguiente = siguiente;
        return siguiente;
    }

    /**
     * Aplica la regla propia y, si se cumple, continúa con el resto de la cadena.
     */
    public final ResultadoValidacion validar(Alumno alumno, Materia materia) {
        ResultadoValidacion resultado = verificar(alumno, materia);
        if (!resultado.esValido() || siguiente == null) {
            return resultado;
        }
        return siguiente.validar(alumno, materia);
    }

    /**
     * Regla concreta que implementa cada eslabón.
     */
    protected abstract ResultadoValidacion verificar(Alumno alumno, Materia materia);
}
