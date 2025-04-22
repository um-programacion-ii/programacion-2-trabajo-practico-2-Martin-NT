package Gestores;
import Interfaces.RecursoDigital;
import Reservas.Reserva;
import Usuarios.Usuario;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.Collectors;

public class GestorReservas {
    // Cola de reservas ordenada por prioridad (menor número = mayor prioridad)
    private PriorityBlockingQueue<Reserva> colaReservas;
    // Dependencia de GestorNotificaciones
    private GestorNotificaciones gestorNotificaciones;

    // Constructor: inicializa la cola de reservas y el gestor de notificaciones
    public GestorReservas(GestorNotificaciones gestorNotificaciones) {
        this.colaReservas = new PriorityBlockingQueue<>();
        this.gestorNotificaciones = gestorNotificaciones;  // Asignar la dependencia
    }

    // Getter: Devuelve la cola de reservas completa
    public PriorityBlockingQueue<Reserva> getColaReservas() {
        return colaReservas;
    }

    // Agrega una reserva a la cola si el usuario aún no ha reservado ese recurso
    public synchronized void agregarReserva(Usuario usuario, RecursoDigital recurso, int prioridad) {
        // Verifica si el usuario ya tiene una reserva para este recurso
        boolean yaReservado = colaReservas.stream().anyMatch(
                reserva -> reserva.getUsuario().getId().equals(usuario.getId())
                        && reserva.getRecurso().getId().equals(recurso.getId())
        );
        if (yaReservado) {
            System.out.println("⚠️ El usuario ya tiene una reserva para este recurso.");
            return;
        }
        // Crear la nueva reserva
        Reserva nuevaReserva = new Reserva(usuario, recurso, prioridad);
        colaReservas.add(nuevaReserva);
        System.out.println("✅ Reserva añadida correctamente con prioridad " + prioridad + ".");

        // Enviar la notificación de reserva exitosa
        String mensaje = "¡Reserva exitosa! Has reservado el recurso: " + recurso.getTitulo();
        gestorNotificaciones.enviarNotificacionPorSMS(mensaje, usuario);
    }

    // Metodo para eliminar una reserva basada en el ID del recurso
    public synchronized void eliminarReserva(String idRecurso) {
        // Buscar la reserva antes de eliminarla
        Reserva reservaAEliminar = colaReservas.stream()
                .filter(reserva -> reserva.getRecurso().getId().equals(idRecurso))
                .findFirst()
                .orElse(null);

        if (reservaAEliminar != null) {
            colaReservas.remove(reservaAEliminar);
            System.out.println("✅ Reserva eliminada correctamente.");

            // Enviar notificación al usuario
            String mensaje = "📌 Tu reserva del recurso '" + reservaAEliminar.getRecurso().getTitulo() + "' ha sido cancelada.";
            gestorNotificaciones.enviarNotificacionPorSMS(mensaje, reservaAEliminar.getUsuario());
        } else {
            System.out.println("⚠️ No hay una reserva activa para el recurso con ID: " + idRecurso);
        }
    }


    // Metodo para mostrar las reservas ordenadas por prioridad
    public void mostrarReservas() {
        if (colaReservas.isEmpty()) {
            System.out.println("📭 No hay reservas registradas.");
        } else {
            // Llamamos al metodo de ordenar reservas por prioridad
            List<Reserva> ordenadas = ordenarReservasPorPrioridad();  // Ordena por prioridad

            // Mostrar las reservas ordenadas
            System.out.println("\n==== Reservas Pendientes (Ordenadas por Prioridad) ====");
            for (Reserva reserva : ordenadas) {
                System.out.println("\n" + reserva);
            }
        }
    }

    // Metodo para mostrar las reservas filtradas
    public void mostrarReservasFiltradas(List<Reserva> reservas) {
        if (reservas.isEmpty()) {
            System.out.println("⚠️ No se encontraron reservas con esos criterios.");
        } else {
            System.out.println("\n==== Reservas Encontradas ====");
            for (Reserva reserva : reservas) {
                System.out.println("\n" + reserva);
            }
        }
    }

    // Metodo para ordenar las reservas por prioridad
    public List<Reserva> ordenarReservasPorPrioridad() {
        // Convertir la cola a una lista, ordenar por prioridad y devolver la lista ordenada
        return colaReservas.stream()
                .sorted(Comparator.comparingInt(Reserva::getPrioridad))  // Ordena por prioridad
                .collect(Collectors.toList());  // Convierte el stream a lista
    }

    // Metodo para ordenar las reservas por fecha de reserva
    public List<Reserva> ordenarReservasPorFecha() {
        return colaReservas.stream()
                .sorted(Comparator.comparing(Reserva::getFechaReserva))  // Ordena por fecha
                .collect(Collectors.toList());
    }

    // Metodo para ordenar las reservas por ID de usuario
    public List<Reserva> ordenarReservasPorIdUsuario() {
        return colaReservas.stream()
                .sorted(Comparator.comparing(reserva -> reserva.getUsuario().getId()))  // Ordena por ID del usuario
                .collect(Collectors.toList());
    }

    // Metodo para buscar reservas por prioridad
    public void buscarPorPrioridad(int prioridad) {
        // Filtramos las reservas que tengan la misma prioridad
        List<Reserva> resultados = colaReservas.stream()
                .filter(reserva -> reserva.getPrioridad() == prioridad)
                .collect(Collectors.toList());  // Convierte el stream a lista

        System.out.println("\n--> Reservas encontradas con prioridad: " + prioridad);
        if (resultados.isEmpty()) {
            System.out.println("📭 No se encontraron reservas con la prioridad " + prioridad);
        } else {
            mostrarReservasFiltradas(resultados);
        }
    }

    public void buscarPorIDUsuario(String idUsuario) {
        List<Reserva> resultados = colaReservas.stream()
                .filter(reserva -> reserva.getUsuario().getId().equals(idUsuario))
                .collect(Collectors.toList());

        System.out.println("\n--> Reservas encontradas para el usuario ID: " + idUsuario);
        if (resultados.isEmpty()) {
            System.out.println("📭 No se encontraron reservas para este usuario.");
        } else {
            mostrarReservasFiltradas(resultados);
        }
    }

    public void buscarPorIDRecurso(String idRecurso) {
        List<Reserva> resultados = colaReservas.stream()
                .filter(reserva -> reserva.getRecurso().getId().equals(idRecurso))
                .collect(Collectors.toList());

        System.out.println("\n--> Reservas encontradas para el recurso ID: " + idRecurso);
        if (resultados.isEmpty()) {
            System.out.println("📭 No se encontraron reservas para este recurso.");
        } else {
            mostrarReservasFiltradas(resultados);
        }
    }

    public void buscarPorFecha(LocalDate fecha) {
            List<Reserva> resultados = colaReservas.stream()
                    .filter(r -> r.getFechaReserva().equals(fecha))
                    .collect(Collectors.toList());
            mostrarReservasFiltradas(resultados);

        System.out.println("\n--> Reservas encontradas para la fecha: " + fecha);
        if (resultados.isEmpty()) {
            System.out.println("📭 No se encontraron reservas para esta fecha.");
        } else {
            mostrarReservasFiltradas(resultados);
        }
    }

}
