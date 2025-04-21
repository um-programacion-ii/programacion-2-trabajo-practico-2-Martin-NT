package classes;
import Excepciones.RecursoNoDisponibleException;
import interfaces.RecursoDigital;
import java.time.LocalDate;
import Enum.EstadoRecurso;

public class Prestamo {
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private boolean activo;

    // Constructor
    public Prestamo(Usuario usuario, RecursoDigital recurso, LocalDate fechaPrestamo, LocalDate fechaDevolucion, boolean activo) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaPrestamo.plusDays(14);
        this.activo = activo;
    }

    // Getters
    public Usuario getUsuario() {
        return usuario;
    }
    public RecursoDigital getRecurso() {
        return recurso;
    }
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
    public boolean isActivo() {
        return activo;
    }

    // Setters
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setRecurso(RecursoDigital recurso) {
        this.recurso = recurso;
    }
    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // Metodo para obtener información del préstamo
    @Override
    public String toString() {
        return "Préstamo de '" + recurso.getTitulo() + "' a " + usuario.getNombre() + " " + usuario.getApellido() +
                "\n - Fecha de préstamo: " + fechaPrestamo +
                "\n - Fecha de devolución: " + fechaDevolucion +
                "\n - Activo: " + (activo ? "Sí" : "No");
    }

    // Metodo para validar si el recurso está disponible
    public boolean validarRecursoDisponible() {
        return recurso.getEstado() == EstadoRecurso.DISPONIBLE;
    }

    // Metodo para realizar el préstamo
    public void realizarPrestamo() throws RecursoNoDisponibleException {
        if (validarRecursoDisponible()) {
            recurso.actualizarEstado(EstadoRecurso.PRESTADO);
            System.out.println("\n--> El recurso '" + recurso.getTitulo() + "' ha sido prestado a " + usuario.getNombre() + " " + usuario.getApellido());
        } else {
            throw new RecursoNoDisponibleException("\n--> El recurso '" + recurso.getTitulo() + "' no está disponible para préstamo.");
        }
    }

    // Metodo para devolver el recurso
    public void devolverRecurso() {
        if (activo) {
            recurso.actualizarEstado(EstadoRecurso.DISPONIBLE);
            activo = false;  // El préstamo ya no está activo
            System.out.println("\n--> El recurso '" + recurso.getTitulo() + "' ha sido devuelto.");
        } else {
            System.out.println("\n--> El recurso '" + recurso.getTitulo() + "' ya fue devuelto.");
        }
    }

}
