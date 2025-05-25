package org.educastur.samuelepv59.TiendaElectronica.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que representa un evento especial.
 * Corresponde a la tabla 'eventos_especiales'.
 */
public class EventoEspecial implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idEvento;
    private String nombre;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin; // Puede ser NULL
    private String encargado;

    /**
     * Constructor para la clase EventoEspecial.
     * @param idEvento Identificador único del evento.
     * @param nombre Nombre del evento.
     * @param descripcion Detalles del evento.
     * @param fechaInicio Fecha de inicio del evento.
     * @param fechaFin Fecha de fin del evento (puede ser null).
     * @param encargado Persona encargada del evento.
     */
    public EventoEspecial(long idEvento, String nombre, String descripcion, LocalDate fechaInicio, LocalDate fechaFin, String encargado) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.encargado = encargado;
    }

    // Getters
    public long getIdEvento() {
        return idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public String getEncargado() {
        return encargado;
    }

    // Setters
    public void setIdEvento(long idEvento) {
        this.idEvento = idEvento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    @Override
    public String toString() {
        return "EventoEspecial{" +
               "idEvento=" + idEvento +
               ", nombre='" + nombre + '\'' +
               ", fechaInicio=" + fechaInicio +
               ", fechaFin=" + fechaFin +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventoEspecial that = (EventoEspecial) o;
        return idEvento == that.idEvento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento);
    }
}