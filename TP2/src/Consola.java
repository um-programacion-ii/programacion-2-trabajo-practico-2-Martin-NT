import java.util.Scanner;
import java.util.List;
import java.util.Map;
import classes.*;
import interfaces.*;

public class Consola {
    private final Scanner scanner;

    public Consola() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Usuarios");
        System.out.println("2. Recursos");
        System.out.println("3. Salir");
        System.out.print("--> Seleccione una opción: ");
    }

    public void mostrarMenuUsuarios() {
        System.out.println("\n===== MENÚ USUARIOS =====");
        System.out.println("1. Ver usuarios");
        System.out.println("2. Buscar usuario por ID");
        System.out.println("3. Volver al menú principal");
        System.out.print("--> Seleccione una opción: ");
    }

    public void mostrarMenuRecursos() {
        System.out.println("\n===== MENÚ RECURSOS =====");
        System.out.println("1. Mostrar categorías disponibles");
        System.out.println("2. Ver todos los recursos (Se modificara)");
        System.out.println("3. Ver Libros (Se modificara)");
        System.out.println("4. Ver Audiolibros (Se modificara)");
        System.out.println("5. Ver Revistas (Se modificara)");
        System.out.println("6. Buscar recurso por título (Se modificara)");
        System.out.println("7. Prestar recurso (no implementado)");
        System.out.println("8. Renovar recurso (no implementado)");
        System.out.println("9. Volver al menú principal");
        System.out.print("--> Seleccione una opción: ");
    }

    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String leerTexto() {
        return scanner.nextLine();
    }

    // Se implementará en los Gestores correspondientes
    public static void mostrarUsuarios(Map<String, Usuario> usuarios) {
        System.out.println("\n📋 Lista de usuarios:");
        for (Usuario u : usuarios.values()) {
            System.out.println("\n" + u);
        }
    }

    public static void mostrarRecursos(List<RecursoDigital> recursos) {
        System.out.println("\n📚 Recursos disponibles:");
        for (RecursoDigital r : recursos) {
            System.out.println("\n" + r);
        }
    }

    public static void mostrarLibros(List<RecursoDigital> recursos) {
        System.out.println("\n📖 Libros disponibles:");
        for (RecursoDigital r : recursos) {
            if (r instanceof Libro) {
                System.out.println("\n" + r);
            }
        }
    }

    public static void mostrarAudiolibros(List<RecursoDigital> recursos) {
        System.out.println("\n🎧 Audiolibros disponibles:");
        for (RecursoDigital r : recursos) {
            if (r instanceof Audiolibro) {
                System.out.println("\n" + r);
            }
        }
    }

    public static void mostrarRevistas(List<RecursoDigital> recursos) {
        System.out.println("\n📰 Revistas disponibles:");
        for (RecursoDigital r : recursos) {
            if (r instanceof Revista) {
                System.out.println("\n" + r);
            }
        }
    }
}
