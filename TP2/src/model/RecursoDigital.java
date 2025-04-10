package model;

public abstract class RecursoDigital {
    private final String id;
    private String titulo;
    private String autor;
    private String fechaPublicacion;

    //Constructor
    public RecursoDigital(String id, String titulo, String autor, String fechaPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }

    //Getters
    public String getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getAutor() {
        return autor;
    }
    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    //Setter para el título, autor y fecha (id no se modifica)
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String toString() {
        return "📚 Recurso: " + id + "\n" +
                " - Título: " + titulo + "\n" +
                " - Autor: " + autor + "\n" +
                " - Fecha de Publicación: " + fechaPublicacion;
    }

}
