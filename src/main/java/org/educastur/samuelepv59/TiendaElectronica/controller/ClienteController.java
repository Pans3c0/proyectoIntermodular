package org.educastur.samuelepv59.TiendaElectronica.controller;

import org.educastur.samuelepv59.TiendaElectronica.builder.ClienteBuilder;
import org.educastur.samuelepv59.TiendaElectronica.model.Cliente;
import org.educastur.samuelepv59.TiendaElectronica.model.Pedido;
import org.educastur.samuelepv59.TiendaElectronica.service.dataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private dataBase dataBase;

    @GetMapping("/delete")
    public String mostrarEliminado(@RequestParam("id") String idDelete ,Model model){
        model.addAttribute("id", idDelete);
        Cliente c = dataBase.buscaCLiente(idDelete);
        dataBase.clientes().remove(c.dni,c);
        return "/Delete";
    }

    @GetMapping("/create")
    public String mostrarCreate(){
        return "/cliente/NuevoCliente";
    }

    @PostMapping("/save")
    public String mostrarGuardado(Cliente c, BindingResult result){
        if(result.hasErrors()){
            for(Object o:result.getAllErrors()){
                o.toString();
            }
            return "cliente/NuevoCliente";
        }
        dataBase.clientes().put(c.getDni(), c);
        return "/cliente/ListaClientes";
    }

    @GetMapping("/listaClientes")
    public String mostrarDetalleUsuario(Model model){
        
        model.addAttribute("clientes", dataBase.clientes().values());
        return "cliente/ListaClientes";
    }
    @GetMapping("/detalle/{id}")
    public String mostrarDetallePedido(@PathVariable("id") String dni,Model model){
        model.addAttribute("dni", dni);
        model.addAttribute("cliente", dataBase.buscaCLiente(dni) );

        return "/cliente/Detalle";
    }
}
