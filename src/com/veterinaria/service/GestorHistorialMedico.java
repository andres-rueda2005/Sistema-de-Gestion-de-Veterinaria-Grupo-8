package com.veterinaria.service;

import com.veterinaria.model.Animal;
import com.veterinaria.model.HistorialMedico;
import com.veterinaria.model.Veterinario;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona los historiales médicos de los animales.
 *
 * Principio SRP: única responsabilidad → gestionar registros médicos.
 * Principio OCP: recibe Animal abstracto; funciona para Perro, Gato
 *               y cualquier subclase futura.
 */
public class GestorHistorialMedico {

    private List<HistorialMedico> historiales;
    private int contadorId;

    public GestorHistorialMedico() {
        this.historiales  = new ArrayList<>();
        this.contadorId   = 1;
    }

    //  Operaciones principales

    /**
     * Crea y registra un nuevo historial médico para un animal.
     */
    public HistorialMedico crearHistorial(Animal animal, Veterinario veterinario,
                                          String diagnostico, String tratamiento,
                                          String observaciones) {
        String hoy = java.time.LocalDate.now().toString();

        // El veterinario receta el tratamiento (delega al modelo)
        HistorialMedico historial = new HistorialMedico(
                contadorId++, hoy, diagnostico, tratamiento, observaciones, animal
        );
        veterinario.recetarTratamiento(historial, tratamiento);
        historiales.add(historial);

        System.out.println(" Historial #" + historial.getIdHistorial() +
                " creado para " + animal.getNombre());
        return historial;
    }

    /**
     * Muestra el historial completo de un animal buscando por id.
     */
    public void mostrarHistorialDeAnimal(int idAnimal) {
        System.out.println("\n══ HISTORIAL DE ANIMAL #" + idAnimal + " ══");
        boolean hay = false;
        for (HistorialMedico h : historiales) {
            if (h.getAnimal().getIdAnimal() == idAnimal) {
                System.out.println(h.obtenerHistorialCompleto());
                hay = true;
            }
        }
        if (!hay) System.out.println("  No se encontraron registros para el animal #" + idAnimal);
    }

    /** Muestra todos los historiales médicos del sistema. */
    public void mostrarTodosLosHistoriales() {
        System.out.println("\n══ TODOS LOS HISTORIALES MÉDICOS ══");
        if (historiales.isEmpty()) {
            System.out.println("  No hay historiales registrados.");
            return;
        }
        for (HistorialMedico h : historiales) {
            System.out.println(h.obtenerHistorialCompleto());
            System.out.println("  ─────────────────────────────────");
        }
    }

    public List<HistorialMedico> getHistoriales() { return historiales; }
}