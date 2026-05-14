package com.veterinaria;

import com.veterinaria.interfaces.AtencionMedica;
import com.veterinaria.interfaces.Notificable;
import com.veterinaria.model.*;
import com.veterinaria.service.*;


public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║    SISTEMA DE GESTIÓN VETERINARIA            ║");
        System.out.println("║        BIENVENIDOS                           ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // ══════════════════════════════════════════════════════════════════════
        // 1. PRINCIPIO DIP — Inyección de dependencia
        //    GestorCitas y GestorVacunacion reciben Notificable (abstracción),
        //    no ServicioNotificacion directamente.
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("── Inicializando servicios (DIP) ──");
        Notificable notificaciones = new ServicioNotificacion();  // abstracción
        GestorMascotas      gestorMascotas  = new GestorMascotas();
        GestorCitas         gestorCitas     = new GestorCitas(notificaciones);       // DIP
        GestorVacunacion    gestorVacunas   = new GestorVacunacion(notificaciones);  // DIP
        GestorHistorialMedico gestorHistorial = new GestorHistorialMedico();

        // ══════════════════════════════════════════════════════════════════════
        // CREAR PROPIETARIOS
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Registrando propietarios ──");
        Propietario dueno1 = new Propietario(1, "Isabella",  "Salas",
                "Calle 5 #10-20, Neiva", "3101234567",
                "Isabella20@email.com");
        Propietario dueno2 = new Propietario(2, "Andres", "Rueda",
                "Carrera 8 #15-30, Neiva", "3209876543",
                "andres21@email.com");
        gestorMascotas.registrarPropietario(dueno1);
        gestorMascotas.registrarPropietario(dueno2);

        // ══════════════════════════════════════════════════════════════════════
        //  CREAR VETERINARIOS (implementan Agendable — ISP)
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Registrando veterinarios ──");
        Veterinario vet1 = new Veterinario(1, "Estefania", "Ramírez",
                "Medicina Interna", "VET-001",
                "Lunes a Viernes 8:00-17:00");
        Veterinario vet2 = new Veterinario(2, "Alex", "Oñate",
                "Cirugía", "VET-002",
                "Lunes, Miércoles y Viernes 9:00-15:00");
        System.out.println("Veterinario registrado: Dr. " + vet1.getNombreCompleto());
        System.out.println("Veterinario registrado: Dr. " + vet2.getNombreCompleto());

        // ══════════════════════════════════════════════════════════════════════
        //  CREAR ANIMALES — OCP: Animal es clase abstracta; agregar Pajaro
        //    en el futuro no requiere modificar ningún código existente.
        //    LSP: Perro y Gato pueden sustituir a Animal en cualquier contexto.
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Registrando mascotas ──");
        Perro perro1 = new Perro(1, "Rocky",   "Labrador",    "2020-03-15",
                25.0, "Macho", "Saludable",   "Amarillo");
        Perro perro2 = new Perro(2, "Toby",    "Beagle",      "2019-07-20",
                12.5, "Macho", "Saludable",   "Tricolor");
        Gato  gato1  = new Gato (3, "Michi",   "Persa",       "2021-11-01",
                4.2, "Hembra", "Saludable",   true, "Blanco");
        Gato  gato2  = new Gato (4, "Simba",   "Siamés",      "2018-05-10",
                5.1, "Macho",  "Saludable",   false, "Café");

        gestorMascotas.registrarMascota(perro1, dueno1);
        gestorMascotas.registrarMascota(perro2, dueno2);
        gestorMascotas.registrarMascota(gato1,  dueno1);
        gestorMascotas.registrarMascota(gato2,  dueno2);

        // ══════════════════════════════════════════════════════════════════════
        //  POLIMORFISMO — mostrarInformacion() se comporta diferente
        //    según si el objeto es Perro o Gato (OCP + LSP en acción)
        // ══════════════════════════════════════════════════════════════════════
        gestorMascotas.mostrarTodasLasMascotas();

        // ══════════════════════════════════════════════════════════════════════
        //  COMPORTAMIENTOS PROPIOS — los perros ladran, los gatos maúllan
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Comportamientos propios ──");
        perro1.ladrar();
        gato1.maullar();

        // ══════════════════════════════════════════════════════════════════════
        //  INTERFACES — ISP: AtencionMedica define vacunar, tratar, diagnosticar
        //    Solo los animales que realmente lo implementan lo ejecutan.
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Diagnóstico con interfaz AtencionMedica (ISP) ──");
        // Lista polimórfica: tipo Animal, contenido Perro o Gato
        java.util.List<Animal> todosLosAnimales = gestorMascotas.getMascotas();
        for (Animal a : todosLosAnimales) {
            if (a instanceof AtencionMedica) {
                AtencionMedica paciente = (AtencionMedica) a;
                paciente.diagnosticar(); // polimorfismo vía interfaz
            }
        }

        // ══════════════════════════════════════════════════════════════════════
        // CITAS — GestorCitas usa Notificable (DIP)
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Programando citas ──");
        Cita cita1 = gestorCitas.programarCita(perro1, vet1, dueno1,
                "2026-05-15", "09:00", "Chequeo general");
        Cita cita2 = gestorCitas.programarCita(gato1, vet2, dueno1,
                "2026-05-16", "10:30", "Esterilización");
        Cita cita3 = gestorCitas.programarCita(perro2, vet1, dueno2,
                "2026-05-17", "14:00", "Vacunación anual");

        gestorCitas.mostrarCitasProgramadas();

        // Cancelar una cita
        System.out.println("\n── Cancelando cita ──");
        if (cita3 != null) gestorCitas.cancelarCita(cita3, dueno2);

        // Atender una cita
        System.out.println("\n── Atendiendo cita ──");
        if (cita1 != null) {
            gestorCitas.atenderCita(cita1, "Perro en excelente estado. Peso ideal.");
            vet1.realizarConsulta(perro1, "Chequeo general");
        }

        gestorCitas.mostrarTodasLasCitas();

        // ══════════════════════════════════════════════════════════════════════
        //  VACUNACION — GestorVacunacion usa AtencionMedica + Notificable (DIP+ISP)
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Aplicando vacunas ──");
        gestorVacunas.aplicarVacuna(perro1, dueno1,
                "Rabia", "2027-05-10", "LOTE-A21", "Zoetis");
        gestorVacunas.aplicarVacuna(gato1, dueno1,
                "Triple Felina", "2026-05-25", "LOTE-B15", "Boehringer");
        gestorVacunas.aplicarVacuna(perro2, dueno2,
                "Parvovirus", "2027-05-15", "LOTE-C30", "Pfizer Animal");

        gestorVacunas.mostrarHistorialVacunacion();
        gestorVacunas.mostrarRefuerzosPendientes();

        // ══════════════════════════════════════════════════════════════════════
        //  HISTORIAL MEDICO — SRP: GestorHistorialMedico solo gestiona registros
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Registrando historiales médicos ──");
        gestorHistorial.crearHistorial(perro1, vet1,
                "Animal sano, sin anomalías",
                "Vitaminas mensuales + desparasitación",
                "Peso dentro del rango ideal para la raza");
        gestorHistorial.crearHistorial(gato1, vet2,
                "Infección ocular leve",
                "Gotas oftálmicas Tobramicina 2 veces al día por 7 días",
                "Revisar en 10 días");

        gestorHistorial.mostrarTodosLosHistoriales();
        gestorHistorial.mostrarHistorialDeAnimal(1); // Rocky

        // ══════════════════════════════════════════════════════════════════════
        //  BUSQUEDA DE MASCOTAS
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Búsqueda de mascotas ──");
        System.out.println("Buscando 'o':");
        gestorMascotas.buscarMascotaPorNombre("o")
                .forEach(a -> System.out.println("  → " + a.getNombre() +
                        " (" + a.getEspecie() + ")"));

        // ══════════════════════════════════════════════════════════════════════
        //  HISTORIAL DE NOTIFICACIONES (DIP demostrado)
        // ══════════════════════════════════════════════════════════════════════
        ((ServicioNotificacion) notificaciones).mostrarHistorial();

        // ══════════════════════════════════════════════════════════════════════
        // 13. AGENDABLE — Veterinario implementa la interfaz Agendable (ISP)
        // ══════════════════════════════════════════════════════════════════════
        System.out.println("\n── Veterinario agenda cita directamente (Agendable) ──");
        vet1.programarCita("2026-05-20", "11:00", "Consulta urgente Toby");

        System.out.println("\n╔══════════════════════════════════════════════╗");
        System.out.println("║  Demostración completada exitosamente          ║");
        System.out.println("╚══════════════════════════════════════════════╝");
    }
}