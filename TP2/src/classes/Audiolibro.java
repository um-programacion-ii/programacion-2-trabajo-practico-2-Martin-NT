package classes;
import interfaces.Prestable;
import interfaces.Renovable;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Audiolibro extends RecursoBase implements Prestable, Renovable {
    private int duracionMinutos;
    private String narrador;
    private String idioma;

    //Constructor
    public Audiolibro(String id, String titulo, String autor, LocalDate fechaPublicacion, EstadoRecurso estado, LocalDateTime fechaDevolucion, int duracionMinutos, String narrador, String idioma) {
        super(id, titulo, autor, fechaPublicacion, estado, fechaDevolucion);
        this.duracionMinutos = duracionMinutos;
        this.narrador = narrador;
        this.idioma = idioma;
    }

    //Getters
    public int getDuracionMinutos() {
        return duracionMinutos;
    }
    public String getNarrador() {
        return narrador;
    }
    public String getIdioma() {
        return idioma;
    }

    //Setters
    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }
    public void setNarrador(String narrador) {
        this.narrador = narrador;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return "[🎧 AUDIOLIBRO]\n" + super.toString() +
                " - Duración en minutos: " + duracionMinutos + "\n" +
                " - Narrador: " + narrador + "\n" +
                " - Idioma: " + idioma + "\n";
    }

    // Implementación del metodo estaDisponible() desde Prestable
    @Override
    public boolean estaDisponible() {
        // El audiolibro está disponible si su estado es DISPONIBLE
        return getEstado() == EstadoRecurso.DISPONIBLE;
    }

    // Implementación del metodo prestar() desde Prestable
    @Override
    public void prestar(Usuario usuario) {
        if (estaDisponible()) {
            // Si el audiolibro está disponible, se cambia el estado a PRESTADO
            setEstado(EstadoRecurso.PRESTADO);
            System.out.println("El audiolibro '" + getTitulo() + "' ha sido prestado a " + usuario.getNombre());
        } else {
            System.out.println("El audiolibro '" + getTitulo() + "' no está disponible para préstamo.");
        }
    }

    // Implementación del metodo renovar() desde Renovable
    @Override
    public void renovar() {
        if (getEstado() == EstadoRecurso.PRESTADO) {
            setFechaDevolucion(getFechaDevolucion().plusDays(7)); // Renovación de la fecha de devolución
            System.out.println("El audiolibro '" + getTitulo() + "' ha sido renovado. Nueva fecha de devolución: " + getFechaDevolucion());
        } else {
            System.out.println("El audiolibro no está prestado y no puede ser renovado.");
        }
    }
}
