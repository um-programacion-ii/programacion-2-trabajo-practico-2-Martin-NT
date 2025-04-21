package Gestores;
import Excepciones.RecursoNoDisponibleException;
import Enums.EstadoRecurso;
import Interfaces.RecursoDigital;
import Prestamos.Prestamo;
import Usuarios.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorPrestamos {
    private List<Prestamo> prestamos = new ArrayList<>();

    // Metodo para validar si el recurso está disponible
    public boolean validarRecursoDisponible(RecursoDigital recurso) {
        return recurso.getEstado() == EstadoRecurso.DISPONIBLE;
    }

    // Metodo para realizar el préstamo
    public void realizarPrestamo(Usuario usuario, RecursoDigital recurso) throws RecursoNoDisponibleException {
        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucion = fechaPrestamo.plusDays(14); // Suponiendo que el préstamo tiene una duración de 14 días
        boolean activo = true;

        // Validar si el recurso está disponible
        if (validarRecursoDisponible(recurso)) {
            // Si está disponible, se realiza el préstamo
            Prestamo nuevoPrestamo = new Prestamo(usuario, recurso, fechaPrestamo, fechaDevolucion, activo);
            prestamos.add(nuevoPrestamo); // Agregar el préstamo a la lista
            recurso.setEstado(EstadoRecurso.PRESTADO); // Cambiar el estado del recurso a PRESTADO
            System.out.println("\nEl recurso '" + recurso.getTitulo() + "' (" + recurso.getId() + ") sido prestado a " + usuario.getNombre() + " " + usuario.getApellido() + " (" + usuario.getId() + ")");
        } else {
            // Si el recurso no está disponible, lanzar una excepción
            throw new RecursoNoDisponibleException("El recurso '" + recurso.getTitulo() + "' (" + recurso.getId() + ") no está disponible para préstamo.");
        }
    }

    // Metodo para devolver el recurso
    public void devolverRecurso(Usuario usuario, RecursoDigital recurso) throws Exception {
        // Buscar el préstamo activo
        Prestamo prestamo = buscarPrestamoActivo(usuario, recurso);

        // Si no encontramos el préstamo activo
        if (prestamo == null) {
            throw new Exception("No se encontró un préstamo activo para el recurso '" + recurso.getTitulo() + "' (" + recurso.getId() + ") con el usuario '" + usuario.getNombre() +  " " + usuario.getApellido() + " (" + usuario.getId() + ")");
        }

        // Si el préstamo está activo, lo marcamos como devuelto
        if (prestamo.isActivo()) {
            prestamo.getRecurso().actualizarEstado(EstadoRecurso.DISPONIBLE); // Cambiar estado a DISPONIBLE
            prestamo.setActivo(false);  // El préstamo ya no está activo
            System.out.println("\nEl recurso '" + recurso.getTitulo() + "' (" +  recurso.getId() + ") ha sido devuelto correctamente.");
        } else {
            System.out.println("\nEl recurso '" + recurso.getTitulo() + "' (" + recurso.getId() + ") ya fue devuelto.");
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

    // Metodo para buscar un préstamo activo de un usuario y recurso
    public Prestamo buscarPrestamoActivo(Usuario usuario, RecursoDigital recurso) {
        for (Prestamo p : prestamos) {
            if (p.getUsuario().equals(usuario) && p.getRecurso().equals(recurso) && p.isActivo()) {
                return p; // Devuelve el préstamo activo si lo encuentra
            }
        }
        return null; // Si no lo encuentra, devuelve null
    }

}
