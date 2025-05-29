package org.educastur.samuelepv59.TiendaElectronica.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.educastur.samuelepv59.TiendaElectronica.model.*;

public interface dataBaseService {
    ArrayList<Pedido> pedidos();

    HashMap<String, Articulo> articulos();

    HashMap<String, Cliente> clientes();

    Map<Long, Usuario> usuarios();

    Map<Long, Culto> cultos();

    List<CultoParticipante> cultosParticipantes();

    Map<Long, Asistencia> asistencias();

    Map<Long, DiezmoOfrenda> diezmosOfrendas();

    Map<Long, EventoEspecial> eventosEspeciales();

    Map<Long, Directiva> directivas(); // Nueva colección para directivas

    List<DirectivaMiembro> directivasMiembros();

    Enum tipoCulto(); // Nueva colección para miembros de directivas
    
    
    Cliente buscaCLiente(String dni);
    Articulo buscaArticulo(String articulo);
    Pedido buscaPedido(String idPedido);
    Usuario buscaUsuario(String idUsuario);

}
