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
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.educastur.samuelepv59.TiendaElectronica.builder.ClienteBuilder;
import org.educastur.samuelepv59.TiendaElectronica.model.*;
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
    private HashMap<String, Articulo> articulos;
    private HashMap<String, Cliente> clientes;
    private ArrayList<String> categoriasArt;
    private String paisMoneda = "España";
    // Colecciones en memoria para cada entidad
    private Map<Long, Usuario> usuarios;
    private Map<Long, Culto> cultos;
    // La clase Participante ha sido eliminada como entidad separada para la
    // persona.
    // Ahora CultoParticipante referencia directamente a Usuario.
    private List<CultoParticipante> cultosParticipantes; // Lista porque es una tabla de unión sin PK propia fácil de
                                                         // mapear a Map
    private Map<Long, Asistencia> asistencias;
    private Map<Long, DiezmoOfrenda> diezmosOfrendas;
    private Map<Long, EventoEspecial> eventosEspeciales;
    private Map<Long, Directiva> directivas; // Nueva colección para directivas
    private List<DirectivaMiembro> directivasMiembros; // Nueva colección para miembros de directivas

    Scanner sc = new Scanner(System.in);
    @Autowired
    private dataBase dataBase;

    public HomeController() {
        this.articulos = new HashMap<>();
        this.clientes = new HashMap<>();
        this.pedidos = new ArrayList<>();
        this.usuarios = new HashMap<>();
        this.cultos = new HashMap<>();
        this.cultosParticipantes = new ArrayList<>();
        this.asistencias = new HashMap<>();
        this.diezmosOfrendas = new HashMap<>();
        this.eventosEspeciales = new HashMap<>();
        this.directivas = new HashMap<>();
        this.directivasMiembros = new ArrayList<>();
        leerArchivos();
    }

    @GetMapping("/")
    public String mostrarHome(Model model) {

        model.addAttribute("mensaje", "Bienssvensisssdos a la pagina de inicio");
        return "Home";
    }

    public void onclick() {
        System.out.println("Hola Mundo");
    }

    @GetMapping("/clientes")
    public String mostrarDetalleUsuario(Model model) {

        model.addAttribute("clientes", usuarios.values());
        return "/clientes";
    }

    @GetMapping("/pedidos")
    public String mostrarDetallePedido(Model model) {

        model.addAttribute("pedidos", pedidos);
        return "/pedidos";
    }

    @GetMapping("/delete")
    public String mostrarEliminado(@RequestParam("id") String idDelete, Model model) {

        model.addAttribute("id", idDelete);
        return "/Delete";
    }

    @GetMapping("/articulos")
    public String mostrarDetalleArticulos(Model model) {

        model.addAttribute("articulos", articulos.values());
        return "/articulos";
    }

    // @GetMapping("/detalleArticulos/{id}")
    // public String mostrarDetalleArticulos(@PathVariable("id") String idArticulo,
    // Model model){
    // model.addAttribute("articulos", articulos.values());
    // model.addAttribute("idArticulo", idArticulo);
    // return "/DetalleArticulos/";
    // }

    @GetMapping("/detallesPedido/{id}")
    public String mostrarDetallePedido(@PathVariable("id") String idPedido, Model model) {
        System.out.println(idPedido + "intento");
        model.addAttribute("idPedido", idPedido);
        Pedido pedido = pedidos.stream()
                .filter(p -> p.getIdPedido()
                        .equals(idPedido))
                .findFirst()
                .orElse(null);
        System.out.println(pedido);
        model.addAttribute("pedido", pedido);
        System.out.println(pedido);
        return "/DetallesPedido";
    }

    @PostMapping("/save")
    public String mostrarGuardado(@RequestParam("Nombre") String nombre, @RequestParam("Apellidos") String apellidos,
            @RequestParam("Dni") String dni, @RequestParam("telefono") String telefono,
            @RequestParam("email") String email) {

        System.out.println(nombre);
        return "save";
    }

    public void cargaDatos2() {
        LocalDate hoy = LocalDate.now();
        System.out.println("Cargando usuarios de la iglesia...");
        usuarios.put(1L,
                new Usuario(1L, "Pastor Juan Pérez", "juan.perez@iglesia.com", "pastor123", RolUsuario.PASTOR));
        usuarios.put(2L, new Usuario(2L, "Diacona Nancy Gutierrez", "nancy.gomez@iglesia.com", "diacono456",
                RolUsuario.DIACONO));
        usuarios.put(3L, new Usuario(3L, "Tesorero Carlos López", "carlos.lopez@iglesia.com", "tesorero789",
                RolUsuario.TESORERO));
        usuarios.put(4L, new Usuario(4L, "Miembro María Rodríguez", "maria.rodriguez@iglesia.com", "miembro1",
                RolUsuario.PARTICIPANTE));
        usuarios.put(5L, new Usuario(5L, "Miembro Pedro Sánchez", "pedro.sanchez@iglesia.com", "miembro2",
                RolUsuario.PARTICIPANTE));
        usuarios.put(6L, new Usuario(6L, "Miembro Laura Fernández", "laura.fernandez@iglesia.com", "miembro3",
                RolUsuario.PARTICIPANTE));

        cultos.put(101L, new Culto(101L, hoy.plusDays(7), LocalTime.of(11, 0), TipoCulto.ADORACION,
                "Alabanza y Adoración", "Pastor Juan", "Domingo de mañana."));
        cultos.put(102L, new Culto(102L, hoy.plusDays(9), LocalTime.of(19, 30), TipoCulto.ESTUDIO,
                "Estudio Bíblico - Romanos", "Diacono Ana", "Traer Biblia."));
        cultos.put(103L, new Culto(103L, hoy.plusDays(14), LocalTime.of(11, 0), TipoCulto.ADORACION,
                "La Palabra de Dios", "Pastor Juan", "Domingo especial."));

        cultosParticipantes.add(new CultoParticipante(1L, 101L, 4L, "Cantante", "Líder de alabanza."));
        cultosParticipantes.add(new CultoParticipante(2L, 101L, 5L, "Músico (Guitarra)", "Acompañamiento musical."));
        cultosParticipantes.add(new CultoParticipante(3L, 102L, 2L, "Líder de Estudio", "Dirige la discusión."));
        cultosParticipantes.add(new CultoParticipante(4L, 103L, 4L, "Cantante", "Participación especial."));
        cultosParticipantes.add(new CultoParticipante(5L, 103L, 6L, "Ayudante", "Apoyo logístico."));
        asistencias.put(1L, new Asistencia(1L, 1L, 101L));

        asistencias.put(2L, new Asistencia(2L, 2L, 101L));
        asistencias.put(3L, new Asistencia(3L, 3L, 101L));
        asistencias.put(4L, new Asistencia(4L, 4L, 101L));
        asistencias.put(5L, new Asistencia(5L, 5L, 101L));
        asistencias.put(6L, new Asistencia(6L, 1L, 102L));
        asistencias.put(7L, new Asistencia(7L, 2L, 102L));
        asistencias.put(8L, new Asistencia(8L, 4L, 103L));
        asistencias.put(9L, new Asistencia(9L, 5L, 103L));
        asistencias.put(10L, new Asistencia(10L, 6L, 103L));

        diezmosOfrendas.put(1L, new DiezmoOfrenda(1L, 4L, TipoDiezmoOfrenda.DIEZMO, new BigDecimal("50.00"),
                hoy.minusDays(5), "Diezmo semanal."));
        diezmosOfrendas.put(2L, new DiezmoOfrenda(2L, 5L, TipoDiezmoOfrenda.OFRENDA, new BigDecimal("25.50"),
                hoy.minusDays(3), "Ofrenda general."));
        diezmosOfrendas.put(3L, new DiezmoOfrenda(3L, null, TipoDiezmoOfrenda.OFRENDA, new BigDecimal("10.00"),
                hoy.minusDays(1), "Anónimo."));
        diezmosOfrendas.put(4L,
                new DiezmoOfrenda(4L, 6L, TipoDiezmoOfrenda.DIEZMO, new BigDecimal("30.00"), hoy, "Diezmo de hoy."));

        eventosEspeciales.put(1L,
                new EventoEspecial(1L, "Conferencia de Jóvenes", "Un fin de semana inspirador para jóvenes.",
                        hoy.plusMonths(2).withDayOfMonth(15), hoy.plusMonths(2).withDayOfMonth(17), "Laura Fernández"));
        eventosEspeciales.put(2L, new EventoEspecial(2L, "Cena de Acción de Gracias", "Celebración comunitaria.",
                hoy.plusMonths(6).withDayOfMonth(24), null, "Comité de Diáconos"));

        directivas.put(1L, new Directiva(1L, "Junta de Diáconos",
                "Cuerpo de liderazgo para asuntos administrativos y espirituales."));
        directivas.put(2L,
                new Directiva(2L, "Ministerio de Alabanza", "Encargados de la música y la adoración en los cultos."));
        directivas.put(3L, new Directiva(3L, "Ministerio de Educación Cristiana",
                "Responsables de la escuela dominical y otros programas de enseñanza."));

        directivasMiembros.add(new DirectivaMiembro(1L, 1L, 2L, hoy.minusYears(1), null, "Presidente")); // Ana en Junta
                                                                                                         // de Diáconos
        directivasMiembros.add(new DirectivaMiembro(2L, 1L, 3L, hoy.minusYears(1), null, "Secretario")); // Carlos en
                                                                                                         // Junta de
                                                                                                         // Diáconos
        directivasMiembros.add(new DirectivaMiembro(3L, 2L, 4L, hoy.minusMonths(6), null, "Líder de Alabanza")); // María
                                                                                                                 // en
                                                                                                                 // Ministerio
                                                                                                                 // de
                                                                                                                 // Alabanza
        directivasMiembros.add(new DirectivaMiembro(4L, 2L, 5L, hoy.minusMonths(6), null, "Músico")); // Pedro en
                                                                                                      // Ministerio de
                                                                                                      // Alabanza
        directivasMiembros.add(new DirectivaMiembro(5L, 3L, 1L, hoy.minusYears(2), null, "Supervisor")); // Juan en
                                                                                                         // Ministerio
                                                                                                         // de Educación
                                                                                                         // Cristiana
        directivasMiembros.add(new DirectivaMiembro(6L, 3L, 6L, hoy.minusMonths(3), null, "Maestro")); // Laura en
                                                                                                       // Ministerio de
                                                                                                       // Educación
                                                                                                       // Cristiana

    }

    public void cargaDatos() {

        clientes.put("80580845T", new ClienteBuilder("80580845T").setNombre("ANA").setTelefono("658111111")
                .setEmail("ana@gmail.com").build());
        clientes.put("36347775R", new ClienteBuilder("36347775R").setNombre("LOLA").setTelefono("649222222")
                .setEmail("lola@gmail.com").build());
        clientes.put("63921307Y", new ClienteBuilder("63921307Y").setNombre("JUAN").setTelefono("652333333")
                .setEmail("juan@gmail.com").build());
        clientes.put("02337565Y", new ClienteBuilder("02337565Y").setNombre("EDU").setTelefono("634567890")
                .setEmail("edu@gmail.com").build());
        clientes.put("14665825J", new ClienteBuilder("14665825J").setNombre("REYSHEN").setTelefono("678170433")
                .setEmail("reyshen35@gmail.com").build());
        clientes.put("65318802K", new ClienteBuilder("65318802K").setNombre("ORCREMA").setTelefono("688870433")
                .setEmail("orcrema@gmail.com").build());
        clientes.put("76648739C", new ClienteBuilder("76648739C").setNombre("JOSEIRO").setTelefono("634999890")
                .setEmail("negritogay@gmail.com").build());
        clientes.put("51395126Q", new ClienteBuilder("51395126Q").setNombre("ROCES").setTelefono("633330433")
                .setEmail("autism@gmail.com").build());
        clientes.put("88558514G", new ClienteBuilder("88558514G").setNombre("MORO").setTelefono("611178833")
                .setEmail("españa@gmail.com").build());
        clientes.put("47540602Q", new ClienteBuilder("47540602Q").setNombre("PACHECO").setTelefono("686666710")
                .setEmail("samupachebq@gmail.com").build());

        articulos.put("1-11", new Articulo("1-11", "RATON LOGITECH ST ", 14, 15));
        articulos.put("1-22", new Articulo("1-22", "TECLADO STANDARD  ", 9, 18));
        articulos.put("1-33", new Articulo("1-33", "AURICULARES STEELSERIES 9", 20, 50));
        articulos.put("1-44", new Articulo("1-44", "MICRÓFONO USB RAZER", 10, 35));
        articulos.put("2-11", new Articulo("2-11", "HDD SEAGATE 1 TB  ", 16, 80));
        articulos.put("2-22", new Articulo("2-22", "SSD KINGSTOM 256GB", 9, 70));
        articulos.put("2-33", new Articulo("2-33", "SSD KINGSTOM 512GB", 0, 200));
        articulos.put("2-44", new Articulo("2-44", "NVME 1TB SAMSUNG", 8, 120));
        articulos.put("3-11", new Articulo("3-11", "HP OFFICEJET 3830", 6, 90));
        articulos.put("3-22", new Articulo("3-22", "EPSON PRINT XP300 ", 5, 80));
        articulos.put("4-11", new Articulo("4-11", "ASUS  MONITOR  22 ", 5, 100));
        articulos.put("4-22", new Articulo("4-22", "HP MONITOR LED 28 ", 5, 180));
        articulos.put("4-33", new Articulo("4-33", "SAMSUNG ODISSEY G5", 12, 580));
        articulos.put("4-44", new Articulo("4-44", "LG MONITOR 24''", 10, 140));
        articulos.put("5-11", new Articulo("5-11", "PLACA BASE MSI B450", 7, 110));

        LocalDate hoy = LocalDate.now();
        pedidos.add(new Pedido(clientes.get("80580845T"), hoy.minusDays(1),
                new ArrayList<>(List.of(new LineaPedido("1-11", 3), new LineaPedido("4-22", 3))), pedidos));
        pedidos.add(new Pedido(clientes.get("80580845T"), hoy.minusDays(2),
                new ArrayList<>(
                        List.of(new LineaPedido("4-11", 3), new LineaPedido("4-22", 2), new LineaPedido("4-33", 4))),
                pedidos));
        pedidos.add(new Pedido(clientes.get("36347775R"), hoy.minusDays(3),
                new ArrayList<>(List.of(new LineaPedido("4-22", 1), new LineaPedido("2-22", 3))), pedidos));
        pedidos.add(new Pedido(clientes.get("36347775R"), hoy.minusDays(5),
                new ArrayList<>(List.of(new LineaPedido("4-33", 3), new LineaPedido("2-11", 3))), pedidos));
        pedidos.add(new Pedido(clientes.get("63921307Y"), hoy.minusDays(4),
                new ArrayList<>(
                        List.of(new LineaPedido("2-11", 5), new LineaPedido("2-33", 3), new LineaPedido("4-33", 2))),
                pedidos));
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
                ObjectOutputStream oosPedidos = new ObjectOutputStream(new FileOutputStream("pedidos.dat"));
                ObjectOutputStream oosUsuarios = new ObjectOutputStream(new FileOutputStream("usuarios.dat"));
                ObjectOutputStream oosCultos = new ObjectOutputStream(new FileOutputStream("cultos.dat"));
                ObjectOutputStream oosCultosParticipantes = new ObjectOutputStream(
                        new FileOutputStream("cultos_participantes.dat"));
                ObjectOutputStream oosAsistencias = new ObjectOutputStream(new FileOutputStream("asistencias.dat"));
                ObjectOutputStream oosDiezmosOfrendas = new ObjectOutputStream(
                        new FileOutputStream("diezmos_ofrendas.dat"));
                ObjectOutputStream oosEventosEspeciales = new ObjectOutputStream(
                        new FileOutputStream("eventos_especiales.dat"));
                ObjectOutputStream oosDirectivas = new ObjectOutputStream(new FileOutputStream("directivas.dat")); // Nuevo
                ObjectOutputStream oosDirectivasMiembros = new ObjectOutputStream(
                        new FileOutputStream("directivas_miembros.dat")) // Nuevo
        ) {

            // LOS PEDIDOS SE GUARDAN OBJETO A OBJETO
            for (Articulo a : articulos.values()) {
                oosArticulos.writeObject(a);
            }

            for (Cliente c : clientes.values()) {
                oosClientes.writeObject(c);
            }

            for (Pedido p : pedidos) {
                oosPedidos.writeObject(p);
            }

            // Guardar Usuarios
            for (Usuario u : usuarios.values()) {
                oosUsuarios.writeObject(u);
            }

            // Guardar Cultos
            for (Culto c : cultos.values()) {
                oosCultos.writeObject(c);
            }

            // Guardar CultosParticipantes (lista)
            for (CultoParticipante cp : cultosParticipantes) {
                oosCultosParticipantes.writeObject(cp);
            }

            // Guardar Asistencias
            for (Asistencia a : asistencias.values()) {
                oosAsistencias.writeObject(a);
            }

            // Guardar DiezmosOfrendas
            for (DiezmoOfrenda d : diezmosOfrendas.values()) {
                oosDiezmosOfrendas.writeObject(d);
            }

            // Guardar EventosEspeciales
            for (EventoEspecial ee : eventosEspeciales.values()) {
                oosEventosEspeciales.writeObject(ee);
            }

            // Guardar Directivas
            for (Directiva dir : directivas.values()) {
                oosDirectivas.writeObject(dir);
            }

            // Guardar DirectivasMiembros (lista)
            for (DirectivaMiembro dm : directivasMiembros) {
                oosDirectivasMiembros.writeObject(dm);
            }
            System.out.println("Copia de seguridad realizada con éxito.");

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void leerArchivos() {
        // Leer Usuarios
        try (ObjectInputStream oisUsuarios = new ObjectInputStream(new FileInputStream("usuarios.dat"))) {
            while (true) {
                Usuario u = (Usuario) oisUsuarios.readObject();
                usuarios.put(u.getIdUsuario(), u);
            }
        } catch (EOFException e) {
            // Fin del archivo, esperado
        } catch (FileNotFoundException e) {
            System.out.println("Archivo usuarios.dat no encontrado. Se iniciará con una lista de usuarios vacía.");
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error al leer usuarios.dat: " + e.getMessage());
        }

        // Leer Cultos
        try (ObjectInputStream oisCultos = new ObjectInputStream(new FileInputStream("cultos.dat"))) {
            while (true) {
                Culto c = (Culto) oisCultos.readObject();
                cultos.put(c.getIdCulto(), c);
            }
        } catch (EOFException e) {
            // Fin del archivo, esperado
        } catch (FileNotFoundException e) {
            System.out.println("Archivo cultos.dat no encontrado. Se iniciará con una lista de cultos vacía.");
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error al leer cultos.dat: " + e.getMessage());
        }

        // Leer CultosParticipantes
        try (ObjectInputStream oisCultosParticipantes = new ObjectInputStream(
                new FileInputStream("cultos_participantes.dat"))) {
            while (true) {
                CultoParticipante cp = (CultoParticipante) oisCultosParticipantes.readObject();
                cultosParticipantes.add(cp);
            }
        } catch (EOFException e) {
            // Fin del archivo, esperado
        } catch (FileNotFoundException e) {
            System.out.println("Archivo cultos_participantes.dat no encontrado. Se iniciará con una lista vacía.");
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error al leer cultos_participantes.dat: " + e.getMessage());
        }

        // Leer Asistencias
        try (ObjectInputStream oisAsistencias = new ObjectInputStream(new FileInputStream("asistencias.dat"))) {
            while (true) {
                Asistencia a = (Asistencia) oisAsistencias.readObject();
                asistencias.put(a.getIdAsistencia(), a);
            }
        } catch (EOFException e) {
            // Fin del archivo, esperado
        } catch (FileNotFoundException e) {
            System.out
                    .println("Archivo asistencias.dat no encontrado. Se iniciará con una lista de asistencias vacía.");
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error al leer asistencias.dat: " + e.getMessage());
        }

        // Leer DiezmosOfrendas
        try (ObjectInputStream oisDiezmosOfrendas = new ObjectInputStream(
                new FileInputStream("diezmos_ofrendas.dat"))) {
            while (true) {
                DiezmoOfrenda d = (DiezmoOfrenda) oisDiezmosOfrendas.readObject();
                diezmosOfrendas.put(d.getIdRegistro(), d);
            }
        } catch (EOFException e) {
            // Fin del archivo, esperado
        } catch (FileNotFoundException e) {
            System.out.println("Archivo diezmos_ofrendas.dat no encontrado. Se iniciará con una lista vacía.");
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error al leer diezmos_ofrendas.dat: " + e.getMessage());
        }

        // Leer EventosEspeciales
        try (ObjectInputStream oisEventosEspeciales = new ObjectInputStream(
                new FileInputStream("eventos_especiales.dat"))) {
            while (true) {
                EventoEspecial ee = (EventoEspecial) oisEventosEspeciales.readObject();
                eventosEspeciales.put(ee.getIdEvento(), ee);
            }
        } catch (EOFException e) {
            // Fin del archivo, esperado
        } catch (FileNotFoundException e) {
            System.out.println("Archivo eventos_especiales.dat no encontrado. Se iniciará con una lista vacía.");
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error al leer eventos_especiales.dat: " + e.getMessage());
        }

        // Leer Directivas
        try (ObjectInputStream oisDirectivas = new ObjectInputStream(new FileInputStream("directivas.dat"))) {
            while (true) {
                Directiva dir = (Directiva) oisDirectivas.readObject();
                directivas.put(dir.getIdDirectiva(), dir);
            }
        } catch (EOFException e) {
            // Fin del archivo, esperado
        } catch (FileNotFoundException e) {
            System.out.println("Archivo directivas.dat no encontrado. Se iniciará con una lista de directivas vacía.");
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error al leer directivas.dat: " + e.getMessage());
        }

        // Leer DirectivasMiembros
        try (ObjectInputStream oisDirectivasMiembros = new ObjectInputStream(
                new FileInputStream("directivas_miembros.dat"))) {
            while (true) {
                DirectivaMiembro dm = (DirectivaMiembro) oisDirectivasMiembros.readObject();
                directivasMiembros.add(dm);
            }
        } catch (EOFException e) {
            // Fin del archivo, esperado
        } catch (FileNotFoundException e) {
            System.out.println("Archivo directivas_miembros.dat no encontrado. Se iniciará con una lista vacía.");
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error al leer directivas_miembros.dat: " + e.getMessage());
        }
        try (ObjectInputStream oisArticulos = new ObjectInputStream(new FileInputStream("articulos.dat"))) {
            Articulo a;
            while ((a = (Articulo) oisArticulos.readObject()) != null) {
                articulos.put(a.getIdArticulo(), a);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (EOFException e) {

        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.toString());
        }

        try (ObjectInputStream oisClientes = new ObjectInputStream(new FileInputStream("clientes.dat"))) {
            Cliente c;
            while ((c = (Cliente) oisClientes.readObject()) != null) {
                clientes.put(c.getDni(), c); // parte variable
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (EOFException e) {

        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.toString());
        }

        try (ObjectInputStream oisPedidos = new ObjectInputStream(new FileInputStream("pedidos.dat"))) {
            Pedido p;
            while ((p = (Pedido) oisPedidos.readObject()) != null) {
                pedidos.add(p);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        } catch (EOFException e) {

        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.toString());
        }

    }

    public void backupSeccion() {
        try (ObjectOutputStream oisImpresora = new ObjectOutputStream(new FileOutputStream("./Data/impresoras.dat"));
                ObjectOutputStream oisMonitores = new ObjectOutputStream(new FileOutputStream("monitores.dat"));
                ObjectOutputStream oisAlmacenamiento = new ObjectOutputStream(
                        new FileOutputStream("almacenamiento.dat"));
                ObjectOutputStream oisPerifericos = new ObjectOutputStream(new FileOutputStream("perifericos.dat"));
                ObjectOutputStream oisComponentes = new ObjectOutputStream(new FileOutputStream("componentes.dat"))) {
            for (Articulo a : articulos.values()) {
                if (a.getSeccion() == 3) {
                    oisImpresora.writeObject(a);
                }
            }
            for (Articulo a : articulos.values()) {
                if (a.getSeccion() == 1) {
                    oisPerifericos.writeObject(a);
                }
            }
            for (Articulo a : articulos.values()) {
                if (a.getSeccion() == 2) {
                    oisAlmacenamiento.writeObject(a);
                }
            }
            for (Articulo a : articulos.values()) {
                if (a.getSeccion() == 4) {
                    oisMonitores.writeObject(a);
                }
            }
            for (Articulo a : articulos.values()) {
                if (a.getSeccion() == 5) {
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
     * public void persistirTienda() throws IOException {
     * ObjectOutputStream oos;
     * try {
     * oos = new ObjectOutputStream(new FileOutputStream("Tienda.dat"));
     * } catch (IOException e) {
     * throw new RuntimeException(e);
     * }
     * oos.writeObject(Tienda);
     * }
     * 
     */
    // endregion

}
