package org.educastur.samuelepv59.TiendaElectronica.service;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.educastur.samuelepv59.TiendaElectronica.builder.ClienteBuilder;
import org.educastur.samuelepv59.TiendaElectronica.model.Articulo;
import org.educastur.samuelepv59.TiendaElectronica.model.Cliente;
import org.educastur.samuelepv59.TiendaElectronica.model.LineaPedido;
import org.educastur.samuelepv59.TiendaElectronica.model.Pedido;
import org.springframework.stereotype.Service;

@Service
public class dataBase implements dataBaseService {

    private ArrayList<Pedido> listaPedidos = new ArrayList<>();
    private HashMap<String,Articulo> listaArticulos = new HashMap<>();
    private HashMap<String, Cliente> listaClientes = new HashMap<>();

    public dataBase(){
        leerArchivos();
    }

    @Override
    public ArrayList<Pedido> pedidos() {
        return listaPedidos;
    }

    @Override
    public HashMap<String, Articulo> articulos() {
        return listaArticulos;
    }

    @Override
    public HashMap<String, Cliente> clientes() {
        return listaClientes;
    }

    public void leerArchivos() {
    try (ObjectInputStream oislistaArticulos = new ObjectInputStream(new FileInputStream("articulos.dat"))){
        Articulo a;
        while ( (a=(Articulo)oislistaArticulos.readObject()) != null){
            listaArticulos.put(a.getIdArticulo(), a);
        }
    } catch (FileNotFoundException e) {
        System.out.println(e.toString());
    } catch (EOFException e){

    } catch (ClassNotFoundException | IOException e) {
        System.out.println(e.toString());
    }

    try (ObjectInputStream oislistaClientes = new ObjectInputStream(new FileInputStream("clientes.dat"))){
        Cliente c;
        while ( (c=(Cliente)oislistaClientes.readObject()) != null){
            listaClientes.put(c.getDni(), c); // parte variable
        }
    } catch (FileNotFoundException e) {
        System.out.println(e.toString());
    } catch (EOFException e){

    } catch (ClassNotFoundException | IOException e) {
        System.out.println(e.toString());
    }


    try (ObjectInputStream oislistaPedidos = new ObjectInputStream(new FileInputStream("pedidos.dat"))){
        Pedido p;
        while ( (p=(Pedido)oislistaPedidos.readObject()) != null){
            listaPedidos.add(p);
        }
    } catch (FileNotFoundException e) {
        System.out.println(e.toString());
    } catch (EOFException e){

    } catch (ClassNotFoundException | IOException e) {
        System.out.println(e.toString());
    }
    }

