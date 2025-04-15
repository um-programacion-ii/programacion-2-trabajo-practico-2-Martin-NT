package interfaces;
import classes.Usuario;
/**
 * Interfaz que representa un servicio de notificaciones.
 * Cualquier implementación debe definir cómo enviar una notificación a un usuario.
 * Esto cumple con DIP porque las clases dependerán de esta abstracción y no de una implementación concreta.
 */
public interface ServicioNotificaciones {
    // Este metodo define el contrato para enviar una notificación a un usuario específico.
    void enviarNotificacion(String mensaje, Usuario usuario);
}
