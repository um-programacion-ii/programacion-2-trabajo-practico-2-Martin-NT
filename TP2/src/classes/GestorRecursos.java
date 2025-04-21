package classes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Comparadores.ComparadorRecurso;
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
    }  ////modificar

    // Metodo que agrega un recurso a la lista
    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    // Metodo que elimina un recurso por su ID
    public void eliminarRecurso(String id) {
        recursos.removeIf(recurso -> recurso.getId().equals(id));
    }

    // Metodo que verifica si existe el id del recurso
    public boolean existeRecurso(String id) {
        return recursos.stream().anyMatch(r -> r.getId().equalsIgnoreCase(id));
    }

    // Metodo para mostrar las categorias disponibles al usuario
    public void mostrarCategoriasDisponibles() {
        System.out.println("\n===== 📝 CATEGORIAS DE RECURSOS DISPONIBLES =====");
        for (CategoriaRecurso categoria : CategoriaRecurso.values()) {
            System.out.println("- " + categoria);
        }
    }

    // Metodo para mostrar todos los recursos
    public void mostrarRecursos() {
        if (recursos.isEmpty()) {
            System.out.println("\n⚠️ No hay recursos disponibles.");
        } else {
            System.out.println("\n===== 📚 RECURSOS DISPONIBLES =====");
            for (RecursoDigital recurso : recursos) {
                System.out.println("\n" + recurso);
            }
        }
    }

    // Metodo para mostrar los recursos filtrados
    public void mostrarRecursosFiltrados(List<RecursoDigital> recursos) {
        if (recursos.isEmpty()) {
            System.out.println("⚠️ No se encontraron recursos con esos criterios.");
        } else {
            System.out.println("\n📚 Recursos encontrados:");
            for (RecursoDigital recurso : recursos) {
                System.out.println(recurso);
                System.out.println("----------------------------------");
            }
        }
    }

    // Metodo para mostrar solo los libros filtrados
    public void filtrarLibros() {
        List<RecursoDigital> libros = recursos.stream()
                .filter(r -> r instanceof Libro)
                .collect(Collectors.toList());

        mostrarRecursosFiltrados(libros); // Este metodo imprime los resultados
    }


    // Metodo para mostrar solo los audiolibros filtrados
    public void filtrarAudiolibros() {
        List<RecursoDigital> audiolibros = recursos.stream()
                .filter(r -> r instanceof Audiolibro)
                .collect(Collectors.toList());

        mostrarRecursosFiltrados(audiolibros); // Este metodo imprime los resultados
    }

    // Metodo para mostrar solo las revistas filtradas
    public void filtrarRevistas() {
        List<RecursoDigital> revistas = recursos.stream()
                .filter(r -> r instanceof Revista)
                .collect(Collectors.toList());

        mostrarRecursosFiltrados(revistas); // Este metodo imprime los resultados
    }

    // Metodo para buscar recursos por titulo
    public void buscarPorTitulo(String titulo) {
        List<RecursoDigital> resultados = recursos.stream()
                .filter(r -> r.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());

        mostrarRecursosFiltrados(resultados);
    }

    // Metodo para buscar recursos por tipo de categoría
    public void buscarPorCategoria(CategoriaRecurso categoria) {
        List<RecursoDigital> resultados = recursos.stream()
                .filter(recurso -> recurso.getCategoria() == categoria)
                .collect(Collectors.toList());

        mostrarRecursosFiltrados(resultados);
    }

    // Metodo que usa comparador par ordenar y mostrar recursos por título
    public void ordenarYMostrarPorTitulo() {
        List<RecursoDigital> ordenados = recursos.stream()
                .sorted(ComparadorRecurso.porTitulo())
                .collect(Collectors.toList());
        mostrarRecursosFiltrados(ordenados);
    }

    // Metodo que usa comparador par ordenar y mostrar recursos por fecha
    public void ordenarYMostrarPorFecha() {
        List<RecursoDigital> ordenados = recursos.stream()
                .sorted(ComparadorRecurso.porFechaPublicacion())
                .collect(Collectors.toList());
        mostrarRecursosFiltrados(ordenados);
    }

}
