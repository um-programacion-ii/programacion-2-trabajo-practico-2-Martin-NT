package classes;
import Interfaces.RecursoDigital;
import java.time.LocalDate;

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
        return "Préstamo de " + recurso.getCategoria() + " (" + recurso.getId() + ")" +
                "\n - Título Recurso: " + recurso.getTitulo() +
                "\n - Usuario ID: " + usuario.getId() +
                "\n - Nombre Completo Usuario: " + usuario.getNombre() + " " + usuario.getApellido() +
                "\n - Fecha de préstamo: " + fechaPrestamo +
                "\n - Fecha de devolución: " + fechaDevolucion +
                "\n - Activo: " + (activo ? "Sí" : "No");
    }

}
