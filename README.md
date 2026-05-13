# Sistema-de-Gestion-de-Veterinaria-Grupo-8

Este proyecto podemos representar un sistema de gestión veterinaria que permite administrar mascotas, propietarios, citas, historiales médicos y vacunas. Está diseñado siguiendo principios de orientación a objetos y modelado UML.

## 👥 Integrantes
```plaintext
Nombre | Correo 
Andres Julian Serna Rueda- ajserna-2025a@corhuila.edu.co
Isabella Salas Jara- isalas-2025a@corhuila.edu.co

<img width="1371" height="1020" alt="VETERINARIA-DIAGRAMA UML drawio" src="https://github.com/user-attachments/assets/7bb79aba-379d-4d4e-9237-a97aaaa93f7e" />

# Estructura del Proyecto

```plaintext
src/
└── com/
    └── veterinaria/
        ├── interfaces/
        │   ├── AtencionMedica.java
        │   ├── Agendable.java
        │   └── Notificable.java
        │
        ├── model/
        │   ├── Animal.java
        │   ├── Perro.java
        │   ├── Gato.java
        │   ├── Propietario.java
        │   ├── Veterinario.java
        │   ├── Cita.java
        │   ├── HistorialMedico.java
        │   └── Vacuna.java
        │
        ├── service/
        │   ├── GestorMascotas.java
        │   ├── GestorCitas.java
        │   ├── GestorVacunacion.java
        │   ├── GestorHistorialMedico.java
        │   └── ServicioNotificacion.java
        │
        └── Main.java





# Explicación de Relaciones
 
🧩 Clases principales
🐶 Animal (Clase abstracta)

Representa la entidad base para todas las mascotas.
Atributos:

idAnimal: int
nombre: String
especie: String
raza: String
fechaNacimiento: Date
peso: double

Métodos:

diagnosticar()
tratar()
vacunar()

🐕 Perro (Hereda de Animal)

Atributos:

raza: String
color: String

Métodos:

ladrar()

🐈 Gato (Hereda de Animal)

Atributos:

esCasero: Boolean
color: String

Métodos:

maullar()

👤 Propietario

Representa al dueño de una mascota.

Atributos:

idPropietario: int
nombre: String
apellido: String
direccion: String
telefono: String
email: String

👨‍⚕️ Veterinario

Encargado de atender a las mascotas.

Atributos:

idVeterinario: int
nombre: String
apellido: String
especialidad: String
numeroLicencia: String

Métodos:

realizarConsulta(Animal, motivo): String
recetarTratamiento(HistorialMedico, tratamiento): String
administrarVacuna(Animal, vacuna): String

📅 Cita

Gestiona las citas médicas.

Atributos:

idCita: int
fecha: Date
hora: Time
motivo: String
estado: String

Métodos:

actualizarEstado(nuevoEstado: String)
obtenerDetallesCita(): String

📋 HistorialMedico

Guarda el historial clínico de la mascota.

Atributos:

idHistorial: int
fechaConsulta: Date
diagnostico: String
tratamiento: String
observaciones: String

Métodos:

agregarNota(nota: String)

💉 Vacuna

Representa las vacunas aplicadas a los animales.

Atributos:

idVacuna: int
nombreVacuna: String
fechaAplicacion: Date
proximaDosis: Date
🖥️ SistemaGestionVeterinaria

Clase principal que controla el sistema.

Métodos:

registroMascotas()
registroPropietarios()
gestionarCitas()
gestionarHistorialMedico()
gestionarVacunacion()
facturarServicios()
buscarMascotaPorNombre(nombre: String)

Atencion medica
Metodos
+ vacunar()
+ tratar()
+ diagnosticar()

Notificacion
Metodos
+ mensaje: String
+ tipo: String

