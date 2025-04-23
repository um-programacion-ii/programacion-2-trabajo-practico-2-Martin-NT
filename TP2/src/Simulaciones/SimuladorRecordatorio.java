package Simulaciones;
import Enums.NivelUrgencia;
import Enums.CategoriaRecurso;
import Enums.EstadoRecurso;
import Gestores.GestorRecordatorio;
import Recursos.Libro;
import Usuarios.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimuladorRecordatorio {

    public static void main(String[] args) {
        // Simulación de creación de usuario y recurso
        Usuario usuario = new Usuario("U005", "Martin", "Navarro", "martin@example.com", "funciona", "2634257345", "diaria");

        // Mostrar los detalles de los usuarios
        System.out.println("\n" + usuario);

        // Si quisieras cambiar la frecuencia de notificación de un usuario
        usuario.setFrecuenciaNotificaciones("semanal");  // Cambiar la frecuencia
        System.out.println("Frecuencia actualizada de " + usuario.getNombre() + " " + usuario.getApellido() + ": " + usuario.getFrecuenciaNotificaciones());
        System.out.println(usuario);

        // Crear un libro
        Libro libro2 = new Libro("L002", "Harry Potter 2", "J.K. Rowling",
                LocalDate.of(1997, 6, 26), EstadoRecurso.DISPONIBLE, CategoriaRecurso.LIBRO, 256, "Fantasía", "Salamandra");

        // Crear un gestor de recordatorios
        GestorRecordatorio gestorRecordatorio = new GestorRecordatorio();

        // Enviar un recordatorio individual
        gestorRecordatorio.enviarRecordatorio("El recurso 'Harry Potter 2' debe ser devuelto pronto.",
                NivelUrgencia.INFO,
                usuario,
                libro2);

        // Enviar un recordatorio general
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);  // Puedes agregar más usuarios si lo deseas
        gestorRecordatorio.enviarRecordatorioGeneral("Recordatorio general: El recurso 'Harry Potter' está por vencer.",
                NivelUrgencia.INFO,
                usuarios);

        // Mostrar historial de recordatorios
        gestorRecordatorio.mostrarHistorial();

        // Simular el envío periódico de recordatorios
        enviarRecordatoriosPeriodicos(gestorRecordatorio, usuario);
    }

    // Metodo para enviar recordatorios periódicos
    public static void enviarRecordatoriosPeriodicos(GestorRecordatorio gestorRecordatorio, Usuario usuario) {
        // Usar ScheduledExecutorService para enviar recordatorios cada 10 segundos (para simulación)
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            gestorRecordatorio.enviarRecordatorio("Este es un recordatorio automático.", NivelUrgencia.WARNING, usuario, null);
        }, 0, 10, TimeUnit.SECONDS); // 0 significa que comienza inmediatamente, luego cada 10 segundos
    }
}
