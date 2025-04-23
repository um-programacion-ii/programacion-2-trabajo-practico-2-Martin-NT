package app;

public class Menus {
    public void MenuPrincipal() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Usuarios");
        System.out.println("2. Recursos");
        System.out.println("3. Reservas");
        System.out.println("4. Préstamos");
        System.out.println("5. Reportes");
        System.out.println("6. Ver Historial Recordatorios");
        System.out.println("7. Pruebas");
        System.out.println("8. Salir");
        System.out.print("--> Seleccione una opción: ");
    }

    public void MenuUsuarios() {
        System.out.println("\n===== MENÚ USUARIOS =====");
        System.out.println("1. Listar usuarios");
        System.out.println("2. Buscar usuarios");
        System.out.println("3. Ordenar usuarios");
        System.out.println("4. Crear usuario");
        System.out.println("5. Eliminar usuario");
        System.out.println("6. Volver al menú principal");
        System.out.print("--> Seleccione una opción: ");
    }

    public void MenuBusquedaUsuarios() {
        System.out.println("\n===== 🔍 MENÚ DE BÚSQUEDA DE USUARIOS =====");
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por Nombre");
        System.out.println("3. Buscar por Apellido");
        System.out.println("4. Volver al Menú de Usuarios");
        System.out.print("--> Seleccione una opción: ");

    }

    public void MenuOrdenarUsuarios() {
        System.out.println("\n===== 📊 ORDENAR USUARIOS =====");
        System.out.println("1. Ordenar por Nombre (A-Z)");
        System.out.println("2. Ordenar por Apellido (A-Z)");
        System.out.println("3. Volver al Menú de Usuarios");
        System.out.print("--> Seleccione una opción: ");

    }

    public void MenuRecursos() {
        System.out.println("\n===== MENÚ RECURSOS =====");
        System.out.println("1. Listar Recursos");
        System.out.println("2. Mostrar categorías");
        System.out.println("3. Buscar Recurso");
        System.out.println("4. Ordenar Recursos"); //menu con opciones de como ordenar recursos
        System.out.println("5. Crear Recurso");
        System.out.println("6. Eliminar Recurso");
        System.out.println("7. Volver al menú principal");
        System.out.print("--> Seleccione una opción: ");
    }

    public void MenuFiltradoPorCategoria() {
        System.out.println("\n===== 📂 FILTRAR POR CATEGORIA DE RECURSO =====");
        System.out.println("1. Listar Libros");
        System.out.println("2. Listar Audiolibros");
        System.out.println("3. Listar Revistas");
        System.out.println("4. Volver al Menú de Recursos");
        System.out.print("--> Seleccione una opción: ");

    }

    public void MenuBusquedaRecursos() {
        System.out.println("\n===== 🔍 MENÚ DE BÚSQUEDA DE RECURSOS =====");
        System.out.println("1. Buscar por ID");
        System.out.println("2. Buscar por Título");
        System.out.println("3. Buscar por Categoría");
        System.out.println("4. Volver al Menú de Recursos");
        System.out.print("--> Seleccione una opción: ");

    }

    public void MenuOrdenarRecursos() {
        System.out.println("\n===== 📊 ORDENAR RECURSOS =====");
        System.out.println("1. Ordenar por Título (A-Z)");
        System.out.println("2. Ordenar por Fecha de Publicación (Más recientes primero)");
        System.out.println("3. Volver al Menú de Recursos");
        System.out.print("--> Seleccione una opción: ");

    }

    public void MenuCrearRecurso() {
        System.out.println("\n===== ➕ CREAR NUEVO RECURSO =====");
        System.out.println("1. Libro");
        System.out.println("2. Audiolibro");
        System.out.println("3. Revista");
        System.out.println("4. Volver al Menú de Recursos");
        System.out.print("Seleccione el tipo de recurso: ");
    }

    public void MenuPrestamos() {
        System.out.println("\n===== MENÚ DE PRÉSTAMOS =====");
        System.out.println("1. Realizar préstamo");
        System.out.println("2. Ver préstamos activos");
        System.out.println("3. Devolver recurso");
        System.out.println("4. Buscar préstamos");
        System.out.println("5. Ordenar préstamos");
        System.out.println("6. Ver alertas de vencimiento");  // Opción para ver alertas de vencimiento
        System.out.println("7. Volver al menú principal");
        System.out.print("--> Seleccione una opción: ");
    }

    public void MenuBuscarPrestamos() {
        System.out.println("\n===== MENÚ DE BÚSQUEDA DE PRÉSTAMOS =====");
        System.out.println("1. Buscar por ID de usuario");
        System.out.println("2. Buscar por ID de recurso");
        System.out.println("3. Buscar por fecha de préstamo");
        System.out.println("4. Volver al menú anterior");
        System.out.print("--> Seleccione una opción: ");
    }

    public void MenuOrdenarPrestamos() {
        System.out.println("\n===== MENÚ DE ORDENAR PRÉSTAMOS =====");
        System.out.println("1. Ordenar por ID de usuario");
        System.out.println("2. Ordenar por fecha de préstamo");
        System.out.println("3. Ordenar por ID de recurso");
        System.out.println("4. Volver al menú anterior");
        System.out.print("--> Seleccione una opción: ");
    }

    public void MenuReservas() {
        System.out.println("\n===== MENÚ DE RESERVAS =====");
        System.out.println("1. Reservar recurso");
        System.out.println("2. Ver reservas pendientes");
        System.out.println("3. Eliminar reserva");
        System.out.println("4. Buscar reservas");
        System.out.println("5. Ordenar reservas");
        System.out.println("6. Ver alertas de disponibilidad");
        System.out.println("7. Volver al menú principal");
        System.out.print("--> Seleccione una opción: ");
    }

    public void MenuBuscarReservas() {
        System.out.println("\n===== MENÚ DE BÚSQUEDA DE RESERVAS =====");
        System.out.println("1. Buscar por id de usuario");
        System.out.println("2. Buscar por id de recurso");
        System.out.println("3. Buscar por prioridad");
        System.out.println("4. Buscar por fecha");
        System.out.println("5. Volver al menú anterior");
        System.out.print("--> Seleccione una opción: ");
    }

    public void MenuOrdenarReservas() {
        System.out.println("\n===== MENÚ DE ORDENAR RESERVAS =====");
        System.out.println("1. Ordenar por prioridad");
        System.out.println("2. Ordenar por fecha");
        System.out.println("3. Ordenar por id de usuario");
        System.out.println("4. Volver al menú anterior");
        System.out.print("--> Seleccione una opción: ");
    }

    public void MenuReportes() {
        System.out.println("\n===== 📊 REPORTES DISPONIBLES =====");
        System.out.println("1. Recursos más prestados");
        System.out.println("2. Usuarios más activos");
        System.out.println("3. Estadísticas por categoría");
        System.out.println("4. Volver al menú principal");
        System.out.print("--> Seleccione una opción: ");
    }
}
