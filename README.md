[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/tc38IXJF)
# 📚 Trabajo Práctico: Sistema de Gestión de Biblioteca Digital (Java 21+)

## 📌 Objetivo General

Desarrollar un sistema de gestión de biblioteca digital que implemente los cinco principios SOLID, programación orientada a objetos, y conceptos avanzados de Java. El sistema deberá manejar diferentes tipos de recursos digitales, préstamos, reservas, y notificaciones en tiempo real.

## 👨‍🎓 Información del Alumno
- **Nombre y Apellido**: Martin Navarro Teixidor

## Documentación

### Descripción General del Sistema
Este sistema está diseñado para gestionar recursos digitales, usuarios, préstamos, reservas y notificaciones dentro de una biblioteca digital, siguiendo los principios SOLID.

## Arquitectura del Sistema
# Estructura General
La estructura del sistema está organizada de la siguiente manera:
- Alertas: Gestión de notificaciones y recordatorios del sistema.
- Comparadores: Clases utilizadas para ordenar o comparar objetos, como usuarios y recursos.
- Enums: Definiciones de valores constantes para categorías, estados y otros tipos.
- Excepciones: Clases personalizadas para manejar errores específicos en el sistema.
- Gestores: Clases encargadas de la lógica de negocio, como la gestión de recursos, usuarios, préstamos, etc.
- Interfaces: Contratos que definen los métodos que las clases deben implementar.
- Main: Punto de entrada del sistema, donde se inicializan y gestionan las operaciones (Main, Consola, Menus).
- Prestamos: Lógica relacionada con la gestión de préstamos de recursos.
- Recursos: Clases que representan los recursos disponibles en la biblioteca digital.
- Reservas: Lógica de gestión de reservas de recursos por los usuarios.
- Servicios: Servicios de notificación que interactúan con el sistema.
- Simulaciones: Clases utilizadas para probar la funcionalidad del sistema en un entorno controlado.
- Usuarios: Gestión y almacenamiento de la información de los usuarios del sistema.

# Principios SOLID
- Single Responsibility Principle: Cada clase tiene una única responsabilidad. Por ejemplo, la clase GestorUsuarios solo maneja las operaciones relacionadas con los usuarios.

- Open/Closed Principle: El sistema está abierto a la extensión (se pueden agregar nuevas funcionalidades) pero cerrado a la modificación (no es necesario modificar las clases existentes para agregar nuevas características).

- Liskov Substitution Principle: Se garantiza que las clases derivadas puedan ser sustituidas por sus clases base sin afectar el comportamiento del sistema.

- Interface Segregation Principle: Las interfaces están diseñadas para ser específicas y no forzar a las clases a implementar métodos que no utilizan.

- Dependency Inversion Principle: Las dependencias se gestionan a través de interfaces, permitiendo una mayor flexibilidad y facilidad de prueba.

## 🔄 Flujo de trabajo del sistema

1. **Menú Principal:**
   - El sistema inicia mostrando el menú principal con las siguientes opciones:
     1. Gestión de usuarios.
     2. Gestión de recursos.
     3. Gestión de reservas.
     4. Gestión de préstamos.
     5. Reportes.
     6. Historial de recordatorios.
     7. Pruebas.
     8. Salir.

2. **Gestión de Usuarios:**
   - Desde el **Menú de Usuarios**, se puede:
     1. Listar usuarios.
     2. Buscar usuarios por ID, nombre o apellido.
     3. Ordenar usuarios por nombre o apellido.
     4. Crear nuevos usuarios.
     5. Eliminar usuarios.
     6. Volver al menú principal.
   
   El sistema permite gestionar completamente a los usuarios registrados.

3. **Gestión de Recursos:**
   - Desde el **Menú de Recursos**, el usuario puede:
     1. Listar recursos disponibles.
     2. Mostrar las categorías de recursos.
     3. Buscar un recurso por ID, título o categoría.
     4. Ordenar los recursos por título o fecha de publicación.
     5. Crear nuevos recursos (libros, audiolibros, revistas).
     6. Eliminar recursos existentes.
     7. Volver al menú principal.

   Se puede buscar y gestionar recursos digitales en función de las necesidades del usuario.

