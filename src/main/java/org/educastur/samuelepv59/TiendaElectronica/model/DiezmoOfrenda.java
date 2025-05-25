package org.educastur.samuelepv59.TiendaElectronica.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase que registra los diezmos y ofrendas.
 * Corresponde a la tabla 'diezmos_ofrendas'.
 */
public class DiezmoOfrenda implements Serializable {
    private static final long serialVersionUID = 1L;
    private long idRegistro;
    private Long idUsuario; // Puede ser NULL, por eso se usa Long (clase wrapper)
    private TipoDiezmoOfrenda tipo;
    private BigDecimal monto;
    private LocalDate fecha;
    private String observaciones;

    /**
     * Constructor para la clase DiezmoOfrenda.
     * @param idRegistro Identificador único del registro.
     * @param idUsuario ID del usuario que dio la ofrenda/diezmo (puede ser null).
     * @param diezmo Tipo de registro (diezmo, ofrenda, especial).
     * @param monto Monto entregado.
     * @param fecha Fecha del registro.
     * @param observaciones Notas adicionales.
     */
    public DiezmoOfrenda(long idRegistro, Long idUsuario, TipoDiezmoOfrenda diezmo, BigDecimal monto, LocalDate fecha, String observaciones) {
        this.idRegistro = idRegistro;
        this.idUsuario = idUsuario;
        this.tipo = diezmo;
        this.monto = monto;
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    // Getters
    public long getIdRegistro() {
        return idRegistro;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public TipoDiezmoOfrenda getTipo() {
        return tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    // Setters
    public void setIdRegistro(long idRegistro) {
        this.idRegistro = idRegistro;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setTipo(TipoDiezmoOfrenda tipo) {
        this.tipo = tipo;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "DiezmoOfrenda{" +
               "idRegistro=" + idRegistro +
               ", idUsuario=" + idUsuario +
               ", tipo=" + tipo +
               ", monto=" + monto +
               ", fecha=" + fecha +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiezmoOfrenda that = (DiezmoOfrenda) o;
        return idRegistro == that.idRegistro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRegistro);
    }
}