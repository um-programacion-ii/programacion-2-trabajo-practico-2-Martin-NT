package Alertas;
import Gestores.GestorPrestamos;
import Prestamos.Prestamo;
import java.time.LocalDate;
import java.util.List;

public class AlertaVencimiento {
    private GestorPrestamos gestorPrestamos;

    // Constructor para inyectar el GestorPrestamos
    public AlertaVencimiento(GestorPrestamos gestorPrestamos) {
        this.gestorPrestamos = gestorPrestamos;
    }

    // Metodo para monitorear los vencimientos de los préstamos
    public void monitorearVencimientos() {
        List<Prestamo> prestamos = gestorPrestamos.getPrestamos();
        LocalDate hoy = LocalDate.now();
        boolean hayAlertas = false;

        // Iteramos sobre los préstamos
        for (Prestamo prestamo : prestamos) {
            LocalDate fechaDevolucion = prestamo.getFechaDevolucion();

            // Si la fecha de vencimiento es mañana o hoy, se genera una alerta
            if (fechaDevolucion.equals(hoy.plusDays(1))) {
                generarAlerta(prestamo, "Recordatorio: El préstamo vence mañana.");
                hayAlertas = true;
            } else if (fechaDevolucion.equals(hoy)) {
                generarAlerta(prestamo, "¡ALERTA! El préstamo vence hoy.");
                hayAlertas = true;
            }
        }

        // Si no hay alertas generadas, mostrar mensaje
        if (!hayAlertas) {
            System.out.println("✅ No se encontraron préstamos vencidos.");
        }
    }

    // Metodo para generar alertas en consola con formato destacado
    private void generarAlerta(Prestamo prestamo, String mensaje) {
        // Imprimir alerta destacada con color
        String alertaDestacada = "\033[1;31m*** ALERTA ***\033[0m " + mensaje + " Recurso: " + prestamo.getRecurso().getTitulo() + " (Vence el: " + prestamo.getFechaDevolucion() + ")";
        System.out.println(alertaDestacada);

        // Si el préstamo vence hoy, permitir la renovación
        if (mensaje.contains("vence hoy")) {
            // Preguntar si el usuario desea renovar el préstamo
            System.out.println("¿Desea renovar este préstamo? (SI/NO): ");

            // Usar un escáner para leer la respuesta del usuario
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String respuesta = scanner.nextLine();

            // Si la respuesta es 'SI', renovar el préstamo
            if (respuesta.equalsIgnoreCase("SI")) {
                try {
                    // Llamar al metodo para renovar el préstamo
                    gestorPrestamos.renovarPrestamo(prestamo);
                    System.out.println("¡Préstamo renovado exitosamente!");
                } catch (Exception e) {
                    // Si hubo un error al renovar el préstamo, mostrar mensaje
                    System.out.println("⚠️ Error al renovar el préstamo: " + e.getMessage());
                }
            }
        }
    }
}
