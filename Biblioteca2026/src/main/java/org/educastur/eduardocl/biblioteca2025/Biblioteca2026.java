/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.educastur.eduardocl.biblioteca2025;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.educastur.eduardocl.biblioteca2025.Libro;
import org.educastur.eduardocl.biblioteca2025.Prestamo;
import org.educastur.eduardocl.biblioteca2025.Usuario;

/**
 * Este es el proyecto de la biblioteca
 *
 * @author eduardo
 */
public class Biblioteca2026 {

    private static ArrayList<Libro> libros = new ArrayList();
    private static ArrayList<Usuario> usuarios = new ArrayList();
    private static ArrayList<Prestamo> prestamos = new ArrayList();
    private static ArrayList<Prestamo> prestamosHist = new ArrayList();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargaDatosExam();
        //listadosConStreams();
        
        ordenarConStreams();
        //menu(); 
        
    }

    //<editor-fold defaultstate="collapsed" desc="PRUEBA OBJETIVA 12-12-2025">
     public static void uno() {
        System.out.print("ISBN LIBRO para consultar prestamos: ");
        String isbn = sc.next();
        int pos = buscaIsbn(isbn);
        if (pos == -1) {
            System.out.println("Ese ISBN no existe en la biblioteca");
        } else {
            System.out.println("Prestamos activos de el libro:  " + libros.get(pos).getTitulo());
            int contador = 0;
            for (Prestamo p : prestamos) {
                if (p.getLibroPrest().getIsbn().equals(isbn)) {
                    System.out.println(p);
                    contador++;
                }
            }
            System.out.println("\n" + libros.get(pos).getTitulo() + " tiene " + contador + " prestamos actualmente");
        }
    }

    public static void dos() {
        System.out.println("\nLibros NUNCA prestados:\n");
        for (Libro l : libros) {
            int cont=0;
            for (Prestamo p : prestamos) {
                if (p.getLibroPrest().equals(l)) {
                    cont++;
                    break;
                }
            }
            if (cont==0){
                for (Prestamo p : prestamosHist) {
                    if (p.getLibroPrest() == l) {
                        cont++;
                        break;
                    }
                }
            }
            if (cont==0) {
                System.out.println(l);
            }
        }
    }
    
    public static void tres() {
        System.out.println("Usuarios que tiene al menos 1 prestamo ACTIVO:\n"); 
        for(Usuario u : usuarios) {
            int cont=0;
            
            try {
                
            } catch (Exception e) {
            }
            
            
            
            
            try {
                for(int i=0;i<=prestamos.size();i++) {
                    if(prestamos.get(i).getUsuarioPrest() == u) {
                        cont++;
                        //break;
                    }
                }
            } catch (Exception e) {
                
            }
            
            
            if (cont>0){
               System.out.println(u.getNombre()+ " tiene " + cont);
            }
	}
    }

    public static void cuatro() {
         /* Se podría resolver con un contador (cont) al igual que los ejercicios
            anteriores, pero en este caso utilizamos una variable booleana (true/false).
            Es otra forma de resolver la lógica de este tipo de ejercicios
         */
        System.out.println("Usuarios que tiene PRESTAMOS FUERA DE PLAZO:\n"); 
        for(Usuario u : usuarios) {
            boolean tieneFueraPlazo = false;
            for(Prestamo p : prestamos) {
                    if(p.getUsuarioPrest().equals(u) && p.getFechaDev().isBefore(LocalDate.now())) {
                        tieneFueraPlazo = true;
                        break;
                    }
            }
            if(tieneFueraPlazo == true) {
                    System.out.println(u);
            }
	}
    }

    public static void cinco() {
        System.out.println("PRESTAMOS realizados en el mes de NOVIEMBRE:\n"); 
        for(Prestamo p : prestamos) {
            if(p.getFechaPrest().getMonthValue()==11) {
                System.out.println(p);
            }
        }
        for(Prestamo p : prestamosHist) {
            if(p.getFechaPrest().getMonthValue()==11) {
                System.out.println(p);
            }
        }
    }
