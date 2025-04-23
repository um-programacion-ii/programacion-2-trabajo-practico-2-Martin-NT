package Main;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import Alertas.AlertaVencimiento;
import Excepciones.RecursoNoDisponibleException;
import Excepciones.UsuarioNoEncontradoException;
import Gestores.GestorRecursos;
import Gestores.GestorReportes;
import Gestores.GestorUsuarios;
import Gestores.Gestores;
import Prestamos.Prestamo;
import Recursos.*;
import Interfaces.*;
import Enums.*;
import Reservas.Reserva;
import Usuarios.Usuario;
import Main.Menus;

public class Consola {
    private final Scanner scanner;
    private final Menus menus;
    private final Gestores gestores;
    private AlertaVencimiento alertaVencimiento;

    public Consola() {
        scanner = new Scanner(System.in);
        menus = new Menus();  // Inicializa la instancia de Menus
        gestores = new Gestores();  // Inicializa la clase Gestores
    }

    //Setter
    public void setAlertaVencimiento(AlertaVencimiento alertaVencimiento) {
        this.alertaVencimiento = alertaVencimiento;
    }

    public void mostrarMenuUsuarios() {
        int opcionUsuarios;
        do {
            menus.MenuUsuarios();
            opcionUsuarios = leerOpcion();
            switch (opcionUsuarios) {
                case 1: // Listar usuarios
                    mostrarListarUsuarios();
                    break;
                case 2: // Buscar Usuarios
                    mostrarMenuBuscarUsuarios();
                    break;
                case 3: // Ordenar usuarios
                    mostrarMenuOrdenarUsuarios();
                    break;
                case 4: // Crear Usuarios
                    mostrarMenuCrearUsuarios(gestores.getGestorUsuarios());
                    break;
                case 5: // Eliminar Usuarios
                    mostrarMenuEliminarUsuarios(gestores.getGestorUsuarios());
                    break;
                case 6:
                    System.out.println("↩️ Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionUsuarios != 6);
    }

    public void mostrarListarUsuarios() {
        try {
            gestores.getGestorUsuarios().mostrarUsuarios();
        } catch (UsuarioNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMenuBuscarUsuarios() {
        int opcionBusquedaUsuarios;
        do {
            menus.MenuBusquedaUsuarios();
            opcionBusquedaUsuarios = leerOpcion();
            switch (opcionBusquedaUsuarios) {
                case 1: // Buscar por ID
                    mostrarMenuBuscarUsuarioId();
                    break;
                case 2: //Buscar por Nombre
                    mostrarMenuBuscarUsuarioNombre();
                    break;
                case 3: // Buscar por Apellido
                    mostrarMenuBuscarUsuarioApellido();
                    break;
                case 4: // Volver al Menú de Usuarios
                    System.out.println("↩️ Volviendo al Menú de Usuarios...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionBusquedaUsuarios != 4);
    }

    public void mostrarMenuBuscarUsuarioId() {
        System.out.print("🔎 Ingrese el ID del usuario a buscar: ");
        String idBuscado = leerTexto();
        try {
            gestores.getGestorUsuarios().buscarPorId(idBuscado);
        } catch (UsuarioNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMenuBuscarUsuarioNombre() {
        System.out.print("🔎 Ingrese el nombre del usuario a buscar: ");
        String nombreBuscado = leerTexto();
        try {
            gestores.getGestorUsuarios().buscarPorNombre(nombreBuscado);
        } catch (UsuarioNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMenuBuscarUsuarioApellido() {
        System.out.print("🔎 Ingrese el apellido del usuario a buscar: ");
        String apellidoBuscado = leerTexto();
        try {
            gestores.getGestorUsuarios().buscarPorApellido(apellidoBuscado);
        } catch (UsuarioNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMenuOrdenarUsuarios() {
        int opcionOrdenUsuario;
        do {
            menus.MenuOrdenarUsuarios();
            opcionOrdenUsuario = leerOpcion();
            switch (opcionOrdenUsuario) {
                case 1: // Ordenar por Nombre (A-Z)
                    mostrarOrdenarUsuarioNombre();
                    break;
                case 2: // Ordenar por Apellido (A-Z)
                    mostrarOrdenarUsuarioApellido();
                    break;
                case 3: //Volver al Menú de Usuarios
                    System.out.println("↩️ Volviendo al Menú de Usuarios...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionOrdenUsuario != 3);
    }

    public void mostrarOrdenarUsuarioNombre() {
        try {
            gestores.getGestorUsuarios().ordenarPorNombre();
        } catch (UsuarioNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
    public void mostrarOrdenarUsuarioApellido() {
        try {
            gestores.getGestorUsuarios().ordenarPorApellido();
        } catch (UsuarioNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMenuCrearUsuarios(GestorUsuarios gestorUsuarios) {
        System.out.println("\n===== ➕ CREAR NUEVO USUARIO =====");

        System.out.print("--> 🆔 Ingrese el ID del usuario: ");
        String id = scanner.nextLine();

        // Validar que no exista un usuario con el mismo ID
        if (gestorUsuarios.existeUsuario(id)) {
            System.out.println("❌ Ya existe un usuario con ese ID.");
            return;
        }
        System.out.print("--> 👤 Ingrese el nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("--> 👤 Ingrese el apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("--> 📧 Ingrese el email: ");
        String email = scanner.nextLine();
        System.out.print("--> 🔒 Ingrese la contraseña: ");
        String password = scanner.nextLine();
        System.out.print("--> 📞 Ingrese el número de teléfono: ");
        String telefono = scanner.nextLine();
        Usuario nuevoUsuario = new Usuario(id, nombre, apellido, email, password, telefono);
        gestorUsuarios.agregarUsuario(nuevoUsuario);
        System.out.println("✅ Usuario creado exitosamente.");
    }

    public void mostrarMenuEliminarUsuarios(GestorUsuarios gestorUsuarios) {
        System.out.println("\n===== 🗑️ ELIMINAR USUARIO =====");
        System.out.print("🆔 Ingrese el ID del usuario que desea eliminar: ");
        String id = scanner.nextLine();
        try {
            gestorUsuarios.eliminarUsuario(id);
            System.out.println("✅ Usuario eliminado correctamente.");
        } catch (UsuarioNoEncontradoException e) {
            System.out.println(e.getMessage()); // Mensaje amigable
        }
    }

    public void mostrarMenuRecursos() {
        int opcionRecursos;
        do {
            menus.MenuRecursos();
            opcionRecursos = leerOpcion();
            switch (opcionRecursos) {
                case 1: // Listar Recursos
                    mostrarListarRecursos();
                    break;
                case 2: // Mostrar categorías
                    mostrarMenuCategoriasRecurso();
                    break;
                case 3: // Buscar Recurso
                    mostrarMenuBuscarRecursos();
                    break;
                case 4: // Ordenar Recursos
                    mostrarMenuOrdenarRecursos();
                    break;
                case 5: // Crear Recursos
                    mostrarMenuCrearRecursos();
                    break;
                case 6: // Eliminar Recursos
                    mostrarMenuEliminarRecursos(gestores.getGestorRecursos());
                    break;
                case 7: // Volver Menu Principal
                    System.out.println("↩️ Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionRecursos != 7);
    }

    public void mostrarListarRecursos() {
        try {
            gestores.getGestorRecursos().mostrarRecursos();
        } catch (RecursoNoDisponibleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMenuCategoriasRecurso() {
        gestores.getGestorRecursos().mostrarCategoriasDisponibles();
        int opcionFiltro;
        do {
            menus.MenuFiltradoPorCategoria();
            opcionFiltro = leerOpcion();
            switch (opcionFiltro) {
                case 1: // Listar Libros
                    mostrarListarLibros();
                    break;
                case 2: // Listar Audiolibros
                    mostrarListarAudiolibros();
                    break;
                case 3: // Listar Revistas
                    mostrarListarRevistas();
                    break;
                case 4: // Volver al Menú de Recursos
                    System.out.println("↩️ Volviendo al Menú de Recursos...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionFiltro != 4);
    }

    public void mostrarListarLibros() {
        try {
            gestores.getGestorRecursos().filtrarLibros();
        } catch (RecursoNoDisponibleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarListarAudiolibros() {
        try {
            gestores.getGestorRecursos().filtrarAudiolibros();
        } catch (RecursoNoDisponibleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarListarRevistas() {
        try {
            gestores.getGestorRecursos().filtrarRevistas();
        } catch (RecursoNoDisponibleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMenuBuscarRecursos() {
        int opcionBusqueda;
        do {
            menus.MenuBusquedaRecursos();
            opcionBusqueda = leerOpcion();
            switch (opcionBusqueda) {
                case 1: // Buscar por ID
                    mostrarMenuBuscarRecursoId();
                    break;
                case 2: // Buscar por Título
                    mostrarMenuBuscarRecursoTitulo();
                    break;
                case 3: // Buscar por Categoría
                    mostrarMenuBuscarRecursoCategoria();
                    break;
                case 4: // Volver al Menú de Recursos
                    System.out.println("↩️ Volviendo al Menú de Recursos...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionBusqueda != 4);
    }

    public void mostrarMenuBuscarRecursoId() {
        System.out.print("🔎 Ingrese el ID del recurso a buscar: ");
        String idBuscado = leerTexto();
        try {
            gestores.getGestorRecursos().buscarPorId(idBuscado);
        } catch (RecursoNoDisponibleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMenuBuscarRecursoTitulo() {
        System.out.print("--> 🔎 Ingrese el título a buscar: ");
        String titulo = leerTexto();
        try {
            gestores.getGestorRecursos().buscarPorTitulo(titulo);
        } catch (RecursoNoDisponibleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMenuBuscarRecursoCategoria() {
        System.out.print("--> 🔎 Ingrese categoría (LIBRO, REVISTA, AUDIOLIBRO): ");
        String cat = leerTexto().toUpperCase();
        try {
            CategoriaRecurso categoria = CategoriaRecurso.valueOf(cat);
            gestores.getGestorRecursos().buscarPorCategoria(categoria);
        } catch (IllegalArgumentException e) {
            System.out.println("⚠️ Categoría no válida.");
        } catch (RecursoNoDisponibleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMenuOrdenarRecursos() {
        int opcionOrden;
        do {
            menus.MenuOrdenarRecursos();
            opcionOrden = leerOpcion();
            switch (opcionOrden) {
                case 1: // Ordenar por Título (A-Z)
                    mostrarMenuOrdenarRecursosTitulo();
                    break;
                case 2: // Ordenar por Fecha de Publicación (Más recientes primero)
                    mostrarMenuOrdenarRecursosFecha();
                    break;
                case 3: // Volver al Menú de Recursos
                    System.out.println("↩️ Volviendo al Menú de Recursos...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionOrden != 3);
    }

    public void mostrarMenuOrdenarRecursosTitulo() {
        try {
            gestores.getGestorRecursos().ordenarYMostrarPorTitulo();
        } catch (RecursoNoDisponibleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMenuOrdenarRecursosFecha() {
        try {
            gestores.getGestorRecursos().ordenarYMostrarPorFecha();
        } catch (RecursoNoDisponibleException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarMenuCrearRecursos() {
        menus.MenuCrearRecurso();
        int tipoRecurso = leerOpcion();
        if (tipoRecurso >= 1 && tipoRecurso <= 3) {
            crearRecursoMenu(tipoRecurso, gestores.getGestorRecursos());
        } else if (tipoRecurso == 4) {
            System.out.println("↩️ Volviendo al menú de recursos...");
        } else {
            System.out.println("❌ Opción no válida.");
        }
    }

    public void crearRecursoMenu(int tipoSeleccionado, GestorRecursos gestorRecursos) {
        System.out.print("🆔 Ingrese el ID: ");
        String id = scanner.nextLine();

        if (gestorRecursos.existeRecurso(id)) {
            System.out.println("❌ Ya existe un recurso con ese ID. Intente con uno diferente.");
            return;
        }

        System.out.print("--> 📖 Ingrese el título: ");
        String titulo = scanner.nextLine();

        System.out.print("--> ✍️ Ingrese el autor: ");
        String autor = scanner.nextLine();

        System.out.print("--> 📅 Ingrese la fecha de publicación (YYYY-MM-DD): ");
        LocalDate fechaPublicacion = LocalDate.parse(scanner.nextLine());

        EstadoRecurso estado = EstadoRecurso.DISPONIBLE;
        LocalDateTime fechaDevolucion = LocalDateTime.now().plusDays(10);
        RecursoDigital recurso = null;

        switch (tipoSeleccionado) {
            case 1: // Libro
                System.out.print("--> 📄 Ingrese el número de páginas: ");
                int paginas = scanner.nextInt();
                scanner.nextLine();

                System.out.print("--> 🎭 Ingrese el género: ");
                String genero = scanner.nextLine();

                System.out.print("--> 🏢 Ingrese la editorial: ");
                String editorial = scanner.nextLine();

                recurso = new Libro(id, titulo, autor, fechaPublicacion, estado,
                        CategoriaRecurso.LIBRO, paginas, genero, editorial);
                break;

            case 2: // Audiolibro
                System.out.print("--> ⏱️ Ingrese la duración en minutos: ");
                int duracion = scanner.nextInt();
                scanner.nextLine();

                System.out.print("--> 🎙️ Ingrese el narrador: ");
                String narrador = scanner.nextLine();

                System.out.print("--> 🌍 Ingrese el idioma: ");
                String idioma = scanner.nextLine();

                recurso = new Audiolibro(id, titulo, autor, fechaPublicacion, estado,
                        CategoriaRecurso.AUDIOLIBRO, duracion, narrador, idioma);
                break;

            case 3: // Revista
                System.out.print("--> 📅 Ingrese el número de edición: ");
                int edicion = scanner.nextInt();
                scanner.nextLine();

                System.out.print("--> 📆 Ingrese la periodicidad: ");
                String periodicidad = scanner.nextLine();

                System.out.print("--> 🗞️ Ingrese la sección principal: ");
                String seccion = scanner.nextLine();

                System.out.print("--> 🏢 Ingrese la editorial: ");
                String editorialRevista = scanner.nextLine();

                recurso = new Revista(id, titulo, autor, fechaPublicacion, estado,
                        CategoriaRecurso.REVISTA, edicion, periodicidad, seccion, editorialRevista);
                break;

            case 4:
                System.out.println("↩️ Volviendo al Menú de Recursos...");
                break;

            default:
                System.out.println("❌ Tipo de recurso no válido.");
        }

        // Si se ha creado un recurso válido, agregarlo al gestor
        if (recurso != null) {
            gestorRecursos.agregarRecurso(recurso);
            System.out.println("✅ Recurso agregado exitosamente.");
        }
    }

    public void mostrarMenuEliminarRecursos(GestorRecursos gestorRecursos) {
        System.out.println("\n===== 🗑️ ELIMINAR RECURSO =====");
        System.out.print("--> 🆔 Ingrese el ID del recurso a eliminar: ");
        String id = scanner.nextLine();

        try {
            gestorRecursos.eliminarRecurso(id);
            System.out.println("✅ El recurso con ID " + id + " ha sido eliminado correctamente.");
        } catch (RecursoNoDisponibleException e) {
            System.out.println(e.getMessage()); // Mensaje personalizado desde la excepción
        }
    }

    public void mostrarMenuPrestamos() {
        int opcionPrestamo;
        do {
            menus.MenuPrestamos();
            opcionPrestamo = leerOpcion();

            switch (opcionPrestamo) {
                case 1: // Realizar un préstamo
                    mostrarMenuCrearPrestamos();
                    break;
                case 2: // Ver préstamos activos
                    mostrarMenuVerPrestamosActivos();
                    break;
                case 3: // Devolver recurso
                    mostrarMenuDevolverRecurso();
                    break;
                case 4: // Buscar préstamos
                    mostrarMenuBuscarPrestamos();
                    break;
                case 5: // Ordenar préstamos
                    mostrarMenuOrdenarPrestamos();
                    break;
                case 6: // Alertas de vencimiento
                    System.out.println("🕒 Verificando alertas de vencimiento...");
                    alertaVencimiento.monitorearVencimientos();
                    break;
                case 7:
                    System.out.println("↩️ Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionPrestamo != 7);
    }

    public void mostrarMenuCrearPrestamos() {
        // Solicitar los datos para el préstamo
        System.out.print("--> Ingrese el ID del usuario que realizará el préstamo: ");
        String idUsuario = leerTexto();
        Usuario usuarioPrestamo = null;
        try {
            usuarioPrestamo = gestores.getGestorUsuarios().obtenerUsuarioPorId(idUsuario);
        } catch (UsuarioNoEncontradoException e) {
            System.out.println("❌ " + e.getMessage());
            //break;  // Salir del case 1 si el usuario no se encuentra
        }

        if (usuarioPrestamo == null) {
            System.out.println("❌ Usuario no encontrado.");
            //break;  // Salir del case 1 si el usuario no se encuentra
        }

        System.out.print("--> Ingrese el ID del recurso que desea prestar: ");
        String idRecurso = leerTexto();
        RecursoDigital recursoPrestamo = null;
        try {
            recursoPrestamo = gestores.getGestorRecursos().obtenerRecursoPorId(idRecurso);
        } catch (RecursoNoDisponibleException e) {
            System.out.println("❌ " + e.getMessage());
            //break;  // Salir del case 1 si el recurso no se encuentra
        }

        if (recursoPrestamo == null) {
            System.out.println("❌ Recurso no encontrado.");
            //break;  // Salir del case 1 si el recurso no se encuentra
        }

        // Llamar al metodo realizarPrestamo del GestorPrestamos
        try {
            gestores.getGestorPrestamos().realizarPrestamo(usuarioPrestamo, recursoPrestamo);
        } catch (RecursoNoDisponibleException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    public void mostrarMenuVerPrestamosActivos() {
        System.out.println("\n📋 Mostrando préstamos activos...");
        gestores.getGestorPrestamos().mostrarPrestamosActivos();
    }

    public void mostrarMenuDevolverRecurso() {
        System.out.print("--> Ingrese el ID del usuario que devuelve el recurso: ");
        String idUsuarioDev = leerTexto();

        // Intentamos obtener el usuario desde el gestor
        Usuario usuarioDev = null;
        try {
            usuarioDev = gestores.getGestorUsuarios().obtenerUsuarioPorId(idUsuarioDev);
        } catch (UsuarioNoEncontradoException e) {
            // Si no se encuentra el usuario, mostramos el error y salimos del case
            System.out.println("❌ " + e.getMessage());
        }

        // Solicitar el recurso a devolver
        System.out.print("Ingrese el ID del recurso que desea devolver: ");
        String idRecursoDev = leerTexto();

        // Intentamos obtener el recurso desde el gestor
        RecursoDigital recursoDev = null;
        try {
            recursoDev = gestores.getGestorRecursos().obtenerRecursoPorId(idRecursoDev);
        } catch (RecursoNoDisponibleException e) {
            System.out.println("❌ " + e.getMessage());
        }

        // Llamar al metodo de devolver recurso
        try {
            gestores.getGestorPrestamos().devolverRecurso(usuarioDev, recursoDev);
        } catch (IllegalStateException e) {
            System.out.println("❌ Error al devolver el recurso: " + e.getMessage());
        }
    }

    public void mostrarMenuBuscarPrestamos() {
        int opcionBusquedaPrestamos;
        do {
            menus.MenuBuscarPrestamos();
            opcionBusquedaPrestamos = leerOpcion();

            switch (opcionBusquedaPrestamos) {
                case 1: // Buscar por ID de usuario
                    System.out.print("--> Ingrese el ID del usuario: ");
                    String idUsuario = leerTexto();
                    gestores.getGestorPrestamos().buscarPorIdUsuario(idUsuario);
                    break;
                case 2: // Buscar por ID de recurso
                    System.out.print("--> Ingrese el ID del recurso: ");
                    String idRecurso = leerTexto();
                    gestores.getGestorPrestamos().buscarPorIdRecurso(idRecurso);
                    break;
                case 3: // Buscar por fecha de préstamo
                    System.out.print("--> Ingrese la fecha de préstamo (formato: YYYY-MM-DD): ");
                    String fechaStr = leerTexto();
                    LocalDate fechaPrestamo = LocalDate.parse(fechaStr);
                    gestores.getGestorPrestamos().buscarPorFecha(fechaPrestamo);
                    break;
                case 4: // Volver al menú anterior
                    System.out.println("↩️ Volviendo al Menú de Préstamos...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionBusquedaPrestamos != 4);
    }

    public void mostrarMenuOrdenarPrestamos() {
        int opcionOrdenPrestamo;
        do {
            menus.MenuOrdenarPrestamos();
            opcionOrdenPrestamo = leerOpcion();
            switch (opcionOrdenPrestamo) {
                case 1: // Ordenar por ID de usuario
                    List<Prestamo> ordenadosPorIdUsuario = gestores.getGestorPrestamos().ordenarPrestamosPorIdUsuario();
                    gestores.getGestorPrestamos().mostrarPrestamosFiltrados(ordenadosPorIdUsuario);
                    break;
                case 2: // Ordenar por fecha de préstamo
                    List<Prestamo> ordenadosPorFecha = gestores.getGestorPrestamos().ordenarPrestamosPorFecha();
                    gestores.getGestorPrestamos().mostrarPrestamosFiltrados(ordenadosPorFecha);
                    break;
                case 3: // Ordenar por ID de recurso
                    List<Prestamo> ordenadosPorIdRecurso = gestores.getGestorPrestamos().ordenarPrestamosPorIdRecurso();
                    gestores.getGestorPrestamos().mostrarPrestamosFiltrados(ordenadosPorIdRecurso);
                    break;
                case 4: // Volver al menú anterior
                    System.out.println("↩️ Volviendo al Menú de Préstamos...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionOrdenPrestamo != 4);
    }

    public void mostrarMenuReservas() {
        int opcionReserva;
        // Declarar  fuera del bucle o switch
        String idRecurso = "";
        String idUsuario = "";
        do {
            menus.MenuReservas();
            opcionReserva = leerOpcion();
            switch (opcionReserva) {
                case 1: // Reservar recurso
                    mostrarMenuReservar();
                    break;
                case 2: // Ver reservas pendientes
                    // Muestra las reservas pendientes en la cola de reservas
                    gestores.getGestorReservas().mostrarReservas();
                    break;
                case 3: // Eliminar reserva
                    System.out.print("--> Ingrese el ID del recurso que desea eliminar: ");
                    idRecurso = leerTexto();
                    gestores.getGestorReservas().eliminarReserva(idRecurso);
                    break;
                case 4: // Buscar reservas
                    mostrarMenuBuscarReservas();
                    break;
                case 5: // Ordenar reservas
                    mostrarMenuOrdenarReservas();
                    break;
                case 6:
                    System.out.println("↩️ Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionReserva != 6);
    }

    public void mostrarMenuReservar() {
        // Solicitar el ID del usuario
        System.out.print("--> Ingrese el ID del usuario que desea realizar la reserva: ");
        String idUsuario = leerTexto();

        // Solicitar el ID del recurso
        System.out.print("--> Ingrese el ID del recurso que desea reservar: ");
        String idRecurso = leerTexto();

        try {
            // Buscar el usuario por ID
            Usuario usuario = gestores.getGestorUsuarios().obtenerUsuarioPorId(idUsuario);

            // Buscar el recurso por ID
            RecursoDigital recurso = gestores.getGestorRecursos().obtenerRecursoPorId(idRecurso);

            // Verificar si el recurso está disponible
            if (gestores.getGestorPrestamos().validarRecursoDisponible(recurso)) {
                // Pedir prioridad
                System.out.println("--> Ingrese la prioridad de la reserva (menor número = mayor prioridad): ");
                int prioridad = leerOpcion();
                // Agregar la reserva
                gestores.getGestorReservas().agregarReserva(usuario, recurso, prioridad);
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
    }

    public void mostrarMenuBuscarReservas() {
        int opcionBusqueda;
        do {
            menus.MenuBuscarReservas();
            opcionBusqueda = leerOpcion();

            switch (opcionBusqueda) {
                case 1: // Buscar por ID de usuario
                    System.out.print("--> Ingrese el ID del usuario: ");
                    String idUsuario = leerTexto();
                    gestores.getGestorReservas().buscarPorIDUsuario(idUsuario);
                    break;

                case 2: // Buscar por ID de recurso
                    System.out.print("--> Ingrese el ID del recurso: ");
                    String idRecurso = leerTexto();
                    gestores.getGestorReservas().buscarPorIDRecurso(idRecurso);
                    break;

                case 3: // Buscar por prioridad
                    System.out.print("--> Ingrese la prioridad: ");
                    int prioridad = leerOpcion();
                    gestores.getGestorReservas().buscarPorPrioridad(prioridad);
                    break;

                case 4: // Buscar por fecha
                    System.out.print("--> Ingrese la fecha de la reserva (formato: YYYY-MM-DD): ");
                    String fechaStr = leerTexto();
                    LocalDate fecha = LocalDate.parse(fechaStr);
                    gestores.getGestorReservas().buscarPorFecha(fecha);
                    break;

                case 5: // Volver al menú anterior
                    System.out.println("↩️ Volviendo al Menú de Reservas...");
                    break;

                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionBusqueda != 5);
    }

    public void mostrarMenuOrdenarReservas() {
        int opcionOrden;
        do {
            menus.MenuOrdenarReservas();
            opcionOrden = leerOpcion();

            switch (opcionOrden) {
                case 1: // Ordenar por prioridad
                    List<Reserva> reservasOrdenadasPorPrioridad = gestores.getGestorReservas().ordenarReservasPorPrioridad();
                    gestores.getGestorReservas().mostrarReservasFiltradas(reservasOrdenadasPorPrioridad);
                    break;
                case 2: // Ordenar por fecha
                    List<Reserva> reservasOrdenadasPorFecha = gestores.getGestorReservas().ordenarReservasPorFecha();
                    gestores.getGestorReservas().mostrarReservasFiltradas(reservasOrdenadasPorFecha);
                    break;
                case 3: // Ordenar por ID de usuario
                    List<Reserva> reservasOrdenadasPorIdUsuario = gestores.getGestorReservas().ordenarReservasPorIdUsuario();
                    gestores.getGestorReservas().mostrarReservasFiltradas(reservasOrdenadasPorIdUsuario);
                    break;
                case 4: // Volver al menú anterior
                    System.out.println("↩️ Volviendo al Menú de Reservas...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionOrden != 4);
    }

    public void mostrarMenuReportes() {
        int opcionReporte;
        do {
            // Llamamos al metodo para mostrar el menú de reportes desde la clase Menu
            menus.MenuReportes();
            opcionReporte = leerOpcion(); // Usamos el metodo de leer la opción

            switch (opcionReporte) {
                case 1:
                    gestores.getGestorReportes().mostrarRecursosMasPrestados();
                    break;
                case 2:
                    gestores.getGestorReportes().mostrarUsuariosMasActivos();
                    break;
                case 3:
                    gestores.getGestorReportes().mostrarEstadisticasPorCategoria();
                    break;
                case 4:
                    System.out.println("🔙 Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida. Intente de nuevo.");
            }
        } while (opcionReporte != 4);
    }

    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String leerTexto() {
        return scanner.nextLine();
    }
}