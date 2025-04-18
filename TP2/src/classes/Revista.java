package classes;
import interfaces.RecursoDigital;

public class Revista extends RecursoBase implements RecursoDigital {
    private int numeroEdicion;
    private String periodicidad;
    private String seccionPrincipal;
    private String editorial;

    //Constructor
    public Revista(String id, String titulo, String autor, String fechaPublicacion, String estado,
                   int numeroEdicion, String periodicidad, String seccionPrincipal, String editorial) {
        super(id, titulo, autor, fechaPublicacion, estado);
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
