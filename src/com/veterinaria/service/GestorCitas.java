package com.veterinaria.service;

import com.veterinaria.interfaces.Notificable;
import com.veterinaria.model.Animal;
import com.veterinaria.model.Cita;
import com.veterinaria.model.Propietario;
import com.veterinaria.model.Veterinario;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona el ciclo de vida completo de las citas veterinarias.
 *
 * Principio SRP: única responsabilidad → administrar citas.
 * Principio DIP: depende de la abstracción Notificable, no de una
 *               implementación concreta de notificaciones.
 */
public class GestorCitas {

    private List<Cita>  citas;
    private Notificable servicioNotificacion;
    private int         contadorId;

    /**
     * Constructor con inyección de dependencia (DIP).
     * @param servicioNotificacion abstracción para notificar propietarios
     */
    public GestorCitas(Notificable servicioNotificacion) {
        this.citas                = new ArrayList<>();
        this.servicioNotificacion = servicioNotificacion;
        this.contadorId           = 1;
    }

    //  Operaciones principales

    /**
     * Programa una nueva cita, notificando al propietario.
     * Polimorfismo: recibe cualquier subclase de Animal.
     */
    public Cita programarCita(Animal animal, Veterinario veterinario,
                              Propietario propietario,
                              String fecha, String hora, String motivo) {
        if (!veterinario.isActivo()) {
            System.out.println("⚠ El veterinario " + veterinario.getNombreCompleto() +
                    " no está activo. Cita no programada.");
            return null;
        }

        Cita cita = new Cita(contadorId++, fecha, hora, motivo,
                "PROGRAMADA", animal, veterinario);
        citas.add(cita);

        // DIP en acción: usa la abstracción Notificable
        servicioNotificacion.enviarNotificacion(propietario,
                "Cita programada para " + animal.getNombre() +
                        " el " + fecha + " a las " + hora +
                        " con Dr. " + veterinario.getNombreCompleto(),
                "CITA");

        System.out.println("✔ Cita #" + cita.getIdCita() + " programada.");
        return cita;
    }

    /**
     * Cancela una cita y notifica al propietario.
     */
    public void cancelarCita(Cita cita, Propietario propietario) {
        if (!"PROGRAMADA".equals(cita.getEstado())) {
            System.out.println("⚠ La cita #" + cita.getIdCita() + " no puede cancelarse (estado: " + cita.getEstado() + ")");
            return;
        }
        cita.cancelarCita();
        servicioNotificacion.enviarNotificacion(propietario,
                "Su cita del " + cita.getFecha() + " para " + cita.getAnimal().getNombre() + " fue cancelada.",
                "CANCELACION");
    }

    /**
     * Marca una cita como atendida y registra observaciones.
     */
    public void atenderCita(Cita cita, String observaciones) {
        cita.actualizarEstado("ATENDIDA");
        cita.setObservaciones(observaciones);
        System.out.println("✔ Cita #" + cita.getIdCita() + " marcada como atendida.");
    }

    //  Consultas

    /** Muestra todas las citas en el sistema. */
    public void mostrarTodasLasCitas() {
        System.out.println("\n══ CITAS REGISTRADAS ══");
        if (citas.isEmpty()) {
            System.out.println("  No hay citas registradas.");
            return;
        }
        for (Cita c : citas) {
            System.out.println("  • " + c.obtenerDetallesCita());
        }
    }

    /** Muestra solo las citas con estado PROGRAMADA. */
    public void mostrarCitasProgramadas() {
        System.out.println("\n══ CITAS PROGRAMADAS ══");
        boolean hay = false;
        for (Cita c : citas) {
            if ("PROGRAMADA".equals(c.getEstado())) {
                System.out.println("  • " + c.obtenerDetallesCita());
                hay = true;
            }
        }
        if (!hay) System.out.println("  No hay citas programadas.");
    }

    public List<Cita> getCitas() { return citas; }
}