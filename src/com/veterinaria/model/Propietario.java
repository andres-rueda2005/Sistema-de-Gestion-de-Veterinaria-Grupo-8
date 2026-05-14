package com.veterinaria.model;

/**
 * Representa al propietario de una o más mascotas.
 * Principio SRP: solo modela los datos del propietario.
 */
public class Propietario {

    private int idPropietario;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;

    public Propietario(int idPropietario, String nombre, String apellido,
                       String direccion, String telefono, String email) {
        this.idPropietario = idPropietario;
        this.nombre        = nombre;
        this.apellido      = apellido;
        this.direccion     = direccion;
        this.telefono      = telefono;
        this.email         = email;
    }

    // Getters y Setters
    public int    getIdPropietario()         { return idPropietario; }
    public String getNombre()                { return nombre; }
    public void   setNombre(String n)        { this.nombre = n; }
    public String getApellido()              { return apellido; }
    public void   setApellido(String a)      { this.apellido = a; }
    public String getNombreCompleto()        { return nombre + " " + apellido; }
    public String getDireccion()             { return direccion; }
    public void   setDireccion(String d)     { this.direccion = d; }
    public String getTelefono()              { return telefono; }
    public void   setTelefono(String t)      { this.telefono = t; }
    public String getEmail()                 { return email; }
    public void   setEmail(String e)         { this.email = e; }

    @Override
    public String toString() {
        return "Propietario{id=" + idPropietario +
                ", nombre='" + getNombreCompleto() +
                "', tel='" + telefono +
                "', email='" + email + "'}";
    }
}