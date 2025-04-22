package Gestores;
import Interfaces.RecursoDigital;
import Reservas.Reserva;
import Usuarios.Usuario;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GestorReservas {
    // Cola de reservas que respeta el orden de llegada (FIFO)
    private BlockingQueue<Reserva> colaReservas;

    // Constructor: inicializa la cola de reservas como una LinkedBlockingQueue
    public GestorReservas() {
        colaReservas = new LinkedBlockingQueue<>();
    }

    // Getter: Devuelve la cola de reservas completa
    public BlockingQueue<Reserva> getColaReservas() {
        return colaReservas;
    }

    // Agrega una reserva a la cola si el usuario aún no ha reservado ese recurso
    public void agregarReserva(Usuario usuario, RecursoDigital recurso) {
        // Verifica si el usuario ya tiene una reserva para ese mismo recurso
        boolean yaReservado = colaReservas.stream().anyMatch(
                reserva -> reserva.getUsuario().getId().equals(usuario.getId())
                        && reserva.getRecurso().getId().equals(recurso.getId())
        );

        // Si ya existe una reserva igual, se informa y no se agrega
        if (yaReservado) {
            System.out.println("⚠️ El usuario ya tiene una reserva para este recurso.");
            return;
        }

        // Si no hay duplicado, se crea y agrega la nueva reserva con la fecha actual
        Reserva nuevaReserva = new Reserva(usuario, recurso);
        colaReservas.add(nuevaReserva);
        System.out.println("✅ Reserva añadida correctamente.");
    }

    // Metodo para eliminar una reserva basada en el ID del recurso
    public void eliminarReserva(String idRecurso) {
        // Buscar y eliminar la reserva
        boolean reservaEliminada = colaReservas.removeIf(reserva -> reserva.getRecurso().getId().equals(idRecurso));

        if (reservaEliminada) {
            System.out.println("✅ Reserva eliminada correctamente.");
        } else {
            System.out.println("⚠️ No se encontró una reserva para el recurso con ID: " + idRecurso);
        }
    }

    // Muestra todas las reservas en la cola
    public void mostrarReservas() {
        if (colaReservas.isEmpty()) {
            System.out.println("📭 No hay reservas registradas.");
        } else {
            System.out.println("\n==== Reservas Pendientes ====");
            for (Reserva reserva : colaReservas) {
                System.out.println("\n" + reserva);

            }
        }
    }
}
