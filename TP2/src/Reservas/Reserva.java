package Reservas;
import Interfaces.RecursoDigital;
import Usuarios.Usuario;
import java.time.LocalDate;

public class Reserva implements Comparable<Reserva>{
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDate fechaReserva;
    private int prioridad;

    // Constructor
    public Reserva(Usuario usuario, RecursoDigital recurso, int prioridad) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = LocalDate.now(); // Fecha actual por defecto
        this.prioridad = prioridad;
    }

    // Getters
    public Usuario getUsuario() {
        return usuario;
    }
    public RecursoDigital getRecurso() {
        return recurso;
    }
    public LocalDate getFechaReserva() {
        return fechaReserva;
    }
    public int getPrioridad() {
        return prioridad;
    }

    // Setters
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setRecurso(RecursoDigital recurso) {
        this.recurso = recurso;
    }
    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Reserva de '" + recurso.getCategoria() + "' (" + recurso.getId() + ")" +
                "\n - Titulo Recurso: " + recurso.getTitulo() +
                "\n - Usuario ID: " + usuario.getId() +
                "\n - Nombre Completo Usuario: " + usuario.getNombre() + " " + usuario.getApellido() +
                "\n - Fecha: " + fechaReserva +
                "\n - Prioridad: " + prioridad;
    }

    @Override
    public int compareTo(Reserva otraReserva) {
        return Integer.compare(this.prioridad, otraReserva.prioridad);
    }
}
