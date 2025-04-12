import java.util.Scanner;
import classes.*;
import interfaces.*;
/**
 * Clase encargada exclusivamente de la interacción con el usuario.
 */
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
        System.out.println("2. Volver al menú principal");
        System.out.print("--> Seleccione una opción: ");
    }

    public void mostrarMenuRecursos() {
        System.out.println("\n===== MENÚ RECURSOS =====");
        System.out.println("1. Ver todos los recursos");
        System.out.println("2. Ver Libros");
        System.out.println("3. Ver Audiolibros");
        System.out.println("4. Ver Revistas");
        System.out.println("5. Prestar recurso (no implementado)");
        System.out.println("6. Renovar recurso (no implementado)");
        System.out.println("7. Volver al menú principal");
        System.out.print("--> Seleccione una opción: ");
    }

    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void mostrarUsuarios(Usuario[] usuarios) {
        System.out.println("\n📋 Lista de usuarios:");
        for (Usuario u : usuarios) {
            System.out.println("\n" + u);
        }
    }

    public static void mostrarRecursos(RecursoDigital[] recursos) {
        System.out.println("\n📚 Recursos disponibles:");
        for (RecursoDigital r : recursos) {
            System.out.println("\n" + r);
        }
    }

    public static void mostrarLibros(RecursoDigital[] recursos) {
        System.out.println("\n📖 Libros disponibles:");
        for (RecursoDigital r : recursos) {
            if (r instanceof Libro) {
                System.out.println("\n" + r);
            }
        }
    }

    public static void mostrarAudiolibros(RecursoDigital[] recursos) {
        System.out.println("\n🎧 Audiolibros disponibles:");
        for (RecursoDigital r : recursos) {
            if (r instanceof Audiolibro) {
                System.out.println("\n" + r);
            }
        }
    }

    public static void mostrarRevistas(RecursoDigital[] recursos) {
        System.out.println("\n📰 Revistas disponibles:");
        for (RecursoDigital r : recursos) {
            if (r instanceof Revista) {
                System.out.println("\n" + r);
            }
        }
    }

}

// Nota: Se aprendio a usar Scanner con ChatGPT