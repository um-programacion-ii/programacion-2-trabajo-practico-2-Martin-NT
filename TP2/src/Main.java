import classes.*;
import interfaces.*;
import services.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear gestor de usuarios y agregar usuarios de prueba
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        gestorUsuarios.agregarUsuario(new Usuario("U001", "Martina Rizzotti", "martirizzotti@example.com", "martincho15", "2613245789"));
        gestorUsuarios.agregarUsuario(new Usuario("U002", "Valentina Rosales", "valerosales@example.com", "sanroman44", "2634257895"));

        // Crear gestor de recursos y agregar recursos
        GestorRecursos gestor = new GestorRecursos();
        gestor.agregarRecurso(new Libro("L001", "Harry Potter y la piedra filosofal", "J.K. Rowling",
                LocalDate.of(1997, 6, 26), RecursoBase.EstadoRecurso.DISPONIBLE,
                LocalDateTime.now().plusDays(10), 256, "Fantasía", "Salamandra"));

        gestor.agregarRecurso(new Revista("R001", "National Geographic", "Varios",
                LocalDate.of(2025, 4, 10), RecursoBase.EstadoRecurso.DISPONIBLE,
                LocalDateTime.now().plusDays(7), 100, "Mensual", "Ciencia y naturaleza", "National Geographic Society"));

        gestor.agregarRecurso(new Audiolibro("A001", "El Principito", "Antoine de Saint-Exupéry",
                LocalDate.of(1943, 4, 6), RecursoBase.EstadoRecurso.DISPONIBLE,
                LocalDateTime.now().plusDays(7), 92, "Dangello Medina", "Español"));

        // Servicios de notificación
        ServicioNotificaciones servicioEmail = new ServicioNotificacionesEmail();
        ServicioNotificaciones servicioSMS = new ServicioNotificacionesSMS();

        System.out.println("\n=== PRUEBAS DE NOTIFICACIONES ===");
        System.out.println("\n- Prueba del servicio email");
        servicioEmail.enviarNotificacion("--> ¡Tienes un nuevo mensaje!", gestorUsuarios.obtenerUsuarioPorId("U001"));

        System.out.println("\n- Prueba del servicio SMS");
        servicioSMS.enviarNotificacion("--> ¡Tienes un nuevo mensaje!", gestorUsuarios.obtenerUsuarioPorId("U002"));

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
                                Consola.mostrarUsuarios(gestorUsuarios.getUsuarios());
                                break;
                            case 2:
                                System.out.print("🔎 Ingrese el ID del usuario a buscar: ");
                                String idBuscado = consola.leerTexto();
                                Usuario usuarioEncontrado = gestorUsuarios.obtenerUsuarioPorId(idBuscado);
                                if (usuarioEncontrado != null) {
                                    System.out.println("✅ Usuario encontrado:\n" + usuarioEncontrado);
                                } else {
                                    System.out.println("❌ Usuario no encontrado.");
                                }
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("⚠️ Opción inválida.");
                        }
                    } while (opcionUsuarios != 3);
                    break;

                case 2:
                    int opcionRecursos;
                    do {
                        consola.mostrarMenuRecursos();
                        opcionRecursos = consola.leerOpcion();
                        switch (opcionRecursos) {
                            case 1:
                                Consola.mostrarRecursos(gestor.getRecursos());
                                break;
                            case 2:
                                Consola.mostrarLibros(gestor.getRecursos());
                                break;
                            case 3:
                                Consola.mostrarAudiolibros(gestor.getRecursos());
                                break;
                            case 4:
                                Consola.mostrarRevistas(gestor.getRecursos());
                                break;
                            case 5:
                                System.out.print("🔎 Ingrese parte del título a buscar: ");
                                String tituloBuscado = consola.leerTexto();
                                List<RecursoDigital> encontrados = gestor.buscarPorTitulo(tituloBuscado);
                                Consola.mostrarRecursos(encontrados);
                                break;
                            case 6:
                                System.out.println("⚠️ Funcionalidad de préstamo aún no implementada.");
                                break;
                            case 7:
                                System.out.println("⚠️ Funcionalidad de renovación aún no implementada.");
                                break;
                            case 8:
                                break;
                            default:
                                System.out.println("⚠️ Opción inválida.");
                        }

                    } while (opcionRecursos != 8);
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