//</editor-fold>
       
    public static void listadosConStreams(){
        //Listados generales de libros y usuarios con STREAMS
        System.out.println("Libros listados desde un STREAM:");
        libros.stream().forEach(l->System.out.println(l));
        System.out.println("\nUsuarios listados desde un STREAM:");
        usuarios.stream().forEach(u->System.out.println(u));
        
        //Listados selectivos (filter) con STREAMS
        System.out.println("\nLibros de la seccion aventuras:");
        libros.stream().filter(l-> l.getGenero().equalsIgnoreCase("aventuras"))
                       .forEach(l->System.out.println(l));
        
        System.out.println("\nLibros de la seccion novela negra o del autor JRR tolkien:");
        libros.stream().filter(l-> l.getGenero().equalsIgnoreCase("novela negra")
                       || l.getAutor().equalsIgnoreCase("jrr tolkien"))
                       .forEach(l->System.out.println(l));
        
        System.out.println("\nPrestamos fuera de plazo:");
        prestamos.stream().filter(p-> p.getFechaDev().isBefore(LocalDate.now()))
                       .forEach(p->System.out.println(p));
        
        System.out.println("\nPrestamos activos y no activos del usuario(teclear NOMBRE):");
        String nombre=sc.next();
        prestamos.stream().filter(p->p.getUsuarioPrest().getNombre().equalsIgnoreCase(nombre))
                .forEach(p->System.out.println(p));
        prestamosHist.stream().filter(p->p.getUsuarioPrest().getNombre().equalsIgnoreCase(nombre))
                .forEach(p->System.out.println(p));
        
        System.out.println("\nPrestamos Fuera de plazo de un usuario(teclear NOMBRE):");
        String nombre2=sc.next();
        prestamos.stream().filter(p->p.getUsuarioPrest().getNombre().equalsIgnoreCase(nombre2)
                        && p.getFechaDev().isBefore(LocalDate.now()))
                        .forEach(p->System.out.println(p));
        
        System.out.println("\nPrestamos activos de libros del genero aventuras:");
        prestamos.stream().filter(p->p.getLibroPrest().getGenero().equalsIgnoreCase("aventuras"))
                .forEach(p->System.out.println(p));
    }  
    
    public static void ordenarConStreams(){
        System.out.println("\nListado de libros ordenados alfabeticamente por título:");
        libros.stream().sorted(Comparator.comparing(Libro::getTitulo)).forEach(l->System.out.println(l));
     
        System.out.println("\nListado de libros ordenados por ejemplares de + a -:");
        libros.stream().sorted(Comparator.comparing(Libro::getEjemplares).reversed()).forEach(l->System.out.println(l));
    
        
        System.out.println("\nListado de Prestamos ordenados por Fecha prestamo:");
        prestamos.stream().sorted(Comparator.comparing(Prestamo::getFechaPrest)).forEach(p->System.out.println(p));
        
        System.out.println("\nListado de libros ordenados por número de prestamos:");
        libros.stream().sorted(Comparator.comparing(l->numPrestamosLibro((Libro) l)).reversed())
                .forEach(l->System.out.println(l + " Unidades prestadas: " + numPrestamosLibro(l)));
    
        
    }
    
    public static int numPrestamosLibro(Libro l){
        int cont=0;
        for (Prestamo p : prestamos) {
            if (p.getLibroPrest().equals(l)){
                cont++;
            }
        }
        for (Prestamo p : prestamosHist) {
            if (p.getLibroPrest().equals(l)){
                cont++;
            }
        }
        return cont;
    }
    
    
    
    public static void cargaDatosExam() {
        libros.add(new Libro("1-11", "El Hobbit", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-22", "El Silmarillon", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-33", "El Medico", "N. Gordon", "Aventuras", 4));
        libros.add(new Libro("1-44", "Chaman", "N. Gordon", "Aventuras", 3));
        libros.add(new Libro("1-55", "Momo", "M. Ende", "Aventuras", 2));
        libros.add(new Libro("1-66", "Paraiso inhabitado", "A.M.Matute", "Novela", 2));
        libros.add(new Libro("1-77", "Olvidado Rey Gudu", "A.M.Matute", "Novela", 0));
        libros.add(new Libro("1-88", "El ultimo barco", "D.Villar", "Novela Negra", 3));
        libros.add(new Libro("1-99", "Ojos de agua", "D.Villar", "Novela Negra", 0));

        usuarios.add(new Usuario("11", "Ana", "ana@email.com", "621111111"));
        usuarios.add(new Usuario("22", "David", "david@email.com", "622222222"));
        usuarios.add(new Usuario("33", "Bea", "bea@email.com", "623333333"));
        usuarios.add(new Usuario("44", "Lucas", "lucas@email.com", "624444444"));
        usuarios.add(new Usuario("55", "Carlota", "carlota@email.com", "625555555"));
        usuarios.add(new Usuario("66", "Juan", "juan@email.com", "626666666"));

        LocalDate hoy = LocalDate.now(); //OBTENEMOS LA FECHA DE HOY CON EL MÉTODO now()

        //PRESTAMOS "NORMALES" REALIZADOS HOY Y QUE SE HAN DE DEVOLVER EN 15 DÍAS
        prestamos.add(new Prestamo(libros.get(0), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(0), usuarios.get(4), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(0), usuarios.get(1), hoy, hoy.plusDays(15)));
        //PRESTAMOS QUE YA TENIAN QUE HABER SIDO DEVUELTOS PORQUE SU FECHA DE DEVOLUCIÓN ES ANTERIOR A HOY
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(1), hoy.minusDays(17), hoy.minusDays(2)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(4), hoy.minusDays(18), hoy.minusDays(3)));
        prestamos.add(new Prestamo(libros.get(2), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(3), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));

        //PRESTAMOS HISTORICOS QUE YA HAN SIDO DEVUELTOS Y POR TANTO ESTÁN EN LA COLECCION prestamosHist
        prestamosHist.add(new Prestamo(libros.get(0), usuarios.get(0), hoy.minusDays(30), hoy.minusDays(15)));
        prestamosHist.add(new Prestamo(libros.get(2), usuarios.get(0), hoy.minusDays(30), hoy.minusDays(15)));
        prestamosHist.add(new Prestamo(libros.get(7), usuarios.get(4), hoy.minusDays(30), hoy.minusDays(15)));
        prestamosHist.add(new Prestamo(libros.get(5), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(15)));
        prestamosHist.add(new Prestamo(libros.get(1), usuarios.get(1), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(7), usuarios.get(2), hoy.minusDays(10), hoy));
        prestamosHist.add(new Prestamo(libros.get(6), usuarios.get(3), hoy.minusDays(10), hoy));
    }

    //<editor-fold defaultstate="collapsed" desc="MENUS">
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tBIBLIOTECA\n");
            System.out.println("\t\t\t\t1 - LIBROS");
            System.out.println("\t\t\t\t2 - USUARIOS");
            System.out.println("\t\t\t\t3 - PRESTAMOS");
            System.out.println("\t\t\t\t4 - LISTADOS");
            System.out.println("\t\t\t\t9 - SALIR");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: {
                    menuLibros();
                    break;
                }
                case 2: {
                    menuUsuarios();
                    break;
                }
                case 3: {
                    menuPrestamos();
                    break;
                }
                case 4: {
                    menuListados();
                    break;
                }
            }
        } while (opcion != 9);
    }

    private static void menuLibros() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tLIBROS\n");
            System.out.println("\t\t\t\t1 - NUEVO LIBRO");
            System.out.println("\t\t\t\t2 - ELIMINAR LIBRO");
            System.out.println("\t\t\t\t3 - MODIFICAR LIBRO");
            System.out.println("\t\t\t\t4 - LISTADO GENERAL DE LIBROS");
            System.out.println("\t\t\t\t9 - SALIR");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: {
                    nuevoLibro();
                    break;
                }
                case 2: {
                    eliminarLibro();
                    break;
                }
                case 3: {
                    modificarLibros();
                    break;
                }
                case 4: {
                    listaLibros();
                    break;
                }
            }
        } while (opcion != 9);
    }

    private static void menuUsuarios() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tUSUARIOS");
            System.out.println("\t\t\t\t1 - NUEVO USUARIO");
            System.out.println("\t\t\t\t2 - ELIMINAR USUARIO");
            System.out.println("\t\t\t\t3 - MODIFICAR USUARIO");
            System.out.println("\t\t\t\t4 - LISTADO GENERAL DE USUARIOS");
            System.out.println("\t\t\t\t9 - SALIR");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: {
                    nuevoUsuario();
                    break;
                }
                case 2: {
                    eliminarUsuario();
                    break;
                }
                case 3: {
                    modificarUsuario();
                    break;
                }
                case 4: {
                    listaUsuarios();
                    break;
                }
            }
        } while (opcion != 9);
    }

    private static void menuPrestamos() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tPRESTAMOS\n");
            System.out.println("\t\t\t\t1 - NUEVO PRESTAMO");
            System.out.println("\t\t\t\t2 - DEVOLUCION PRESTAMO");
            System.out.println("\t\t\t\t3 - PRORROGAR PRESTAMO");
            System.out.println("\t\t\t\t4 - LISTADO GENERAL DE PRESTAMOS");
            System.out.println("\t\t\t\t9 - SALIR");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: {
                    nuevoPrestamo();
                    break;
                }
                case 2: {
                    devolucion();
                    break;
                }
                case 3: {
                    prorroga();
                    break;
                }
                case 4: {
                    listaPrestamos();
                    break;
                }
            }
        } while (opcion != 9);
    }

    private static void menuListados() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tLISTADOS\n");
            System.out.println("\t\t\t\t1 - LISTADO 1");
            System.out.println("\t\t\t\t2 - LISTADO 2");
            System.out.println("\t\t\t\t3 - LISTADO 3");
            System.out.println("\t\t\t\t4 - LISTADO 4");
            System.out.println("\t\t\t\t9 - SALIR");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1: {
                    listaPrestamosUsu();
                    break;
                }
                case 2: {
                    listaPrestamosLibro();
                    break;
                }
                case 3: {
                    libroMasLeido();
                    break;
                }
                case 4: {
                    usuarioMasLector();
                    break;
                }
            }
        } while (opcion != 9);
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="GESTION LIBROS">
    private static void nuevoLibro() {
        Scanner sc = new Scanner(System.in);

        String dni;
        do {
            System.out.println("teclea DNI:");
            dni = sc.next();
        } while (!MetodosAux.validarDNI(dni));
        System.out.println("El dni bueno es: " + dni);

        String unidadesSt;
        do {
            System.out.println("Unidades disponibles LIBRO:");
            unidadesSt = sc.next();
        } while (!MetodosAux.esInt(unidadesSt));
        int unidades = Integer.parseInt(unidadesSt);

        String precioSt;
        do {
            System.out.println("Precio LIBRO:");
            precioSt = sc.next();
        } while (!MetodosAux.esDouble(precioSt));
        double precio = Double.parseDouble(precioSt);

        System.out.println("Unidades tecleadas (En formato int: " + unidades);
        System.out.println("precio (En formato Double: " + precio);
    }

    private static void eliminarLibro() {
    }

    private static void modificarLibros() {
    }

    private static void listaLibros() {
        for (Libro l : libros) {
            System.out.println(l);
        }
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="GESTION USUARIOS">
    private static void nuevoUsuario() {
    }

    private static void eliminarUsuario() {
    }

    private static void modificarUsuario() {
    }

    private static void listaUsuarios() {
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GESTION PRESTAMOS">
    /**
     * Método básico V1 para realizar un nuevo préstamo
     */
   
    private static void nuevoPrestamo() {
        System.out.println("Identificacion del usuario:");
        String dni=sc.next();
        
        int posUsuario = buscaDni(dni);
        if (posUsuario==-1){
            System.out.println("No es usuario de la biblioteca");
        }else{
            System.out.println("Identificacion del libro:"); 
            String isbn=sc.next();
            
            try{
                int posLibro=stockLibro(isbn); // LLAMO AL METODO stockLibro, PUEDEN SALTAR 2 EXCEPCIONES
                LocalDate hoy=LocalDate.now();
                prestamos.add(new Prestamo(libros.get(posLibro),usuarios.get(posUsuario),hoy,hoy.plusDays(15)));
                libros.get(posLibro).setEjemplares(libros.get(posLibro).getEjemplares()-1);
            }catch (LibroNoExiste ex){
                System.out.println(ex.getMessage());
            }catch (LibroNoDisponible ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    
    /**
     * Método para realizar una prórroga a un préstamo ampliando su fechaDev 15
     * días más
     */
    private static void prorroga() {
        System.out.print("DNI usuario: ");
        String dni = sc.next();
        System.out.print("ISBN Libro: ");
        String isbn = sc.next();
        int posPrestamo = buscaPrestamo(dni, isbn);
        if (posPrestamo == -1) {
            System.out.println("No hay ningun préstamo con esos datos");
        } else {
            prestamos.get(posPrestamo).setFechaDev(prestamos.get(posPrestamo).getFechaDev().plusDays(15));
            System.out.println("Se amplió el plazo del préstamo 15 días");
        }
    }

    /**
     * Método para realizar una devolución. Consiste en poner hoy como FechaDev,
     * añadir el préstamo a la colección prestamosHist y eliminarlo de la
     * colección prestamos
     */
    private static void devolucion() {
        System.out.print("DNI usuario: ");
        String dni = sc.next();
        System.out.print("ISBN Libro: ");
        String isbn = sc.next();
        int posPrestamo = buscaPrestamo(dni, isbn);
        if (posPrestamo == -1) {
            System.out.println("No hay ningun préstamo con esos datos");
        } else {
            prestamos.get(posPrestamo).setFechaDev(LocalDate.now());
            libros.get(buscaIsbn(isbn))
                    .setEjemplares(libros.get(buscaIsbn(isbn)).getEjemplares() + 1);
            prestamosHist.add(prestamos.get(posPrestamo));
            prestamos.remove(posPrestamo);
        }
    }

    private static void listaPrestamos() {

        System.out.println("Préstamos activos fuera de plazo");
        for (Prestamo p : prestamos) {
            if (p.getFechaDev().isBefore(LocalDate.now())) {
                System.out.println(p);
            }
        }
        System.out.println("");
        System.out.println("Préstamos activos y en plazo");
        for (Prestamo p : prestamos) {
            if (!p.getFechaDev().isBefore(LocalDate.now())) {
                System.out.println(p);
            }
        }
        System.out.println("");
        System.out.println("\nListado de prestamos historicos");
        for (Prestamo p : prestamosHist) {
            System.out.println(p);
        }
    }

    private static void listaPrestamosUsu() {
        String dni = solicitaDni();
        int pos = buscaDni(dni);

        if (pos == -1) {
            System.out.println("No tengo a nadie con ese DNI");
        } else {
            System.out.println("Prestamos activos de: "
                    + usuarios.get(pos).getNombre());
            for (Prestamo p : prestamos) {
                if (p.getUsuarioPrest().getDni().equals(dni)) {
                    if (p.getFechaDev().isBefore(LocalDate.now())) {
                        System.out.print("Libro fuera de plazo: ");
                    }
                    System.out.println(p);
                }
            }
            System.out.println("\nPrestamos ya devueltos por: "
                    + usuarios.get(pos).getNombre());
            for (Prestamo p : prestamosHist) {
                if (p.getUsuarioPrest().getDni().equals(dni)) {
                    System.out.println(p);
                }
            }
        }
    }

    private static void listaPrestamosLibro() {
        String isbn = solicitaIsbn();
        int pos = buscaIsbn(isbn);
        if (pos == -1) {
            System.out.println("No tengo ningún libro con ese ISBN");
        } else {
            System.out.println("Usuarios/as que lo estan leyendo");
            for (Prestamo p : prestamos) {
                if (p.getLibroPrest().getIsbn().equals(isbn)) {
                    System.out.println(p.getUsuarioPrest());
                }
            }

            System.out.println("Usuarios/as que ya lo han leido");
            for (Prestamo p : prestamosHist) {
                if (p.getLibroPrest().getIsbn().equals(isbn)) {
                    System.out.println(p.getUsuarioPrest());
                }
            }
        }
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="LISTADOS">
    private static void libroMasLeido() {
        ArrayList<Integer> contadoresLibros = new ArrayList();
        int contador;
        for (Libro l : libros) {
            contador = 0;
            for (Prestamo p : prestamos) {
                if (l == p.getLibroPrest()) {
                    contador++;
                }
            }
            contadoresLibros.add(contador);

            //Si hay prestamosHist hay que recorrerlo y contabilizar tb
        }

        int max = 0;
        for (int c : contadoresLibros) {
            if (c > max) {
                max = c;
            }
        }
        //ALTERNATIVA max = Collections.max(contadoresLibros);

        System.out.println("El libro/s mas leido/s con " + max + " prestamos es/son: ");

        for (int i = 0; i < contadoresLibros.size(); i++) {
            if (contadoresLibros.get(i) == max) {
                System.out.println(libros.get(i));
            }
        }

        System.out.println("");

        /* CON STREAMS 
        Si existe prestamosHist hay que fusionar ambas colecciones prestamos y prestamosHist
        ArrayList <Prestamo> prestamosTodos= new ArrayList(prestamos);
        prestamosTodos.addAll(prestamosHist);
         */
        Map<Libro, Long> librosLecturas = prestamos.stream()
                .map(p -> p.getLibroPrest())
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        Long maxLecturas = Collections.max(librosLecturas.values());
        System.out.println("El máximo de veces que un libro ha sido préstado es: " + maxLecturas);
        // Para encontrar los libros asociados al valor máximo
        for (Map.Entry<Libro, Long> entry : librosLecturas.entrySet()) {
            if (entry.getValue().equals(maxLecturas)) {
                System.out.println(entry.getKey());
            }
        }
    }

    private static void usuarioMasLector() {
        ArrayList<Integer> contadoresUsuarios = new ArrayList();
        int contador;
        for (Usuario u : usuarios) {
            contador = 0;
            for (Prestamo p : prestamos) {
                if (u == p.getUsuarioPrest()) {
                    contador++;
                }
            }
            //Si hay prestamosHist hay que recorrerlo y contabilizar tb

            contadoresUsuarios.add(contador);
        }

        int max = 0;
        for (int c : contadoresUsuarios) {
            if (c > max) {
                max = c;
            }
        }
        //ALTERNATIVA max = Collections.max(contadoresUsuarios);

        System.out.println("Los usuarios/as mas lectores/as con " + max + " prestamos son: ");

        for (int i = 0; i < contadoresUsuarios.size(); i++) {
            if (contadoresUsuarios.get(i) == max) {
                System.out.println(usuarios.get(i));
            }
        }

        /* CON STREAMS 
        Si existe prestamosHist hay que fusionar ambas colecciones prestamos y prestamosHist
        ArrayList <Prestamo> prestamosTodos= new ArrayList(prestamos);
        prestamosTodos.addAll(prestamosHist);
         */
        Map<Usuario, Long> usuariosLecturas = prestamos.stream()
                .map(p -> p.getUsuarioPrest())
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        Long maxLecturas = Collections.max(usuariosLecturas.values());
        System.out.println("El máximo préstamos que ha realizado un usuario ha sido: " + maxLecturas);
        // Para encontrar los usuarios asociados al valor máximo
        for (Map.Entry<Usuario, Long> entry : usuariosLecturas.entrySet()) {
            if (entry.getValue().equals(maxLecturas)) {
                System.out.println(entry.getKey());
            }
        }

    }
//</editor-fold>
     
    //<editor-fold defaultstate="collapsed" desc="OTROS METODOS">
     public static int stockLibro(String isbn) throws LibroNoExiste, LibroNoDisponible {
        int pos = buscaIsbn(isbn);
        if (pos == -1) {
            throw new LibroNoExiste("No existe en esta biblioteca la referencia: " + isbn);
        } else if (libros.get(pos).getEjemplares() == 0) {
            String cadena = "No hay unidades del libro " + libros.get(pos).getTitulo()
                    + " disponibles actualmente"
                    + "\nFechas de devolución previstas: ";
            for (Prestamo p : prestamos) {
                if (p.getLibroPrest().getIsbn().equals(isbn)) {
                    cadena = cadena + "\n * " + p.getFechaDev();
                }
            }
            throw new LibroNoDisponible(cadena);
        }
        return pos;
    }

    public static void fueraPlazo() {
        System.out.println("Prestamos fuera de plazo:");
        for (Prestamo p : prestamos) {
            if (p.getFechaDev().isBefore(LocalDate.now())) {
                System.out.println(p);
            }
        }
    }

    public static String solicitaDni() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Teclea el dni del usuario:");
        String dni = sc.next();
        return dni;
    }

    /**
     * Método para solicitar por teclado el ISBN de un libro. pdte de validación
     *
     * @return (String) isbn del libro tecleado
     */
    public static String solicitaIsbn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Teclea el isbn del libro:");
        String isbn = sc.next();
        return isbn;
    }

    /**
     * Método para buscar un dni en la colección usuarios
     *
     * @param dni (String) del usuario a buscar en la colección
     * @return posición (int) del usuario en el Arraylist, valor -1 si no se
     * encuentra
     */
    public static int buscaDni(String dni) {
        int pos = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getDni().equals(dni)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    /**
     * Método para buscar un libro en la colección libros
     *
     * @param isbn (String) del libro a buscar en la colección
     * @return posición (int) del libro el Arraylist, valor -1 si no se
     * encuentra
     */
    public static int buscaIsbn(String isbn) {
        int pos = -1;
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getIsbn().equals(isbn)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    /**
     * Método para buscar un préstamo en la colección préstamos
     *
     * @param dni (String) del usuario que realizó el préstamo
     * @param isbn (String) del libro prestado
     * @return posición (int) del préstamo en el Arraylist, valor -1 si no se
     * encuentra un préstamo con esos datos
     */
    public static int buscaPrestamo(String dni, String isbn) {
        int pos = -1;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getUsuarioPrest().getDni().equals(dni)
                    && prestamos.get(i).getLibroPrest().getIsbn().equals(isbn)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public static void cargaDatos() {
        libros.add(new Libro("1-11", "El Hobbit", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-22", "El Silmarillon", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-33", "El Medico", "N. Gordon", "Aventuras", 4));
        libros.add(new Libro("1-44", "Chaman", "N. Gordon", "Aventuras", 3));
        libros.add(new Libro("1-55", "Momo", "M. Ende", "Aventuras", 2));
        libros.add(new Libro("1-66", "Paraiso inhabitado", "A.M.Matute", "Aventuras", 2));
        libros.add(new Libro("1-77", "Olvidado Rey Gudu", "A.M.Matute", "Aventuras", 0));
        libros.add(new Libro("1-88", "El ultimo barco", "D.Villar", "Novela Negra", 3));
        libros.add(new Libro("1-99", "Ojos de agua", "D.Villar", "Novela Negra", 0));

        usuarios.add(new Usuario("11", "Ana", "ana@email.com", "621111111"));
        usuarios.add(new Usuario("22", "David", "david@email.com", "622222222"));
        usuarios.add(new Usuario("33", "Bea", "bea@email.com", "623333333"));
        usuarios.add(new Usuario("44", "Lucas", "lucas@email.com", "624444444"));
        usuarios.add(new Usuario("55", "Carlota", "carlota@email.com", "625555555"));
        usuarios.add(new Usuario("66", "Juan", "juan@email.com", "626666666"));

        LocalDate hoy = LocalDate.now(); //OBTENEMOS LA FECHA DE HOY CON EL MÉTODO now()

        //PRESTAMOS "NORMALES" REALIZADOS HOY Y QUE SE HAN DE DEVOLVER EN 15 DÍAS
        prestamos.add(new Prestamo(libros.get(0), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(6), usuarios.get(4), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(6), usuarios.get(1), hoy, hoy.plusDays(15)));
        //PRESTAMOS QUE YA TENIAN QUE HABER SIDO DEVUELTOS PORQUE SU FECHA DE DEVOLUCIÓN ES ANTERIOR A HOY
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(1), hoy.minusDays(17), hoy.minusDays(2)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(4), hoy.minusDays(18), hoy.minusDays(3)));
        prestamos.add(new Prestamo(libros.get(2), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(3), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));

        //PRESTAMOS HISTORICOS QUE YA HAN SIDO DEVUELTOS Y POR TANTO ESTÁN EN LA COLECCION prestamosHist
        prestamosHist.add(new Prestamo(libros.get(0), usuarios.get(0), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(2), usuarios.get(0), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(7), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(5), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(1), usuarios.get(1), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(7), usuarios.get(2), hoy.minusDays(15), hoy));
        prestamosHist.add(new Prestamo(libros.get(6), usuarios.get(3), hoy.minusDays(15), hoy));
    }   
    
    //</editor-fold>
    
    
   
        
}
