import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Map;
import classes.*;
import interfaces.*;
import Enum.*;

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
        System.out.println("1. Listar usuarios");
        System.out.println("2. Buscar usuarios");
        System.out.println("3. Ordenar usuarios");
        System.out.println("4. Crear usuario");
        System.out.println("5. Eliminar usuario");
        System.out.println("6. Volver al menú principal");
        System.out.print("--> Seleccione una opción: ");
    }

    public void mostrarMenuBusquedaUsuarios() {
        System.out.println("\n===== 🔍 MENÚ DE BÚSQUEDA DE USUARIOS =====");
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por Nombre");
        System.out.println("3. Buscar por Apellido");
        System.out.println("4. Volver al Menú de Usuarios");
        System.out.print("--> Seleccione una opción: ");

    }

    public void mostrarMenuOrdenarUsuarios() {
        System.out.println("\n===== 📊 ORDENAR USUARIOS =====");
        System.out.println("1. Ordenar por Nombre (A-Z)");
        System.out.println("2. Ordenar por Apellido (A-Z)");
        System.out.println("3. Volver al Menú de Usuarios");
        System.out.print("--> Seleccione una opción: ");

    }

    public void mostrarMenuRecursos() {
        System.out.println("\n===== MENÚ RECURSOS =====");
        System.out.println("1. Listar Recursos");
        System.out.println("2. Mostrar categorías"); // que se muestren las categorias disponibles y menu para ver los recursos filtrados (solo libros,etc)
        System.out.println("3. Buscar Recurso"); // otro menu con opciones de como buscar
        System.out.println("4. Ordenar Recursos"); //menu con opciones de como ordenar recursos
        System.out.println("5. Crear Recurso");
        System.out.println("6. Eliminar Recurso");
        System.out.println("7. Volver al menú principal");
        System.out.print("--> Seleccione una opción: ");
    }

    public void mostrarMenuFiltradoPorCategoria() {
        System.out.println("\n===== 📂 FILTRAR POR CATEGORIA DE RECURSO =====");
        System.out.println("1. Ver Libros");
        System.out.println("2. Ver Audiolibros");
        System.out.println("3. Ver Revistas");
        System.out.println("4. Volver al Menú de Recursos");
        System.out.print("--> Seleccione una opción: ");

    }

    public void mostrarMenuBusquedaRecursos() {
        System.out.println("\n===== 🔍 MENÚ DE BÚSQUEDA DE RECURSOS =====");
        System.out.println("1. Buscar por Título");
        System.out.println("2. Buscar por Categoría");
        System.out.println("3. Volver al Menú de Recursos");
        System.out.print("--> Seleccione una opción: ");

    }

    public void mostrarMenuOrdenarRecursos() {
        System.out.println("\n===== 📊 ORDENAR RECURSOS =====");
        System.out.println("1. Ordenar por Título (A-Z)");
        System.out.println("2. Ordenar por Fecha de Publicación (Más recientes primero)");
        System.out.println("3. Volver al Menú de Recursos");
        System.out.print("--> Seleccione una opción: ");

    }

    public void mostrarMenuCrearRecurso() {
        System.out.println("\n===== ➕ CREAR NUEVO RECURSO =====");
        System.out.println("1. Libro");
        System.out.println("2. Audiolibro");
        System.out.println("3. Revista");
        System.out.println("4. Volver al Menú de Recursos");
        System.out.print("Seleccione el tipo de recurso: ");
    }

    public void crearRecurso(int tipoSeleccionado, GestorRecursos gestor) {
        System.out.print("🆔 Ingrese el ID: ");
        String id = scanner.nextLine();

        System.out.print("📖 Ingrese el título: ");
        String titulo = scanner.nextLine();

        System.out.print("✍️ Ingrese el autor: ");
        String autor = scanner.nextLine();

        System.out.print("📅 Ingrese la fecha de publicación (YYYY-MM-DD): ");
        LocalDate fechaPublicacion = LocalDate.parse(scanner.nextLine());

        EstadoRecurso estado = EstadoRecurso.DISPONIBLE;
        LocalDateTime fechaDevolucion = LocalDateTime.now().plusDays(10);
        RecursoDigital recurso = null;

        switch (tipoSeleccionado) {
            case 1: // Libro
                System.out.print("📄 Ingrese el número de páginas: ");
                int paginas = scanner.nextInt();
                scanner.nextLine();

                System.out.print("🎭 Ingrese el género: ");
                String genero = scanner.nextLine();

                System.out.print("🏢 Ingrese la editorial: ");
                String editorial = scanner.nextLine();

                recurso = new Libro(id, titulo, autor, fechaPublicacion, estado, fechaDevolucion,
                        CategoriaRecurso.LIBRO, paginas, genero, editorial);
                break;

            case 2: // Audiolibro
                System.out.print("⏱️ Ingrese la duración en minutos: ");
                int duracion = scanner.nextInt();
                scanner.nextLine();

                System.out.print("🎙️ Ingrese el narrador: ");
                String narrador = scanner.nextLine();

                System.out.print("🌍 Ingrese el idioma: ");
                String idioma = scanner.nextLine();

                recurso = new Audiolibro(id, titulo, autor, fechaPublicacion, estado, fechaDevolucion,
                        CategoriaRecurso.AUDIOLIBRO, duracion, narrador, idioma);
                break;

            case 3: // Revista
                System.out.print("📅 Ingrese el número de edición: ");
                int edicion = scanner.nextInt();
                scanner.nextLine();

                System.out.print("📆 Ingrese la periodicidad: ");
                String periodicidad = scanner.nextLine();

                System.out.print("🗞️ Ingrese la sección principal: ");
                String seccion = scanner.nextLine();

                System.out.print("🏢 Ingrese la editorial: ");
                String editorialRevista = scanner.nextLine();

                recurso = new Revista(id, titulo, autor, fechaPublicacion, estado, fechaDevolucion,
                        CategoriaRecurso.REVISTA, edicion, periodicidad, seccion, editorialRevista);
                break;

            case 4:
                System.out.println("↩️ Volviendo al Menú de Recursos...");
                break;

            default:
                System.out.println("❌ Tipo de recurso no válido.");
        }

        // Si se ha creado un recurso válido, agregarlo al gestor
        if (recurso != null) {
            gestor.agregarRecurso(recurso);
            System.out.println("✅ Recurso agregado exitosamente.");
        }
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

}
