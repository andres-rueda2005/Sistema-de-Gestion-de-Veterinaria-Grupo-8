package com.veterinaria.interfaces;

/**
 * Contrato para entidades que pueden programar y cancelar citas.
 * Principio ISP: separada de AtencionMedica porque no toda entidad
 * que agenda citas necesariamente recibe atención médica directa.
 * Principio DIP: los servicios de alto nivel dependen de esta abstracción.
 */
public interface Agendable {

    /**
     * Programa una cita médica.
     * @param fecha fecha de la cita (yyyy-MM-dd)
     * @param hora  hora de la cita (HH:mm)
     * @param motivo motivo de la consulta
     * @return true si la cita fue programada exitosamente
     */
    boolean programarCita(String fecha, String hora, String motivo);

    /**
     * Cancela una cita previamente programada.
     */
    void cancelarCita();

}
