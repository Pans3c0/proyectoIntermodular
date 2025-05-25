package org.educastur.samuelepv59.TiendaElectronica.model;

import java.io.Serializable;
import java.util.Objects;



public class Usuario implements Serializable {
     private static final long serialVersionUID = 1L; // Versión para serialización
    private long idUsuario;
    private String nombre;
    private String correo;
    private String contrasena; // Contraseña cifrada
    private RolUsuario rol; // Rol general en la iglesia

    /**
     * Constructor para la clase Usuario.
     * @param idUsuario Identificador único del usuario.
     * @param nombre Nombre completo del usuario.
     * @param correo Correo electrónico del usuario (debe ser único).
     * @param contrasena Contraseña cifrada del usuario.
     * @param pastor Rol del usuario (Pastor, Diacono, Tesorero, Participante).
     */
    public Usuario(long idUsuario, String nombre, String correo, String contrasena, RolUsuario rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }


    // Getters
    public long getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public RolUsuario getRol() {
        return rol;
    }

    // Setters
    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "idUsuario=" + idUsuario +
               ", nombre='" + nombre + '\'' +
               ", correo='" + correo + '\'' +
               ", rol=" + rol +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }
}
