package Simulaciones;
import Enums.CategoriaRecurso;
import Enums.EstadoRecurso;
import Recursos.Audiolibro;
import Recursos.Libro;
import Recursos.Revista;
import Gestores.GestorRecursos;
import java.time.LocalDate;

public class SimuladorRecursos {
    public static void cargarRecursos(GestorRecursos gestorRecursos) {
        // Crear recursos
        Libro libro1 = new Libro("L001", "Harry Potter y la piedra filosofal", "J.K. Rowling",
                LocalDate.of(1997, 6, 26), EstadoRecurso.DISPONIBLE, CategoriaRecurso.LIBRO, 256, "Fantasía", "Salamandra");

        Revista revista1 = new Revista("R001", "National Geographic", "Varios",
                LocalDate.of(2025, 4, 10), EstadoRecurso.DISPONIBLE, CategoriaRecurso.REVISTA, 100, "Mensual", "Ciencia y naturaleza", "National Geographic Society");

        Audiolibro audiolibro1 = new Audiolibro("A001", "El Principito", "Antoine de Saint-Exupéry",
                LocalDate.of(1943, 4, 6), EstadoRecurso.DISPONIBLE, CategoriaRecurso.AUDIOLIBRO, 92, "Dangello Medina", "Español");


        // Agregar Recursos al Gestor
        gestorRecursos.agregarRecurso(libro1);
        gestorRecursos.agregarRecurso(revista1);
        gestorRecursos.agregarRecurso(audiolibro1);
    }
}
