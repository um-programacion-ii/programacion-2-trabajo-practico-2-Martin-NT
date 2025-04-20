import classes.*;
import interfaces.*;
import services.*;
import Enum.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Crear gestor de usuarios y agregar usuarios de prueba
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        gestorUsuarios.agregarUsuario(new Usuario("U001", "Martina Rizzotti", "martirizzotti@example.com", "martincho15", "2613245789"));
        gestorUsuarios.agregarUsuario(new Usuario("U002", "Valentina Rosales", "valerosales@example.com", "sanroman44", "2634257895"));

        // Crear gestor de recursos y agregar recursos
        GestorRecursos gestorRecursos = new GestorRecursos();
        gestorRecursos.agregarRecurso(new Libro("L001", "Harry Potter y la piedra filosofal", "J.K. Rowling",
                LocalDate.of(1997, 6, 26), EstadoRecurso.DISPONIBLE,
                LocalDateTime.now().plusDays(10), CategoriaRecurso.LIBRO, 256, "Fantasía", "Salamandra"));

        gestorRecursos.agregarRecurso(new Revista("R001", "National Geographic", "Varios",
                LocalDate.of(2025, 4, 10), EstadoRecurso.DISPONIBLE,
                LocalDateTime.now().plusDays(7), CategoriaRecurso.REVISTA, 100, "Mensual", "Ciencia y naturaleza", "National Geographic Society"));

        gestorRecursos.agregarRecurso(new Audiolibro("A001", "El Principito", "Antoine de Saint-Exupéry",
                LocalDate.of(1943, 4, 6), EstadoRecurso.DISPONIBLE,
                LocalDateTime.now().plusDays(7), CategoriaRecurso.AUDIOLIBRO, 92, "Dangello Medina", "Español"));

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
                                gestorRecursos.mostrarRecursos();
                                break;

                            case 2: // MOSTRAR POR CATEGORÍA
                                gestorRecursos.mostrarCategoriasDisponibles();
                                int opcionFiltro;
                                do {
                                    consola.mostrarMenuFiltradoPorCategoria();
                                    opcionFiltro = consola.leerOpcion();
                                    switch (opcionFiltro) {
                                        case 1:
                                            gestorRecursos.filtrarLibros();
                                            break;
                                        case 2:
                                            gestorRecursos.filtrarAudiolibros();
                                            break;
                                        case 3:
                                            gestorRecursos.filtrarRevistas();
                                            break;
                                        case 4:
                                            System.out.println("↩️ Volviendo al Menú de Recursos...");
                                            break;
                                        default:
                                            System.out.println("⚠️ Opción inválida.");
                                    }
                                } while (opcionFiltro != 4);
                                break;

                            case 3: // BUSCAR
                                int opcionBusqueda;
                                do {
                                    consola.mostrarMenuBusqueda();
                                    opcionBusqueda = consola.leerOpcion();
                                    switch (opcionBusqueda) {
                                        case 1:
                                            System.out.print("--> 🔎 Ingrese el título a buscar: ");
                                            String titulo = consola.leerTexto();
                                            gestorRecursos.buscarPorTitulo(titulo);
                                            break;
                                        case 2:
                                            System.out.print("--> 🔎 Ingrese categoría (LIBRO, REVISTA, AUDIOLIBRO): ");
                                            String cat = consola.leerTexto().toUpperCase();
                                            try {
                                                CategoriaRecurso categoria = CategoriaRecurso.valueOf(cat);
                                                gestorRecursos.buscarPorCategoria(categoria);
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("⚠️ Categoría no válida.");
                                            }
                                            break;
                                        case 3:
                                            System.out.println("↩️ Volviendo al Menú de Recursos...");
                                            break;
                                        default:
                                            System.out.println("⚠️ Opción inválida.");
                                    }
                                } while (opcionBusqueda != 3);
                                break;

                            case 4: // ORDENAR
                                int opcionOrden;
                                do {
                                    consola.mostrarMenuOrdenar();
                                    opcionOrden = consola.leerOpcion();
                                    switch (opcionOrden) {
                                        case 1:
                                            gestorRecursos.ordenarYMostrarPorTitulo();
                                            break;
                                        case 2:
                                            gestorRecursos.ordenarYMostrarPorFecha();
                                            break;
                                        case 3:
                                            System.out.println("↩️ Volviendo al Menú de Recursos...");
                                            break;
                                        default:
                                            System.out.println("⚠️ Opción inválida.");
                                    }
                                } while (opcionOrden != 3);
                                break;

                            case 5: // CREAR
                                consola.mostrarMenuCrearRecurso();
                                int tipoRecurso = consola.leerOpcion();
                                if (tipoRecurso >= 1 && tipoRecurso <= 3) {
                                    consola.crearRecurso(tipoRecurso, gestorRecursos);
                                } else if (tipoRecurso == 4) {
                                    System.out.println("↩️ Volviendo al menú de recursos...");
                                } else {
                                    System.out.println("❌ Opción no válida.");
                                }
                                break;


                            case 6:
                                System.out.println("⚠️ Funcionalidad de eliminar recurso aún no implementada.");
                                break;

                            case 7:
                                System.out.println("↩️ Volviendo al Menú Principal...");
                                break;

                            default:
                                System.out.println("⚠️ Opción inválida.");
                        }
                    } while (opcionRecursos != 7);
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionPrincipal != 3);
    }
}