     public void cargaDatos(){
 
 
 
         listaClientes.put("80580845T",new ClienteBuilder("80580845T").setNombre("ANA").setTelefono("658111111").setEmail("ana@gmail.com").build());
         listaClientes.put("36347775R", new ClienteBuilder("36347775R").setNombre("LOLA").setTelefono("649222222").setEmail("lola@gmail.com").build());
        listaClientes.put("63921307Y", new ClienteBuilder("63921307Y").setNombre("JUAN").setTelefono("652333333").setEmail("juan@gmail.com").build());
        listaClientes.put("02337565Y", new ClienteBuilder("02337565Y").setNombre("EDU").setTelefono("634567890").setEmail("edu@gmail.com").build());
        listaClientes.put("14665825J", new ClienteBuilder("14665825J").setNombre("REYSHEN").setTelefono("678170433").setEmail("reyshen35@gmail.com").build());
        listaClientes.put("65318802K", new ClienteBuilder("65318802K").setNombre("ORCREMA").setTelefono("688870433").setEmail("orcrema@gmail.com").build());
        listaClientes.put("76648739C", new ClienteBuilder("76648739C").setNombre("JOSEIRO").setTelefono("634999890").setEmail("negritogay@gmail.com").build());
        listaClientes.put("51395126Q", new ClienteBuilder("51395126Q").setNombre("ROCES").setTelefono("633330433").setEmail("autism@gmail.com").build());
        listaClientes.put("88558514G", new ClienteBuilder("88558514G").setNombre("MORO").setTelefono("611178833").setEmail("españa@gmail.com").build());
        listaClientes.put("47540602Q", new ClienteBuilder("47540602Q").setNombre("PACHECO").setTelefono("686666710").setEmail("samupachebq@gmail.com").build());
 
 
         listaArticulos.put("1-11",new Articulo("1-11","RATON LOGITECH ST ",14,15));
         listaArticulos.put("1-22",new Articulo("1-22","TECLADO STANDARD  ",9,18));
         listaArticulos.put("1-33", new Articulo("1-33", "AURICULARES STEELSERIES 9", 20, 50));
         listaArticulos.put("1-44", new Articulo("1-44", "MICRÓFONO USB RAZER", 10, 35));
         listaArticulos.put("2-11",new Articulo("2-11","HDD SEAGATE 1 TB  ",16,80));
         listaArticulos.put("2-22",new Articulo("2-22","SSD KINGSTOM 256GB",9,70));
         listaArticulos.put("2-33",new Articulo("2-33","SSD KINGSTOM 512GB",0,200));
         listaArticulos.put("2-44", new Articulo("2-44", "NVME 1TB SAMSUNG", 8, 120));
         listaArticulos.put("3-11", new Articulo("3-11", "HP OFFICEJET 3830", 6, 90));
         listaArticulos.put("3-22",new Articulo("3-22","EPSON PRINT XP300 ",5,80));
         listaArticulos.put("4-11",new Articulo("4-11","ASUS  MONITOR  22 ",5,100));
         listaArticulos.put("4-22",new Articulo("4-22","HP MONITOR LED 28 ",5,180));
         listaArticulos.put("4-33",new Articulo("4-33","SAMSUNG ODISSEY G5",12,580));
         listaArticulos.put("4-44", new Articulo("4-44", "LG MONITOR 24''", 10, 140));
         listaArticulos.put("5-11", new Articulo("5-11", "PLACA BASE MSI B450", 7, 110));
 
         LocalDate hoy = LocalDate.now();
         listaPedidos.add(new Pedido(listaClientes.get("80580845T"),hoy.minusDays(1), new ArrayList<>
                 (List.of(new LineaPedido("1-11",3),new LineaPedido("4-22",3))),listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("80580845T"),hoy.minusDays(2), new ArrayList<>
                 (List.of(new LineaPedido("4-11",3),new LineaPedido("4-22",2),new LineaPedido("4-33",4))),listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("36347775R"),hoy.minusDays(3), new ArrayList<>
                 (List.of(new LineaPedido("4-22",1),new LineaPedido("2-22",3))),listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("36347775R"),hoy.minusDays(5), new ArrayList<>
                 (List.of(new LineaPedido("4-33",3),new LineaPedido("2-11",3))),listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("63921307Y"),hoy.minusDays(4), new ArrayList<>
                 (List.of(new LineaPedido("2-11",5),new LineaPedido("2-33",3),new LineaPedido("4-33",2))),listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("65318802K"), hoy.minusDays(2),
                 new ArrayList<>(List.of(new LineaPedido("1-33", 2), new LineaPedido("4-11", 1))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("14665825J"), hoy.minusDays(3),
                 new ArrayList<>(List.of(new LineaPedido("2-11", 1), new LineaPedido("2-22", 2))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("02337565Y"), hoy.minusDays(1),
                 new ArrayList<>(List.of(new LineaPedido("5-11", 1), new LineaPedido("3-22", 1))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("80580845T"), hoy.minusDays(4),
                 new ArrayList<>(List.of(new LineaPedido("1-44", 1), new LineaPedido("2-44", 2))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("88558514G"), hoy.minusDays(6),
                 new ArrayList<>(List.of(new LineaPedido("4-22", 1), new LineaPedido("3-11", 2))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("47540602Q"), hoy.minusDays(5),
                 new ArrayList<>(List.of(new LineaPedido("4-44", 1), new LineaPedido("5-11", 1))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("36347775R"), hoy.minusDays(7),
                 new ArrayList<>(List.of(new LineaPedido("1-22", 2), new LineaPedido("4-33", 1))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("51395126Q"), hoy.minusDays(8),
                 new ArrayList<>(List.of(new LineaPedido("2-33", 1), new LineaPedido("3-22", 3))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("76648739C"), hoy.minusDays(9),
                 new ArrayList<>(List.of(new LineaPedido("4-22", 2), new LineaPedido("1-44", 1))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("63921307Y"), hoy.minusDays(10),
                 new ArrayList<>(List.of(new LineaPedido("2-44", 1), new LineaPedido("3-11", 1))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("65318802K"), hoy.minusDays(11),
                 new ArrayList<>(List.of(new LineaPedido("5-11", 1), new LineaPedido("1-22", 1))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("14665825J"), hoy.minusDays(12),
                 new ArrayList<>(List.of(new LineaPedido("1-33", 1), new LineaPedido("2-11", 2))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("02337565Y"), hoy.minusDays(13),
                 new ArrayList<>(List.of(new LineaPedido("4-44", 1), new LineaPedido("2-22", 1))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("88558514G"), hoy.minusDays(14),
                 new ArrayList<>(List.of(new LineaPedido("3-22", 2), new LineaPedido("1-11", 3))), listaPedidos));
         listaPedidos.add(new Pedido(listaClientes.get("47540602Q"), hoy.minusDays(15),
                 new ArrayList<>(List.of(new LineaPedido("2-33", 2), new LineaPedido("4-22", 1))), listaPedidos));
 
 
 
     }

     
     public void backup() {
    try (ObjectOutputStream oosArticulos = new ObjectOutputStream(new FileOutputStream("articulos.dat"));
         ObjectOutputStream oosClientes = new ObjectOutputStream(new FileOutputStream("clientes.dat"));
         ObjectOutputStream oosPedidos = new ObjectOutputStream (new FileOutputStream("pedidos.dat"))) {

        //LOS PEDIDOS SE GUARDAN OBJETO A OBJETO
        for (Articulo a:listaArticulos.values()){
            oosArticulos.writeObject(a);
        }

        for (Cliente c: listaClientes.values()){
            oosClientes.writeObject(c);
        }

        for (Pedido p:listaPedidos){
            oosPedidos.writeObject(p);
        }

        System.out.println("Copia de seguridad realizada con éxito.");

    } catch (FileNotFoundException e) {
        System.out.println(e.toString());
    } catch (IOException e) {
        System.out.println(e.toString());
    }
}

     @Override
     public Cliente buscaCLiente(String dni) {
        return 
        listaClientes
            .values()
            .stream()
            .filter(c -> c.getDni()
            .equals(dni))
            .findFirst()
            .orElse(null);
    }

     @Override
     public Articulo buscaArticulo(String idArticulo) {
        return 
        listaArticulos
            .values()
            .stream()
            .filter(a -> a.getIdArticulo()
            .equals(idArticulo))
            .findFirst()
            .orElse(null);    
    }

     @Override
     public Pedido buscaPedido(String idPedido) {
        
        return 
        listaPedidos
            .stream()
            .filter(p -> p.getIdPedido()
            .equals(idPedido))
            .findFirst()
            .orElse(null);
    }

}
