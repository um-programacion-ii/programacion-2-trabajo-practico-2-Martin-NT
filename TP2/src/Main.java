import Excepciones.RecursoNoDisponibleException;
import Excepciones.UsuarioNoEncontradoException;
import classes.*;
import interfaces.*;
import services.*;
import Enum.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Crear gestor de usuarios
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        // Crear gestor de recursos
        GestorRecursos gestorRecursos = new GestorRecursos();

        // Crear Usuarios
        Usuario usuario1 = new Usuario("U001", "Martina", "Rizzotti", "martirizzotti@example.com", "martincho15", "2613245789");
        Usuario usuario2 = new Usuario("U002", "Valentina", "Rosales", "valerosales@example.com", "valero1911", "2634257895");
        Usuario usuario3 = new Usuario("U003", "Facundo", "San Roman", "facundo@example.com", "sanroman44", "2634257895");
        Usuario usuario4 = new Usuario("U004", "Valentino", "Rizzotti", "valenrizzotti@example.com", "bianquita10", "2613467543");

        // Crear recursos
        Libro libro1 = new Libro("L001", "Harry Potter y la piedra filosofal", "J.K. Rowling",
                LocalDate.of(1997, 6, 26), EstadoRecurso.DISPONIBLE, CategoriaRecurso.LIBRO, 256, "Fantasía", "Salamandra");

        Revista revista1 = new Revista("R001", "National Geographic", "Varios",
                LocalDate.of(2025, 4, 10), EstadoRecurso.DISPONIBLE, CategoriaRecurso.REVISTA, 100, "Mensual", "Ciencia y naturaleza", "National Geographic Society");

        Audiolibro audiolibro1 = new Audiolibro("A001", "El Principito", "Antoine de Saint-Exupéry",
                LocalDate.of(1943, 4, 6), EstadoRecurso.DISPONIBLE, CategoriaRecurso.AUDIOLIBRO, 92, "Dangello Medina", "Español");

        // Agregar Usuarios al Gestor
        gestorUsuarios.agregarUsuario(usuario1);
        gestorUsuarios.agregarUsuario(usuario2);
        gestorUsuarios.agregarUsuario(usuario3);
        gestorUsuarios.agregarUsuario(usuario4);

        // Agregar Recursos al Gestor
        gestorRecursos.agregarRecurso(libro1);
        gestorRecursos.agregarRecurso(revista1);
        gestorRecursos.agregarRecurso(audiolibro1);

        // Crear Prestamos
        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucion = fechaPrestamo.plusDays(14); // Suponiendo que el préstamo tiene una duración de 14 días
        boolean activo = true;
        Prestamo prestamo1 = new Prestamo(usuario1, libro1, fechaPrestamo, fechaDevolucion, activo);

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
                            case 1: // Ver usuarios
                                try {
                                    gestorUsuarios.mostrarUsuarios();
                                } catch (UsuarioNoEncontradoException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case 2: // Buscar Usuarios
                                int opcionBusquedaUsuarios;
                                do {
                                    consola.mostrarMenuBusquedaUsuarios();
                                    opcionBusquedaUsuarios = consola.leerOpcion();
                                    switch (opcionBusquedaUsuarios) {
                                        case 1:
                                            System.out.print("🔎 Ingrese el ID del usuario a buscar: ");
                                            String idBuscado = consola.leerTexto();
                                            try {
                                                gestorUsuarios.buscarPorId(idBuscado);
                                            } catch (UsuarioNoEncontradoException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 2:
                                            System.out.print("🔎 Ingrese el nombre del usuario a buscar: ");
                                            String nombreBuscado = consola.leerTexto();
                                            try {
                                                gestorUsuarios.buscarPorNombre(nombreBuscado);
                                            } catch (UsuarioNoEncontradoException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 3:
                                            System.out.print("🔎 Ingrese el apellido del usuario a buscar: ");
                                            String apellidoBuscado = consola.leerTexto();
                                            try {
                                                gestorUsuarios.buscarPorApellido(apellidoBuscado);
                                            } catch (UsuarioNoEncontradoException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 4:
                                            System.out.println("↩️ Volviendo al Menú de Usuarios...");
                                            break;
                                        default:
                                            System.out.println("⚠️ Opción inválida.");
                                    }
                                } while (opcionBusquedaUsuarios != 4);
                                break;


                            case 3: // ORDENAR
                                int opcionOrdenUsuario;
                                do {
                                    consola.mostrarMenuOrdenarUsuarios();
                                    opcionOrdenUsuario = consola.leerOpcion();
                                    switch (opcionOrdenUsuario) {
                                        case 1:
                                            try {
                                                gestorUsuarios.ordenarPorNombre();
                                            } catch (UsuarioNoEncontradoException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 2:
                                            try {
                                                gestorUsuarios.ordenarPorApellido();
                                            } catch (UsuarioNoEncontradoException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 3:
                                            System.out.println("↩️ Volviendo al Menú de Usuarios...");
                                            break;
                                        default:
                                            System.out.println("⚠️ Opción inválida.");
                                    }
                                } while (opcionOrdenUsuario != 3);
                                break;

                            case 4: // CREAR
                                consola.crearUsuario(gestorUsuarios);
                                break;

                            case 5: // ELIMINAR
                                consola.eliminarUsuario(gestorUsuarios);
                                break;

                            case 6:
                                System.out.println("↩️ Volviendo al Menú Principal...");
                                break;

                            default:
                                System.out.println("⚠️ Opción inválida.");
                        }
                    } while (opcionUsuarios != 6);
                    break;

                case 2:
                    int opcionRecursos;
                    do {
                        consola.mostrarMenuRecursos();
                        opcionRecursos = consola.leerOpcion();
                        switch (opcionRecursos) {
                            case 1:
                                try {
                                    gestorRecursos.mostrarRecursos();
                                } catch (RecursoNoDisponibleException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;

                            case 2: // MOSTRAR POR CATEGORÍA
                                gestorRecursos.mostrarCategoriasDisponibles();
                                int opcionFiltro;
                                do {
                                    consola.mostrarMenuFiltradoPorCategoria();
                                    opcionFiltro = consola.leerOpcion();
                                    switch (opcionFiltro) {
                                        case 1:
                                            try {
                                                gestorRecursos.filtrarLibros();
                                            } catch (RecursoNoDisponibleException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 2:
                                            try {
                                                gestorRecursos.filtrarAudiolibros();
                                            } catch (RecursoNoDisponibleException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 3:
                                            try {
                                                gestorRecursos.filtrarRevistas();
                                            } catch (RecursoNoDisponibleException e) {
                                                System.out.println(e.getMessage());
                                            }
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
                                    consola.mostrarMenuBusquedaRecursos();
                                    opcionBusqueda = consola.leerOpcion();
                                    switch (opcionBusqueda) {
                                        case 1:
                                            System.out.print("🔎 Ingrese el ID del recurso a buscar: ");
                                            String idBuscado = consola.leerTexto();
                                            try {
                                                gestorRecursos.buscarPorId(idBuscado);
                                            } catch (RecursoNoDisponibleException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 2:
                                            System.out.print("--> 🔎 Ingrese el título a buscar: ");
                                            String titulo = consola.leerTexto();
                                            try {
                                                gestorRecursos.buscarPorTitulo(titulo);
                                            } catch (RecursoNoDisponibleException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 3:
                                            System.out.print("--> 🔎 Ingrese categoría (LIBRO, REVISTA, AUDIOLIBRO): ");
                                            String cat = consola.leerTexto().toUpperCase();
                                            try {
                                                CategoriaRecurso categoria = CategoriaRecurso.valueOf(cat);
                                                gestorRecursos.buscarPorCategoria(categoria);
                                            } catch (IllegalArgumentException e) {
                                                System.out.println("⚠️ Categoría no válida.");
                                            } catch (RecursoNoDisponibleException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 4:
                                            System.out.println("↩️ Volviendo al Menú de Recursos...");
                                            break;
                                        default:
                                            System.out.println("⚠️ Opción inválida.");
                                    }
                                } while (opcionBusqueda != 4);
                                break;

                            case 4: // ORDENAR
                                int opcionOrden;
                                do {
                                    consola.mostrarMenuOrdenarRecursos();
                                    opcionOrden = consola.leerOpcion();
                                    switch (opcionOrden) {
                                        case 1:
                                            try {
                                                gestorRecursos.ordenarYMostrarPorTitulo();
                                            } catch (RecursoNoDisponibleException e) {
                                                System.out.println(e.getMessage());
                                            }
                                            break;
                                        case 2:
                                            try {
                                                gestorRecursos.ordenarYMostrarPorFecha();
                                            } catch (RecursoNoDisponibleException e) {
                                                System.out.println(e.getMessage());
                                            }
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


                            case 6: // ELIMINAR
                                consola.eliminarRecursoMenu(gestorRecursos);
                                break;

                            case 7:
                                System.out.println("↩️ Volviendo al Menú Principal...");
                                break;

                            default:
                                System.out.println("⚠️ Opción inválida.");
                        }
                    } while (opcionRecursos != 7);
                    break;

                case 3: // PREUBAS
                    System.out.println("\n==== PRUEBAS DE NOTIFICACIONES ====");
                    // Prueba 1: Creación del Prestamo
                    System.out.println("\n=== PRUEBA 1: Creación de préstamo ===");
                    System.out.println(prestamo1);

                    // Prueba 2: Realizar préstamo
                    System.out.println("\n=== PRUEBA 2: Realizar préstamo ===");
                    try {
                        prestamo1.realizarPrestamo();
                    } catch (RecursoNoDisponibleException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("\n" + libro1);

                    // Prueba 3: Validar que no se puede realizar un préstamo si el recurso ya está prestado
                    System.out.println("\n=== PRUEBA 3: Intentar prestar un recurso ya prestado ===");
                    Prestamo prestamo2 = new Prestamo(usuario1, libro1, fechaPrestamo, fechaDevolucion, activo); // Intentar nuevo préstamo con el mismo libro
                    try {
                        prestamo2.realizarPrestamo();
                    } catch (RecursoNoDisponibleException e) {
                        System.out.println(e.getMessage());  // Debe lanzar error porque el libro ya está prestado
                    }

                    // PRUEBA 4: Devolver el recurso
                    System.out.println("\n=== PRUEBA 4: Devolver el recurso ===");
                    prestamo1.devolverRecurso();
                    System.out.println("\n" + libro1);  // Verificar que el estado del libro cambió a DISPONIBLE

                    // PRUEBA 5: Verificar la fecha de devolución
                    System.out.println("\n=== PRUEBA 5: Verificar fecha de devolución ===");
                    System.out.println("\n--> Fecha de devolución prevista: " + prestamo1.getFechaDevolucion());

                    // PRUEBA 6: Intentar devolver el recurso nuevamente (no debería permitir)
                    System.out.println("\n=== PRUEBA 6: Intentar devolver un recurso ya devuelto ===");
                    prestamo1.devolverRecurso();  // Debería decir que el recurso ya fue devuelto

                    // Servicios de notificación
                    ServicioNotificaciones servicioEmail = new ServicioNotificacionesEmail();
                    ServicioNotificaciones servicioSMS = new ServicioNotificacionesSMS();

                    System.out.println("\n==== PRUEBAS DE NOTIFICACIONES ====");
                    try {
                        System.out.println("\n- Prueba del servicio email");
                        servicioEmail.enviarNotificacion("--> ¡Tienes un nuevo mensaje!", gestorUsuarios.obtenerUsuarioPorId("U001"));

                        System.out.println("\n- Prueba del servicio SMS");
                        servicioSMS.enviarNotificacion("--> ¡Tienes un nuevo mensaje!", gestorUsuarios.obtenerUsuarioPorId("U002"));
                    } catch (UsuarioNoEncontradoException e) {
                        System.out.println("❌ Error al enviar notificación: " + e.getMessage());
                    }

                case 4:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionPrincipal != 4);
    }
}