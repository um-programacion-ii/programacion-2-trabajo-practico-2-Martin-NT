package Main;
import Excepciones.RecursoNoDisponibleException;
import Excepciones.UsuarioNoEncontradoException;
import Gestores.*;
import Prestamos.Prestamo;
import Interfaces.*;
import Reservas.Reserva;
import Servicios.*;
import Enums.*;
import Simulaciones.SimuladorConcurrencia;
import Simulaciones.SimuladorNotificaciones;
import Simulaciones.SimuladorRecursos;
import Simulaciones.SimuladorUsuarios;
import Usuarios.Usuario;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de los servicios de notificación
        ServicioNotificaciones servicioEmail = new ServicioNotificacionesEmail();
        ServicioNotificaciones servicioSMS = new ServicioNotificacionesSMS();

        // Crear gestores
        GestorNotificaciones gestorNotificaciones = new GestorNotificaciones(servicioEmail, servicioSMS);
        GestorReservas gestorReservas = new GestorReservas(gestorNotificaciones);
        GestorPrestamos gestorPrestamos = new GestorPrestamos(gestorNotificaciones, gestorReservas);
        GestorRecursos gestorRecursos = new GestorRecursos();
        GestorUsuarios gestorUsuarios = new GestorUsuarios();

        // Cargar usuarios y recursos con los simuladores
        SimuladorUsuarios.cargarUsuarios(gestorUsuarios);
        SimuladorRecursos.cargarRecursos(gestorRecursos);


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
                                consola.crearUsuarioMenu(gestorUsuarios);
                                break;

                            case 5: // ELIMINAR
                                consola.eliminarUsuarioMenu(gestorUsuarios);
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
                                    consola.crearRecursoMenu(tipoRecurso, gestorRecursos);
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

                            case 4: // Buscar préstamos
                                int opcionBusquedaPrestamos;
                                do {
                                    consola.mostrarMenuBuscarPrestamos();
                                    opcionBusquedaPrestamos = consola.leerOpcion();

                                    switch (opcionBusquedaPrestamos) {
                                        case 1: // Buscar por ID de usuario
                                            System.out.print("--> Ingrese el ID del usuario: ");
                                            idUsuario = consola.leerTexto();
                                            gestorPrestamos.buscarPorIdUsuario(idUsuario);
                                            break;

                                        case 2: // Buscar por ID de recurso
                                            System.out.print("--> Ingrese el ID del recurso: ");
                                            idRecurso = consola.leerTexto();
                                            gestorPrestamos.buscarPorIdRecurso(idRecurso);
                                            break;

                                        case 3: // Buscar por fecha de préstamo
                                            System.out.print("--> Ingrese la fecha de préstamo (formato: YYYY-MM-DD): ");
                                            String fechaStr = consola.leerTexto();
                                            LocalDate fechaPrestamo = LocalDate.parse(fechaStr);
                                            gestorPrestamos.buscarPorFecha(fechaPrestamo);
                                            break;

                                        case 4: // Volver al menú anterior
                                            System.out.println("↩️ Volviendo al Menú de Préstamos...");
                                            break;

                                        default:
                                            System.out.println("⚠️ Opción inválida.");
                                    }
                                } while (opcionBusquedaPrestamos != 4);
                                break;

                            case 5: // Ordenar préstamos
                                int opcionOrdenPrestamo;
                                do {
                                    consola.mostrarMenuOrdenarPrestamos();  // Mostrar menú de ordenación
                                    opcionOrdenPrestamo = consola.leerOpcion();  // Leer la opción de ordenación

                                    switch (opcionOrdenPrestamo) {
                                        case 1: // Ordenar por ID de usuario
                                            List<Prestamo> ordenadosPorIdUsuario = gestorPrestamos.ordenarPrestamosPorIdUsuario();
                                            gestorPrestamos.mostrarPrestamosFiltrados(ordenadosPorIdUsuario);
                                            break;

                                        case 2: // Ordenar por fecha de préstamo
                                            List<Prestamo> ordenadosPorFecha = gestorPrestamos.ordenarPrestamosPorFecha();
                                            gestorPrestamos.mostrarPrestamosFiltrados(ordenadosPorFecha);
                                            break;

                                        case 3: // Ordenar por ID de recurso
                                            List<Prestamo> ordenadosPorIdRecurso = gestorPrestamos.ordenarPrestamosPorIdRecurso();
                                            gestorPrestamos.mostrarPrestamosFiltrados(ordenadosPorIdRecurso);
                                            break;

                                        case 4: // Volver al menú anterior
                                            System.out.println("↩️ Volviendo al Menú de Préstamos...");
                                            break;

                                        default:
                                            System.out.println("⚠️ Opción inválida.");
                                    }
                                } while (opcionOrdenPrestamo != 4);
                                break;

                            case 6:
                                System.out.println("↩️ Volviendo al Menú Principal...");
                                break;

                            default:
                                System.out.println("⚠️ Opción inválida.");

                        }
                    } while (opcionPrestamo != 6);
                    break;

                case 4:
                    int opcionReserva;
                    // Declarar  fuera del bucle o switch
                    String idRecurso = "";
                    String idUsuario = "";
                    do {
                        consola.mostrarMenuReservas();
                        opcionReserva = consola.leerOpcion();

                        switch (opcionReserva) {
                            case 1: // Reservar recurso
                                // Solicitar el ID del usuario
                                System.out.print("--> Ingrese el ID del usuario que desea realizar la reserva: ");
                                idUsuario = consola.leerTexto();

                                // Solicitar el ID del recurso
                                System.out.print("--> Ingrese el ID del recurso que desea reservar: ");
                                idRecurso = consola.leerTexto();

                                try {
                                    // Buscar el usuario por ID
                                    Usuario usuario = gestorUsuarios.obtenerUsuarioPorId(idUsuario);

                                    // Buscar el recurso por ID
                                    RecursoDigital recurso = gestorRecursos.obtenerRecursoPorId(idRecurso);

                                    // Verificar si el recurso está disponible
                                    if (gestorPrestamos.validarRecursoDisponible(recurso)) {
                                        // Pedir prioridad
                                        System.out.println("--> Ingrese la prioridad de la reserva (menor número = mayor prioridad): ");
                                        int prioridad = consola.leerOpcion();
                                        // Agregar la reserva
                                        gestorReservas.agregarReserva(usuario, recurso, prioridad);
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

                            case 4: // Buscar reservas
                                int opcionBusqueda;
                                do {
                                    consola.mostrarMenuBuscarReservas();
                                    opcionBusqueda = consola.leerOpcion();

                                    switch (opcionBusqueda) {
                                        case 1: // Buscar por ID de usuario
                                            System.out.print("--> Ingrese el ID del usuario: ");
                                            idUsuario = consola.leerTexto();
                                            gestorReservas.buscarPorIDUsuario(idUsuario);
                                            break;

                                        case 2: // Buscar por ID de recurso
                                            System.out.print("--> Ingrese el ID del recurso: ");
                                            idRecurso = consola.leerTexto();
                                            gestorReservas.buscarPorIDRecurso(idRecurso);
                                            break;

                                        case 3: // Buscar por prioridad
                                            System.out.print("--> Ingrese la prioridad: ");
                                            int prioridad = consola.leerOpcion();
                                            gestorReservas.buscarPorPrioridad(prioridad);
                                            break;

                                        case 4: // Buscar por fecha
                                            System.out.print("--> Ingrese la fecha de la reserva (formato: YYYY-MM-DD): ");
                                            String fechaStr = consola.leerTexto();
                                            LocalDate fecha = LocalDate.parse(fechaStr);
                                            gestorReservas.buscarPorFecha(fecha);
                                            break;

                                        case 5: // Volver al menú anterior
                                            System.out.println("↩️ Volviendo al Menú de Reservas...");
                                            break;

                                        default:
                                            System.out.println("⚠️ Opción inválida.");
                                    }
                                } while (opcionBusqueda != 5);
                                break;

                            case 5: // Ordenar reservas
                                int opcionOrden;
                                do {
                                    consola.mostrarMenuOrdenarReservas();
                                    opcionOrden = consola.leerOpcion();

                                    switch (opcionOrden) {
                                        case 1: // Ordenar por prioridad
                                            List<Reserva> reservasOrdenadasPorPrioridad = gestorReservas.ordenarReservasPorPrioridad();
                                            gestorReservas.mostrarReservasFiltradas(reservasOrdenadasPorPrioridad);
                                            break;

                                        case 2: // Ordenar por fecha
                                            List<Reserva> reservasOrdenadasPorFecha = gestorReservas.ordenarReservasPorFecha();
                                            gestorReservas.mostrarReservasFiltradas(reservasOrdenadasPorFecha);
                                            break;

                                        case 3: // Ordenar por ID de usuario
                                            List<Reserva> reservasOrdenadasPorIdUsuario = gestorReservas.ordenarReservasPorIdUsuario();
                                            gestorReservas.mostrarReservasFiltradas(reservasOrdenadasPorIdUsuario);
                                            break;

                                        case 4: // Volver al menú anterior
                                            System.out.println("↩️ Volviendo al Menú de Reservas...");
                                            break;

                                        default:
                                            System.out.println("⚠️ Opción inválida.");
                                    }
                                } while (opcionOrden != 4);
                                break;

                            case 6:
                                System.out.println("↩️ Volviendo al Menú Principal...");
                                break;

                            default:
                                System.out.println("⚠️ Opción inválida.");
                        }
                    } while (opcionReserva != 6);
                    break;

                case 5: // PRUEBAS
                    // Probar notificaciones
                    SimuladorNotificaciones.probarServicios(servicioEmail, servicioSMS, gestorUsuarios);

                    // Llamar al simulador de concurrencia para probar la ejecución concurrente
                    SimuladorConcurrencia.simularConcurrencia(gestorRecursos, gestorUsuarios, gestorPrestamos, gestorNotificaciones);
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