4. **Gestión de Reservas:**
   - Desde el **Menú de Reservas**, los usuarios pueden:
     1. Realizar reservas para recursos no disponibles.
     2. Ver las reservas pendientes.
     3. Eliminar una reserva.
     4. Buscar reservas por ID de usuario, ID de recurso, prioridad o fecha.
     5. Ordenar reservas por prioridad, fecha o ID de usuario.
     6. Ver alertas de disponibilidad (cuando el recurso reservado se libera).
     7. Volver al menú principal.

   Las reservas permiten a los usuarios asegurar recursos no disponibles, y el sistema maneja la cola de reservas.

5. **Gestión de Préstamos:**
   - Desde el **Menú de Préstamos**, los usuarios pueden:
     1. Realizar un préstamo de un recurso disponible.
     2. Ver los préstamos activos.
     3. Devolver recursos prestados.
     4. Buscar préstamos por ID de usuario, ID de recurso o fecha.
     5. Ordenar los préstamos por ID de usuario, fecha de préstamo o ID de recurso.
     6. Ver alertas de vencimiento (notificación de la fecha límite de devolución).
     7. Volver al menú principal.

   Los préstamos permiten que los usuarios tomen recursos prestados bajo ciertas condiciones y los devuelvan al sistema.

6. **Reportes:**
   - Desde el **Menú de Reportes**, se puede ver informes detallados, como:
     1. Los recursos más prestados.
     2. Los usuarios más activos.
     3. Estadísticas por categoría.
     4. Volver al menú principal.

   Los reportes proporcionan una visión general de la actividad dentro del sistema.

7. **Notificaciones y Alertas:**
   - El sistema envía notificaciones automáticas para confirmación de acciones realizadas (registro, préstamo, reserva, devolución, etc.).
   - Además, se generan alertas sobre vencimientos de préstamos y disponibilidad de recursos reservados.

8. **Interacción continua:**
   - Los usuarios pueden navegar entre las opciones del menú principal, realizar operaciones de gestión, consultar reportes o salir del sistema en cualquier momento.

## 🚀 Poner en funcionamiento

# 🛠️ Requisitos Previos
- Java 17 o superior instalado.
- IDE recomendado: IntelliJ, Eclipse o Visual Studio Code.
- Git instalado
- Opcional: Herramienta de compilación (Maven o Gradle).

# 🚀 Instrucciones para Compilar y Ejecutar
1. Clonar el repositorio:
   ```bash
   git clone git@github.com:um-programacion-ii/programacion-2-trabajo-practico-2-Martin-NT.git
   cd programacion-2-trabajo-practico-2-Martin-NT
   
2. Descargar el compilador de Java (javac)
   ```bash
   sudo apt install default-jdk
   
2. Navegar al directorio del proyecto
   ```bash
   cd TP2
   
3. Compilar Proyecto
   ```bash
   find src -name "*.java" > sources.txt
javac -d out @sources.txt
   
