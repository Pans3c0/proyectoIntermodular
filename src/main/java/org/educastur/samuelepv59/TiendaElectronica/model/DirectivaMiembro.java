package org.educastur.samuelepv59.TiendaElectronica.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase de unión que representa la membresía de un usuario en una directiva.
 * Permite controlar qué usuarios pertenecen a qué directivas y con qué cargo.
 */
public class DirectivaMiembro implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id; // Identificador único de la membresía en la directiva
    private long idDirectiva; // Clave foránea a Directiva
    private long idUsuario; // Clave foránea a Usuario
    private LocalDate fechaInicio;
    private LocalDate fechaFin; // Puede ser NULL (si aún es miembro)
    private String cargo; // Cargo dentro de la directiva (ej. "Presidente", "Vocal")

    /**
     * Constructor para la clase DirectivaMiembro.
     * @param id Identificador único de la membresía.
     * @param idDirectiva ID de la directiva.
     * @param idUsuario ID del usuario miembro.
     * @param fechaInicio Fecha de inicio de la membresía.
     * @param fechaFin Fecha de fin de la membresía (puede ser null).
     * @param cargo Cargo del usuario en la directiva.
     */
    public DirectivaMiembro(long id, long idDirectiva, long idUsuario, LocalDate fechaInicio, LocalDate fechaFin, String cargo) {
        this.id = id;
        this.idDirectiva = idDirectiva;
        this.idUsuario = idUsuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cargo = cargo;
    }

    // Getters
    public long getId() {
        return id;
    }

    public long getIdDirectiva() {
        return idDirectiva;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public String getCargo() {
        return cargo;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setIdDirectiva(long idDirectiva) {
        this.idDirectiva = idDirectiva;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "DirectivaMiembro{" +
               "id=" + id +
               ", idDirectiva=" + idDirectiva +
               ", idUsuario=" + idUsuario +
               ", cargo='" + cargo + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectivaMiembro that = (DirectivaMiembro) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}