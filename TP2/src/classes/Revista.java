package classes;
import Excepciones.RecursoNoDisponibleException;
import interfaces.Prestable;
import Enum.EstadoRecurso;
import Enum.CategoriaRecurso;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Revista extends RecursoBase {
    private int numeroEdicion;
    private String periodicidad;
    private String seccionPrincipal;
    private String editorial;

    //Constructor
    public Revista(String id, String titulo, String autor, LocalDate fechaPublicacion,
                   EstadoRecurso estado, CategoriaRecurso categoria, int numeroEdicion,
                   String periodicidad, String seccionPrincipal, String editorial) {
        super(id, titulo, autor, fechaPublicacion, estado, categoria);
        this.numeroEdicion = numeroEdicion;
        this.periodicidad = periodicidad;
        this.seccionPrincipal = seccionPrincipal;
        this.editorial = editorial;
    }

    //Getters
    public int getNumeroEdicion() {
        return numeroEdicion;
    }
    public String getPeriodicidad() {
        return periodicidad;
    }
    public String getSeccionPrincipal() {
        return seccionPrincipal;
    }
    public String getEditorial() {
        return editorial;
    }

    //Setters
    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }
    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }
    public void setSeccionPrincipal(String seccionPrincipal) {
        this.seccionPrincipal = seccionPrincipal;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "[📰 REVISTA]\n" + super.toString() +
                " - Número de Edición: " + numeroEdicion + "\n" +
                " - Periodicidad: " + periodicidad + "\n" +
                " - Sección Principal: " + seccionPrincipal + "\n" +
                " - Editorial: " + editorial + "\n";
    }

}
