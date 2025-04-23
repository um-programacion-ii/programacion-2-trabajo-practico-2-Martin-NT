package Simulaciones;

import Gestores.Gestores;
import Prestamos.Prestamo;
import Interfaces.RecursoDigital;
import Usuarios.Usuario;
import Gestores.GestorPrestamos;
import Excepciones.UsuarioNoEncontradoException;
import Excepciones.RecursoNoDisponibleException;
import Alertas.AlertaVencimiento;
import java.time.LocalDate;

public class SimuladorAlertas {
    // Hacer gestores estático para que pueda ser utilizado en un contexto estático
    private static final Gestores gestores = new Gestores();  // Inicializa la clase Gestores

    // Metodo para generar el préstamo que vence mañana
    public static void generarPrestamoPorVencer(GestorPrestamos gestorPrestamos) {
        System.out.println("\n===== 📊 INICIO DE GENERAR PRESTAMO POR VENCER DE PRUEBA =====");

        // Declaración de las variables
        Usuario usuario = null;
        RecursoDigital recurso = null;

        try {
            usuario = gestores.getGestorUsuarios().obtenerUsuarioPorId("U001");
            recurso = gestores.getGestorRecursos().obtenerRecursoPorId("L001");

            if (usuario != null && recurso != null) {
                // Crear el préstamo con fecha de devolución para mañana
                Prestamo prestamo = new Prestamo(usuario, recurso, LocalDate.now().minusDays(14), LocalDate.now().plusDays(1), true);
                gestorPrestamos.getPrestamos().add(prestamo); // Añadir el préstamo al gestor

                // Añadir la reserva
                gestores.getGestorReservas().agregarReserva(usuario, recurso, 1);

                // Realizar el préstamo (cambiar el estado del recurso y demás)
                gestorPrestamos.realizarPrestamo(usuario, recurso);

                // Imprimir mensaje de éxito
                System.out.println("🔔 Se ha generado un préstamo que vence mañana para pruebas de alertas.");
            } else {
                System.out.println("⚠️ No se pudo crear préstamo de prueba: Usuario o Recurso no encontrados.");
            }
        } catch (UsuarioNoEncontradoException e) {
            System.out.println("⚠️ No se encontró el usuario: " + e.getMessage());
        } catch (RecursoNoDisponibleException e) {
            System.out.println("⚠️ El recurso no está disponible: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠️ Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    // Metodo para verificar las alertas
    public static void verificarAlertas(GestorPrestamos gestorPrestamos) {
        System.out.println("\n===== 📊 INICIO DE VERIFICAR ALERTAS VENCIMIENTO DE PRUEBA =====");

        // Creamos el objeto de la clase AlertaVencimiento
        AlertaVencimiento alertaVencimiento = new AlertaVencimiento(gestorPrestamos);

        // Llamamos al metodo para verificar las alertas de vencimiento
        alertaVencimiento.monitorearVencimientos();
    }
}
