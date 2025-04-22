package Interfaces;
import Enums.EstadoRecurso;
import Enums.CategoriaRecurso;
import java.time.LocalDate;
/**
   Interfaz que define el contrato para todos los recursos digitales.
   Toda clase que implemente esta interfaz está obligada a definir estos métodos.
 */
public interface RecursoDigital {
    /**
     “Cualquier clase que implemente esta interfaz tiene que proporcionar
     un metodo que devuelva el título, y otro para cambiarlo.”
     */
    // Getters - Métodos para obtener información del recurso
    String getId();
    String getTitulo();
    String getAutor();
    LocalDate getFechaPublicacion();
    EstadoRecurso getEstado();
    CategoriaRecurso getCategoria();
    void actualizarEstado(EstadoRecurso estado);

    //Setters - Métodos para modificar información del recurso
    void setTitulo(String titulo);
    void setAutor(String autor);
    void setFechaPublicacion(LocalDate fechaPublicacion);
    void setEstado(EstadoRecurso estado);
    void setCategoria(CategoriaRecurso categoria);

    // Metodo común para mostrar información
    String toString();

}
