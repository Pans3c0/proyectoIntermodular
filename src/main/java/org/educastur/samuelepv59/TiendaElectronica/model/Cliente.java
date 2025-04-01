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

   
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setEmail(String email) {
        this.email = email;
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
