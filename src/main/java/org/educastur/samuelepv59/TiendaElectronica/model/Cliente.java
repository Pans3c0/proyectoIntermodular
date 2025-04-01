package org.educastur.samuelepv59.TiendaElectronica.model;

import org.educastur.samuelepv59.TiendaElectronica.builder.ClienteBuilder;

import java.io.Serializable;

public class Cliente implements Serializable {
    public String dni;
    public String nombre;
    public String telefono;
    public String email;

    public Cliente(String dni)  {
        this.dni = dni;
    }

    //Cloneable
    public Cliente clonar(){
        return new ClienteBuilder(dni)
                .setNombre(nombre)
                .setTelefono(telefono)
                .setEmail(email)
                .build();

    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return nombre + '\'' + telefono + '\'' + email + '\'';
    }
}
