package interfaces;
import classes.Usuario;
import java.time.LocalDateTime;
import Excepciones.RecursoNoDisponibleException;

// Interfaz específica para recursos que se pueden prestar
public interface Prestable {
    boolean estaDisponible(); // Indica si el recurso está disponible para préstamo
    //LocalDateTime getFechaDevolucion(); // Devuelve la fecha límite para devolver el recurso
    void prestar(Usuario usuario); // Metodo para realizar el préstamo
}
