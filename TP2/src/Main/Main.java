package Main;

import Excepciones.RecursoNoDisponibleException;
import Excepciones.UsuarioNoEncontradoException;
import Gestores.GestorPrestamos;
import Gestores.GestorRecursos;
import Gestores.GestorReservas;
import Gestores.GestorUsuarios;
import Prestamos.Prestamo;
import Recursos.*;
import Interfaces.*;
import Servicios.*;
import Enums.*;
import Usuarios.Usuario;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Crear gestor de usuarios
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        // Crear gestor de recursos
        GestorRecursos gestorRecursos = new GestorRecursos();
        // Crear gestor de prestamos
        GestorPrestamos gestorPrestamos = new GestorPrestamos();
        // Crear gestor de reservas
        GestorReservas gestorReservas = new GestorReservas();

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

        // Crear instancias de los servicios de notificación
        ServicioNotificaciones servicioEmail = new ServicioNotificacionesEmail();
        ServicioNotificaciones servicioSMS = new ServicioNotificacionesSMS();

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

                case 3:  // Menú de Préstamos
                    int opcionPrestamo;
                    do {
                        consola.mostrarMenuPrestamos();
                        opcionPrestamo = consola.leerOpcion();

                        switch (opcionPrestamo) {
                            case 1: // Realizar un préstamo
                                // Solicitar los datos para el préstamo
                                System.out.print("--> Ingrese el ID del usuario que realizará el préstamo: ");
                                String idUsuario = consola.leerTexto();
                                Usuario usuarioPrestamo = null;
                                try {
                                    usuarioPrestamo = gestorUsuarios.obtenerUsuarioPorId(idUsuario);  // Esto puede lanzar la excepción UsuarioNoEncontradoException
                                } catch (UsuarioNoEncontradoException e) {
                                    System.out.println("❌ " + e.getMessage());
                                    break;  // Salir del case 1 si el usuario no se encuentra
                                }

                                if (usuarioPrestamo == null) {
                                    System.out.println("❌ Usuario no encontrado.");
                                    break;  // Salir del case 1 si el usuario no se encuentra
                                }

                                System.out.print("--> Ingrese el ID del recurso que desea prestar: ");
                                String idRecurso = consola.leerTexto();
                                RecursoDigital recursoPrestamo = null;
                                try {
                                    recursoPrestamo = gestorRecursos.obtenerRecursoPorId(idRecurso);  // Esto también puede lanzar una excepción
                                } catch (RecursoNoDisponibleException e) {
                                    System.out.println("❌ " + e.getMessage());
                                    break;  // Salir del case 1 si el recurso no se encuentra
                                }

                                if (recursoPrestamo == null) {
                                    System.out.println("❌ Recurso no encontrado.");
                                    break;  // Salir del case 1 si el recurso no se encuentra
                                }

                                // Llamar al metodo realizarPrestamo del GestorPrestamos
                                try {
                                    gestorPrestamos.realizarPrestamo(usuarioPrestamo, recursoPrestamo);
                                } catch (RecursoNoDisponibleException e) {
                                    System.out.println("❌ " + e.getMessage());
                                }
                                break;

                            case 2: // Ver préstamos activos
                                System.out.println("\n📋 Mostrando préstamos activos...");
                                gestorPrestamos.mostrarPrestamosActivos();
                                break;

                            case 3: // Devolver recurso
                                System.out.print("Ingrese el ID del usuario que devuelve el recurso: ");
                                String idUsuarioDev = consola.leerTexto();

                                // Intentamos obtener el usuario desde el gestor
                                Usuario usuarioDev = null;
                                try {
                                    usuarioDev = gestorUsuarios.obtenerUsuarioPorId(idUsuarioDev);
                                } catch (UsuarioNoEncontradoException e) {
                                    // Si no se encuentra el usuario, mostramos el error y salimos del case
                                    System.out.println("❌ " + e.getMessage());
                                    break;
                                }

                                // Solicitar el recurso a devolver
                                System.out.print("Ingrese el ID del recurso que desea devolver: ");
                                String idRecursoDev = consola.leerTexto();

                                // Intentamos obtener el recurso desde el gestor
                                RecursoDigital recursoDev = null;
                                try {
                                    recursoDev = gestorRecursos.obtenerRecursoPorId(idRecursoDev);
                                } catch (RecursoNoDisponibleException e) {
                                    System.out.println("❌ " + e.getMessage());
                                    break;
                                }

                                // Llamar al metodo de devolver recurso
                                try {
                                    gestorPrestamos.devolverRecurso(usuarioDev, recursoDev);
                                } catch (IllegalStateException e) {
                                    System.out.println("❌ Error al devolver el recurso: " + e.getMessage());
                                }
                                break;

                            case 4:
                                System.out.println("↩️ Volviendo al Menú Principal...");
                                break;

                            default:
                                System.out.println("⚠️ Opción inválida.");

                        }
                    } while (opcionPrestamo != 4);
                    break;

                case 4:
                    int opcionReserva;
                    String idRecurso = ""; // Declarar  fuera del bucle o switch
                    do {
                        consola.mostrarMenuReservas();
                        opcionReserva = consola.leerOpcion();

                        switch (opcionReserva) {
                            case 1: // Reservar recurso
                                // Solicitar el ID del usuario
                                System.out.print("Ingrese el ID del usuario que desea realizar la reserva: ");
                                String idUsuario = consola.leerTexto();

                                // Solicitar el ID del recurso
                                System.out.print("Ingrese el ID del recurso que desea reservar: ");
                                idRecurso = consola.leerTexto();

                                try {
                                    // Buscar el usuario por ID
                                    Usuario usuario = gestorUsuarios.obtenerUsuarioPorId(idUsuario); // Obtiene el usuario

                                    // Buscar el recurso por ID
                                    RecursoDigital recurso = gestorRecursos.obtenerRecursoPorId(idRecurso);

                                    // Verificar si el recurso está disponible
                                    if (gestorPrestamos.validarRecursoDisponible(recurso)) {
                                        // Agregar la reserva
                                        gestorReservas.agregarReserva(usuario, recurso);
                                    } else {
                                        System.out.println("⚠️ El recurso no está disponible en este momento. ¡Por favor, espere!");
                                    }

                                } catch (UsuarioNoEncontradoException e) {
                                    // Si el usuario no se encuentra
                                    System.out.println(e.getMessage());
                                } catch (RecursoNoDisponibleException e) {
                                    // Si el recurso no está disponible
                                    System.out.println(e.getMessage());
                                } catch (Exception e) {
                                    // En caso de cualquier otra excepción no controlada
                                    System.out.println("Error inesperado: " + e.getMessage());
                                }
                                break;

                            case 2: // Ver reservas pendientes
                                // Muestra las reservas pendientes en la cola de reservas
                                gestorReservas.mostrarReservas();
                                break;

                            case 3: // Eliminar reserva
                                System.out.print("--> Ingrese el ID del recurso que desea eliminar: ");
                                idRecurso = consola.leerTexto();
                                gestorReservas.eliminarReserva(idRecurso);
                                break;

                            case 4:
                                System.out.println("↩️ Volviendo al Menú Principal...");
                                break;

                            default:
                                System.out.println("⚠️ Opción inválida.");
                        }
                    } while (opcionReserva != 4);
                    break;

                case 5: // PRUEBAS
                    System.out.println("\n==== PRUEBAS DE NOTIFICACIONES ====");
                    try {
                        System.out.println("\n- Prueba del servicio email");
                        servicioEmail.enviarNotificacion("--> ¡Tienes un nuevo mensaje!", gestorUsuarios.obtenerUsuarioPorId("U001"));

                        System.out.println("\n- Prueba del servicio SMS");
                        servicioSMS.enviarNotificacion("--> ¡Tienes un nuevo mensaje!", gestorUsuarios.obtenerUsuarioPorId("U002"));
                    } catch (UsuarioNoEncontradoException e) {
                        System.out.println("❌ Error al enviar notificación: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionPrincipal != 6);
    }
}