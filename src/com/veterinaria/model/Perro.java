package com.veterinaria.model;

import com.veterinaria.interfaces.AtencionMedica;

/**
 * Perro: subclase concreta de Animal.
 * Implementa AtencionMedica porque los perros reciben tratamiento veterinario completo.
 *
 * Principio OCP: se extiende Animal sin modificar la clase padre.
 * Principio LSP: Perro puede sustituir a Animal en cualquier contexto.
 * Principio ISP: implementa AtencionMedica (vacunar, tratar, diagnosticar)
 *                porque aplica para perros.
 */
public class Perro extends Animal implements AtencionMedica {

    private String colorPelaje;

    public Perro(int idAnimal, String nombre, String raza, String fechaNacimiento,
                 double peso, String sexo, String estadoSalud, String colorPelaje) {
        super(idAnimal, nombre, "Perro", raza, fechaNacimiento, peso, sexo, estadoSalud);
        this.colorPelaje = colorPelaje;
    }

    // ── Comportamiento propio de Perro ───────────────────────────────────────
    /**
     * El perro ladra. Comportamiento específico de esta subclase.
     */
    public void ladrar() {
        System.out.println(nombre + " dice: ¡Guau guau!");
    }

    // ── Implementación de AtencionMedica ─────────────────────────────────────
    @Override
    public void vacunar(String nombreVacuna) {
        System.out.println("[PERRO] " + nombre + " vacunado con: " + nombreVacuna);
        this.estadoSalud = "Vacunado - " + nombreVacuna;
    }

    @Override
    public void tratar(String tratamiento) {
        System.out.println("[PERRO] Tratamiento aplicado a " + nombre + ": " + tratamiento);
        this.estadoSalud = "En tratamiento: " + tratamiento;
    }

    @Override
    public String diagnosticar() {
        String diagnostico = "[PERRO] Diagnóstico de " + nombre +
                " | Peso: " + peso + " kg" +
                " | Estado: " + estadoSalud +
                " | Edad: " + calcularEdad() + " años";
        System.out.println(diagnostico);
        return diagnostico;
    }

    // ── Implementación del método abstracto de Animal ────────────────────────
    @Override
    public String mostrarInformacion() {
        return "[PERRO] " + nombre +
                " | Raza: " + raza +
                " | Color: " + colorPelaje +
                " | Peso: " + peso + " kg" +
                " | Sexo: " + sexo +
                " | Edad: " + calcularEdad() + " años" +
                " | Salud: " + estadoSalud;
    }

    public String getColorPelaje() { return colorPelaje; }
    public void setColorPelaje(String c) { this.colorPelaje = c; }

    @Override
    public String toString() {
        return "Perro{nombre='" + nombre + "', raza='" + raza + "', color='" + colorPelaje + "'}";
    }
}