package Services;
import classes.Usuario;
import Interfaces.ServicioNotificaciones;
/**
 * Implementación concreta del ServicioNotificaciones para envío de emails.
 * Cumple con el principio DIP porque implementa una abstracción (la interfaz ServicioNotificaciones)
 * y puede ser inyectado en cualquier clase que lo necesite.
 */
public class ServicioNotificacionesEmail implements ServicioNotificaciones {

    @Override
    public void enviarNotificacion(String mensaje, Usuario usuario) {
        System.out.println("Enviando correo a: " + usuario.getNombre() + " al correo: " + usuario.getEmail());
        System.out.println("Contenido del mensaje: " + mensaje);
    }
}
