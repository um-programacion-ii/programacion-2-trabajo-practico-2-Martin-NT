package Gestores;

import Excepciones.RecursoNoDisponibleException;
import Interfaces.RecursoDigital;
import Prestamos.Prestamo;
import Usuarios.Usuario;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class GestorReportes {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    private final GestorPrestamos gestorPrestamos;
    private final GestorUsuarios gestorUsuarios;
    private final GestorRecursos gestorRecursos;

    public GestorReportes(GestorPrestamos gestorPrestamos, GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos) {
        this.gestorPrestamos = gestorPrestamos;
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;

        // Hook para cerrar hilos al salir
        Runtime.getRuntime().addShutdownHook(new Thread(this::apagar));
    }

    public void apagar() {
        executor.shutdown();
    }

    public void mostrarRecursosMasPrestadosAsync() {
        executor.submit(() -> {
            System.out.println("\n🛠️ Generando reporte: prestados");
            simularProgreso();

            System.out.println("\n📚 Recursos más prestados:");
            Map<String, Long> conteo = gestorPrestamos.getPrestamos().stream()
                    .collect(Collectors.groupingBy(p -> p.getRecurso().getId(), Collectors.counting()));

            conteo.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .forEach(entry -> {
                        try {
                            RecursoDigital recurso = gestorRecursos.obtenerRecursoPorId(entry.getKey());
                            if (recurso != null) {
                                System.out.println("🔸 " + recurso.getTitulo() + " - " + entry.getValue() + " préstamo(s) - Categoría: " + recurso.getCategoria().name());
                            }
                        } catch (RecursoNoDisponibleException e) {
                            System.err.println("\u001B[31m❌ Error al obtener recurso con ID " + entry.getKey() + ": " + e.getMessage() + "\u001B[0m");
                        }
                    });

            System.out.println("🕒 Generado en: " + LocalDateTime.now());
            System.out.println("\n✅ Reporte 'prestados' generado con éxito.\n");
            System.out.print("--> Seleccione una opción del menú: ");
        });
    }

    public void mostrarUsuariosMasActivosAsync() {
        executor.submit(() -> {
            System.out.println("\n🛠️ Generando reporte: usuarios activos");
            simularProgreso();

            System.out.println("\n👥 Usuarios más activos:");
            Map<String, Long> conteo = gestorPrestamos.getPrestamos().stream()
                    .collect(Collectors.groupingBy(p -> p.getUsuario().getId(), Collectors.counting()));

            conteo.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .limit(5)
                    .forEach(entry -> {
                        Usuario usuario = gestorUsuarios.getUsuarios().get(entry.getKey());
                        if (usuario != null) {
                            System.out.println("👤 " + usuario.getNombre() + " " + usuario.getApellido() + " - " + entry.getValue() + " préstamos");
                        }
                    });

            System.out.println("🕒 Generado en: " + LocalDateTime.now());
            System.out.println("\n✅ Reporte 'usuarios activos' generado con éxito.\n");
            System.out.print("--> Seleccione una opción del menú: ");
        });
    }

    public void mostrarEstadisticasPorCategoriaAsync() {
        executor.submit(() -> {
            System.out.println("\n🛠️ Generando reporte: categorías");
            simularProgreso();

            System.out.println("\n📈 Estadísticas por categoría de recurso:");
            Map<String, Long> conteo = gestorPrestamos.getPrestamos().stream()
                    .collect(Collectors.groupingBy(p -> p.getRecurso().getCategoria().name(), Collectors.counting()));

            conteo.forEach((categoria, cantidad) ->
                    System.out.println("📌 " + categoria + ": " + cantidad + " préstamos"));

            System.out.println("🕒 Generado en: " + LocalDateTime.now());
            System.out.println("\n✅ Reporte 'categorías' generado con éxito.\n");
            System.out.print("--> Seleccione una opción del menú: ");
        });
    }

    // Simula progreso con barra visual
    private void simularProgreso() {
        try {
            int total = 3;
            for (int i = 1; i <= total; i++) {
                Thread.sleep(500);
                int porcentaje = i * 100 / total;
                String barra = generarBarraProgreso(porcentaje);
                System.out.println("⏳ Progreso: " + barra + " " + porcentaje + "%");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private String generarBarraProgreso(int porcentaje) {
        int bloques = porcentaje / 10;
        return "[" + "█".repeat(bloques) + "░".repeat(10 - bloques) + "]";
    }
}
