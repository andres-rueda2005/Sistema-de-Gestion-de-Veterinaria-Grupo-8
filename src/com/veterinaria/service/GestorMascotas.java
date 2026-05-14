package com.veterinaria.service;

import com.veterinaria.model.Animal;
import com.veterinaria.model.Propietario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gestiona el registro y consulta de mascotas y propietarios.
 *
 * Principio SRP: única responsabilidad → administrar el catálogo de mascotas.
 * Principio OCP: trabaja con Animal abstracto; funciona para Perro, Gato
 *                y cualquier subclase futura sin modificar este servicio.
 */
public class GestorMascotas {

    private List<Animal>      mascotas;
    private List<Propietario> propietarios;
    /** Relaciona idAnimal → Propietario */
    private Map<Integer, Propietario> mascotaPropietario;

    public GestorMascotas() {
        this.mascotas           = new ArrayList<>();
        this.propietarios       = new ArrayList<>();
        this.mascotaPropietario = new HashMap<>();
    }

    //  Registro

    /** Registra una nueva mascota con su propietario. */
    public void registrarMascota(Animal animal, Propietario propietario) {
        mascotas.add(animal);
        if (!propietarios.contains(propietario)) {
            propietarios.add(propietario);
        }
        mascotaPropietario.put(animal.getIdAnimal(), propietario);
        System.out.println("✔ Mascota registrada: " + animal.getNombre() +
                " | Propietario: " + propietario.getNombreCompleto());
    }

    /** Registra solo un propietario. */
    public void registrarPropietario(Propietario propietario) {
        propietarios.add(propietario);
        System.out.println("✔ Propietario registrado: " + propietario.getNombreCompleto());
    }

    //  Consultas

    /**
     * Muestra todas las mascotas registradas (polimorfismo: cada animal
     * llama su propia versión de mostrarInformacion()).
     */
    public void mostrarTodasLasMascotas() {
        System.out.println("\n══ MASCOTAS REGISTRADAS ══");
        if (mascotas.isEmpty()) {
            System.out.println("  No hay mascotas registradas.");
            return;
        }
        for (Animal a : mascotas) {
            System.out.println("  • " + a.mostrarInformacion()); // Polimorfismo
        }
    }

    /**
     * Busca mascotas cuyo nombre contenga el texto indicado.
     * @param nombre texto a buscar (case-insensitive)
     * @return lista de animales que coinciden
     */
    public List<Animal> buscarMascotaPorNombre(String nombre) {
        List<Animal> resultado = new ArrayList<>();
        for (Animal a : mascotas) {
            if (a.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(a);
            }
        }
        return resultado;
    }

    /** Retorna el propietario de una mascota dado su id. */
    public Propietario obtenerPropietario(int idAnimal) {
        return mascotaPropietario.get(idAnimal);
    }

    public List<Animal>      getMascotas()     { return mascotas; }
    public List<Propietario> getPropietarios() { return propietarios; }
}
