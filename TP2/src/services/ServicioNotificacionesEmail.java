package services;
import classes.Usuario;
import interfaces.ServicioNotificaciones;
/**
 * Implementación concreta del ServicioNotificaciones para envío de emails.
 * Cumple con el principio DIP porque implementa una abstracción (la interfaz ServicioNotificaciones)
 * y puede ser inyectado en cualquier clase que lo necesite.
 */
public class ServicioNotificacionesEmail implements ServicioNotificaciones {

    //Implementación del metodo para enviar una notificación por email
    @Override
    public void notificar(String destinatario, String mensaje) {
        //Simulo el envio del correo
        System.out.println("Enviando correo a: " + destinatario);
        System.out.println("Contenido del mensaje: " + mensaje);
    }

    //Implementación del metodo para enviar una notificación por correo a un usuario
    @Override
    public void enviarNotificacion(String mensaje, Usuario usuario) {
        // Usamos el correo electrónico del usuario
        notificar(usuario.getEmail(), mensaje);
    }
}
