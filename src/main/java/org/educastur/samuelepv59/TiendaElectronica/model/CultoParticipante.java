package org.educastur.samuelepv59.TiendaElectronica.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase de unión que relaciona cultos con usuarios que participan en un servicio específico.
 * Corresponde a la tabla 'cultos_participantes' (anteriormente 'cultos_participantes').
 * Ahora, en lugar de un 'Participante' genérico, se referencia directamente a un 'Usuario'
 * y se especifica el rol que ese usuario desempeña en ESE culto en particular.
 */
public class CultoParticipante implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id; // Identificador de la participación específica en este culto
    private long idCulto; // Clave foránea a Culto
    private long idUsuario; // Clave foránea a Usuario (quien participa en el servicio)
    private String rolServicio; // Función específica en este culto: cantante, guitarra, sonido, etc.
    private String observaciones; // Notas sobre la participación puntual en ese culto

    /**
     * Constructor para la clase CultoParticipante.
     * @param id Identificador único de la relación.
     * @param idCulto ID del culto al que se asocia.
     * @param idUsuario ID del usuario que participa en el servicio.
     * @param rolServicio Rol específico que el usuario desempeña en este culto (ej. "Cantante", "Sonido").
     * @param observaciones Notas específicas sobre la participación en este culto.
     */
    public CultoParticipante(long id, long idCulto, long idUsuario, String rolServicio, String observaciones) {
        this.id = id;
        this.idCulto = idCulto;
        this.idUsuario = idUsuario;
        this.rolServicio = rolServicio;
        this.observaciones = observaciones;
    }

    // Getters
    public long getId() {
        return id;
    }

    public long getIdCulto() {
        return idCulto;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public String getRolServicio() {
        return rolServicio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setIdCulto(long idCulto) {
        this.idCulto = idCulto;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setRolServicio(String rolServicio) {
        this.rolServicio = rolServicio;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "CultoParticipante{" +
               "id=" + id +
               ", idCulto=" + idCulto +
               ", idUsuario=" + idUsuario +
               ", rolServicio='" + rolServicio + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CultoParticipante that = (CultoParticipante) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}