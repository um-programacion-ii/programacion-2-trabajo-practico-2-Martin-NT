package classes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import Comparadores.ComparadorRecurso;
import Excepciones.RecursoNoDisponibleException;
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
    public void eliminarRecurso(String id) throws RecursoNoDisponibleException {
        //Retorna true si eliminó al menos un recurso, y false si no encontró ninguno con ese ID.
        boolean eliminado = recursos.removeIf(recurso -> recurso.getId().equalsIgnoreCase(id));

        if (!eliminado) {
            throw new RecursoNoDisponibleException("❌ No se encontró un recurso con el ID: " + id);
        }
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
    public void mostrarRecursos() throws RecursoNoDisponibleException {
        if (recursos.isEmpty()) {
            throw new RecursoNoDisponibleException("⚠️ No hay recursos disponibles.");
        } else {
            System.out.println("\n===== 📚 RECURSOS DISPONIBLES =====");
            for (RecursoDigital recurso : recursos) {
                System.out.println("\n" + recurso);
            }
        }
    }

    // Metodo para mostrar los recursos filtrados
    public void mostrarRecursosFiltrados(List<RecursoDigital> recursos) throws RecursoNoDisponibleException {
        if (recursos.isEmpty()) {
            throw new RecursoNoDisponibleException("⚠️ No se encontraron recursos con esos criterios.");
        } else {
            System.out.println("\n📚 Recursos encontrados:");
            for (RecursoDigital recurso : recursos) {
                System.out.println(recurso);
                System.out.println("----------------------------------");
            }
        }
    }

    // Metodo para mostrar solo los libros filtrados
    public void filtrarLibros() throws RecursoNoDisponibleException {
        List<RecursoDigital> libros = recursos.stream()
                .filter(r -> r instanceof Libro)
                .collect(Collectors.toList());
        System.out.println("\n--> Recursos filtrados por Libros");
        mostrarRecursosFiltrados(libros); // Este metodo imprime los resultados
    }


    // Metodo para mostrar solo los audiolibros filtrados
    public void filtrarAudiolibros() throws RecursoNoDisponibleException {
        List<RecursoDigital> audiolibros = recursos.stream()
                .filter(r -> r instanceof Audiolibro)
                .collect(Collectors.toList());
        System.out.println("\n--> Recursos filtrados por Audiolibros");
        mostrarRecursosFiltrados(audiolibros); // Este metodo imprime los resultados
    }

    // Metodo para mostrar solo las revistas filtradas
    public void filtrarRevistas() throws RecursoNoDisponibleException {
        List<RecursoDigital> revistas = recursos.stream()
                .filter(r -> r instanceof Revista)
                .collect(Collectors.toList());
        System.out.println("\n--> Recursos filtrados por Revistas");
        mostrarRecursosFiltrados(revistas); // Este metodo imprime los resultados
    }

    // Metodo para buscar recursos por titulo
    public void buscarPorTitulo(String titulo) throws RecursoNoDisponibleException {
        List<RecursoDigital> resultados = recursos.stream()
                .filter(r -> r.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\n--> Recursos buscados por titulo: " + titulo);
        mostrarRecursosFiltrados(resultados);
    }

    // Metodo para buscar recursos por tipo de categoría
    public void buscarPorCategoria(CategoriaRecurso categoria) throws RecursoNoDisponibleException {
        List<RecursoDigital> resultados = recursos.stream()
                .filter(recurso -> recurso.getCategoria() == categoria)
                .collect(Collectors.toList());
        System.out.println("\n--> Recursos buscados por categoria: " + categoria);
        mostrarRecursosFiltrados(resultados);
    }

    // Metodo que usa comparador par ordenar y mostrar recursos por título
    public void ordenarYMostrarPorTitulo() throws RecursoNoDisponibleException {
        List<RecursoDigital> ordenados = recursos.stream()
                .sorted(ComparadorRecurso.porTitulo())
                .collect(Collectors.toList());
        System.out.println("\n--> Recursos ordenados por titulo");
        mostrarRecursosFiltrados(ordenados);
    }

    // Metodo que usa comparador par ordenar y mostrar recursos por fecha
    public void ordenarYMostrarPorFecha() throws RecursoNoDisponibleException {
        List<RecursoDigital> ordenados = recursos.stream()
                .sorted(ComparadorRecurso.porFechaPublicacion())
                .collect(Collectors.toList());
        System.out.println("\n--> Recursos ordenados por fecha");
        mostrarRecursosFiltrados(ordenados);
    }

}
