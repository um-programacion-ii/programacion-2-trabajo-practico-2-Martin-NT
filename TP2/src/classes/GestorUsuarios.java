package classes;
import Comparadores.ComparadorUsuario;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Clase responsable de gestionar operaciones relacionadas con usuarios.
 */
public class GestorUsuarios {
    // Mapa para almacenar usuarios con su ID como clave
    private Map<String, Usuario> usuarios;

    // Constructor que inicializa el mapa de usuarios
    public GestorUsuarios() {
        usuarios = new HashMap<>();
    }

    // Getter que devuelve el mapa completo de usuarios
    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    // Metodo para agregar un nuevo usuario al mapa
    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    // Metodo para eliminar un usuario usando su ID
    public void eliminarUsuario(String id) {
        usuarios.remove(id);
    }

    // Metodo que devuelve un usuario por su ID
    public Usuario obtenerUsuarioPorId(String id) {
        return usuarios.get(id);
    }

    // Metodo que verifica si un usuario existe
    public boolean existeUsuario(String id) {
        return usuarios.containsKey(id);
    }

    // Metodo para mostrar todos los usuarios
    public void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("\n⚠️ No hay usuarios registrados.");
        } else {
            System.out.println("\n📋 Lista de usuarios:");
            for (Usuario u : usuarios.values()) {
                System.out.println("\n" + u);
            }
        }
    }

    // Metodo para mostrar los usuarios filtrados
    public void mostrarUsuariosFiltrados(List<Usuario> usuariosFiltrados) {
        if (usuariosFiltrados.isEmpty()) {
            System.out.println("⚠️ No se encontraron usuarios con esos criterios.");
        } else {
            System.out.println("\n👤 Usuarios encontrados:");
            for (Usuario usuario : usuariosFiltrados) {
                System.out.println(usuario);
                System.out.println("----------------------------------");
            }
        }
    }

    // Metodo para buscar usuarios por ID
    public void buscarPorId(String id) {
        List<Usuario> resultados = usuarios.values().stream()
                .filter(u -> u.getId().toLowerCase().contains(id.toLowerCase()))
                .collect(Collectors.toList());

        mostrarUsuariosFiltrados(resultados);
    }


    // Metodo para buscar usuario por nombre
    public void buscarPorNombre(String nombre) {
        List<Usuario> resultados = usuarios.values().stream()
                .filter(usuario -> usuario.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());

        mostrarUsuariosFiltrados(resultados);
    }

    // Metodo para buscar usuario por apellido
    public void buscarPorApellido(String apellido) {
        List<Usuario> resultados = usuarios.values().stream()
                .filter(usuario -> usuario.getApellido().toLowerCase().contains(apellido.toLowerCase()))
                .collect(Collectors.toList());

        mostrarUsuariosFiltrados(resultados);
    }

    // Metodo para ordenar usuarios por nombre
    public void ordenarPorNombre() {
        List<Usuario> listaUsuarios = new ArrayList<>(usuarios.values());
        Collections.sort(listaUsuarios, new ComparadorUsuario.OrdenarPorNombre());
        System.out.println("\nUsuarios ordenados por nombre:");
        for (Usuario usuario : listaUsuarios) {
            System.out.println(usuario);
        }
    }

    // Metodo para ordenar usuarios por apellido
    public void ordenarPorApellido() {
        List<Usuario> listaUsuarios = new ArrayList<>(usuarios.values());
        Collections.sort(listaUsuarios, new ComparadorUsuario.OrdenarPorApellido());
        System.out.println("\nUsuarios ordenados por apellido:");
        for (Usuario usuario : listaUsuarios) {
            System.out.println(usuario);
        }
    }
}

