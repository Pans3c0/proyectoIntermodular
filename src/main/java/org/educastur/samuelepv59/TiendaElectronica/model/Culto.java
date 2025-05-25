package org.educastur.samuelepv59.TiendaElectronica.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;



public class Culto implements Serializable{
    private static final long serialVersionUID = 1L;
    private long idCulto;
    private LocalDate fecha;
    private LocalTime hora;
    private TipoCulto tipo;
    private String tema;
    private String predicador;
    private String notas;

    /**
     * Constructor para la clase Culto.
     * @param idCulto Identificador único del culto.
     * @param fecha Fecha del culto.
     * @param hora Hora del culto.
     * @param tipo Tipo de culto (Adoración, Estudio, etc.).
     * @param tema Tema del culto.
     * @param predicador Nombre del predicador.
     * @param notas Notas adicionales sobre el culto.
     */
    public Culto(long idCulto, LocalDate fecha, LocalTime hora, TipoCulto tipo, String tema, String predicador, String notas) {
        this.idCulto = idCulto;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.tema = tema;
        this.predicador = predicador;
        this.notas = notas;
    }

    // Getters
    public long getIdCulto() {
        return idCulto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public TipoCulto getTipo() {
        return tipo;
    }

    public String getTema() {
        return tema;
    }

    public String getPredicador() {
        return predicador;
    }

    public String getNotas() {
        return notas;
    }

    // Setters
    public void setIdCulto(long idCulto) {
        this.idCulto = idCulto;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setTipo(TipoCulto tipo) {
        this.tipo = tipo;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setPredicador(String predicador) {
        this.predicador = predicador;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Culto{" +
               "idCulto=" + idCulto +
               ", fecha=" + fecha +
               ", hora=" + hora +
               ", tipo=" + tipo +
               ", tema='" + tema + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Culto culto = (Culto) o;
        return idCulto == culto.idCulto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCulto);
    }
}
