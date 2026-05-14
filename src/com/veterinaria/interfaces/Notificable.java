package com.veterinaria.interfaces;

import com.veterinaria.model.Propietario;

/**
 * Abstracción para el envío de notificaciones a propietarios.
 * Principio DIP: los servicios de alto nivel (GestorCitas, GestorVacunacion)
 * dependen de esta abstracción, no de una implementación concreta (email, SMS).
 * Principio ISP: interfaz específica solo para notificaciones.
 */
public interface Notificable {

    /**
     * Envía una notificación al propietario de una mascota.
     * @param propietario destinatario de la notificación
     * @param mensaje     contenido del mensaje
     * @param tipo        tipo de notificación (CITA, VACUNA, RECORDATORIO, etc.)
     */
    void enviarNotificacion(Propietario propietario, String mensaje, String tipo);
}