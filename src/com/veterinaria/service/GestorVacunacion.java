package com.veterinaria.service;

import com.veterinaria.interfaces.AtencionMedica;
import com.veterinaria.interfaces.Notificable;
import com.veterinaria.model.Animal;
import com.veterinaria.model.Propietario;
import com.veterinaria.model.Vacuna;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona el plan de vacunación de los animales.
 *
 * Principio SRP: única responsabilidad → administrar vacunas.
 * Principio DIP: depende de Notificable (abstracción) y de AtencionMedica.
 * Principio OCP: funciona con cualquier Animal que implemente AtencionMedica.
 */
public class GestorVacunacion {

    private List<Vacuna> vacunas;
    private Notificable  servicioNotificacion;
    private int          contadorId;

    /**
     * Constructor con inyección de dependencia (DIP).
     */
    public GestorVacunacion(Notificable servicioNotificacion) {
        this.vacunas              = new ArrayList<>();
        this.servicioNotificacion = servicioNotificacion;
        this.contadorId           = 1;
    }

    //  Operaciones principales

    /**
     * Registra y aplica una vacuna a un animal.
     * Solo lo aplica si el animal implementa AtencionMedica (ISP).
     */
    public Vacuna aplicarVacuna(Animal animal, Propietario propietario,
                                String nombreVacuna, String proximaDosis,
                                String lote, String fabricante) {
        String hoy = java.time.LocalDate.now().toString();

        // ISP: solo aplica vacuna si el animal implementa AtencionMedica
        if (animal instanceof AtencionMedica) {
            AtencionMedica paciente = (AtencionMedica) animal;
            paciente.vacunar(nombreVacuna); // polimorfismo: Perro o Gato
        }

        Vacuna vacuna = new Vacuna(contadorId++, nombreVacuna, hoy,
                proximaDosis, lote, fabricante, animal);
        vacunas.add(vacuna);

        // DIP: notifica usando la abstracción
        servicioNotificacion.enviarNotificacion(propietario,
                "Vacuna '" + nombreVacuna + "' aplicada a " + animal.getNombre() +
                        ". Próxima dosis: " + proximaDosis,
                "VACUNA");

        System.out.println(" Vacuna registrada: " + vacuna);
        return vacuna;
    }

    /**
     * Muestra todas las vacunas que requieren refuerzo próximo.
     */
    public void mostrarRefuerzosPendientes() {
        System.out.println("\n══ VACUNAS QUE REQUIEREN REFUERZO ══");
        boolean hay = false;
        for (Vacuna v : vacunas) {
            if (v.esRefuerzoNecesario()) {
                System.out.println("  ⚠ " + v.getNombreVacuna() +
                        " para " + v.getAnimal().getNombre() +
                        " — Próxima dosis: " + v.getProximaDosis());
                hay = true;
            }
        }
        if (!hay) System.out.println("  No hay refuerzos pendientes próximamente.");
    }

    /** Muestra el historial completo de vacunación. */
    public void mostrarHistorialVacunacion() {
        System.out.println("\n══ HISTORIAL DE VACUNACIÓN ══");
        if (vacunas.isEmpty()) {
            System.out.println("  No hay vacunas registradas.");
            return;
        }
        for (Vacuna v : vacunas) {
            System.out.println("  • " + v);
        }
    }

    public List<Vacuna> getVacunas() { return vacunas; }
}