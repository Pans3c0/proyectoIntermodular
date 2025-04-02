package org.educastur.samuelepv59.TiendaElectronica.controller;

import org.educastur.samuelepv59.TiendaElectronica.model.Cliente;
import org.educastur.samuelepv59.TiendaElectronica.model.Pedido;
import org.educastur.samuelepv59.TiendaElectronica.service.dataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/pedido")
public class PedidoController {
    
    @Autowired
     private dataBase dataBase;

    @GetMapping("/delete")
    public String mostrarEliminado(@RequestParam("id") String idDelete ,Model model){
        model.addAttribute("id", idDelete);
        Pedido p = dataBase.buscaPedido(idDelete);
            dataBase.pedidos().remove(p);
        return "/Delete";
    }

    @GetMapping("/create")
    public String mostrarCreate(){
        return "/Create";
    }

    @PostMapping("/save")
    public String mostrarSave(){
        return "/Save";
    }
    
    @GetMapping("/listaPedidos")
    public String mostrarListaPedidos(Model model){
        model.addAttribute("pedidos", dataBase.pedidos() );
        return "/pedido/listaPedidos";
    }
    @GetMapping("/detallesPedido/{id}")
    public String mostrarDetallePedido(@PathVariable("id") String idPedido,Model model){
        System.out.println(idPedido + "intento");
        model.addAttribute("idPedido", idPedido);
        Pedido pedidoEncontrado =dataBase.pedidos()
            .stream()
            .filter(p -> p.getIdPedido()
            .equals(idPedido))
            .findFirst()
            .orElse(null);
        
        model.addAttribute("pedido", pedidoEncontrado );

        return "/pedido/DetallesPedido";
    }

    @GetMapping("/nuevoPedido")
    public String mostrarNuevoPedido(Model model) {
        
        return new String();
    }
    
}
