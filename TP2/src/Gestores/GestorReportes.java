package Gestores;
import Excepciones.RecursoNoDisponibleException;
import Interfaces.RecursoDigital;
import Prestamos.Prestamo;
import Usuarios.Usuario;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Clase responsable de generar reportes y estadísticas
 * a partir de los datos de préstamos, usuarios y recursos.
 */
public class GestorReportes {
    // Dependencias de los gestores
    private final GestorPrestamos gestorPrestamos;
    private final GestorUsuarios gestorUsuarios;
    private final GestorRecursos gestorRecursos;

    //Constructor que recibe las colecciones necesarias para generar reportes.
    public GestorReportes(GestorPrestamos gestorPrestamos, GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos) {
        this.gestorPrestamos = gestorPrestamos;
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;
    }

    // Mostrar recursos más prestados
    public void mostrarRecursosMasPrestados() {
        System.out.println("\n📚 Recursos más prestados:");

        // Agrupa los préstamos por ID de recurso y los cuenta
        Map<String, Long> conteo = gestorPrestamos.getPrestamos().stream()
                .collect(Collectors.groupingBy(p -> p.getRecurso().getId(), Collectors.counting()));

        // Ordena de mayor a menor y muestra todos los recursos prestados
        conteo.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> {
                    try {
                        RecursoDigital recurso = gestorRecursos.obtenerRecursoPorId(entry.getKey());
                        if (recurso != null) {
                            System.out.println("🔸 " + recurso.getTitulo() + " - " + entry.getValue() + " préstamo(s) - Categoría: " + recurso.getCategoria().name());
                        }
                    } catch (RecursoNoDisponibleException e) {
                        System.out.println("Error al obtener recurso con ID " + entry.getKey() + ": " + e.getMessage());
                    }
                });
    }

    // Muestra los usuarios más activos (los que más préstamos realizaron)
    public void mostrarUsuariosMasActivos() {
        System.out.println("\n👥 Usuarios más activos:");

        // Agrupa los préstamos por ID de usuario y los cuenta
        Map<String, Long> conteo = gestorPrestamos.getPrestamos().stream()
                .collect(Collectors.groupingBy(p -> p.getUsuario().getId(), Collectors.counting()));

        // Ordena de mayor a menor y muestra los 5 más activos
        conteo.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> {
                    Usuario usuario = gestorUsuarios.getUsuarios().get(entry.getKey());
                    if (usuario != null) {
                        System.out.println("👤 " + usuario.getNombre() + " " + usuario.getApellido() + " - " + entry.getValue() + " préstamos");
                    }
                });
    }

    // Muestra estadísticas de préstamos agrupadas por categoría del recurso.
    public void mostrarEstadisticasPorCategoria() {
        System.out.println("\n📈 Estadísticas por categoría de recurso:");

        // Agrupa los préstamos por categoría de recurso y los cuenta
        Map<String, Long> conteo = gestorPrestamos.getPrestamos().stream()
                .collect(Collectors.groupingBy(p -> p.getRecurso().getCategoria().name(), Collectors.counting()));

        // Muestra las estadísticas de préstamos por categoría
        conteo.forEach((categoria, cantidad) ->
                System.out.println("📌 " + categoria + ": " + cantidad + " préstamos"));
    }
}

