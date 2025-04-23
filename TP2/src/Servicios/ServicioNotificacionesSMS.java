package Servicios;
import Usuarios.Usuario;
import Interfaces.ServicioNotificaciones;

//Implementación concreta del ServicioNotificaciones para envío de SMS
public class ServicioNotificacionesSMS implements ServicioNotificaciones {

    @Override
    public void enviarNotificacion(String mensaje, Usuario usuario) {
        System.out.println("\nEnviando SMS a " + usuario.getNombre() + " " +usuario.getApellido() + " al número: " + usuario.getTelefono());
        System.out.println("Contenido del mensaje: " + mensaje);
    }

}
