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
    private List<Prestamo> prestamos;
    private GestorNotificaciones gestorNotificaciones;
    private GestorReservas gestorReservas;

    // Constructor con inyección de dependencias
    public GestorPrestamos(GestorNotificaciones gestorNotificaciones, GestorReservas gestorReservas) {
        this.prestamos = new ArrayList<>();
        this.gestorNotificaciones = gestorNotificaciones;
        this.gestorReservas = gestorReservas;
    }

    // Metodo para validar si el recurso está disponible
    public boolean validarRecursoDisponible(RecursoDigital recurso) {
        return recurso.getEstado() == EstadoRecurso.DISPONIBLE;
    }

    // Metodo para realizar el préstamo
    public void realizarPrestamo(Usuario usuario, RecursoDigital recurso) throws RecursoNoDisponibleException {
        // Eliminar la reserva si existe usando el gestor inyectado
        gestorReservas.eliminarReserva(recurso.getId());

        LocalDate fechaPrestamo = LocalDate.now();
        LocalDate fechaDevolucion = fechaPrestamo.plusDays(14); // Suponiendo 14 días de préstamo
        boolean activo = true;

        if (validarRecursoDisponible(recurso)) {
            Prestamo nuevoPrestamo = new Prestamo(usuario, recurso, fechaPrestamo, fechaDevolucion, activo);
            prestamos.add(nuevoPrestamo);
            recurso.setEstado(EstadoRecurso.PRESTADO);
            // Notificación (opcional, si también inyectaste GestorNotificaciones)
            String mensaje = "📚 Se ha realizado el préstamo del recurso '" + recurso.getTitulo()
                    + "' hasta el " + fechaDevolucion + ".";
            gestorNotificaciones.enviarNotificacionPorEmail(mensaje, usuario);

            System.out.println("\nEl " + recurso.getCategoria() + " '" + recurso.getTitulo() + "' (" + recurso.getId() + ") ha sido prestado a " + usuario.getNombre() + " " + usuario.getApellido() + " (" + usuario.getId() + ")");


        } else {
            throw new RecursoNoDisponibleException("\nEl " + recurso.getCategoria() + " '" + recurso.getTitulo() + "' (" + recurso.getId() + ") no está disponible para préstamo.");
        }
    }


    // Metodo para devolver el recurso
    public void devolverRecurso(Usuario usuario, RecursoDigital recurso) {
        // Buscar el préstamo activo para este recurso y usuario
        Prestamo prestamo = buscarPrestamoActivo(usuario, recurso);

        if (prestamo == null) {
            throw new IllegalStateException("No se encontró un préstamo activo para el " + recurso.getCategoria() + " '" + recurso.getTitulo() + "' (" + recurso.getId() + ") con el usuario '" + usuario.getNombre() + " " + usuario.getApellido() + " (" + usuario.getId() + ")");
        }

        if (prestamo.isActivo()) {
            // Registrar devolución
            prestamo.registrarDevolucion();
            recurso.setEstado(EstadoRecurso.DISPONIBLE);

            // Mensaje por consola
            System.out.println("\nEl " + recurso.getCategoria() + " '" + recurso.getTitulo() + "' (" + recurso.getId() + ") ha sido devuelto correctamente.");

            // Enviar notificación al usuario
            String mensaje = "📥 Has devuelto el recurso '" + recurso.getTitulo() + "' correctamente. ¡Gracias!";
            gestorNotificaciones.enviarNotificacionPorEmail(mensaje, usuario); // o SMS, como prefieras
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
