package classes;
import interfaces.Prestable;
import interfaces.RecursoDigital;
import Enum.EstadoRecurso;
import Enum.CategoriaRecurso;
import java.time.LocalDateTime;
import java.time.LocalDate;

public abstract class RecursoBase implements RecursoDigital, Prestable {
    private final String id;
    private String titulo;
    private String autor;
    private LocalDate fechaPublicacion;
    private EstadoRecurso estado;
    private LocalDateTime fechaDevolucion;
    private CategoriaRecurso categoria;

    // Constructor
    public RecursoBase(String id, String titulo, String autor,
                       LocalDate fechaPublicacion, EstadoRecurso estado,
                       LocalDateTime fechaDevolucion, CategoriaRecurso categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.estado = estado;
        this.fechaDevolucion = fechaDevolucion;
        this.categoria = categoria;
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
    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }
    @Override
    public EstadoRecurso getEstado() {
        return estado;
    }
    @Override
    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }
    @Override
    public CategoriaRecurso getCategoria() {
        return categoria;
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
    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    @Override
    public void setEstado(EstadoRecurso estado) {
        this.estado = estado;
    }
    public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
    @Override
    public void setCategoria(CategoriaRecurso categoria) {
        this.categoria = categoria;
    }

    // Metodo toString() que será sobrescrito en las clases hijas
    @Override
    public String toString() {
        return " - Recurso ID: " + id + "\n" +
                " - Título: " + titulo + "\n" +
                " - Autor: " + autor + "\n" +
                " - Fecha de Publicación: " + fechaPublicacion + "\n" +
                " - Estado: " + estado + "\n" +
                " - Categoria: " + categoria + "\n";
    }

    @Override
    public void actualizarEstado(EstadoRecurso estado) {
        this.estado = estado;
    }
}
