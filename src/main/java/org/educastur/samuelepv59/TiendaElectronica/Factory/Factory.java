package org.educastur.samuelepv59.TiendaElectronica.Factory;

public class Factory {
    private Precio precio;

    private Factory(){

    }
    public Factory(String pais){
        if (pais.equals("España")) {
            this.precio = new PrecioEUR();
        } else {
            this.precio = new PrecioUSD();
        }
    }
    public double getPrecio(){return this.precio.getPrecio();}
}
