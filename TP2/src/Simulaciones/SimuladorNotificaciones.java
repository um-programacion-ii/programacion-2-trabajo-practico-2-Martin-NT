package Simulaciones;
import Excepciones.UsuarioNoEncontradoException;
import Gestores.GestorUsuarios;
import Interfaces.ServicioNotificaciones;

public class SimuladorNotificaciones {
    public static void probarServicios(ServicioNotificaciones servicioEmail,
                                       ServicioNotificaciones servicioSMS,
                                       GestorUsuarios gestorUsuarios) {
        System.out.println("\n==== PRUEBAS DE NOTIFICACIONES ====");
        try {
            System.out.println("\n- Prueba del servicio Email");
            servicioEmail.enviarNotificacion("--> ¡Tienes un nuevo mensaje!", gestorUsuarios.obtenerUsuarioPorId("U001"));

            System.out.println("\n- Prueba del servicio SMS");
            servicioSMS.enviarNotificacion("--> ¡Tienes un nuevo mensaje!", gestorUsuarios.obtenerUsuarioPorId("U002"));
        } catch (UsuarioNoEncontradoException e) {
            System.out.println("❌ Error al enviar notificación: " + e.getMessage());
        }
    }
}
