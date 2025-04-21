package Comparadores;
import interfaces.RecursoDigital;
import java.util.Comparator;
// Se aprendio a hacer los comparadores con chatgpt

public class ComparadorRecurso {

    public static Comparator<RecursoDigital> porTitulo() {
        // Compara los títulos usando orden alfabético sin distinguir entre mayúsculas y minúsculas
        return Comparator.comparing(RecursoDigital::getTitulo, String.CASE_INSENSITIVE_ORDER);
    }

    public static Comparator<RecursoDigital> porFechaPublicacion() {
        // Compara las fechas de publicación usando orden cronológico
        return Comparator.comparing(RecursoDigital::getFechaPublicacion);
    }
}
