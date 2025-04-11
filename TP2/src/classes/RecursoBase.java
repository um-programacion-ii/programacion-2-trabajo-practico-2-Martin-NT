package classes;
import interfaces.RecursoDigital;

public abstract class RecursoBase implements RecursoDigital {
    private final String id;
    private String titulo;
    private String autor;
    private String fechaPublicacion;
    private String estado;

    // Constructor
    public RecursoBase(String id, String titulo, String autor, String fechaPublicacion, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.estado = estado;
    }

    //Getters
    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getTitulo() {
        return titulo;
    }
    @Override
    public String getAutor() {
        return autor;
    }
    @Override
    public String getFechaPublicacion() {
        return fechaPublicacion;
    }
    @Override
    public String getEstado() {
        return estado;
    }

    //Setters
    @Override
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    @Override
    public void setAutor(String autor) {
        this.autor = autor;
    }
    @Override
    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    @Override
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Metodo toString() que será sobrescrito en las clases hijas
    @Override
    public String toString() {
        return "📚 Recurso ID: " + id + "\n" +
                " - Título: " + titulo + "\n" +
                " - Autor: " + autor + "\n" +
                " - Fecha de Publicación: " + fechaPublicacion + "\n" +
                " - Estado: " + estado + "\n";
    }
}
