package Simulaciones;
import Gestores.GestorUsuarios;
import Usuarios.Usuario;

public class SimuladorUsuarios {
    public static void cargarUsuarios(GestorUsuarios gestorUsuarios) {
        // Crear Usuarios
        Usuario usuario1 = new Usuario("U001", "Martina", "Rizzotti", "martirizzotti@example.com", "martincho15", "2613245789");
        Usuario usuario2 = new Usuario("U002", "Valentina", "Rosales", "valerosales@example.com", "valero1911", "2634257895");
        Usuario usuario3 = new Usuario("U003", "Facundo", "San Roman", "facundo@example.com", "sanroman44", "2634257895");
        Usuario usuario4 = new Usuario("U004", "Valentino", "Rizzotti", "valenrizzotti@example.com", "bianquita10", "2613467543");

        // Agregar Usuarios al Gestor
        gestorUsuarios.agregarUsuario(usuario1);
        gestorUsuarios.agregarUsuario(usuario2);
        gestorUsuarios.agregarUsuario(usuario3);
        gestorUsuarios.agregarUsuario(usuario4);
    }
}
