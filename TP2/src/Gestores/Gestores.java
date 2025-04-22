package Gestores;
import Interfaces.ServicioNotificaciones;
import Servicios.ServicioNotificacionesEmail;
import Servicios.ServicioNotificacionesSMS;
import Simulaciones.SimuladorRecursos;
import Simulaciones.SimuladorUsuarios;

public class Gestores {
    // Crear instancias de los servicios de notificación
    ServicioNotificaciones servicioEmail = new ServicioNotificacionesEmail();
    ServicioNotificaciones servicioSMS = new ServicioNotificacionesSMS();

    // Crear Gestores
    GestorNotificaciones gestorNotificaciones = new GestorNotificaciones(servicioEmail, servicioSMS);
    GestorReservas gestorReservas = new GestorReservas(gestorNotificaciones);
    GestorPrestamos gestorPrestamos = new GestorPrestamos(gestorNotificaciones, gestorReservas);
    GestorRecursos gestorRecursos = new GestorRecursos();
    GestorUsuarios gestorUsuarios = new GestorUsuarios();
    GestorReportes gestorReportes = new GestorReportes(gestorPrestamos, gestorUsuarios, gestorRecursos);

    // Constructor
    public Gestores() {
        // Cargar usuarios y recursos con los simuladores
        SimuladorUsuarios.cargarUsuarios(gestorUsuarios);
        SimuladorRecursos.cargarRecursos(gestorRecursos);
    }

    // Getters: para acceder a los gestores desde otras clases
    public ServicioNotificaciones getServicioEmail() {
        return servicioEmail;
    }
    public ServicioNotificaciones getServicioSMS() {
        return servicioSMS;
    }
    public GestorNotificaciones getGestorNotificaciones() {
        return gestorNotificaciones;
    }
    public GestorReservas getGestorReservas() {
        return gestorReservas;
    }
    public GestorPrestamos getGestorPrestamos() {
        return gestorPrestamos;
    }
    public GestorRecursos getGestorRecursos() {
        return gestorRecursos;
    }
    public GestorUsuarios getGestorUsuarios() {
        return gestorUsuarios;
    }
    public GestorReportes getGestorReportes() {
        return gestorReportes;
    }
}
