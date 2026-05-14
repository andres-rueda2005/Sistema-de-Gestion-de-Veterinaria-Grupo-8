package com.veterinaria.interfaces;

/**
 * Contrato para animales que pueden recibir atención médica.
 * Principio ISP: interfaz pequeña y específica para atención médica.
 * Principio OCP: permite agregar nuevos tipos de animales sin modificar
 * el código que usa esta interfaz.
 */
public interface AtencionMedica {

    /**
     * Aplica una vacuna al animal.
     * @param nombreVacuna nombre de la vacuna a aplicar
     */
    void vacunar(String nombreVacuna);

    /**
     * Aplica un tratamiento médico al animal.
     * @param tratamiento descripción del tratamiento
     */
    void tratar(String tratamiento);

    /**
     * Realiza un diagnóstico al animal.
     * @return resultado del diagnóstico
     */
    String diagnosticar();
}