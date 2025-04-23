package Comparadores;
import Usuarios.Usuario;
import java.util.Comparator;
// Se aprendio a hacer los comparadores con chatgpt

public class ComparadorUsuario {
    // Comparador para ordenar por nombre (A-Z, sin distinguir mayúsculas y minúsculas)
    public static class OrdenarPorNombre implements Comparator<Usuario> {
        @Override
        public int compare(Usuario u1, Usuario u2) {
            return u1.getNombre().compareToIgnoreCase(u2.getNombre());
        }
    }

    // Comparador para ordenar por apellido (A-Z, sin distinguir mayúsculas y minúsculas)
    public static class OrdenarPorApellido implements Comparator<Usuario> {
        @Override
        public int compare(Usuario u1, Usuario u2) {
            return u1.getApellido().compareToIgnoreCase(u2.getApellido());
        }
    }
}