# 🔗 Relaciones
Animal` (abstracta)** es la clase padre de `Perro` y `Gato` (herencia).
- **`Propietario`** tiene una relación de composición con uno o más `Animal`.
- **`Veterinario`** implementa `Agendable` y se asocia a `Cita` e `HistorialMedico`.
- **`Cita`** relaciona un `Animal` con un `Veterinario`.
- **`Vacuna`** se asocia a un `Animal`.
- **`GestorCitas`** y **`GestorVacunacion`** dependen de `Notificable` (DIP).

# Aplicacion de Principios SOLID:

S- SINGLE RESPONSIBILITY PRINCIPLE (SRP) 
CADA CLASE TIENE UNA UNICA RESPOSABILIDAD
¿DONDE SE APLICA? 
Propietario - Administra Datos del Dueño
Veterinario - Administra Consultas Medicas
Cita - Gestiona Citas 
HistorialMedico - Almacena informacion clinica 
Vacuna - Administra Vacunacion

Codigo: 
public class GestorCitas {
    private Notificable servicioNotificacion; // abstracción
    public Cita programarCita(Animal a, Veterinario v, Propietario p, ...) { ... }
    public void cancelarCita(Cita c, Propietario p) { ... }
    public void atenderCita(Cita c, String observaciones) { ... }
}

O-Open/Closed Principle(OPC)
¿Donde se aplica?
En el sistema se puede extender agregando nuevos tipos de animales sin modificar el codigo que ya es existente. Se podria agregar una nueva mascota (AVE) sin modificar Animal.

Codigo:
public abstract class Animal {
    public abstract String mostrarInformacion();
}
public class Pajaro extends Animal implements AtencionMedica {
    @Override
    public String mostrarInformacion() { return "[PAJARO] " + nombre + "..."; }
 
}
for (Animal a : mascotas) {
    System.out.println(a.mostrarInformacion()); // Pajaro también funciona
}

L-Liskov Substitution Principle(LSP)
¿Donde se aplica?
Las clases de PERRO y GATO pueden utilizarse como objetos tipo animal, el sistema puede funcionar bien independientemente del tipo especifico de animal.

Codigo:
public void mostrarTodasLasMascotas() {
    for (Animal a : mascotas) {
        System.out.println(a.mostrarInformacion()); // cada subclase respeta el contrato
    }
}
List<Animal> todos = gestorMascotas.getMascotas();
for (Animal a : todos) {
    if (a instanceof AtencionMedica) {
        ((AtencionMedica) a).diagnosticar(); // Perro y Gato cumplen el contrato
    }
}

I-Interface Segregation Principle(ISP)
¿Donde se aplica? 
Las interfaces estan separadas segun sus resposabilidades especificas
Agendable- Veterinario
AtencionMedica-Animal
Notificacion-Cita

Codigo:
public interface AtencionMedica {
    void vacunar(String nombreVacuna);
    void tratar(String tratamiento);
    String diagnosticar();
}

 Interfaz solo para agendar:
public interface Agendable {
    boolean programarCita(String fecha, String hora, String motivo);
    void cancelarCita();
}

 Perro y Gato implementan AtencionMedica (no se fuerzan a implementar Agendable):
public class Perro extends Animal implements AtencionMedica { ... }

 Veterinario implementa Agendable (no se fuerza a implementar AtencionMedica):
public class Veterinario implements Agendable { ... }

D-Dependency Inversion Principle(DIP)
¿Donde se aplica? 
Las clases depende de abstracciones y no de implementaciones concretas, ya que el sistema trabaja mediante interfaces para reducir acoplamiento. 

Codigo:
 Abstracción:
public interface Notificable {
    void enviarNotificacion(Propietario propietario, String mensaje, String tipo);
}

// GestorCitas depende de la abstracción, no de la implementación:
public class GestorCitas {
    private Notificable servicioNotificacion; // abstracción ✔

   public GestorCitas(Notificable servicioNotificacion) { // inyección por constructor
        this.servicioNotificacion = servicioNotificacion;
    }
}
 En Main se elige qué implementación inyectar (fácil de cambiar):
Notificable notif = new ServicioNotificacion(); // o new ServicioEmailNotificacion()
GestorCitas gestorCitas = new GestorCitas(notif);

# CLASES IMPLEMENTADAS 

|CLASE|----|TIPO|----|RESPONSABILIDAD|
|1 Animal| - |Abstracta| - |Representa una mascota| 
|2 Perro|  - |Subclase| - |Tipo especifico de animal|
|3 Gato| - |Subclase| - |Tipo especifico de animal|
|4 Propietario| - |Clase| - |Datos del dueño|
|5 Veterinario| - |Clase| - |Gestion Medica|
|6 Cita| - |Clase| - |Programacion de consultas|
|7 HistorialMedico| - |Clase| - |Historial Clinico|
|8 Vacuna| - |Clase| - |Informacion de vacunacion|
|9 SistemaGestionVeterinaria| - |Clase| - |Gestion general del sistema|
|10 AtencionMedica| - |interface| - |Procesos medicos|
|11 Agendable| - |Interface| - |Gestion Citas|
|12 Notificacion| - |Interface| - |Gestion de mensajes|

# CONCLUSIONES

Aprendimos que los principos SOLID no son restricciones, si no herramientas que mejoran la mantenibilidad. Por ejemplo, separar los servicios en clases distintas (SRP) hizo que el codigo fuera mucho mas facil de leer y de modificar que si todo estuviera en el SistemaGestionVeterinaria. Tambien el uso de herencia nos facilito reutilizar atributos y metodos entre los distintos tipos de animales. Tambien las interfaces nos ayudaron a separar responsabilidades especificas y aplicar el principio (ISP), y por ultimo el principio OCP nos permitio agregar nuevos tipos de mascotas sin modificar las clases existentes. Gracias. 

# Tecnologías Utilizadas en el Proyecto
Java
UML (para modelado)
IntelliJ IDEA

*Proyecto de Programación y Diseño Orientado a Objetos — Corhuila 2026*
