import classes.*;
import interfaces.*;

public class Main {
    public static void main(String[] args) {
        // Crear usuarios de prueba
        Usuario[] usuarios = {
                new Usuario("U001", "Martina Rizzotti", "martirizzotti@example.com"),
                new Usuario("U002", "Valentina Rosales", "valerosales@example.com")
        };

        // Crear recursos de prueba
        RecursoDigital[] recursos = {
                new Libro("L001", "Harry Potter", "J.K. Rowling", "2001-09-11", "Disponible", 256, "Fantasía", "Salamandra"),
                new Revista("R001", "National Geographic", "Varios", "2025-04-10", "Disponible", 12, "Semanal", "Naturaleza", "NG Media"),
                new Audiolibro("A001", "El Principito", "Antoine de Saint-Exupéry", "1943-04-06", "Disponible", 92, "Dangello Medina", "Español")
        };

        Consola consola = new Consola();
        int opcionPrincipal;

        do {
            consola.mostrarMenuPrincipal();
            opcionPrincipal = consola.leerOpcion();

            switch (opcionPrincipal) {
                case 1:
                    int opcionUsuarios;
                    do {
                        consola.mostrarMenuUsuarios();
                        opcionUsuarios = consola.leerOpcion();
                        switch (opcionUsuarios) {
                            case 1:
                                Consola.mostrarUsuarios(usuarios);
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("⚠️ Opción inválida.");
                        }
                    } while (opcionUsuarios != 2);
                    break;

                case 2:
                    int opcionRecursos;
                    do {
                        consola.mostrarMenuRecursos();
                        opcionRecursos = consola.leerOpcion();
                        switch (opcionRecursos) {
                            case 1:
                                Consola.mostrarRecursos(recursos);
                                break;
                            case 2:
                                break;
                            default:
                                System.out.println("⚠️ Opción inválida.");
                        }
                    } while (opcionRecursos != 2);
                    break;

                case 3:
                    System.out.println("👋 Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcionPrincipal != 3);
    }
}

// Nota: Se aprendio a usar Scanner con ChatGPT