package org.educastur.samuelepv59.TiendaElectronica.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que registra la asistencia de usuarios a los cultos (opcional).
 * Corresponde a la tabla 'asistencias'.
 */
public class Asistencia implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idAsistencia;
    private long idUsuario; // Clave foránea a Usuario
    private long idCulto; // Clave foránea a Culto

    /**
     * Constructor para la clase Asistencia.
     * @param idAsistencia Identificador único de la asistencia.
     * @param idUsuario ID del usuario que asistió.
     * @param idCulto ID del culto al que asistió.
     */
    public Asistencia(long idAsistencia, long idUsuario, long idCulto) {
        this.idAsistencia = idAsistencia;
        this.idUsuario = idUsuario;
        this.idCulto = idCulto;
    }

    // Getters
    public long getIdAsistencia() {
        return idAsistencia;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public long getIdCulto() {
        return idCulto;
    }

    // Setters
    public void setIdAsistencia(long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdCulto(long idCulto) {
        this.idCulto = idCulto;
    }

    @Override
    public String toString() {
        return "Asistencia{" +
               "idAsistencia=" + idAsistencia +
               ", idUsuario=" + idUsuario +
               ", idCulto=" + idCulto +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asistencia that = (Asistencia) o;
        return idAsistencia == that.idAsistencia;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAsistencia);
    }
}