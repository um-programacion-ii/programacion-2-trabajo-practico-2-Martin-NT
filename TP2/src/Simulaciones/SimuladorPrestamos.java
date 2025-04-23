package Simulaciones;

import Gestores.GestorPrestamos;
import Gestores.GestorRecursos;
import Gestores.GestorUsuarios;
import Interfaces.RecursoDigital;
import Prestamos.Prestamo;
import Usuarios.Usuario;

import java.time.LocalDate;

public class SimuladorPrestamos {
    public static void cargarPrestamos(GestorPrestamos gestorPrestamos, GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos) {
        try {
            // Obtener usuarios
            Usuario u1 = gestorUsuarios.getUsuarios().get("U001");
            Usuario u2 = gestorUsuarios.getUsuarios().get("U002");
            Usuario u3 = gestorUsuarios.getUsuarios().get("U003");

            // Obtener recursos
            RecursoDigital r1 = gestorRecursos.obtenerRecursoPorId("L001");
            RecursoDigital r2 = gestorRecursos.obtenerRecursoPorId("A001");
            RecursoDigital r3 = gestorRecursos.obtenerRecursoPorId("R001");

            // Simular préstamos
            gestorPrestamos.realizarPrestamo(u1, r1);
            gestorPrestamos.realizarPrestamo(u1, r1);
            gestorPrestamos.realizarPrestamo(u1, r1);

            gestorPrestamos.realizarPrestamo(u2, r2);
            gestorPrestamos.realizarPrestamo(u2, r2);

            gestorPrestamos.realizarPrestamo(u3, r3);

        } catch (Exception e) {
            System.out.println("Error cargando préstamos: " + e.getMessage());
        }
    }
}
