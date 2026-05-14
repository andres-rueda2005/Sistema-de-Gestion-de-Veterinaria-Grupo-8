package com.veterinaria.model;

/**
 * Clase abstracta que representa a cualquier animal atendido en la veterinaria.
 * Principio OCP: cerrada para modificación, abierta para extensión mediante subclases.
 * Principio LSP: cualquier subclase puede sustituir a Animal sin romper el comportamiento.
 */
public abstract class Animal {

    protected int idAnimal;
    protected String nombre;
    protected String especie;
    protected String raza;
    protected String fechaNacimiento; // formato: yyyy-MM-dd
    protected double peso;
    protected String sexo;
    protected String estadoSalud;

    public Animal(int idAnimal, String nombre, String especie, String raza,
                  String fechaNacimiento, double peso, String sexo, String estadoSalud) {
        this.idAnimal       = idAnimal;
        this.nombre         = nombre;
        this.especie        = especie;
        this.raza           = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.peso           = peso;
        this.sexo           = sexo;
        this.estadoSalud    = estadoSalud;
    }

    // Método abstracto: cada subclase muestra su propia info (OCP + LSP)
    /**
     * Muestra la información detallada del animal.
     * Método abstracto: cada subclase implementa su propia versión.
     * @return String con la información completa del animal
     */
    public abstract String mostrarInformacion();

    // ── Métodos concretos compartidos ────────────────────────────────────────

    /**
     * Actualiza el peso del animal.
     * @param peso nuevo peso en kg
     */
    public void actualizarPeso(double peso) {
        this.peso = peso;
        System.out.println("Peso de " + nombre + " actualizado a: " + peso + " kg");
    }

    /**
     * Calcula la edad aproximada del animal en años.
     * @return edad en años
     */
    public int calcularEdad() {
        int anioNacimiento = Integer.parseInt(fechaNacimiento.substring(0, 4));
        int anioActual = java.time.LocalDate.now().getYear();
        return anioActual - anioNacimiento;
    }

    // ── Getters y Setters ────────────────────────────────────────────────────
    public int getIdAnimal()             { return idAnimal; }
    public String getNombre()            { return nombre; }
    public void setNombre(String n)      { this.nombre = n; }
    public String getEspecie()           { return especie; }
    public String getRaza()              { return raza; }
    public void setRaza(String r)        { this.raza = r; }
    public String getFechaNacimiento()   { return fechaNacimiento; }
    public double getPeso()              { return peso; }
    public String getSexo()              { return sexo; }
    public String getEstadoSalud()       { return estadoSalud; }
    public void setEstadoSalud(String e) { this.estadoSalud = e; }

    @Override
    public String toString() {
        return "Animal{id=" + idAnimal + ", nombre='" + nombre +
                "', especie='" + especie + "', raza='" + raza + "'}";
    }
}