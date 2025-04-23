package Simulaciones;

import Gestores.Gestores;
import Gestores.GestorReportes;

public class SimuladorReportes {

    public static void ejecutarReportes(Gestores gestores) {
        System.out.println("\n===== 📊 INICIO DE REPORTES DE PRUEBA =====");

        GestorReportes gestorReportes = gestores.getGestorReportes();

        // Mostrar recursos más prestados
        gestorReportes.mostrarRecursosMasPrestadosAsync();

        // Mostrar usuarios más activos
        gestorReportes.mostrarUsuariosMasActivosAsync();

        // Mostrar estadísticas por categoría
        gestorReportes.mostrarEstadisticasPorCategoriaAsync();

        System.out.println("\n===== ✅ FIN DE REPORTES DE PRUEBA =====\n");
    }
}
