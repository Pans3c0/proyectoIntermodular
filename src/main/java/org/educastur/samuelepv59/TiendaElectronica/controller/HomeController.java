package org.educastur.samuelepv59.TiendaElectronica.controller;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.educastur.samuelepv59.TiendaElectronica.builder.ClienteBuilder;
import org.educastur.samuelepv59.TiendaElectronica.model.Articulo;
import org.educastur.samuelepv59.TiendaElectronica.model.Cliente;
import org.educastur.samuelepv59.TiendaElectronica.model.LineaPedido;
import org.educastur.samuelepv59.TiendaElectronica.model.Pedido;
import org.educastur.samuelepv59.TiendaElectronica.service.dataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class HomeController {
    private ArrayList<Pedido> pedidos;
    private HashMap<String,Articulo> articulos;
    private  HashMap<String, Cliente> clientes;
    private ArrayList<String> categoriasArt;
    private String paisMoneda = "España";

    Scanner sc = new Scanner(System.in);
    @Autowired
    private dataBase dataBase;
    public HomeController(){
            this.articulos=new HashMap<>();
            this.clientes = new HashMap<>();
            this.pedidos = new ArrayList<>();
            leerArchivos();
    }
    
    @GetMapping("/")
    public String mostrarHome(Model model) {
        
        model.addAttribute("mensaje", "Bienssvensisssdos a la pagina de inicio");
        return "home";
    }

    public void onclick(){
        System.out.println("Hola Mundo");
    }

    @GetMapping("/clientes")
    public String mostrarDetalleUsuario(Model model){
        
        model.addAttribute("clientes", clientes.values());
        return "/clientes";
    }

    @GetMapping("/pedidos")
    public String mostrarDetallePedido(Model model){
        
        model.addAttribute("pedidos", pedidos);
        return "/pedidos";
    }

    @GetMapping("/delete")
    public String mostrarEliminado(@RequestParam("id") String idDelete ,Model model){
        
        model.addAttribute("id", idDelete);
        return "/Delete";
    }

    @GetMapping("/articulos")
    public String mostrarDetalleArticulos(Model model){
        
        model.addAttribute("articulos", articulos.values());
        return "/articulos";
    }

    // @GetMapping("/detalleArticulos/{id}")
    // public String mostrarDetalleArticulos(@PathVariable("id") String idArticulo, Model model){
    //     model.addAttribute("articulos", articulos.values());
    //     model.addAttribute("idArticulo", idArticulo);
    //     return "/DetalleArticulos/";
    // }

    @GetMapping("/detallesPedido/{id}")
    public String mostrarDetallePedido(@PathVariable("id") String idPedido,Model model){
        System.out.println(idPedido + "intento");
        model.addAttribute("idPedido", idPedido);
        Pedido pedido = pedidos.stream()
        .filter(p -> p.getIdPedido()
        .equals(idPedido))
        .findFirst()
        .orElse(null);
        System.out.println(pedido);
        model.addAttribute("pedido", pedido );
        System.out.println(pedido);
        return "/DetallesPedido";
    }


    @PostMapping("/save")
    public String mostrarGuardado(@RequestParam("Nombre") String nombre, @RequestParam("Apellidos") String apellidos, @RequestParam("Dni") String dni, @RequestParam("telefono") String telefono, @RequestParam("email") String email){
        
        System.out.println(nombre);
        return "save";
    }

    public void cargaDatos(){
 
 
 
        clientes.put("80580845T",new ClienteBuilder("80580845T").setNombre("ANA").setTelefono("658111111").setEmail("ana@gmail.com").build());
        clientes.put("36347775R", new ClienteBuilder("36347775R").setNombre("LOLA").setTelefono("649222222").setEmail("lola@gmail.com").build());
       clientes.put("63921307Y", new ClienteBuilder("63921307Y").setNombre("JUAN").setTelefono("652333333").setEmail("juan@gmail.com").build());
       clientes.put("02337565Y", new ClienteBuilder("02337565Y").setNombre("EDU").setTelefono("634567890").setEmail("edu@gmail.com").build());
       clientes.put("14665825J", new ClienteBuilder("14665825J").setNombre("REYSHEN").setTelefono("678170433").setEmail("reyshen35@gmail.com").build());
       clientes.put("65318802K", new ClienteBuilder("65318802K").setNombre("ORCREMA").setTelefono("688870433").setEmail("orcrema@gmail.com").build());
       clientes.put("76648739C", new ClienteBuilder("76648739C").setNombre("JOSEIRO").setTelefono("634999890").setEmail("negritogay@gmail.com").build());
       clientes.put("51395126Q", new ClienteBuilder("51395126Q").setNombre("ROCES").setTelefono("633330433").setEmail("autism@gmail.com").build());
       clientes.put("88558514G", new ClienteBuilder("88558514G").setNombre("MORO").setTelefono("611178833").setEmail("españa@gmail.com").build());
       clientes.put("47540602Q", new ClienteBuilder("47540602Q").setNombre("PACHECO").setTelefono("686666710").setEmail("samupachebq@gmail.com").build());


        articulos.put("1-11",new Articulo("1-11","RATON LOGITECH ST ",14,15));
        articulos.put("1-22",new Articulo("1-22","TECLADO STANDARD  ",9,18));
        articulos.put("1-33", new Articulo("1-33", "AURICULARES STEELSERIES 9", 20, 50));
        articulos.put("1-44", new Articulo("1-44", "MICRÓFONO USB RAZER", 10, 35));
        articulos.put("2-11",new Articulo("2-11","HDD SEAGATE 1 TB  ",16,80));
        articulos.put("2-22",new Articulo("2-22","SSD KINGSTOM 256GB",9,70));
        articulos.put("2-33",new Articulo("2-33","SSD KINGSTOM 512GB",0,200));
        articulos.put("2-44", new Articulo("2-44", "NVME 1TB SAMSUNG", 8, 120));
        articulos.put("3-11", new Articulo("3-11", "HP OFFICEJET 3830", 6, 90));
        articulos.put("3-22",new Articulo("3-22","EPSON PRINT XP300 ",5,80));
        articulos.put("4-11",new Articulo("4-11","ASUS  MONITOR  22 ",5,100));
        articulos.put("4-22",new Articulo("4-22","HP MONITOR LED 28 ",5,180));
        articulos.put("4-33",new Articulo("4-33","SAMSUNG ODISSEY G5",12,580));
        articulos.put("4-44", new Articulo("4-44", "LG MONITOR 24''", 10, 140));
        articulos.put("5-11", new Articulo("5-11", "PLACA BASE MSI B450", 7, 110));

        LocalDate hoy = LocalDate.now();
        pedidos.add(new Pedido(clientes.get("80580845T"),hoy.minusDays(1), new ArrayList<>
                (List.of(new LineaPedido("1-11",3),new LineaPedido("4-22",3))),pedidos));
        pedidos.add(new Pedido(clientes.get("80580845T"),hoy.minusDays(2), new ArrayList<>
                (List.of(new LineaPedido("4-11",3),new LineaPedido("4-22",2),new LineaPedido("4-33",4))),pedidos));
        pedidos.add(new Pedido(clientes.get("36347775R"),hoy.minusDays(3), new ArrayList<>
                (List.of(new LineaPedido("4-22",1),new LineaPedido("2-22",3))),pedidos));
        pedidos.add(new Pedido(clientes.get("36347775R"),hoy.minusDays(5), new ArrayList<>
                (List.of(new LineaPedido("4-33",3),new LineaPedido("2-11",3))),pedidos));
        pedidos.add(new Pedido(clientes.get("63921307Y"),hoy.minusDays(4), new ArrayList<>
                (List.of(new LineaPedido("2-11",5),new LineaPedido("2-33",3),new LineaPedido("4-33",2))),pedidos));
        pedidos.add(new Pedido(clientes.get("65318802K"), hoy.minusDays(2),
                new ArrayList<>(List.of(new LineaPedido("1-33", 2), new LineaPedido("4-11", 1))), pedidos));
        pedidos.add(new Pedido(clientes.get("14665825J"), hoy.minusDays(3),
                new ArrayList<>(List.of(new LineaPedido("2-11", 1), new LineaPedido("2-22", 2))), pedidos));
        pedidos.add(new Pedido(clientes.get("02337565Y"), hoy.minusDays(1),
                new ArrayList<>(List.of(new LineaPedido("5-11", 1), new LineaPedido("3-22", 1))), pedidos));
        pedidos.add(new Pedido(clientes.get("80580845T"), hoy.minusDays(4),
                new ArrayList<>(List.of(new LineaPedido("1-44", 1), new LineaPedido("2-44", 2))), pedidos));
        pedidos.add(new Pedido(clientes.get("88558514G"), hoy.minusDays(6),
                new ArrayList<>(List.of(new LineaPedido("4-22", 1), new LineaPedido("3-11", 2))), pedidos));
        pedidos.add(new Pedido(clientes.get("47540602Q"), hoy.minusDays(5),
                new ArrayList<>(List.of(new LineaPedido("4-44", 1), new LineaPedido("5-11", 1))), pedidos));
        pedidos.add(new Pedido(clientes.get("36347775R"), hoy.minusDays(7),
                new ArrayList<>(List.of(new LineaPedido("1-22", 2), new LineaPedido("4-33", 1))), pedidos));
        pedidos.add(new Pedido(clientes.get("51395126Q"), hoy.minusDays(8),
                new ArrayList<>(List.of(new LineaPedido("2-33", 1), new LineaPedido("3-22", 3))), pedidos));
        pedidos.add(new Pedido(clientes.get("76648739C"), hoy.minusDays(9),
                new ArrayList<>(List.of(new LineaPedido("4-22", 2), new LineaPedido("1-44", 1))), pedidos));
        pedidos.add(new Pedido(clientes.get("63921307Y"), hoy.minusDays(10),
                new ArrayList<>(List.of(new LineaPedido("2-44", 1), new LineaPedido("3-11", 1))), pedidos));
        pedidos.add(new Pedido(clientes.get("65318802K"), hoy.minusDays(11),
                new ArrayList<>(List.of(new LineaPedido("5-11", 1), new LineaPedido("1-22", 1))), pedidos));
        pedidos.add(new Pedido(clientes.get("14665825J"), hoy.minusDays(12),
                new ArrayList<>(List.of(new LineaPedido("1-33", 1), new LineaPedido("2-11", 2))), pedidos));
        pedidos.add(new Pedido(clientes.get("02337565Y"), hoy.minusDays(13),
                new ArrayList<>(List.of(new LineaPedido("4-44", 1), new LineaPedido("2-22", 1))), pedidos));
        pedidos.add(new Pedido(clientes.get("88558514G"), hoy.minusDays(14),
                new ArrayList<>(List.of(new LineaPedido("3-22", 2), new LineaPedido("1-11", 3))), pedidos));
        pedidos.add(new Pedido(clientes.get("47540602Q"), hoy.minusDays(15),
                new ArrayList<>(List.of(new LineaPedido("2-33", 2), new LineaPedido("4-22", 1))), pedidos));



    }

    // region persistencias
public void backup() {
    try (ObjectOutputStream oosArticulos = new ObjectOutputStream(new FileOutputStream("articulos.dat"));
         ObjectOutputStream oosClientes = new ObjectOutputStream(new FileOutputStream("clientes.dat"));
         ObjectOutputStream oosPedidos = new ObjectOutputStream (new FileOutputStream("pedidos.dat"))) {

        //LOS PEDIDOS SE GUARDAN OBJETO A OBJETO
        for (Articulo a:articulos.values()){
            oosArticulos.writeObject(a);
        }

        for (Cliente c: clientes.values()){
            oosClientes.writeObject(c);
        }

        for (Pedido p:pedidos){
            oosPedidos.writeObject(p);
        }

        System.out.println("Copia de seguridad realizada con éxito.");

    } catch (FileNotFoundException e) {
        System.out.println(e.toString());
    } catch (IOException e) {
        System.out.println(e.toString());
    }
}

    public void leerArchivos() {
        try (ObjectInputStream oisArticulos = new ObjectInputStream(new FileInputStream("articulos.dat"))){
            Articulo a;
            while ( (a=(Articulo)oisArticulos.readObject()) != null){
                articulos.put(a.getIdArticulo(), a);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (EOFException e){

        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.toString());
        }

        try (ObjectInputStream oisClientes = new ObjectInputStream(new FileInputStream("clientes.dat"))){
            Cliente c;
            while ( (c=(Cliente)oisClientes.readObject()) != null){
                clientes.put(c.getDni(), c); // parte variable
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (EOFException e){

        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.toString());
        }


        try (ObjectInputStream oisPedidos = new ObjectInputStream(new FileInputStream("pedidos.dat"))){
            Pedido p;
            while ( (p=(Pedido)oisPedidos.readObject()) != null){
                pedidos.add(p);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (EOFException e){

        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.toString());
        }

    }

public void backupSeccion(){
    try(ObjectOutputStream oisImpresora=new ObjectOutputStream(new FileOutputStream("./Data/impresoras.dat"));
        ObjectOutputStream oisMonitores=new ObjectOutputStream(new FileOutputStream("monitores.dat"));
        ObjectOutputStream oisAlmacenamiento=new ObjectOutputStream(new FileOutputStream("almacenamiento.dat"));
        ObjectOutputStream oisPerifericos=new ObjectOutputStream(new FileOutputStream("perifericos.dat"));
        ObjectOutputStream oisComponentes=new ObjectOutputStream(new FileOutputStream("componentes.dat"))){
        for (Articulo a : articulos.values()){
            if (a.getSeccion()==3){
                oisImpresora.writeObject(a);
            }
        }
        for (Articulo a : articulos.values()){
            if (a.getSeccion()==1){
                oisPerifericos.writeObject(a);
            }
        }
        for (Articulo a : articulos.values()){
            if (a.getSeccion()==2){
                oisAlmacenamiento.writeObject(a);
            }
        }
        for (Articulo a : articulos.values()){
            if (a.getSeccion()==4){
                oisMonitores.writeObject(a);
            }
        }
        for (Articulo a : articulos.values()){
            if (a.getSeccion()==5){
                oisComponentes.writeObject(a);
            }
        }
        System.out.println("Copia de las seccinoes realizada con exito.");
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
   
    /**
    public void persistirTienda() throws IOException {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("Tienda.dat"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        oos.writeObject(Tienda);
    }

    */
// endregion

}