4. Ejecutar la aplicación principal: 
   ```bash
   java -cp out app.Main
   
O desde tu IDE, ejecutar la clase app.Main.

## Funcionalidades implementadas

Usuarios
- Registrar nuevo usuario
- Visualizar usuarios registrados desde el menú
- Buscar y Ordenar usuarios
- Eliminar Usuario

Recursos
- Registrar libros, revistas o audiolibros.
- Listar todos los recursos-
- Mostrar categorias y listar recursos por categoria.
- Buscar y Ordenar recursos.
- Eliminar recursos.

Reservas y Prestamos
- Reservar un recurso.
- Ver reservas pendientes.
- Ver alertas de disponibilidad y poder realizar prestamo de recurso desde alerta.
- Eliminar reserva.
- Buscar y Ordenar reservas.
- 
- Ver alertas de vencmiento
- Realizar prestamos.
- Ver prestamos activos.
- Devolver recurso.
- Buscar y Ordenar prestamos.

Reportes
- Recursos más prestados.
- Usuarios más activos.
- Estadísticas por categoría
- Generación asincrónica usando ExecutorService.

Recordatorios 
- Historial disponible desde el menú principal.
- Alertas por préstamos cercanos al vencimiento (24h antes y el día exacto).

Pruebas
- Simular y Verificar Alerta Vencimiento.
- Simular y Verificar Alerta Disponibilidad.
- Simular Concurrencia.
- Ejecutar Reportes.
- Probar servicios de Notificación.
- Recordatorios.
---

## Ejemplos de Flujo Completo
1. Podemos ver usuarios y recursos que ya estan registrados con la opcion 1 de sus respectivos menús.
📋 Lista de usuarios:

📘 Usuario ID: U004
 - Nombre: Valentino
 - Apellido: Rizzotti
 - Email: valenrizzotti@example.com
 - Telefono: 2613467543
 - Frecuencia de Notificación: semanal

📘 Usuario ID: U003
 - Nombre: Facundo
 - Apellido: San Roman
 - Email: facundo@example.com
 - Telefono: 2634257895
 - Frecuencia de Notificación: semanal

📘 Usuario ID: U002
 - Nombre: Valentina
 - Apellido: Rosales
 - Email: valerosales@example.com
 - Telefono: 2634257895
 - Frecuencia de Notificación: diaria

📘 Usuario ID: U001
 - Nombre: Martina
 - Apellido: Rizzotti
 - Email: martirizzotti@example.com
 - Telefono: 2613245789
 - Frecuencia de Notificación: diaria

===== 📚 RECURSOS DISPONIBLES =====

[📚 LIBRO]
 - Recurso ID: L001
 - Título: Harry Potter y la piedra filosofal
 - Autor: J.K. Rowling
 - Fecha de Publicación: 1997-06-26
 - Estado: DISPONIBLE
 - Categoria: LIBRO
 - Páginas: 256
 - Género: Fantasía
 - Editorial: Salamandra


[📰 REVISTA]
 - Recurso ID: R001
 - Título: National Geographic
 - Autor: Varios
 - Fecha de Publicación: 2025-04-10
 - Estado: DISPONIBLE
 - Categoria: REVISTA
 - Número de Edición: 100
 - Periodicidad: Mensual
 - Sección Principal: Ciencia y naturaleza
 - Editorial: National Geographic Society


[🎧 AUDIOLIBRO]
 - Recurso ID: A001
 - Título: El Principito
 - Autor: Antoine de Saint-Exupéry
 - Fecha de Publicación: 1943-04-06
 - Estado: DISPONIBLE
 - Categoria: AUDIOLIBRO
 - Duración en minutos: 92
 - Narrador: Dangello Medina
 - Idioma: Español

1.1 Podemos crear si usuarios y recursos nuevos (Opción 4 de sus respectivos menús) y Eliminar a los nuevos y ya creados recursos y usuarios (Opción 5 de sus respectivos menús).

2. Reserva y Prestamo
- Seleccionamos la Opción 3 del menu principal (Reservas)
- Reservamos recurso (Opción 1) por ejemplo con ID Usuario: U001, ID Recurso: L001, Prioridad: 1
- Vemos que la reserva está pendiente (Seleccionando opción 2)
- Seleccionamos Ver alertas de disponibilidad (Opción 6), vemos la alerta de disponibilidad y podemos realizar el prestamo del recurso desde la alerta, Ejemplo:
   ```bash
   ===== MENÚ DE RESERVAS =====
1. Reservar recurso
2. Ver reservas pendientes
3. Eliminar reserva
4. Buscar reservas
5. Ordenar reservas
6. Ver alertas de disponibilidad
7. Volver al menú principal
--> Seleccione una opción: 6
📢 Verificando alertas de disponibilidad...

