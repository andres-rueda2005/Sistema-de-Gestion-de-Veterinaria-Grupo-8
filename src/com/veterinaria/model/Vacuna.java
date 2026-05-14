package com.veterinaria.model;

/**
 * Representa una vacuna aplicada a un animal.
 * Principio SRP: solo modela los datos de una vacuna.
 */
public class Vacuna {

    private int    idVacuna;
    private String nombreVacuna;
    private String fechaAplicacion;
    private String proximaDosis;
    private String lote;
    private String fabricante;
    private Animal animal;

    public Vacuna(int idVacuna, String nombreVacuna, String fechaAplicacion,
                  String proximaDosis, String lote, String fabricante, Animal animal) {
        this.idVacuna        = idVacuna;
        this.nombreVacuna    = nombreVacuna;
        this.fechaAplicacion = fechaAplicacion;
        this.proximaDosis    = proximaDosis;
        this.lote            = lote;
        this.fabricante      = fabricante;
        this.animal          = animal;
    }

    //  Métodos de negocio

    /**
     * Determina si se requiere una dosis de refuerzo próximamente
     * (si la próxima dosis es dentro de los próximos 30 días).
     * @return true si el refuerzo es necesario pronto
     */
    public boolean esRefuerzoNecesario() {
        try {
            java.time.LocalDate hoy  = java.time.LocalDate.now();
            java.time.LocalDate prox = java.time.LocalDate.parse(proximaDosis);
            long diasRestantes = java.time.temporal.ChronoUnit.DAYS.between(hoy, prox);
            return diasRestantes <= 30 && diasRestantes >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    //  Getters y Setters
    public int    getIdVacuna()              { return idVacuna; }
    public String getNombreVacuna()          { return nombreVacuna; }
    public String getFechaAplicacion()       { return fechaAplicacion; }
    public String getProximaDosis()          { return proximaDosis; }
    public void   setProximaDosis(String p)  { this.proximaDosis = p; }
    public String getLote()                  { return lote; }
    public String getFabricante()            { return fabricante; }
    public Animal getAnimal()               { return animal; }

    @Override
    public String toString() {
        return "Vacuna{#" + idVacuna +
                ", nombre='" + nombreVacuna +
                "', aplicacion='" + fechaAplicacion +
                "', proximaDosis='" + proximaDosis +
                "', refuerzoNecesario=" + esRefuerzoNecesario() + '}';
    }
}
