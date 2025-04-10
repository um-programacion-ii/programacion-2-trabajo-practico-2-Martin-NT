package classes;

public class Usuario {
    private final String id;
    private String nombre;
    private String email;

    //Constructor
    public Usuario(String id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    //Getters
    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }

    // Setters (permiten modificar nombre y email, no el id)
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Metodo para representar al usuario como string
    @Override
    public String toString() {
        return "📘 model.Usuario: " + id + "\n" +
                " - Nombre: " + nombre + "\n" +
                " - Email: " + email;
    }

}
