package com.veterinaria.model;

/**
 * Representa el historial médico de un animal.
 * Principio SRP: solo modela un registro médico.
 */
public class HistorialMedico {

    private int    idHistorial;
    private String fechaConsulta;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    private Animal animal;

    public HistorialMedico(int idHistorial, String fechaConsulta,
                           String diagnostico, String tratamiento,
                           String observaciones, Animal animal) {
        this.idHistorial   = idHistorial;
        this.fechaConsulta = fechaConsulta;
        this.diagnostico   = diagnostico;
        this.tratamiento   = tratamiento;
        this.observaciones = observaciones;
        this.animal        = animal;
    }

    // Métodos de negocio

    /** Retorna un resumen completo del historial. */
    public String obtenerHistorialCompleto() {
        return "═══ HISTORIAL #" + idHistorial + " ═══" +
                "\n  Paciente   : " + animal.getNombre() + " (" + animal.getEspecie() + ")" +
                "\n  Fecha      : " + fechaConsulta +
                "\n  Diagnóstico: " + diagnostico +
                "\n  Tratamiento: " + tratamiento +
                "\n  Observ.    : " + observaciones;
    }

    /** Agrega una nota adicional al historial. */
    public void agregarNota(String nota) {
        this.observaciones += " | " + nota;
        System.out.println("Nota agregada al historial #" + idHistorial + ": " + nota);
    }

    //  Getters y Setters
    public int    getIdHistorial()           { return idHistorial; }
    public String getFechaConsulta()         { return fechaConsulta; }
    public String getDiagnostico()           { return diagnostico; }
    public void   setDiagnostico(String d)   { this.diagnostico = d; }
    public String getTratamiento()           { return tratamiento; }
    public void   setTratamiento(String t)   { this.tratamiento = t; }
    public String getObservaciones()         { return observaciones; }
    public void   setObservaciones(String o) { this.observaciones = o; }
    public Animal getAnimal()               { return animal; }

    @Override
    public String toString() {
        return "HistorialMedico{#" + idHistorial +
                ", fecha='" + fechaConsulta +
                "', diagnostico='" + diagnostico + "'}";
    }
}