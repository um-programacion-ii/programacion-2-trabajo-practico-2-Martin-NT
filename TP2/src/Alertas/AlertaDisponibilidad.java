package Alertas;

import Gestores.GestorReservas;
import Gestores.GestorRecursos;
import Gestores.GestorPrestamos;
import Interfaces.RecursoDigital;
import Recursos.RecursoBase;
import Reservas.Reserva;
import Usuarios.Usuario;

import java.util.List;

public class AlertaDisponibilidad {
    private GestorReservas gestorReservas;
    private GestorRecursos gestorRecursos;
    private GestorPrestamos gestorPrestamos;

    // Constructor para inyectar gestores necesarios
    public AlertaDisponibilidad(GestorReservas gestorReservas, GestorRecursos gestorRecursos, GestorPrestamos gestorPrestamos) {
        this.gestorReservas = gestorReservas;
        this.gestorRecursos = gestorRecursos;
        this.gestorPrestamos = gestorPrestamos;
    }

    // Metodo principal para verificar recursos reservados disponibles
    public void verificarDisponibilidad() {
        List<Reserva> reservas = gestorReservas.getReservas();
        boolean hayAlertas = false;

        for (Reserva reserva : reservas) {
            // Verificamos que el recurso sea de tipo RecursoBase
            if (reserva.getRecurso() instanceof RecursoBase) {
                RecursoBase recurso = (RecursoBase) reserva.getRecurso();
                Usuario usuario = reserva.getUsuario();

                // Verificamos si el recurso está disponible
                if (recurso.estaDisponible()) {
                    hayAlertas = true;
                    generarAlerta(recurso, usuario, reserva);
                }
            }
        }

        if (!hayAlertas) {
            System.out.println("✅ No hay recursos reservados disponibles actualmente.");
        }
    }

    // Metodo para generar alertas de disponibilidad con opción a préstamo
    private void generarAlerta(RecursoDigital recurso, Usuario usuario, Reserva reserva) {
        // Mostrar mensaje destacado en consola
        String alerta = "\033[1;34m*** DISPONIBLE ***\033[0m Recurso: " + recurso.getTitulo() + " - Reservado por: " + usuario.getNombre() + " " + usuario.getApellido();
        System.out.println(alerta);

        // Preguntar si el usuario desea realizar el préstamo
        System.out.println("¿Desea realizar el préstamo ahora? (SI/NO):");

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("SI")) {
            try {
                gestorPrestamos.realizarPrestamo(usuario, recurso);
                gestorReservas.eliminarReserva(reserva.getRecurso().getId());
                System.out.println("✅ Préstamo realizado con éxito.");
            } catch (Exception e) {
                System.out.println("⚠️ No se pudo realizar el préstamo: " + e.getMessage());
            }
        } else {
            System.out.println("🔕 El préstamo no fue realizado.");
        }

        System.out.println("--------------------------------------------");
    }
}
