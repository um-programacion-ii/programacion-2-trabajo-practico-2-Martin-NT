package interfaces;

/**
   Interfaz que define el contrato para todos los recursos digitales.
   Toda clase que implemente esta interfaz está obligada a definir estos métodos.
 */
public interface RecursoDigital {
    /**
     “Cualquier clase que implemente esta interfaz tiene que proporcionar
     un metodo que devuelva el título, y otro para cambiarlo.”
     */

    // Getters - Métodos para obtener información del recurso
    String getId();
    String getTitulo();
    String getAutor();
    String getFechaPublicacion();
    String getEstado();

    //Setters - Métodos para modificar información del recurso
    void setTitulo(String titulo);
    void setAutor(String autor);
    void setFechaPublicacion(String fechaPublicacion);
    void setEstado(String estado);


    // Metodo común para mostrar información
    String toString();

}
