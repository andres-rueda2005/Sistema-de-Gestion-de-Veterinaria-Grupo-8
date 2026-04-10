# Sistema-de-Gestion-de-Veterinaria-Grupo-8
Este proyecto podemos representar un sistema de gestión veterinaria que permite administrar mascotas, propietarios, citas, historiales médicos y vacunas. Está diseñado siguiendo principios de orientación a objetos y modelado UML.

## 👥 Integrantes
Nombre | Correo 
Andres Julian Serna Rueda- ajserna-2025a@corhuila.edu.co
Isabella Salas Jara- isalas-2025a@corhuila.edu.co

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

🔗 Relaciones
Un Propietario puede tener 0.. Animales*
Un Animal pertenece a 1 Propietario
Un Veterinario atiende 0.. Citas*
Una Cita está asociada a:
1 Animal
1 Veterinario
Un Animal puede tener:
0..* Vacunas
0..* Historiales Médicos
🧪 Interfaces
🩺 Diagnosticable
diagnosticar()
💊 Tratable
tratar()
💉 Vacunable
vacunar()

🧱 Tecnologías sugeridas
Java
UML (para modelado)
IntelliJ IDEA o Eclipse
🚀 Funcionalidades principales
Registro de mascotas y propietarios
Gestión de citas veterinarias
Control de historial médico
Registro y seguimiento de vacunas
Atención por veterinarios
Búsqueda de mascotas


 Veterinaria- Proyecto POO Corhuila.
