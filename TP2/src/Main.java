import classes.*;
import interfaces.*;
import services.*;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.time.LocalDateTime;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        // Crear usuarios de prueba
        Usuario[] usuarios = {
                new Usuario("U001", "Martina Rizzotti", "martirizzotti@example.com", "martincho15", "2613245789"),
                new Usuario("U002", "Valentina Rosales", "valerosales@example.com", "sanroman44", "2634257895")
        };

        // Crear recursos de prueba
        RecursoDigital[] recursos = {
                // Libro de ejemplo
                new Libro("L001", "Harry Potter y la piedra filosofal", "J.K. Rowling",
                        LocalDate.of(1997, 6, 26), RecursoBase.EstadoRecurso.DISPONIBLE,
                        LocalDateTime.now().plusDays(10), 256, "Fantasía", "Salamandra"),

                // Revista de ejemplo
                new Revista("R001", "National Geographic", "Varios",
                        LocalDate.of(2025, 4, 10), RecursoBase.EstadoRecurso.DISPONIBLE,
                        LocalDateTime.now().plusDays(7), 100, "Mensual", "Ciencia y naturaleza", "National Geographic Society"),

                // Audiolibro de ejemplo
                new Audiolibro("A001", "El Principito", "Antoine de Saint-Exupéry",
                        LocalDate.of(1943, 4, 6), RecursoBase.EstadoRecurso.DISPONIBLE,
                        LocalDateTime.now().plusDays(7), 92, "Dangello Medina", "Español")
        };

        // Crear instancias de los servicios de notificación
        ServicioNotificaciones servicioEmail = new ServicioNotificacionesEmail();
        ServicioNotificaciones servicioSMS = new ServicioNotificacionesSMS();

        System.out.println("\n=== PRUEBAS DE NOTIFICACIONES ===");
        // Enviar una notificación por correo electrónico
        System.out.println("\n- Prueba del servicio email");
        System.out.println("--> Enviando notificación por correo electrónico:");
        servicioEmail.enviarNotificacion("--> ¡Tienes un nuevo mensaje!", usuarios[0]);

        // Enviar una notificación por SMS (en este caso estamos simulando el correo como SMS)
        System.out.println("\n- Prueba del servicio SMS");
        System.out.println("--> Enviando notificación por SMS:");
        servicioSMS.enviarNotificacion("--> ¡Tienes un nuevo mensaje!", usuarios[1]);

        Consola consola = new Consola();
        int opcionPrincipal;

        do {
            consola.mostrarMenuPrincipal();
            opcionPrincipal = consola.leerOpcion();

            switch (opcionPrincipal) {
                case 1:
                    int opcionUsuarios;
                    do {
                        consola.mostrarMenuUsuarios();
                        opcionUsuarios = consola.leerOpcion();
                        switch (opcionUsuarios) {
                            case 1:
                                Consola.mostrarUsuarios(usuarios);
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("⚠️ Opción inválida.");
                        }
                    } while (opcionUsuarios != 2);
                    break;

                case 2:
                    int opcionRecursos;
                    do {
                        consola.mostrarMenuRecursos();
                        opcionRecursos = consola.leerOpcion();
                        switch (opcionRecursos) {
                            case 1:
                                Consola.mostrarRecursos(recursos);
                                break;
                            case 2:
                                Consola.mostrarLibros(recursos);
                                break;
                            case 3:
                                Consola.mostrarAudiolibros(recursos);
                                break;
                            case 4:
                                Consola.mostrarRevistas(recursos);
                                break;
                            case 5:
                                System.out.println("⚠️ Funcionalidad de préstamo aún no implementada.");
                                break;
                            case 6:
                                System.out.println("⚠️ Funcionalidad de renovación aún no implementada.");
                                break;
                            case 7:
                                // Salir al menú principal
                                break;
                            default:
                                System.out.println("⚠️ Opción inválida.");
                        }

                    } while (opcionRecursos != 7);
                    break;

                case 3:
                    System.out.println("👋 Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionPrincipal != 3);
    }
}

// Nota: Se aprendio a usar Scanner con ChatGPT