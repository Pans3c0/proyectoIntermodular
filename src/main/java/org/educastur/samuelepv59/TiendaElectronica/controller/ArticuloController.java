package org.educastur.samuelepv59.TiendaElectronica.controller;

import org.educastur.samuelepv59.TiendaElectronica.builder.ClienteBuilder;
import org.educastur.samuelepv59.TiendaElectronica.model.Articulo;
import org.educastur.samuelepv59.TiendaElectronica.model.Cliente;
import org.educastur.samuelepv59.TiendaElectronica.model.Pedido;
import org.educastur.samuelepv59.TiendaElectronica.service.dataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/articulo")
public class ArticuloController {
    @Autowired
    private dataBase dataBase;
    @GetMapping("/delete")
    public String mostrarEliminado(@RequestParam("id") String idDelete ,Model model){
        model.addAttribute("id", idDelete);
        Articulo a = dataBase.buscaArticulo(idDelete);

            dataBase.articulos().remove(a.getIdArticulo(),a);
            dataBase.backup();
        return "/Delete";
    }

    @GetMapping("/listaArticulos")
    public String mostrarListaArticulos(Model model){
        model.addAttribute("articulos", dataBase.articulos().values());
        return "articulo/ListaArticulos";
    }
    @GetMapping("/create")
    public String mostrarCreate(){
        return "/articulo/NuevoArticulo";
    }

    @PostMapping("/save")
    public String mostrarGuardado(@RequestParam("fid") String idArticulo, @RequestParam("fdescripcion") String descripcion, @RequestParam("fexistencias") int existencias, @RequestParam("fpvp") double pvp){
        Articulo a = new Articulo(idArticulo, descripcion, existencias, pvp);
        dataBase.articulos().put(idArticulo, a);
        dataBase.backup();
        return "/articulo/SaveArticulo";
    }
}
