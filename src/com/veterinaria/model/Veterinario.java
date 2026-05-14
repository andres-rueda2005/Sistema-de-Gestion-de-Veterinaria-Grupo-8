package com.veterinaria.model;

import com.veterinaria.interfaces.Agendable;

/**
 * Representa a un veterinario del sistema.
 * Implementa Agendable porque los veterinarios pueden programar y cancelar citas.
 *
 * Principio SRP: solo gestiona los datos y comportamiento del veterinario.
 * Principio LSP: cumple el contrato de Agendable correctamente.
 */
public class Veterinario implements Agendable {

    private int    idVeterinario;
    private String nombre;
    private String apellido;
    private String especialidad;
    private String numeroLicencia;
    private String horarioAtencion;
    private boolean activo;

    public Veterinario(int idVeterinario, String nombre, String apellido,
                       String especialidad, String numeroLicencia,
                       String horarioAtencion) {
        this.idVeterinario  = idVeterinario;
        this.nombre         = nombre;
        this.apellido       = apellido;
        this.especialidad   = especialidad;
        this.numeroLicencia = numeroLicencia;
        this.horarioAtencion = horarioAtencion;
        this.activo         = true;
    }

    //  Métodos propios del Veterinario

    /**
     * Realiza una consulta al animal y retorna el resultado.
     */
    public String realizarConsulta(Animal animal, String motivo) {
        String resultado = "Consulta realizada por Dr. " + getNombreCompleto() +
                " | Paciente: " + animal.getNombre() +
                " | Motivo: " + motivo +
                " | Fecha: " + java.time.LocalDate.now();
        System.out.println(resultado);
        return resultado;
    }

    /**
     * Receta un tratamiento y lo registra en el historial médico.
     */
    public void recetarTratamiento(HistorialMedico historial, String tratamiento) {
        historial.setTratamiento(tratamiento);
        System.out.println("Dr. " + getNombreCompleto() +
                " recetó: " + tratamiento +
                " para historial #" + historial.getIdHistorial());
    }

    /**
     * Administra una vacuna a la mascota.
     */
    public void administrarVacuna(Animal mascota, String vacuna) {
        System.out.println("Dr. " + getNombreCompleto() +
                " administró vacuna '" + vacuna +
                "' a " + mascota.getNombre());
    }

    //  Implementación de Agendable
    @Override
    public boolean programarCita(String fecha, String hora, String motivo) {
        if (!activo) {
            System.out.println("El veterinario " + getNombreCompleto() + " no está activo.");
            return false;
        }
        System.out.println("Cita programada con Dr. " + getNombreCompleto() +
                " el " + fecha + " a las " + hora +
                " | Motivo: " + motivo);
        return true;
    }

    @Override
    public void cancelarCita() {
        System.out.println("Cita con Dr. " + getNombreCompleto() + " cancelada.");
    }

    //  Getters y Setters
    public int    getIdVeterinario()            { return idVeterinario; }
    public String getNombre()                   { return nombre; }
    public String getApellido()                 { return apellido; }
    public String getNombreCompleto()           { return nombre + " " + apellido; }
    public String getEspecialidad()             { return especialidad; }
    public String getNumeroLicencia()           { return numeroLicencia; }
    public String getHorarioAtencion()          { return horarioAtencion; }
    public boolean isActivo()                   { return activo; }
    public void   setActivo(boolean a)          { this.activo = a; }
    public void   setHorarioAtencion(String h)  { this.horarioAtencion = h; }

    @Override
    public String toString() {
        return "Veterinario{id=" + idVeterinario +
                ", nombre='Dr. " + getNombreCompleto() +
                "', especialidad='" + especialidad +
                "', activo=" + activo + '}';
    }
}