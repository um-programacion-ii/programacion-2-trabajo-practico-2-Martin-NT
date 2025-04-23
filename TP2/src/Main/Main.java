package Main;
import Gestores.*;
import Simulaciones.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear Instancias
        Consola consola = new Consola();
        Gestores gestores = new Gestores();
        Menus menus = new Menus();
        Scanner scanner = new Scanner(System.in);

        // Importante para conectar la alerta a la consola
        consola.setAlertaVencimiento(gestores.getAlertaVencimiento());

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

                case 3: // Menu Reservas
                    consola.mostrarMenuReservas();
                    break;

                case 4:  // Menu Prestamos
                    consola.mostrarMenuPrestamos();
                    break;

                case 5: // Menu Reportes
                    consola.mostrarMenuReportes();
                    break;

                case 6: // PRUEBAS
                    int opcion;
                    do {
                        System.out.println("\n===== 🧪 MENÚ DE PRUEBAS =====");
                        System.out.println("- Importante Probar en Orden");
                        System.out.println("1. Simular y Verificar Alerta Vencimiento");
                        System.out.println("2. Simular y Verificar Alerta Disponibilidad");
                        System.out.println("3. Simular Concurrencia");
                        System.out.println("4. Ejecutar Reportes");
                        System.out.println("5. Probar servicios de Notificación");
                        System.out.println("6. Volver al menú principal");
                        System.out.print("Seleccione una opción: ");
                        opcion = scanner.nextInt();
                        scanner.nextLine(); // limpiar buffer

                        switch (opcion) {
                            case 1: // Alertas Vencimiento
                                // Simular préstamo por vencer (se agrega manualmente)
                                SimuladorAlertasVencimiento.generarPrestamoPorVencer(gestores.getGestorPrestamos());

                                // Verificar alertas (esto debería disparar la alerta y ofrecer renovación)
                                SimuladorAlertasVencimiento.verificarAlertas(gestores.getGestorPrestamos());
                                break;

                            case 2: // Alertas Disponibilidad
                                // Generar reserva de prueba
                                SimuladorAlertasDisponibilidad.generarReservaDePrueba();

                                // Verificar alertas de disponibilidad
                                SimuladorAlertasDisponibilidad.verificarAlertasDisponibilidad();
                                break;

                            case 3: // Concurrencia
                                // Pruebas de concurrencia (para testear)
                                SimuladorConcurrencia.simularConcurrencia(
                                        gestores.getGestorRecursos(),
                                        gestores.getGestorUsuarios(),
                                        gestores.getGestorPrestamos(),
                                        gestores.getGestorNotificaciones()
                                );
                                break;

                            case 4: // Reportes
                                // 🔥 NUEVO Paso intermedio: Simular préstamos
                                SimuladorUsuarios.cargarUsuarios(gestores.getGestorUsuarios());
                                SimuladorRecursos.cargarRecursos(gestores.getGestorRecursos());
                                SimuladorPrestamos.cargarPrestamos(
                                        gestores.getGestorPrestamos(),
                                        gestores.getGestorUsuarios(),
                                        gestores.getGestorRecursos()
                                );

                                // Paso 3: Ejecutar reportes para ver el estado actual
                                SimuladorReportes.ejecutarReportes(gestores);
                                break;

                            case 5: // Notificaciones
                                // Probar servicios de notificación
                                SimuladorNotificaciones.probarServicios(
                                        gestores.getServicioEmail(),
                                        gestores.getServicioSMS(),
                                        gestores.getGestorUsuarios()
                                );
                                break;

                            case 6:
                                System.out.println("Volviendo al menú principal...");
                                break;

                            default:
                                System.out.println("⚠️ Opción inválida. Intente nuevamente.");
                        }

                    } while (opcion != 6);

                case 7:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionPrincipal != 7);
    }
}