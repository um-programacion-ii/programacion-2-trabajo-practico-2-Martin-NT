package Gestores;

import Interfaces.ServicioNotificaciones;
import Servicios.ServicioNotificacionesEmail;
import Servicios.ServicioNotificacionesSMS;
import Usuarios.Usuario;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GestorNotificaciones {
    private final ExecutorService executorService;
    private final ServicioNotificaciones servicioEmail;
    private final ServicioNotificaciones servicioSMS;

    // Constructor: ahora acepta los servicios como dependencias
    public GestorNotificaciones(ServicioNotificaciones servicioEmail, ServicioNotificaciones servicioSMS) {
        this.executorService = Executors.newFixedThreadPool(2); // 2 hilos, uno para cada tipo de notificación
        this.servicioEmail = servicioEmail;
        this.servicioSMS = servicioSMS;
    }

    // Enviar notificación por correo
    public void enviarNotificacionPorEmail(String mensaje, Usuario usuario) {
        Future<?> future = executorService.submit(() -> {
            try {
                servicioEmail.enviarNotificacion(mensaje, usuario);
            } catch (Exception e) {
                System.err.println("Error al enviar notificación por email: " + e.getMessage());
            }
        });

        try {
            future.get(); // ⚠️ Espera a que termine el envío por email
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al esperar que termine el envío de notificación por email: " + e.getMessage());
        }
    }

    // Enviar notificación por SMS
    public void enviarNotificacionPorSMS(String mensaje, Usuario usuario) {
        Future<?> future = executorService.submit(() -> {
            try {
                servicioSMS.enviarNotificacion(mensaje, usuario);
            } catch (Exception e) {
                System.err.println("Error al enviar notificación por SMS: " + e.getMessage());
            }
        });

        try {
            future.get(); // ⚠️ Espera a que termine el envío por email
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al esperar que termine el envío de notificación por SMS: " + e.getMessage());
        }
    }

    // Cerrar el ExecutorService
    public void cerrarExecutor() {
        try {
            executorService.shutdown(); // Intentar cerrar el ExecutorService
            if (!executorService.awaitTermination(60, java.util.concurrent.TimeUnit.SECONDS)) {
                executorService.shutdownNow(); // Forzar el cierre si no termina en 60 segundos
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow(); // Interrumpir y cerrar de inmediato
        }
    }
}

// Esta clase fue implementado y entendido con la ayuda de chatgpt
