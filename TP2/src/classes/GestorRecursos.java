package classes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import interfaces.RecursoDigital;
import Enum.CategoriaRecurso;
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

    // Getter: Devuelve la lista completa de recursos
    public List<RecursoDigital> getRecursos() {
        return recursos;
    }

    // Metodo que agrega un recurso a la lista
    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    // Metodo que elimina un recurso por su ID
    public void eliminarRecurso(String id) {
        recursos.removeIf(recurso -> recurso.getId().equals(id));
    }

    // Metodo para mostrar las categorias disponibles al usuario
    public void mostrarCategoriasDisponibles() {
        System.out.println("\n📝 Categorías de recursos disponibles:");
        for (CategoriaRecurso categoria : CategoriaRecurso.values()) {
            System.out.println("- " + categoria);
        }
    }

    // Busca recursos cuyo título contenga el texto indicado (ignora mayúsculas/minúsculas).
    public List<RecursoDigital> buscarPorTitulo(String titulo) {

        // Creamos una lista vacía donde vamos a guardar los recursos que coincidan con la búsqueda
        List<RecursoDigital> resultado = new ArrayList<>();

        for (RecursoDigital recurso : recursos) {
            // Obtenemos el título del recurso y lo pasamos a minúsculas para que la búsqueda no sea sensible a mayúsculas/minúsculas
            String tituloRecurso = recurso.getTitulo().toLowerCase();

            // También convertimos el texto que el usuario está buscando a minúsculas
            String tituloBuscado = titulo.toLowerCase();

            // Verificamos si el título del recurso contiene el texto buscado
            if (tituloRecurso.contains(tituloBuscado)) {
                // Si coincide, lo agregamos a la lista de resultados
                resultado.add(recurso);
            }
        }
        // Devolvemos la lista con todos los recursos que coincidieron
        return resultado;
    }

}
