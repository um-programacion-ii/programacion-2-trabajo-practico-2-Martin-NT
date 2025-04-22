package Recursos;
import Enums.EstadoRecurso;
import Enums.CategoriaRecurso;
import java.time.LocalDate;

public class Audiolibro extends RecursoBase {
    private int duracionMinutos;
    private String narrador;
    private String idioma;

    //Constructor
    public Audiolibro(String id, String titulo, String autor, LocalDate fechaPublicacion,
                      EstadoRecurso estado, CategoriaRecurso categoria, int duracionMinutos, String narrador, String idioma) {
        super(id, titulo, autor, fechaPublicacion, estado, categoria);
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

}
