package org.educastur.samuelepv59.TiendaElectronica.controller;


import org.educastur.samuelepv59.TiendaElectronica.model.*;
import org.educastur.samuelepv59.TiendaElectronica.service.dataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/culto")
public class CultoController {
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

    @GetMapping("/ListaCultos")
    public String mostrarListaCultos(Model model){
        model.addAttribute("cultos", dataBase.cultos().values());
        return "cultos/ListaCultos";
    }
    @GetMapping("/create")
    public String mostrarCreate(){
        
        return "/articulo/NuevoArticulo";
    }


    @PostMapping("/save")
    public String mostrarGuardado(Articulo a, BindingResult result){
        if(result.hasErrors()){
            for(Object o : result.getAllErrors()){
                System.out.println("error" + o.toString());
            }
            return "articulo/NuevoArticulo";
        }
        //Articulo a = new Articulo(idArticulo, descripcion, existencias, pvp);
        dataBase.articulos().put(a.getIdArticulo(), a);
        return "/articulo/SaveArticulo";
    }
}
