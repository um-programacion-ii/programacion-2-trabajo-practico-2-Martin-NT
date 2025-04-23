package Recursos;
import Interfaces.Prestable;
import Interfaces.RecursoDigital;
import Enums.EstadoRecurso;
import Enums.CategoriaRecurso;
import Usuarios.Usuario;

import java.time.LocalDate;

public abstract class RecursoBase implements RecursoDigital, Prestable {
    private final String id;
    private String titulo;
    private String autor;
    private LocalDate fechaPublicacion;
    private EstadoRecurso estado;
    private CategoriaRecurso categoria;

    // Constructor
    public RecursoBase(String id, String titulo, String autor,
                       LocalDate fechaPublicacion, EstadoRecurso estado, CategoriaRecurso categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.estado = estado;
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

    // Implementación del metodo estaDisponible() desde Prestable
    @Override
    public boolean estaDisponible() {
        return this.estado == EstadoRecurso.DISPONIBLE;
    }

    // Implementación del metodo prestar() desde Renovable
    @Override
    public void prestar(Usuario usuario) {
        setEstado(EstadoRecurso.PRESTADO);
    }


}
