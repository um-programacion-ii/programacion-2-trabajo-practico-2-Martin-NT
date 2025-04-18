import java.util.Scanner;
/**
 * Clase encargada exclusivamente de la interacción con el usuario.
 */
public class Consola {
    private final Scanner scanner;

    //Constructor
    public Consola() {
        scanner = new Scanner(System.in);
    }

    //Metodo que muestra el menu principal
    public void mostrarMenuPrincipal() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Listar usuarios");
        System.out.println("2. Listar recursos");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }
    // Metodo que lee la opción ingresada por el usuario y la devuelve como número entero
    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // opción inválida
        }
    }
}
