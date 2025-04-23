package Usuarios;

import Enums.NivelUrgencia;

public class Usuario {
    private final String id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;
    private NivelUrgencia nivelPreferido = NivelUrgencia.INFO;
    private String frecuenciaNotificaciones;


    //Constructor
    public Usuario(String id, String nombre, String apellido, String email, String password, String telefono, String frecuenciaNotificaciones) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.frecuenciaNotificaciones = frecuenciaNotificaciones;
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
    public NivelUrgencia getNivelPreferido() {return nivelPreferido;}
    public String getFrecuenciaNotificaciones() {return frecuenciaNotificaciones;}

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
    public void setNivelPreferido(NivelUrgencia nivelPreferido) {this.nivelPreferido = nivelPreferido;}
    public void setFrecuenciaNotificaciones(String frecuenciaNotificaciones) {
        this.frecuenciaNotificaciones = frecuenciaNotificaciones;
    }

    // Metodo para representar al usuario como string
    @Override
    public String toString() {
        return "📘 Usuario ID: " + id + "\n" +
                " - Nombre: " + nombre + "\n" +
                " - Apellido: " + apellido + "\n" +
                " - Email: " + email + "\n" +
                " - Telefono: " + telefono + "\n" +
                " - Frecuencia de Notificación: " + frecuenciaNotificaciones;
    }

}
