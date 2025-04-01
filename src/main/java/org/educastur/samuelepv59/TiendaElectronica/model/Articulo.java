package org.educastur.samuelepv59.TiendaElectronica.model;

import org.educastur.samuelepv59.TiendaElectronica.Factory.Factory;

import java.io.Serializable;

public class Articulo implements Comparable<Articulo>, Serializable {
    private String idArticulo;
    private String descripcion;
    private int existencias;
    private double pvp;

    public Articulo(String idArticulo, String descripcion, int existencias, double pvp) {
        this.idArticulo = idArticulo;
        this.descripcion = descripcion;
        this.existencias = existencias;
        this.pvp = pvp;
    }

    @Override
    public String toString() {
        return "Articulo: " + descripcion + "\nExistencias:" + existencias + "\nPrecio: " + pvp ;
    }

    public String getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(String idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    /**
     * Monea Default Factory EUR
     * @return precio articulo EUR
     */
    public Double getPvp() {
        double moneda = new Factory("España").getPrecio();
        return pvp * moneda;
    }
    public Double getPvp(Factory factory) {
        double moneda = factory.getPrecio();
        return pvp * moneda;
    }

    public void setPvp(Double pvp) {
        this.pvp = pvp;
    }

    @Override
    public int compareTo(Articulo a) {
        return idArticulo.compareTo(a.idArticulo);

    }
    public int getSeccion() {
        return Integer.parseInt(this.idArticulo.split("-")[0]);
    }

}
