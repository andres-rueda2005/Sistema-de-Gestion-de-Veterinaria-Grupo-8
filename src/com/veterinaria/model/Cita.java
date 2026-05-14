package com.veterinaria.model;

/**
 * Representa una cita veterinaria.
 * Principio SRP: solo modela los datos de una cita médica.
 */
public class Cita {

    private int    idCita;
    private String fecha;       // yyyy-MM-dd
    private String hora;        // HH:mm
    private String motivo;
    private String estado;      // PROGRAMADA, ATENDIDA, CANCELADA
    private String observaciones;
    private Animal animal;
    private Veterinario veterinario;

    public Cita(int idCita, String fecha, String hora, String motivo,
                String estado, Animal animal, Veterinario veterinario) {
        this.idCita      = idCita;
        this.fecha       = fecha;
        this.hora        = hora;
        this.motivo      = motivo;
        this.estado      = estado;
        this.animal      = animal;
        this.veterinario = veterinario;
        this.observaciones = "";
    }

    //  Métodos de negocio

    /** Cancela la cita cambiando su estado. */
    public void cancelarCita() {
        this.estado = "CANCELADA";
        System.out.println("Cita #" + idCita + " cancelada.");
    }

    /** Actualiza el estado de la cita. */
    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        System.out.println("Cita #" + idCita + " → nuevo estado: " + nuevoEstado);
    }

    /** Retorna un resumen de los detalles de la cita. */
    public String obtenerDetallesCita() {
        return "Cita #" + idCita +
                " | Fecha: " + fecha + " " + hora +
                " | Paciente: " + animal.getNombre() +
                " | Veterinario: Dr. " + veterinario.getNombreCompleto() +
                " | Motivo: " + motivo +
                " | Estado: " + estado;
    }

    /** Reprograma la cita a una nueva fecha y hora. */
    public void reprogramar(String nuevaFecha, String nuevaHora) {
        this.fecha = nuevaFecha;
        this.hora  = nuevaHora;
        this.estado = "PROGRAMADA";
        System.out.println("Cita #" + idCita + " reprogramada para " + nuevaFecha + " " + nuevaHora);
    }

    //  Getters y Setters
    public int         getIdCita()                 { return idCita; }
    public String      getFecha()                  { return fecha; }
    public void        setFecha(String f)          { this.fecha = f; }
    public String      getHora()                   { return hora; }
    public void        setHora(String h)           { this.hora = h; }
    public String      getMotivo()                 { return motivo; }
    public String      getEstado()                 { return estado; }
    public void        setEstado(String e)         { this.estado = e; }
    public String      getObservaciones()          { return observaciones; }
    public void        setObservaciones(String o)  { this.observaciones = o; }
    public Animal      getAnimal()                 { return animal; }
    public Veterinario getVeterinario()            { return veterinario; }

    @Override
    public String toString() {
        return "Cita{#" + idCita +
                ", fecha=" + fecha + " " + hora +
                ", paciente='" + animal.getNombre() +
                "', vet='Dr. " + veterinario. getNombreCompleto() +
                "', estado='" + estado + "'}";
    }
}