package Simulaciones;
import Excepciones.UsuarioNoEncontradoException;
import Gestores.*;
import Interfaces.RecursoDigital;
import Usuarios.Usuario;
import Excepciones.RecursoNoDisponibleException;

public class SimuladorConcurrencia {

    public static void simularConcurrencia(GestorRecursos gestorRecursos, GestorUsuarios gestorUsuarios, GestorPrestamos gestorPrestamos, GestorNotificaciones gestorNotificaciones) {
        System.out.println("\n==== PRUEBAS DE CONCURRENCIA ====");

        // Crear un recurso compartido
        final RecursoDigital recursoCompartido;
        try {
            recursoCompartido = gestorRecursos.obtenerRecursoPorId("R001");
        } catch (RecursoNoDisponibleException e) {
            System.out.println("\n⚠️ Error: " + e.getMessage());
            return;
        }

        Thread[] hilos = new Thread[4];

        for (int i = 1; i <= 4; i++) {
            final int userId = i;
            hilos[i - 1] = new Thread(() -> {
                Usuario usuario = null;
                try {
                    usuario = gestorUsuarios.obtenerUsuarioPorId("U00" + userId);
                } catch (UsuarioNoEncontradoException e) {
                    System.out.println("\n[" + Thread.currentThread().getName() + "] ⚠️ Usuario no encontrado: " + e.getMessage());
                    return;
                }

                try {
                    gestorPrestamos.realizarPrestamo(usuario, recursoCompartido);
                } catch (RecursoNoDisponibleException e) {
                    // El mensaje de error ya lo imprime realizarPrestamo()
                }
            }, "Thread-" + i);

            hilos[i - 1].start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("\n❌ Error esperando hilo: " + e.getMessage());
            }
        }
        // Cerrar el ExecutorService al final
        gestorNotificaciones.cerrarExecutor();
    }
}

// Esta clase fue implementado y entendido con la ayuda de chatgpt