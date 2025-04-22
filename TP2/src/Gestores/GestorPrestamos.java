package Gestores;
import Excepciones.RecursoNoDisponibleException;
import Enums.EstadoRecurso;
import Interfaces.RecursoDigital;
import Prestamos.Prestamo;
import Usuarios.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GestorPrestamos {
    private List<Prestamo> prestamos = new ArrayList<>();

    // Metodo para validar si el recurso está disponible
    public boolean validarRecursoDisponible(RecursoDigital recurso) {
        return recurso.getEstado() == EstadoRecurso.DISPONIBLE;
    }

    // Metodo para realizar el préstamo
    public void realizarPrestamo(Usuario usuario, RecursoDigital recurso) throws RecursoNoDisponibleException {
        // Crear una instancia de GestorReservas
        GestorReservas gestorReservas = new GestorReservas();

        // Eliminar la reserva del recurso si existe
        gestorReservas.eliminarReserva(recurso.getId());  // Elimina la reserva antes de proceder con el préstamo

        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucion = fechaPrestamo.plusDays(14); // Suponiendo que el préstamo tiene una duración de 14 días
        boolean activo = true;

        // Validar si el recurso está disponible
        if (validarRecursoDisponible(recurso)) {
            // Si está disponible, se realiza el préstamo
            Prestamo nuevoPrestamo = new Prestamo(usuario, recurso, fechaPrestamo, fechaDevolucion, activo);
            prestamos.add(nuevoPrestamo); // Agregar el préstamo a la lista
            recurso.setEstado(EstadoRecurso.PRESTADO); // Cambiar el estado del recurso a PRESTADO
            System.out.println("\nEl " + recurso.getCategoria() + " '" + recurso.getTitulo() + "' (" + recurso.getId() + ") ha sido prestado a " + usuario.getNombre() + " " + usuario.getApellido() + " (" + usuario.getId() + ")");
        } else {
            // Si el recurso no está disponible, lanzar una excepción
            throw new RecursoNoDisponibleException("\nEl " + recurso.getCategoria() + " '" + recurso.getTitulo() + "' (" + recurso.getId() + ") no está disponible para préstamo.");
        }
    }

    // Metodo para devolver el recurso
    public void devolverRecurso(Usuario usuario, RecursoDigital recurso) {
        // Buscar el préstamo activo para este recurso y usuario
        Prestamo prestamo = buscarPrestamoActivo(usuario, recurso);

        // Si no se encuentra el préstamo activo, lanzamos una excepción
        if (prestamo == null) {
            throw new IllegalStateException("No se encontró un préstamo activo para el " + recurso.getCategoria() + " '" + recurso.getTitulo() + "' (" + recurso.getId() + ") con el usuario '" + usuario.getNombre() + " " + usuario.getApellido() + " (" + usuario.getId() + ")");
        }

        // Si el préstamo está activo, lo marcamos como devuelto
        if (prestamo.isActivo()) {
            // Registramos la devolución del préstamo
            prestamo.registrarDevolucion();

            // Actualizamos el estado del recurso a DISPONIBLE
            recurso.setEstado(EstadoRecurso.DISPONIBLE);
            System.out.println("\nEl " + recurso.getCategoria() + " '" + recurso.getTitulo() + "' (" + recurso.getId() + ") ha sido devuelto correctamente.");
        } else {
            System.out.println("\nEl " + recurso.getCategoria() + " '" + recurso.getTitulo() + "' (" + recurso.getId() + ") ya fue devuelto.");
        }
    }

    // Metodo para mostrar los prestamos activos
    public void mostrarPrestamosActivos() {
        System.out.println("\n==== Préstamos Activos ====");
        boolean hayActivos = false;
        for (Prestamo p : prestamos) {
            if (p.isActivo()) {
                System.out.println("\n" + p);
                hayActivos = true;
            }
        }
        if (!hayActivos) {
            System.out.println("⚠️ No hay préstamos activos actualmente.");
        }
    }

    // Metodo para mostrar los préstamos filtrados
    public void mostrarPrestamosFiltrados(List<Prestamo> prestamos) {
        if (prestamos.isEmpty()) {
            System.out.println("⚠️ No se encontraron préstamos con esos criterios.");
        } else {
            System.out.println("\n==== Préstamos Encontrados ====");
            for (Prestamo prestamo : prestamos) {
                System.out.println("\n" + prestamo);

            }
        }
    }

    // Metodo para buscar un préstamo activo de un usuario y recurso
    public Prestamo buscarPrestamoActivo(Usuario usuario, RecursoDigital recurso) {
        return prestamos.stream()
                .filter(p -> p.getRecurso().equals(recurso) && p.getUsuario().equals(usuario) && p.isActivo())
                .findFirst()
                .orElse(null);
    }

    public void buscarPorIdUsuario(String idUsuario) {
        List<Prestamo> resultados = prestamos.stream()
                .filter(prestamo -> prestamo.getUsuario().getId().equals(idUsuario))
                .collect(Collectors.toList());

        System.out.println("\n--> Préstamos encontrados para el usuario con ID: " + idUsuario);
        if (resultados.isEmpty()) {
            System.out.println("📭 No se encontraron préstamos para este usuario.");
        } else {
            mostrarPrestamosFiltrados(resultados);
        }
    }

    public void buscarPorIdRecurso(String idRecurso) {
        List<Prestamo> resultados = prestamos.stream()
                .filter(prestamo -> prestamo.getRecurso().getId().equals(idRecurso))
                .collect(Collectors.toList());

        System.out.println("\n--> Préstamos encontrados para el recurso con ID: " + idRecurso);
        if (resultados.isEmpty()) {
            System.out.println("📭 No se encontraron préstamos para este recurso.");
        } else {
            mostrarPrestamosFiltrados(resultados);
        }
    }

    public void buscarPorFecha(LocalDate fecha) {
        List<Prestamo> resultados = prestamos.stream()
                .filter(prestamo -> prestamo.getFechaPrestamo().equals(fecha))
                .collect(Collectors.toList());

        System.out.println("\n--> Préstamos encontrados para la fecha: " + fecha);
        if (resultados.isEmpty()) {
            System.out.println("📭 No se encontraron préstamos para esta fecha.");
        } else {
            mostrarPrestamosFiltrados(resultados);
        }
    }

    public List<Prestamo> ordenarPrestamosPorIdUsuario() {
        return prestamos.stream()
                .sorted(Comparator.comparing(p -> p.getUsuario().getId()))
                .collect(Collectors.toList());
    }

    public List<Prestamo> ordenarPrestamosPorIdRecurso() {
        return prestamos.stream()
                .sorted(Comparator.comparing(p -> p.getRecurso().getId()))  // Ordena por ID de recurso
                .collect(Collectors.toList());
    }

    public List<Prestamo> ordenarPrestamosPorFecha() {
        return prestamos.stream()
                .sorted(Comparator.comparing(Prestamo::getFechaPrestamo))
                .collect(Collectors.toList());
    }







}
