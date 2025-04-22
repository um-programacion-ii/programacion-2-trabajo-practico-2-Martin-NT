package Simulaciones;

import Gestores.Gestores;
import Interfaces.RecursoDigital;
import Usuarios.Usuario;
import Gestores.GestorReservas;
import Alertas.AlertaDisponibilidad;
import Reservas.Reserva;
import java.time.LocalDate;

public class SimuladorAlertasDisponibilidad {
    // Hacer gestores estáticos para poder ser utilizados en un contexto estático
    private static final Gestores gestores = new Gestores();  // Inicializa la clase Gestores

    // Metodo para generar reservas para los recursos
    public static void generarReservaDePrueba() {
        System.out.println("\n===== 📊 INICIO DE GENERAR RESERVA DE RECURSO PARA PRUEBAS DE DISPONIBILIDAD =====");

        // Declaración de las variables
        Usuario usuario = null;
        RecursoDigital recurso = null;

        try {
            // Crear un usuario y un recurso para las pruebas
            usuario = gestores.getGestorUsuarios().obtenerUsuarioPorId("U001");
            recurso = gestores.getGestorRecursos().obtenerRecursoPorId("L001");

            if (usuario != null && recurso != null) {
                // Crear una reserva para el recurso
                gestores.getGestorReservas().agregarReserva(usuario, recurso, 1);

                // Imprimir mensaje de éxito
                System.out.println("🔔 Se ha generado una reserva para el recurso.");
            } else {
                System.out.println("⚠️ No se pudo crear la reserva de prueba: Usuario o Recurso no encontrados.");
            }
        } catch (Exception e) {
            System.out.println("⚠️ Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    // Metodo para verificar las alertas de disponibilidad
    public static void verificarAlertasDisponibilidad() {
        System.out.println("\n===== 📊 INICIO DE VERIFICAR ALERTAS DE DISPONIBILIDAD DE RECURSOS =====");

        // Creamos el objeto de la clase AlertaDisponibilidad
        AlertaDisponibilidad alertaDisponibilidad = new AlertaDisponibilidad(
                gestores.getGestorReservas(),
                gestores.getGestorRecursos(),
                gestores.getGestorPrestamos()
        );

        // Llamamos al metodo para verificar las alertas de disponibilidad
        alertaDisponibilidad.verificarDisponibilidad();
    }
}

