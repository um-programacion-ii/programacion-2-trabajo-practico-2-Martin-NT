package classes;
import java.util.ArrayList;
import java.util.List;
import interfaces.RecursoDigital;
/**
 * Clase responsable de gestionar los recursos digitales.
 */
public class GestorRecursos {
    // Lista para almacenar los recursos digitales
    private List<RecursoDigital> recursos = new ArrayList<>();

    // Constructor que inicializa la lista de recursos
    public GestorRecursos() {
        this.recursos = new ArrayList<>();
    }

    // Agrega un recurso a la lista
    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    // Getter: Devuelve la lista completa de recursos
    public List<RecursoDigital> getRecursos() {
        return recursos;
    }

}
