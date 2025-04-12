import classes.*;
import interfaces.*;
import java.time.LocalDateTime;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        // Crear usuarios de prueba
        Usuario[] usuarios = {
                new Usuario("U001", "Martina Rizzotti", "martirizzotti@example.com"),
                new Usuario("U002", "Valentina Rosales", "valerosales@example.com")
        };

        // Crear recursos de prueba
        RecursoDigital[] recursos = {
                // Libro de ejemplo
                new Libro("L001", "Harry Potter y la piedra filosofal", "J.K. Rowling",
                        LocalDate.of(1997, 6, 26), RecursoBase.EstadoRecurso.DISPONIBLE,
                        LocalDateTime.now().plusDays(10), 256, "Fantasía", "Salamandra"),

                // Revista de ejemplo
                new Revista("R001", "National Geographic", "Varios",
                        LocalDate.of(2025, 4, 10), RecursoBase.EstadoRecurso.DISPONIBLE,
                        LocalDateTime.now().plusDays(7), 100, "Mensual", "Ciencia y naturaleza", "National Geographic Society"),

                // Audiolibro de ejemplo
                new Audiolibro("A001", "El Principito", "Antoine de Saint-Exupéry",
                        LocalDate.of(1943, 4, 6), RecursoBase.EstadoRecurso.DISPONIBLE,
                        LocalDateTime.now().plusDays(7), 92, "Dangello Medina", "Español")
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