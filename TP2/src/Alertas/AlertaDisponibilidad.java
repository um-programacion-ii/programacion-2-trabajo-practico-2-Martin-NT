package Alertas;

import Excepciones.RecursoNoDisponibleException;
import Gestores.GestorNotificaciones;
import Gestores.GestorReservas;
import Gestores.GestorRecursos;
import Gestores.GestorPrestamos;
import Interfaces.RecursoDigital;
import Enums.EstadoRecurso;
import Interfaces.ServicioNotificaciones;
import Prestamos.Prestamo;
import Recursos.RecursoBase;
import Reservas.Reserva;
import Servicios.ServicioNotificacionesEmail;
import Servicios.ServicioNotificacionesSMS;
import Usuarios.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlertaDisponibilidad {
    private GestorReservas gestorReservas;
    private GestorRecursos gestorRecursos;
    private GestorPrestamos gestorPrestamos;
    private GestorNotificaciones gestorNotificaciones;

    private ServicioNotificaciones servicioEmail = new ServicioNotificacionesEmail();
    private ServicioNotificaciones servicioSMS = new ServicioNotificacionesSMS();

    public AlertaDisponibilidad(GestorReservas gestorReservas, GestorRecursos gestorRecursos, GestorPrestamos gestorPrestamos) {
        this.gestorReservas = gestorReservas;
        this.gestorRecursos = gestorRecursos;
        this.gestorPrestamos = gestorPrestamos;
    }

    public void verificarDisponibilidad() {
        List<Reserva> reservas = gestorReservas.getReservas();
        List<RecursoBase> recursosDisponibles = new ArrayList<>();  // Lista de recursos disponibles
        boolean hayAlertas = false;

        // Aquí almacenamos el usuario de la reserva, lo pasamos como parámetro más tarde
        Usuario usuarioReserva = null;

        // Recorremos las reservas
        for (Reserva reserva : reservas) {
            if (reserva.getRecurso() instanceof RecursoBase recurso) {
                usuarioReserva = reserva.getUsuario();  // Guardamos el usuario de la reserva

                // Verificamos que el recurso esté disponible
                if (recurso.estaDisponible()) {
                    recursosDisponibles.add(recurso);  // Añadimos el recurso disponible a la lista
                    hayAlertas = true;

                    // Cambiamos el estado del recurso a RESERVADO
                    recurso.setEstado(EstadoRecurso.RESERVADO);

                    // Mostrar alerta visual en consola
                    System.out.println("\n📢 \033[1;33mALERTA DE DISPONIBILIDAD\033[0m");
                    System.out.println("📘 Recurso: " + recurso.getTitulo() + " (ID: " + recurso.getId() + ")");
                    System.out.println("👤 Reservado por: " + usuarioReserva.getNombre() + " " + usuarioReserva.getApellido());
                    System.out.println("📩 Se notificó a " + usuarioReserva.getNombre() + " por correo y SMS.");

                    // Simulación de envío de notificaciones
                    String mensaje = "📘 Tu recurso '" + recurso.getTitulo() + "' está disponible para retirar.";

                    if (servicioEmail != null && servicioSMS != null) {
                        servicioEmail.enviarNotificacion(mensaje, usuarioReserva);
                        servicioSMS.enviarNotificacion(mensaje, usuarioReserva);
                    } else {
                        System.out.println("⚠️ Error: Los servicios de notificación no están inicializados.");
                    }
                }
            }
        }

        // Si encontramos recursos disponibles, los mostramos
        if (hayAlertas) {
            System.out.println("\nLista de recursos disponibles para retiro:");
            for (RecursoBase recurso : recursosDisponibles) {
                System.out.println("- " + recurso.getTitulo() + " (ID: " + recurso.getId() + ")");
            }

            // Solicitar al usuario si desea realizar el préstamo
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n¿Quieres realizar un préstamo de algún recurso disponible? (SI/NO): ");
            String respuesta = scanner.nextLine();

            if ("SI".equalsIgnoreCase(respuesta)) {
                // Solicitar ID del recurso para realizar el préstamo
                System.out.println("Ingresa el ID del recurso que deseas prestar:");
                String idRecurso = scanner.nextLine();

                // Buscar el recurso por ID
                RecursoBase recursoSeleccionado = null;
                for (RecursoBase recurso : recursosDisponibles) {
                    if (recurso.getId().equals(idRecurso)) {
                        recursoSeleccionado = recurso;
                        break;
                    }
                }

                if (recursoSeleccionado != null) {
                    try {
                        // Ahora tenemos al usuario correcto, podemos realizar el préstamo
                        gestorPrestamos.realizarPrestamo(usuarioReserva, recursoSeleccionado);
                    } catch (RecursoNoDisponibleException e) {
                        System.out.println("⚠️ No se puede realizar el préstamo. El recurso no está disponible.");
                    }
                } else {
                    System.out.println("⚠️ Recurso no encontrado.");
                }

            } else {
                System.out.println("✅ No se realizó el préstamo.");
            }
        } else {
            System.out.println("✅ No hay recursos reservados disponibles actualmente.");
        }
    }

    // Metodo para verificar disponibilidad y realizar préstamo (desde las alertas)
    public void verificarYRealizarPrestamo(Usuario usuario, RecursoBase recurso) throws RecursoNoDisponibleException {
        if (recurso.estaDisponible()) {  // Cambié isDisponible() a estaDisponible()
            // Llamar al metodo del gestor de préstamos
            gestorPrestamos.realizarPrestamo(usuario, recurso);

            // Si el préstamo es exitoso, se envían notificaciones
            String mensaje = "📚 El recurso '" + recurso.getTitulo() + "' ha sido prestado con éxito a " + usuario.getNombre() + " " + usuario.getApellido();
            gestorNotificaciones.enviarNotificacionPorEmail(mensaje, usuario);
        } else {
            // Manejar el caso en que el recurso no esté disponible
            System.out.println("⚠️ El recurso no está disponible para préstamo.");
        }
    }



}
