package services;
import classes.Usuario;
import interfaces.ServicioNotificaciones;

//Implementación concreta del ServicioNotificaciones para envío de SMS
public class ServicioNotificacionesSMS implements ServicioNotificaciones {

    @Override
    public void enviarNotificacion(String mensaje, Usuario usuario) {
        System.out.println("Enviando SMS a: " + usuario.getNombre() + " al número: " + usuario.getTelefono());
        System.out.println("Contenido del mensaje: " + mensaje);
    }

}
