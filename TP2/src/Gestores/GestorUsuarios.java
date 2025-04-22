package Gestores;
import Comparadores.ComparadorUsuario;
import Excepciones.UsuarioNoEncontradoException;
import Usuarios.Usuario;
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
    public void eliminarUsuario(String id) throws UsuarioNoEncontradoException {
        if (!usuarios.containsKey(id)) {
            throw new UsuarioNoEncontradoException("❌ No se puede eliminar. El usuario con ID " + id + " no existe.");
        }
        usuarios.remove(id);
    }

    // Metodo que devuelve un usuario por su ID
    public Usuario obtenerUsuarioPorId(String id) throws UsuarioNoEncontradoException {
        Usuario usuario = usuarios.get(id);
        if (usuario == null) {
            throw new UsuarioNoEncontradoException("❌ No se encontró un usuario con el ID: " + id);
        }
        return usuario;
    }

    // Metodo que verifica si un usuario existe
    public boolean existeUsuario(String id) {
        return usuarios.containsKey(id);
    }

    // Metodo para mostrar todos los usuarios
    public void mostrarUsuarios() throws UsuarioNoEncontradoException {
        if (usuarios.isEmpty()) {
            throw new UsuarioNoEncontradoException("\n⚠️ No hay usuarios registrados.");
        } else {
            System.out.println("\n📋 Lista de usuarios:");
            for (Usuario u : usuarios.values()) {
                System.out.println("\n" + u);
            }
        }
    }

    // Metodo para mostrar los usuarios filtrados
    public void mostrarUsuariosFiltrados(List<Usuario> usuariosFiltrados) throws UsuarioNoEncontradoException {
        if (usuariosFiltrados.isEmpty()) {
            throw new UsuarioNoEncontradoException("\n⚠️ No se encontraron usuarios con esos criterios.");
        } else {
            System.out.println("\n👤 Usuarios encontrados:");
            for (Usuario usuario : usuariosFiltrados) {
                System.out.println("\n" + usuario);
            }
        }
    }

    // Metodo para buscar usuarios por ID
    public void buscarPorId(String id) throws UsuarioNoEncontradoException {
        List<Usuario> resultados = usuarios.values().stream()
                .filter(u -> u.getId().toLowerCase().contains(id.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\n--> Usuario buscado por id");
        mostrarUsuariosFiltrados(resultados);
    }


    // Metodo para buscar usuario por nombre
    public void buscarPorNombre(String nombre) throws UsuarioNoEncontradoException {
        List<Usuario> resultados = usuarios.values().stream()
                .filter(usuario -> usuario.getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());
        System.out.println("\n--> Usuarios buscados por nombre");
        mostrarUsuariosFiltrados(resultados);
    }

    // Metodo para buscar usuario por apellido
    public void buscarPorApellido(String apellido) throws UsuarioNoEncontradoException {
        List<Usuario> resultados = usuarios.values().stream()
                .filter(usuario -> usuario.getApellido().equalsIgnoreCase(apellido))
                .collect(Collectors.toList());
        System.out.println("\n--> Usuarios buscados por apellido");
        mostrarUsuariosFiltrados(resultados);
    }

    // Metodo para ordenar usuarios por nombre
    public void ordenarPorNombre() throws UsuarioNoEncontradoException {
        List<Usuario> listaUsuarios = new ArrayList<>(usuarios.values());
        listaUsuarios.sort(new ComparadorUsuario.OrdenarPorNombre());
        System.out.println("\n--> Usuarios ordenados por nombre");
        mostrarUsuariosFiltrados(listaUsuarios);  // Mostrar usuarios filtrados
    }


    // Metodo para ordenar usuarios por apellido
    public void ordenarPorApellido() throws UsuarioNoEncontradoException {
        List<Usuario> listaUsuarios = new ArrayList<>(usuarios.values());
        listaUsuarios.sort(new ComparadorUsuario.OrdenarPorApellido());
        System.out.println("\n--> Usuarios ordenados por apellido");
        mostrarUsuariosFiltrados(listaUsuarios);  // Mostrar usuarios filtrados
    }

}

