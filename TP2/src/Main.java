import classes.Usuario;

public class Main {
    public static void main(String[] args) {
        // Crear un usuario
        Usuario u1 = new Usuario("U001", "Martina Rizzotti", "martirizzotti@example.com");
        Usuario u2 = new Usuario("U002", "Valentina Rosales", "valerosales@example.com");

        System.out.println("Usuarios creados:");
        System.out.println(u1);
        System.out.println(u2);

        System.out.println("\nNota: No se puede crear una instancia de model.RecursoDigital directamente porque es abstracta.");
        System.out.println("Se usará como clase base más adelante.");
    }
}
