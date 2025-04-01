package org.educastur.samuelepv59.TiendaElectronica.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.educastur.samuelepv59.TiendaElectronica.model.Articulo;
import org.educastur.samuelepv59.TiendaElectronica.model.Cliente;
import org.educastur.samuelepv59.TiendaElectronica.model.Pedido;

public interface dataBaseService {
    ArrayList<Pedido> pedidos();
    HashMap<String,Articulo> articulos();
    HashMap<String, Cliente> clientes();
    Cliente buscaCLiente(String dni);
    Articulo buscaArticulo(String articulo);
    Pedido buscaPedido(String idPedido);
}
