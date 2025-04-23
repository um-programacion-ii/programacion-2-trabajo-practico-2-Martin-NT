package Alertas;
import Enums.NivelUrgencia;
import Interfaces.RecursoDigital;
import Usuarios.Usuario;

import java.time.LocalDate;

public class Recordatorio {
    public String mensaje;
    private LocalDate fecha;
    private NivelUrgencia nivelUrgencia;
    private Usuario usuario;
    private RecursoDigital recurso;


    // Constructor
    public Recordatorio(String mensaje, NivelUrgencia nivelUrgencia, Usuario usuario, RecursoDigital recurso) {
        this.mensaje = mensaje;
        this.fecha = LocalDate.now();
        this.nivelUrgencia = nivelUrgencia;
        this.usuario = usuario;
        this.recurso = recurso;
    }

    //Getters
    public String getMensaje() {
        return mensaje;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public NivelUrgencia getNivelUrgencia() {
        return nivelUrgencia;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public RecursoDigital getRecurso() {
        return recurso;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Recordatorio [").append(fecha).append("] [").append(nivelUrgencia).append("] ");
        sb.append(mensaje);

        if (usuario != null) {
            sb.append(" | Usuario: ").append(usuario.getNombre()).append(" ").append(usuario.getApellido());
        }

        if (recurso != null) {
            sb.append(" | Recurso: ").append(recurso.getTitulo());
        }

        return sb.toString();
    }



}
