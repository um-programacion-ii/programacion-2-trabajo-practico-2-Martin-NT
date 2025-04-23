package Simulaciones;

import Gestores.Gestores;
import Gestores.GestorReportes;

public class SimuladorReportes {

    public static void ejecutarReportes(Gestores gestores) {
        System.out.println("\n===== 📊 INICIO DE REPORTES DE PRUEBA =====");

        GestorReportes gestorReportes = gestores.getGestorReportes();

        // Mostrar recursos más prestados
        gestorReportes.mostrarRecursosMasPrestados();

        // Mostrar usuarios más activos
        gestorReportes.mostrarUsuariosMasActivos();

        // Mostrar estadísticas por categoría
        gestorReportes.mostrarEstadisticasPorCategoria();

        System.out.println("\n===== ✅ FIN DE REPORTES DE PRUEBA =====\n");
    }
}