📢 ALERTA DE DISPONIBILIDAD
📘 Recurso: Harry Potter y la piedra filosofal (ID: L001)
👤 Reservado por: Martina Rizzotti
📩 Se notificó a Martina por correo y SMS.

Enviando correo a Martina Rizzotti al correo: martirizzotti@example.com
Contenido del mensaje: 📘 Tu recurso 'Harry Potter y la piedra filosofal' está disponible para retirar.

Enviando SMS a Martina Rizzotti al número: 2613245789
Contenido del mensaje: 📘 Tu recurso 'Harry Potter y la piedra filosofal' está disponible para retirar.

Lista de recursos disponibles para retiro:
- Harry Potter y la piedra filosofal (ID: L001)

¿Quieres realizar un préstamo de algún recurso disponible? (SI/NO): 
SI
Ingresa el ID del recurso que deseas prestar:
L001

[main] 🔄 Intentando prestar: Harry Potter y la piedra filosofal
✅ Reserva eliminada correctamente.

Enviando SMS a Martina Rizzotti al número: 2613245789
Contenido del mensaje: 📌 Tu reserva del recurso 'Harry Potter y la piedra filosofal' ha sido cancelada.
Creando préstamo: Harry Potter y la piedra filosofal con estado activo: true

Enviando correo a Martina Rizzotti al correo: martirizzotti@example.com
Contenido del mensaje: 📚 Se ha realizado el préstamo del recurso 'Harry Potter y la piedra filosofal' hasta el 2025-05-07.

RECORDATORIO WARNING: El préstamo del recurso 'Harry Potter y la piedra filosofal' vence el 2025-05-07.

[main] ✅ Préstamo exitoso: Harry Potter y la piedra filosofal


3. Seleccionamos 7. Volver al menú principal, luego 4. Préstamos
4. Seleccionamos 2. Ver préstamos activos y vemos que el prestamo se ha realizado
5. En 6. Ver alertas de vencimiento nos saldra cuando la opcion este a 1 dia de vencer, sino no mostrara prestamos vencidos
6. Devolvemos el prestamo (Opción 3)
   ```bash
   ===== MENÚ DE PRÉSTAMOS =====
1. Realizar préstamo
2. Ver préstamos activos
3. Devolver recurso
4. Buscar préstamos
5. Ordenar préstamos
6. Ver alertas de vencimiento
7. Volver al menú principal
--> Seleccione una opción: 3
--> Ingrese el ID del usuario que devuelve el recurso: U001
Ingrese el ID del recurso que desea devolver: L001
Devolución registrada para: Harry Potter y la piedra filosofal

El LIBRO 'Harry Potter y la piedra filosofal' (L001) ha sido devuelto correctamente.

Enviando correo a Martina Rizzotti al correo: martirizzotti@example.com
Contenido del mensaje: 📥 Has devuelto el recurso 'Harry Potter y la piedra filosofal' correctamente. ¡Gracias!

8. Si se desea podemos ver los Reportes por ejemplo de recursos prestados:
   ```bash
   --> Seleccione una opción: 1
⏳ El reporte se está generando en segundo plano...
🔁 Podés seguir navegando por el sistema.


===== 📊 REPORTES DISPONIBLES =====
1. Recursos más prestados
2. Usuarios más activos
3. Estadísticas por categoría
4. Volver al menú principal

🛠️ Generando reporte: prestados
--> Seleccione una opción: ⏳ Progreso: [███░░░░░░░] 33%
⏳ Progreso: [██████░░░░] 66%
⏳ Progreso: [██████████] 100%

📚 Recursos más prestados:
🔸 Harry Potter y la piedra filosofal - 1 préstamo(s) - Categoría: LIBRO
🕒 Generado en: 2025-04-23T13:54:13.629400329

✅ Reporte 'prestados' generado con éxito.

--> Seleccione una opción del menú:

