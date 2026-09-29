/**
 * UNIVERSIDAD DE LA CUENCA DEL PLATA
 * INGENIERIA EN SISTEMAS DE INFORMACION
 * MATERIA: PARADIGMAS Y LENGUAJES DE PROGRAMACION II
 * ACTIVIDAD DE EVALUACION A2 (INDIVIDUAL) - Extension de la solucion grupal AE1
 * ALUMNO: BENITEZ MAXIMO
 * CURSO: 2° "B"
 */
package universidad;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase asociativa entre Alumno y Materia.
 * Representa la inscripción de un Alumno a una Materia puntual, con su estado
 * de cursado y la cantidad de inasistencias registradas.
 *
 * Es el Sujeto del patrón Observer: publica un evento cada vez que la
 * inscripción se confirma, se registra una inasistencia, se supera el límite
 * de inasistencias o se finaliza el cursado.
 */
public class Inscripcion implements Sujeto {

    private Alumno alumno;
    private Materia materia;
    private EstadoCursado estado;
    private int cantidadInasistencias;
    private final List<Observador> observadores;

    public Inscripcion(Alumno alumno, Materia materia) {
        this.alumno = alumno;
        this.materia = materia;
        this.estado = EstadoCursado.CURSANDO;
        this.cantidadInasistencias = 0;
        this.observadores = new ArrayList<>();
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public EstadoCursado getEstado() {
        return estado;
    }

    public int getCantidadInasistencias() {
        return cantidadInasistencias;
    }

    public boolean estaCursando() {
        return this.estado == EstadoCursado.CURSANDO;
    }

    // ---------------------------------------------------------------
    // Sujeto (Observer)
    // ---------------------------------------------------------------

    @Override
    public void agregarObservador(Observador observador) {
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    @Override
    public void quitarObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores(EventoInscripcion evento) {
        // Se itera sobre una copia por si un observador se da de baja al ser notificado.
        for (Observador o : new ArrayList<>(observadores)) {
            o.actualizar(evento);
        }
    }

    // ---------------------------------------------------------------
    // Operaciones de negocio
    // ---------------------------------------------------------------

    /**
     * Publica la confirmación de la inscripción. Se invoca una vez que los
     * observadores ya están suscriptos.
     */
    public void confirmar() {
        notificarObservadores(new EventoInscripcion(TipoEvento.INSCRIPCION_CONFIRMADA, this,
                "Inscripción confirmada en \"" + materia.getNombre() + "\" ("
                + materia.getCarrera().getNombre() + ")."));
    }

    /**
     * Carga la situación final. Sólo es posible mientras el alumno está
     * CURSANDO: una vez finalizado, el estado es definitivo.
     */
    public boolean finalizarCursado(EstadoCursado estadoFinal) {
        if (!estaCursando()) {
            System.out.println("El cursado de \"" + materia.getNombre()
                    + "\" ya está finalizado (estado actual: " + estado + ").");
            return false;
        }
        if (estadoFinal == EstadoCursado.CURSANDO) {
            System.out.println("La situación final no puede ser CURSANDO.");
            return false;
        }
        this.estado = estadoFinal;
        notificarObservadores(new EventoInscripcion(TipoEvento.CURSADO_FINALIZADO, this,
                "Cursado de \"" + materia.getNombre() + "\" finalizado: " + estadoFinal + "."));
        return true;
    }

    /**
     * Suma una inasistencia. Sólo es posible mientras el alumno está CURSANDO.
     * Si se supera el límite de inasistencias de la materia, la inscripción
     * pasa automáticamente a LIBRE.
     */
    public boolean sumarInasistencia() {
        if (!estaCursando()) {
            System.out.println("No se pueden registrar inasistencias: el cursado de \""
                    + materia.getNombre() + "\" ya está finalizado (estado actual: " + estado + ").");
            return false;
        }
        this.cantidadInasistencias++;
        notificarObservadores(new EventoInscripcion(TipoEvento.INASISTENCIA_REGISTRADA, this,
                "Inasistencia N° " + cantidadInasistencias + " registrada en \"" + materia.getNombre()
                + "\" (límite permitido: " + materia.getLimiteInasistencias() + ")."));

        if (cantidadInasistencias > materia.getLimiteInasistencias()) {
            this.estado = EstadoCursado.LIBRE;
            notificarObservadores(new EventoInscripcion(TipoEvento.LIMITE_INASISTENCIAS_SUPERADO, this,
                    alumno.getNombreCompleto() + " superó el límite de " + materia.getLimiteInasistencias()
                    + " inasistencias en \"" + materia.getNombre() + "\" y quedó LIBRE."));
        }
        return true;
    }

    @Override
    public String toString() {
        return alumno.getNombreCompleto() + " (Legajo " + alumno.getLegajo() + ") - "
                + materia.getNombre() + " - Estado: " + estado
                + " - Inasistencias: " + cantidadInasistencias;
    }
}
