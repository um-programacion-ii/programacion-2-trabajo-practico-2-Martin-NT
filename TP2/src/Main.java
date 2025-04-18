import classes.Usuario;

public class Main {
    public static void main(String[] args) {
        // Crear algunos usuarios de prueba
        Usuario u1 = new Usuario("U001", "Martina Rizzotti", "martirizzotti@example.com");
        Usuario u2 = new Usuario("U002", "Valentina Rosales", "valerosales@example.com");

        // Crear instancia de la clase Consola, que mostrará el menú e interactuará con el usuario
        Consola consola = new Consola();

        int opcion;
        do {
            // Mostrar el menú principal de opciones
            consola.mostrarMenuPrincipal();
            // Leer la opción elegida por el usuario desde la consola
            opcion = consola.leerOpcion();

            // Procesar la opción seleccionada utilizando una estructura switch
            switch (opcion) {
                case 1:
                    System.out.println("\n📋 Lista de usuarios:");
                    System.out.println("--> Usuarios creados de prueba");
                    System.out.println(u1);
                    System.out.println(u2);
                    break;
                case 2:
                    System.out.println("\n📚 Aún no hay recursos disponibles.");
                    System.out.println("\nNota: No se puede crear una instancia de model.RecursoDigital directamente porque es abstracta.");
                    System.out.println("Se usará como clase base más adelante.");
                    break;
                case 3:
                    System.out.println("👋 Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("⚠️ Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 3); // Repite mientras no se elija salir
    }
}
