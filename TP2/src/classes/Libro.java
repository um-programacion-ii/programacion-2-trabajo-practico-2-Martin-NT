package classes;
import Excepciones.RecursoNoDisponibleException;
import interfaces.Prestable;
import interfaces.Renovable;
import Enum.EstadoRecurso;
import Enum.CategoriaRecurso;
import java.time.LocalDate;
import java.time.LocalDateTime;

// La clase Libro es un tipo de RecursoDigital con atributos específicos
public class Libro extends RecursoBase implements Prestable, Renovable {
    private int paginas;
    private String genero;
    private String editorial;

    //Constructor
    public Libro(String id, String titulo, String autor, LocalDate fechaPublicacion,
                 EstadoRecurso estado, LocalDateTime fechaDevolucion,
                 CategoriaRecurso categoria, int paginas, String genero, String editorial) {
        super(id, titulo, autor, fechaPublicacion, estado, fechaDevolucion, categoria);
        this.paginas = paginas;
        this.genero = genero;
        this.editorial = editorial;
    }

    //Getter
    public int getPaginas() {
        return paginas;
    }
    public String getGenero() {
        return genero;
    }
    public String getEditorial() {
        return editorial;
    }

    //Setter
    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "[📚 LIBRO]\n" + super.toString() +
                " - Páginas: " + paginas + "\n" +
                " - Género: " + genero + "\n" +
                " - Editorial: " + editorial + "\n";
    }

    // Implementación del metodo estaDisponible() desde Prestable
    @Override
    public boolean estaDisponible() {
        // El libro está disponible si su estado es DISPONIBLE
        return getEstado() == EstadoRecurso.DISPONIBLE;
    }

    // Implementación del metodo prestar() desde Prestable
    @Override
    public void prestar(Usuario usuario) throws RecursoNoDisponibleException {
        if (estaDisponible()) {
            // Si el libro está disponible, se cambia el estado a PRESTADO
            setEstado(EstadoRecurso.PRESTADO);
            System.out.println("El libro '" + getTitulo() + "' ha sido prestado a " + usuario.getNombre());
        } else {
            // Si no está disponible, lanzamos la excepción
            throw new RecursoNoDisponibleException("❌ El libro '" + getTitulo() + "' no está disponible para préstamo.");
        }
    }

    // Implementación del metodo renovar() desde Renovable
    @Override
    public void renovar() {
        if (getEstado() == EstadoRecurso.PRESTADO) {
            setFechaDevolucion(getFechaDevolucion().plusDays(7)); // Renovación de la fecha de devolución
            System.out.println("El libro '" + getTitulo() + "' ha sido renovado. Nueva fecha de devolución: " + getFechaDevolucion());
        } else {
            System.out.println("El libro no está prestado y no puede ser renovado.");
        }
    }

}
