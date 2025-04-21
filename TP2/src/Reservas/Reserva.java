package Reservas;
import Interfaces.RecursoDigital;
import Usuarios.Usuario;
import java.time.LocalDate;

public class Reserva {
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDate fechaReserva;

    // Constructor
    public Reserva(Usuario usuario, RecursoDigital recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = LocalDate.now();
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

    @Override
    public String toString() {
        return "Reserva de '" + recurso.getCategoria() + "' (" + recurso.getId() + ")" +
                "\n - Titulo Recurso: " + recurso.getTitulo() +
                "\n - Usuario ID: " + usuario.getId() +
                "\n - Nombre Completo Usuario: " + usuario.getNombre() + " " + usuario.getApellido() +
                "\n - Fecha: " + fechaReserva;
    }
}
