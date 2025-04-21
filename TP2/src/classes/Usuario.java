package classes;

public class Usuario {
    private final String id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;

    //Constructor
    public Usuario(String id, String nombre, String apellido, String email, String password, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }

    //Getters
    public String getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getTelefono() {
        return telefono;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // Metodo para representar al usuario como string
    @Override
    public String toString() {
        return "📘 Usuario ID: " + id + "\n" +
                " - Nombre: " + nombre + "\n" +
                " - Apellido: " + apellido + "\n" +
                " - Email: " + email + "\n" +
                " - Telefono: " + telefono;
    }

}
