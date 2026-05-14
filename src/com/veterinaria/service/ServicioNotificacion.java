package com.veterinaria.service;

import com.veterinaria.interfaces.Notificable;
import com.veterinaria.model.Propietario;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación concreta del servicio de notificaciones por consola.
 *
 * Principio DIP: implementa la abstracción Notificable. GestorCitas y
 * GestorVacunacion dependen de Notificable (la interfaz), no de esta clase.
 * Principio SRP: única responsabilidad → gestionar y enviar notificaciones.
 * Principio OCP: si se necesita enviar por SMS o email, se crea otra
 * implementación de Notificable sin modificar esta ni los gestores.
 */
public class ServicioNotificacion implements Notificable {

    /** Registro interno de todas las notificaciones enviadas. */
    private List<String> historial;
    private int contadorId;

    public ServicioNotificacion() {
        this.historial   = new ArrayList<>();
        this.contadorId  = 1;
    }

    @Override
    public void enviarNotificacion(Propietario propietario, String mensaje, String tipo) {
        String fecha  = java.time.LocalDate.now().toString();
        String registro = "[" + tipo + " #" + contadorId++ + "] " +
                fecha + " → " + propietario.getNombreCompleto() +
                " | " + mensaje;
        historial.add(registro);
        System.out.println("   NOTIFICACIÓN → " + propietario.getNombreCompleto() +
                " (" + tipo + "): " + mensaje);
    }

    /** Muestra todo el historial de notificaciones enviadas. */
    public void mostrarHistorial() {
        System.out.println("\n══ HISTORIAL DE NOTIFICACIONES ══");
        if (historial.isEmpty()) {
            System.out.println("  No se han enviado notificaciones.");
            return;
        }
        for (String n : historial) {
            System.out.println("  • " + n);
        }
    }

    public List<String> getHistorial() { return historial; }
}