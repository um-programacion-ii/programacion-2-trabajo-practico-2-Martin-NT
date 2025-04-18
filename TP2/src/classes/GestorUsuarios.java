package classes;
import java.util.HashMap;
import java.util.Map;
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

}
