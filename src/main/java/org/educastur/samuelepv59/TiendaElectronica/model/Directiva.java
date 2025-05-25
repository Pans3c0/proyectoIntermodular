package org.educastur.samuelepv59.TiendaElectronica.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que representa una directiva o comité dentro de la iglesia.
 * Permite organizar a los usuarios en grupos de liderazgo o trabajo específicos.
 */
public class Directiva implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idDirectiva;
    private String nombre;
    private String descripcion;

    /**
     * Constructor para la clase Directiva.
     * @param idDirectiva Identificador único de la directiva.
     * @param nombre Nombre de la directiva (ej. "Junta de Diáconos", "Comité de Alabanza").
     * @param descripcion Descripción de la directiva.
     */
    public Directiva(long idDirectiva, String nombre, String descripcion) {
        this.idDirectiva = idDirectiva;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters
    public long getIdDirectiva() {
        return idDirectiva;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Setters
    public void setIdDirectiva(long idDirectiva) {
        this.idDirectiva = idDirectiva;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Directiva{" +
               "idDirectiva=" + idDirectiva +
               ", nombre='" + nombre + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directiva directiva = (Directiva) o;
        return idDirectiva == directiva.idDirectiva;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDirectiva);
    }
}
