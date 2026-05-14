package com.veterinaria.model;

import com.veterinaria.interfaces.AtencionMedica;

/**
 * Gato: subclase concreta de Animal.
 * Implementa AtencionMedica.
 *
 * Principio LSP: Gato puede sustituir a Animal sin alterar el comportamiento esperado.
 * Principio ISP: implementa AtencionMedica — todos sus métodos aplican a gatos.
 */
public class Gato extends Animal implements AtencionMedica {

    private boolean esCasero;
    private String colorPelaje;

    public Gato(int idAnimal, String nombre, String raza, String fechaNacimiento,
                double peso, String sexo, String estadoSalud,
                boolean esCasero, String colorPelaje) {
        super(idAnimal, nombre, "Gato", raza, fechaNacimiento, peso, sexo, estadoSalud);
        this.esCasero    = esCasero;
        this.colorPelaje = colorPelaje;
    }

    //  Comportamiento propio de Gato
    /**
     * El gato maúlla. Comportamiento específico de esta subclase.
     */
    public void maullar() {
        System.out.println(nombre + " dice: ¡Miau!");
    }

    //  Implementación de AtencionMedica
    @Override
    public void vacunar(String nombreVacuna) {
        System.out.println("[GATO] " + nombre + " vacunado con: " + nombreVacuna);
        this.estadoSalud = "Vacunado - " + nombreVacuna;
    }

    @Override
    public void tratar(String tratamiento) {
        System.out.println("[GATO] Tratamiento aplicado a " + nombre + ": " + tratamiento);
        this.estadoSalud = "En tratamiento: " + tratamiento;
    }

    @Override
    public String diagnosticar() {
        String diagnostico = "[GATO] Diagnóstico de " + nombre +
                " | Peso: " + peso + " kg" +
                " | Estado: " + estadoSalud +
                " | Casero: " + (esCasero ? "Sí" : "No") +
                " | Edad: " + calcularEdad() + " años";
        System.out.println(diagnostico);
        return diagnostico;
    }

    // Implementación del método abstracto de Animal
    @Override
    public String mostrarInformacion() {
        return "[GATO] " + nombre +
                " | Raza: " + raza +
                " | Color: " + colorPelaje +
                " | Casero: " + (esCasero ? "Sí" : "No") +
                " | Peso: " + peso + " kg" +
                " | Sexo: " + sexo +
                " | Edad: " + calcularEdad() + " años" +
                " | Salud: " + estadoSalud;
    }

    public boolean isEsCasero()            { return esCasero; }
    public void setEsCasero(boolean e)     { this.esCasero = e; }
    public String getColorPelaje()         { return colorPelaje; }
    public void setColorPelaje(String c)   { this.colorPelaje = c; }

    @Override
    public String toString() {
        return "Gato{nombre='" + nombre + "', raza='" + raza +
                "', casero=" + esCasero + '}';
    }
}