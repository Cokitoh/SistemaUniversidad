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
 * Resultado de aplicar una regla de validación de inscripción.
 * Indica si la regla se cumple y, en caso contrario, el motivo del rechazo.
 */
public final class ResultadoValidacion {

    private final boolean valido;
    private final String motivo;

    private ResultadoValidacion(boolean valido, String motivo) {
        this.valido = valido;
        this.motivo = motivo;
    }

    public static ResultadoValidacion aprobado() {
        return new ResultadoValidacion(true, "");
    }

    public static ResultadoValidacion rechazado(String motivo) {
        return new ResultadoValidacion(false, motivo);
    }

    public boolean esValido() {
        return valido;
    }

    public String getMotivo() {
        return motivo;
    }
}
