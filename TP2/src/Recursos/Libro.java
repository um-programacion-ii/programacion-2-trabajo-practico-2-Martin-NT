package Recursos;
import Enums.EstadoRecurso;
import Enums.CategoriaRecurso;
import java.time.LocalDate;

// La clase Libro es un tipo de RecursoDigital con atributos específicos
public class Libro extends RecursoBase {
    private int paginas;
    private String genero;
    private String editorial;

    //Constructor
    public Libro(String id, String titulo, String autor, LocalDate fechaPublicacion,
                 EstadoRecurso estado, CategoriaRecurso categoria, int paginas,
                 String genero, String editorial) {
        super(id, titulo, autor, fechaPublicacion, estado, categoria);
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

}