9. Como no hay prestamos por vencer si nos dirigimos al menu de pruebas y seleccionamos la opcion 1. Simular y Verificar Alerta Vencimiento, podemos ver como funcionaria el sistema de alerta vencmiento:
   ```bash
   ===== 📊 INICIO DE GENERAR PRESTAMO POR VENCER DE PRUEBA =====
Creando préstamo: Harry Potter y la piedra filosofal con estado activo: true
✅ Reserva añadida correctamente con prioridad 1.

Enviando SMS a Martina Rizzotti al número: 2613245789
Contenido del mensaje: ¡Reserva exitosa! Has reservado el recurso: Harry Potter y la piedra filosofal

[main] 🔄 Intentando prestar: Harry Potter y la piedra filosofal
Creando préstamo: Harry Potter y la piedra filosofal con estado activo: true

Enviando correo a Martina Rizzotti al correo: martirizzotti@example.com
Contenido del mensaje: 📚 Se ha realizado el préstamo del recurso 'Harry Potter y la piedra filosofal' hasta el 2025-05-07.

RECORDATORIO WARNING: El préstamo del recurso 'Harry Potter y la piedra filosofal' vence el 2025-05-07.

[main] ✅ Préstamo exitoso: Harry Potter y la piedra filosofal
🔔 Se ha generado un préstamo que vence mañana para pruebas de alertas.

===== 📊 INICIO DE VERIFICAR ALERTAS VENCIMIENTO DE PRUEBA =====
*** ALERTA *** ¡ALERTA! El préstamo vence hoy. Recurso: Harry Potter y la piedra filosofal (Vence el: 2025-04-23)
¿Desea renovar este préstamo? (SI/NO): 
SI
El préstamo del recurso 'Harry Potter y la piedra filosofal' ha sido renovado hasta el 2025-05-07

Enviando correo a Martina Rizzotti al correo: martirizzotti@example.com
Contenido del mensaje: 📅 Tu préstamo del recurso 'Harry Potter y la piedra filosofal' ha sido renovado hasta el 2025-05-07.
¡Préstamo renovado exitosamente!

10. Por ultimo se dejaron opciones por si se desea probar lo siguiente:
    - 2. Simular y Verificar Alerta Disponibilidad
    - 3. Simular Concurrencia
    - 4. Ejecutar Reportes
    - 5. Probar servicios de Notificación
    - 6. Recordatorios
    
11. Finalizamos el programa con 8. Salir

## 📋 Requisitos Adicionales

### Documentación del Sistema
Como parte del trabajo práctico, deberás incluir en este README una guía de uso que explique:

1. **Cómo funciona el sistema**:
   - Descripción general de la arquitectura
   - Explicación de los componentes principales
   - Flujo de trabajo del sistema

2. **Cómo ponerlo en funcionamiento**:
   - Deberás incluir las instrucciones detalladas de puesta en marcha
   - Explicar los requisitos previos necesarios
   - Describir el proceso de compilación
   - Detallar cómo ejecutar la aplicación

3. **Cómo probar cada aspecto desarrollado**:
   - Deberás proporcionar ejemplos de uso para cada funcionalidad implementada
   - Incluir casos de prueba que demuestren el funcionamiento del sistema
   - Describir flujos de trabajo completos que muestren la interacción entre diferentes componentes

La guía debe ser clara, concisa y permitir a cualquier usuario entender y probar el sistema. Se valorará especialmente:
- La claridad de las instrucciones
- La completitud de la documentación
- La organización de la información
- La inclusión de ejemplos prácticos

### Prueba de Funcionalidades

#### 1. Gestión de Recursos
- **Agregar Libro**: 
  - Proceso para agregar un nuevo libro al sistema
  - Verificación de que el libro se agregó correctamente
  - Validación de los datos ingresados

- **Buscar Recurso**:
  - Proceso de búsqueda de recursos
  - Verificación de resultados de búsqueda
  - Manejo de casos donde no se encuentran resultados

- **Listar Recursos**:
  - Visualización de todos los recursos
  - Filtrado por diferentes criterios
  - Ordenamiento de resultados

#### 2. Gestión de Usuarios
- **Registrar Usuario**:
  - Proceso de registro de nuevos usuarios
  - Validación de datos del usuario
  - Verificación del registro exitoso

- **Buscar Usuario**:
  - Proceso de búsqueda de usuarios
  - Visualización de información del usuario
  - Manejo de usuarios no encontrados

#### 3. Préstamos
- **Realizar Préstamo**:
  - Proceso completo de préstamo
  - Verificación de disponibilidad
  - Actualización de estados

- **Devolver Recurso**:
  - Proceso de devolución
  - Actualización de estados
  - Liberación del recurso

#### 4. Reservas
- **Realizar Reserva**:
  - Proceso de reserva de recursos
  - Gestión de cola de reservas
  - Notificación de disponibilidad

#### 5. Reportes
- **Ver Reportes**:
  - Generación de diferentes tipos de reportes
  - Visualización de estadísticas
  - Exportación de datos

#### 6. Alertas
- **Verificar Alertas**:
  - Sistema de notificaciones
  - Diferentes tipos de alertas
  - Gestión de recordatorios

### Ejemplos de Prueba
1. **Flujo Completo de Préstamo**:
   - Registrar un usuario
   - Agregar un libro
   - Realizar un préstamo
   - Verificar el estado del recurso
   - Devolver el recurso
   - Verificar la actualización del estado

2. **Sistema de Reservas**:
   - Registrar dos usuarios
   - Agregar un libro
   - Realizar una reserva con cada usuario
   - Verificar la cola de reservas
   - Procesar las reservas

3. **Alertas y Notificaciones**:
   - Realizar un préstamo
   - Esperar a que se acerque la fecha de vencimiento
   - Verificar las alertas generadas
   - Probar la renovación del préstamo

## 🧩 Tecnologías y Herramientas

- Java 21+ (LTS)
- Git y GitHub
- GitHub Projects
- GitHub Issues
- GitHub Pull Requests

## 📘 Etapas del Trabajo

### Etapa 1: Diseño Base y Principios SOLID
- **SRP**: 
  - Crear clase `Usuario` con atributos básicos (nombre, ID, email)
  - Crear clase `RecursoDigital` como clase base abstracta
  - Implementar clase `GestorUsuarios` separada de `GestorRecursos`
  - Cada clase debe tener una única responsabilidad clara
  - Implementar clase `Consola` para manejar la interacción con el usuario

- **OCP**: 
  - Diseñar interfaz `RecursoDigital` con métodos comunes
  - Implementar clases concretas `Libro`, `Revista`, `Audiolibro`
  - Usar herencia para extender funcionalidad sin modificar código existente
  - Ejemplo: agregar nuevo tipo de recurso sin cambiar clases existentes
  - Implementar menú de consola extensible para nuevos tipos de recursos

- **LSP**: 
  - Asegurar que todas las subclases de `RecursoDigital` puedan usarse donde se espera `RecursoDigital`
  - Implementar métodos comunes en la clase base
  - Validar que el comportamiento sea consistente en todas las subclases
  - Crear métodos de visualización en consola para todos los tipos de recursos

- **ISP**: 
  - Crear interfaz `Prestable` para recursos que se pueden prestar
  - Crear interfaz `Renovable` para recursos que permiten renovación
  - Implementar solo las interfaces necesarias en cada clase
  - Diseñar menús de consola específicos para cada tipo de operación

- **DIP**: 
  - Crear interfaz `ServicioNotificaciones`
  - Implementar `ServicioNotificacionesEmail` y `ServicioNotificacionesSMS`
  - Usar inyección de dependencias en las clases que necesitan notificaciones
  - Implementar visualización de notificaciones en consola

### Etapa 2: Gestión de Recursos y Colecciones
- Implementar colecciones:
  - Usar `ArrayList<RecursoDigital>` para almacenar recursos
  - Usar `Map<String, Usuario>` para gestionar usuarios
  - Implementar métodos de búsqueda básicos
  - Crear menú de consola para gestión de recursos

- Crear servicios de búsqueda:
  - Implementar búsqueda por título usando Streams
  - Implementar filtrado por categoría
  - Crear comparadores personalizados para ordenamiento
  - Diseñar interfaz de consola para búsquedas con filtros

- Sistema de categorización:
  - Crear enum `CategoriaRecurso`
  - Implementar método de asignación de categorías
  - Crear búsqueda por categoría
  - Mostrar categorías disponibles en consola

- Manejo de excepciones:
  - Crear `RecursoNoDisponibleException`
  - Crear `UsuarioNoEncontradoException`
  - Implementar manejo adecuado de excepciones en los servicios
  - Mostrar mensajes de error amigables en consola

### Etapa 3: Sistema de Préstamos y Reservas
- Implementar sistema de préstamos:
  - Crear clase `Prestamo` con atributos básicos
  - Implementar lógica de préstamo y devolución
  - Manejar estados de los recursos (disponible, prestado, reservado)
  - Diseñar menú de consola para préstamos

- Sistema de reservas:
  - Crear clase `Reserva` con atributos necesarios
  - Implementar cola de reservas usando `BlockingQueue`
  - Manejar prioridad de reservas
  - Mostrar estado de reservas en consola

- Notificaciones:
  - Implementar sistema básico de notificaciones
  - Crear diferentes tipos de notificaciones
  - Usar `ExecutorService` para enviar notificaciones
  - Mostrar notificaciones en consola

- Concurrencia:
  - Implementar sincronización en operaciones de préstamo
  - Usar `synchronized` donde sea necesario
  - Manejar condiciones de carrera
  - Mostrar estado de operaciones concurrentes en consola

### Etapa 4: Reportes y Análisis
- Generar reportes básicos:
  - Implementar reporte de recursos más prestados
  - Crear reporte de usuarios más activos
  - Generar estadísticas de uso por categoría
  - Diseñar visualización de reportes en consola

- Sistema de alertas:
  - Implementar alertas por vencimiento de préstamos:
    - Crear clase `AlertaVencimiento` que monitorea fechas de devolución
    - Implementar lógica de recordatorios (1 día antes, día del vencimiento)
    - Mostrar alertas en consola con formato destacado
    - Permitir renovación desde la alerta
  
  - Crear notificaciones de disponibilidad:
    - Implementar `AlertaDisponibilidad` para recursos reservados
    - Notificar cuando un recurso reservado está disponible
    - Mostrar lista de recursos disponibles en consola
    - Permitir préstamo inmediato desde la notificación
  
  - Manejar recordatorios automáticos:
    - Implementar sistema de recordatorios periódicos
    - Crear diferentes niveles de urgencia (info, warning, error)
    - Mostrar historial de alertas en consola
    - Permitir configuración de preferencias de notificación

- Concurrencia en reportes:
  - Implementar generación de reportes en segundo plano
  - Usar `ExecutorService` para tareas asíncronas
  - Manejar concurrencia en acceso a datos
  - Mostrar progreso de generación de reportes en consola

## 📋 Detalle de Implementación

### 1. Estructura Base
```java
// Interfaces principales
public interface RecursoDigital {
    String getIdentificador();
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
}

