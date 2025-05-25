package org.educastur.samuelepv59.TiendaElectronica.controller;

import org.educastur.samuelepv59.TiendaElectronica.model.*;
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
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private dataBase dataBase;

    /** 
    @GetMapping("/delete")
    public String mostrarEliminado(@RequestParam("id") String idDelete ,Model model){
        model.addAttribute("id", idDelete);
        usuario c = dataBase.buscaUsuario(idDelete);
        dataBase.usuarios().remove(c.dni,c);
        return "/Delete";
    }

    @GetMapping("/create")
    public String mostrarCreate(){
        return "/usuario/Nuevousuario";
    }

    @PostMapping("/save")
    public String mostrarGuardado(usuario c, BindingResult result){
        if(result.hasErrors()){
            for(Object o:result.getAllErrors()){
                o.toString();
            }
            return "usuario/Nuevousuario";
        }
        dataBase.usuarios().put(c.getDni(), c);
        return "/usuario/Listausuarios";
    }

    */

    @GetMapping("/ListaUsuarios")
    public String mostrarDetalleUsuario(Model model){
        
        model.addAttribute("usuarios", dataBase.usuarios().values());
        return "usuario/ListaUsuarios";
    }
    
    @GetMapping("/detalle/{id}")
    public String mostrarDetallePedido(@PathVariable("id") String dni,Model model){
        model.addAttribute("dni", dni);
        model.addAttribute("usuario", dataBase.buscaUsuario(dni) );

        return "/usuario/Detalle";
    }
    
}
