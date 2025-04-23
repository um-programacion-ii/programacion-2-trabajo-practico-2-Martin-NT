package Gestores;
import Alertas.Recordatorio;
import Enums.NivelUrgencia;
import Usuarios.Usuario;
import Interfaces.RecursoDigital;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GestorRecordatorio {
    private List<Recordatorio> historial = new ArrayList<>();

    // Enviar recordatorio individual (con usuario y recurso)
    public void enviarRecordatorio(String mensaje, NivelUrgencia urgencia, Usuario usuario, RecursoDigital recurso) {
        Recordatorio recordatorio = new Recordatorio(mensaje, urgencia, usuario, recurso);
        historial.add(recordatorio);

        // Verificar si el nivel de urgencia del recordatorio es suficiente para el usuario
        if (usuario != null && urgencia.ordinal() >= usuario.getNivelPreferido().ordinal()) {
            System.out.println("\nRECORDATORIO " + urgencia + ": " + mensaje);
        }
    }

    // Enviar un recordatorio general para todos los usuarios
    public void enviarRecordatorioGeneral(String mensaje, NivelUrgencia urgencia, List<Usuario> usuarios) {
        System.out.println("\n[GENERAL] RECORDATORIO - " + urgencia + ": " + mensaje);

        for (Usuario u : usuarios) {
            Recordatorio recordatorio = new Recordatorio(mensaje, urgencia, u, null);
            historial.add(recordatorio);

            // Mostrar el mensaje si el nivel de urgencia es suficiente para el usuario
            if (urgencia.ordinal() >= u.getNivelPreferido().ordinal()) {
                System.out.println("[GENERAL] -> para " + u.getNombre() + ": " + mensaje);
            }
        }
    }

    // Mostrar historial completo de alertas
    public void mostrarHistorial() {
        System.out.println("\n--- HISTORIAL DE RECORDATORIOS ---");

        if (historial.isEmpty()) {
            System.out.println("⚠️  No hay recordatorios.");
            return;
        }

        for (Recordatorio recordatorio : historial) {
            String color = switch (recordatorio.getNivelUrgencia()) {
                case INFO -> "\u001B[34m";   // Azul
                case WARNING -> "\u001B[33m"; // Amarillo
                case ERROR -> "\u001B[31m";   // Rojo
            };

            System.out.println(color + recordatorio + "\u001B[0m"); // Reset color
        }
    }

    // Enviar recordatorio periódico a un usuario según la frecuencia
    public void enviarRecordatoriosPeriodicos(Usuario usuario, String mensaje, NivelUrgencia urgencia) {
        String frecuencia = usuario.getFrecuenciaNotificaciones();
        long interval = 0;
        TimeUnit unidad = TimeUnit.SECONDS;

        switch (frecuencia) {
            case "diaria":
                interval = 24 * 60 * 60; // 24 horas en segundos
                unidad = TimeUnit.SECONDS;
                break;
            case "semanal":
                interval = 7 * 24 * 60 * 60; // 7 días en segundos
                unidad = TimeUnit.SECONDS;
                break;
            case "mensual":
                interval = 30 * 24 * 60 * 60; // 30 días en segundos
                unidad = TimeUnit.SECONDS;
                break;
            default:
                System.out.println("Frecuencia no válida.");
                return;
        }

        // Programar el envío periódico del recordatorio
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            enviarRecordatorio(mensaje, urgencia, usuario, null);  // Enviar el recordatorio
        }, 0, interval, unidad); // 0: iniciar inmediatamente, luego se repite según la frecuencia
    }
}
