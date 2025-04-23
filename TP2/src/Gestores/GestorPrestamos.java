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

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    // Metodo para validar si el recurso está disponible
    public boolean validarRecursoDisponible(RecursoDigital recurso) {
        return recurso.getEstado() == EstadoRecurso.DISPONIBLE;
    }

    // Metodo para realizar el préstamo
    public void realizarPrestamo(Usuario usuario, RecursoDigital recurso) throws RecursoNoDisponibleException {
        // Si dos hilos intentan prestar el mismo recurso, se bloquean correctamente.
        synchronized (recurso) {
            System.out.println("\n[" + Thread.currentThread().getName() + "] 🔄 Intentando prestar: " + recurso.getTitulo());

            LocalDate fechaPrestamo = LocalDate.now();
            LocalDate fechaDevolucion = fechaPrestamo.plusDays(14);
            boolean activo = true;

            if (validarRecursoDisponible(recurso)) {
                gestorReservas.eliminarReserva(recurso.getId());
                Prestamo nuevoPrestamo = new Prestamo(usuario, recurso, fechaPrestamo, fechaDevolucion, activo);
                prestamos.add(nuevoPrestamo);
                recurso.setEstado(EstadoRecurso.PRESTADO);

                String mensaje = "📚 Se ha realizado el préstamo del recurso '" + recurso.getTitulo() + "' hasta el " + fechaDevolucion + ".";
                gestorNotificaciones.enviarNotificacionPorEmail(mensaje, usuario);

                System.out.println("\n[" + Thread.currentThread().getName() + "] ✅ Préstamo exitoso: " + recurso.getTitulo());
            } else {
                System.out.println("\n[" + Thread.currentThread().getName() + "] ❌ Recurso no disponible: " + recurso.getTitulo());
                throw new RecursoNoDisponibleException("El recurso '" + recurso.getTitulo() + "' no está disponible.");
            }
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
            gestorNotificaciones.enviarNotificacionPorEmail(mensaje, usuario);
        } else {
            System.out.println("\nEl " + recurso.getCategoria() + " '" + recurso.getTitulo() + "' (" + recurso.getId() + ") ya fue devuelto.");
        }
    }

    // Metodo para renovar el préstamo
    public void renovarPrestamo(Prestamo prestamo) {
        // Validar si el préstamo aún es activo
        if (prestamo.isActivo()) {
            LocalDate nuevaFechaDevolucion = prestamo.getFechaDevolucion().plusDays(14);  // Renovamos 14 días más
            prestamo.setFechaDevolucion(nuevaFechaDevolucion);

            System.out.println("El préstamo del recurso '" + prestamo.getRecurso().getTitulo() + "' ha sido renovado hasta el " + nuevaFechaDevolucion);

            // Enviar notificación al usuario
            String mensaje = "📅 Tu préstamo del recurso '" + prestamo.getRecurso().getTitulo() + "' ha sido renovado hasta el " + nuevaFechaDevolucion + ".";
            gestorNotificaciones.enviarNotificacionPorEmail(mensaje, prestamo.getUsuario());
        } else {
            System.out.println("No se puede renovar el préstamo porque ya está cerrado.");
        }
    }

    // Metodo para mostrar los prestamos activos
    public void mostrarPrestamosActivos() {
        System.out.println("\n==== Préstamos Activos ====");
        boolean hayActivos = false;

        // Iterar sobre los préstamos activos
        synchronized (this) {
            for (Prestamo p : prestamos) {
                if (p.isActivo()) {
                    // Mostrar detalles del préstamo utilizando toString() de la clase Prestamo
                    System.out.println(p);
                    hayActivos = true;
                }
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
        synchronized (this) {
            return prestamos.stream()
                    .filter(p -> p.getRecurso().equals(recurso) && p.getUsuario().equals(usuario) && p.isActivo())
                    .findFirst()
                    .orElse(null);
        }
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
