package services;
import classes.Usuario;
import interfaces.ServicioNotificaciones;

//Implementación concreta del ServicioNotificaciones para envío de SMS
public class ServicioNotificacionesSMS implements ServicioNotificaciones {

    //Implementación del metodo para enviar una notificación por SMS
    @Override
    public void notificar(String destinatario, String mensaje) {
        //Simulo el envio por SMS
        System.out.println("Enviando SMS al: " + destinatario);
        System.out.println("Contenido del mensaje: " + mensaje);
    }

    //Implementación del metodo para enviar una notificación por SMS a un usuario
    @Override
    public void enviarNotificacion(String mensaje, Usuario usuario) {
        // Usamos el correo electrónico del usuario
        notificar(usuario.getEmail(), mensaje);
    }

}