public interface Prestable {
    boolean estaDisponible();
    LocalDateTime getFechaDevolucion();
    void prestar(Usuario usuario);
}

public interface Notificable {
    void enviarNotificacion(String mensaje);
    List<Notificacion> getNotificacionesPendientes();
}

// Clase base abstracta
public abstract class RecursoBase implements RecursoDigital, Prestable {
    // Implementación común
}
```

### 2. Gestión de Biblioteca
```java
public class GestorBiblioteca {
    private final Map<String, RecursoDigital> recursos;
    private final List<Prestamo> prestamos;
    private final ExecutorService notificador;
    // Implementación de gestión
}
```

### 3. Sistema de Préstamos
```java
public class SistemaPrestamos {
    private final BlockingQueue<SolicitudPrestamo> colaSolicitudes;
    private final ExecutorService procesadorPrestamos;
    // Implementación de préstamos
}
```

## ✅ Entrega y Flujo de Trabajo con GitHub

1. **Configuración del Repositorio**
   - Proteger la rama `main`
   - Crear template de Issues y Pull Requests

2. **Project Kanban**
   - `To Do`
   - `In Progress`
   - `Code Review`
   - `Done`

3. **Milestones**
   - Etapa 1: Diseño Base
   - Etapa 2: Gestión de Recursos
   - Etapa 3: Sistema de Préstamos
   - Etapa 4: Reportes

4. **Issues y Pull Requests**
   - Crear Issues detallados para cada funcionalidad
   - Asociar cada Issue a un Milestone
   - Implementar en ramas feature
   - Revisar código antes de merge

## 📝 Ejemplo de Issue

### Título
Implementar sistema de préstamos concurrente

### Descripción
Crear el sistema de préstamos que utilice hilos y el patrón productor-consumidor para procesar solicitudes de préstamo en tiempo real.

#### Requisitos
- Implementar `BlockingQueue` para solicitudes de préstamo
- Crear procesador de solicitudes usando `ExecutorService`
- Implementar sistema de notificaciones
- Asegurar thread-safety en operaciones de préstamo

#### Criterios de Aceptación
- [ ] Sistema procesa préstamos concurrentemente
- [ ] Manejo adecuado de excepciones
- [ ] Documentación de diseño

### Labels
- `enhancement`
- `concurrency`

## ✅ Requisitos para la Entrega

- ✅ Implementación completa de todas las etapas
- ✅ Código bien documentado
- ✅ Todos los Issues cerrados
- ✅ Todos los Milestones completados
- ✅ Pull Requests revisados y aprobados
- ✅ Project actualizado

> ⏰ **Fecha de vencimiento**: 23/04/2025 a las 13:00 hs

## 📚 Recursos Adicionales

- Documentación oficial de Java 21
- Guías de estilo de código
- Ejemplos de implementación concurrente
- Patrones de diseño aplicados

## 📝 Consideraciones Éticas

### Uso de Inteligencia Artificial
El uso de herramientas de IA en este trabajo práctico debe seguir las siguientes pautas:

1. **Transparencia**
   - Documentar claramente qué partes del código fueron generadas con IA
   - Explicar las modificaciones realizadas al código generado
   - Mantener un registro de las herramientas utilizadas

2. **Aprendizaje**
   - La IA debe usarse como herramienta de aprendizaje, no como reemplazo
   - Comprender y ser capaz de explicar el código generado
   - Utilizar la IA para mejorar la comprensión de conceptos

3. **Integridad Académica**
   - El trabajo final debe reflejar tu aprendizaje y comprensión personal
   - No se permite la presentación de código generado sin comprensión
   - Debes poder explicar y defender cualquier parte del código

4. **Responsabilidad**
   - Verificar la corrección y seguridad del código generado
   - Asegurar que el código cumple con los requisitos del proyecto
   - Mantener la calidad y estándares de código establecidos

5. **Desarrollo Individual**
   - La IA puede usarse para facilitar tu proceso de aprendizaje
   - Documentar tu proceso de desarrollo y decisiones tomadas
   - Mantener un registro de tu progreso y aprendizaje

### Consecuencias del Uso Inadecuado
El uso inadecuado de IA puede resultar en:
- Calificación reducida o nula
- Sanciones académicas
- Pérdida de oportunidades de aprendizaje
- Impacto negativo en tu desarrollo profesional

## 📝 Licencia

Este trabajo es parte del curso de Programación Avanzada de Ingeniería en Informática. Uso educativo únicamente.
