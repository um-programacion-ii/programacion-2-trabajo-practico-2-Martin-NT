package Main;
import Gestores.*;
import Simulaciones.*;

public class Main {
    public static void main(String[] args) {
        // Crear Instancias
        Consola consola = new Consola();
        Gestores gestores = new Gestores();
        Menus menus = new Menus();

        int opcionPrincipal;
        do {
            menus.MenuPrincipal();
            opcionPrincipal = consola.leerOpcion();

            switch (opcionPrincipal) {
                case 1: // Menu Usuarios
                    consola.mostrarMenuUsuarios();
                    break;

                case 2: // Menu Recursos
                    consola.mostrarMenuRecursos();
                    break;

                case 3:  // Menu Prestamos
                    consola.mostrarMenuPrestamos();
                    break;

                case 4: // Menu Reservas
                    consola.mostrarMenuReservas();
                    break;

                case 5: // Menu Reportes
                    consola.mostrarMenuReportes();
                    break;

                case 6: // PRUEBAS
                    // Probar notificaciones
                    SimuladorNotificaciones.probarServicios(gestores.getServicioEmail(), gestores.getServicioSMS(), gestores.getGestorUsuarios());

                    // Llamar al simulador de concurrencia para probar la ejecución concurrente
                    SimuladorConcurrencia.simularConcurrencia(gestores.getGestorRecursos(), gestores.getGestorUsuarios(), gestores.getGestorPrestamos(), gestores.getGestorNotificaciones());

                    // Cargar datos de prueba
                    SimuladorUsuarios.cargarUsuarios(gestores.getGestorUsuarios());
                    SimuladorRecursos.cargarRecursos(gestores.getGestorRecursos());

                    // Ejecutar reportes
                    SimuladorReportes.ejecutarReportes(gestores);
                    break;

                case 7:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionPrincipal != 7);
    }
}