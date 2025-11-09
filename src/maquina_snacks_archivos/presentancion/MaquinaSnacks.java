
package maquina_snacks_archivos.presentancion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import maquina_snacks_archivos.dominio.Snack;
import maquina_snacks_archivos.servicio.IServicioSnacks;
import maquina_snacks_archivos.servicio.ServicioSnacksArchivos;

public class MaquinaSnacks {

    public static void main(String[] args) {
        maquinaSnacks();
        

    }
    
    public static void maquinaSnacks(){
        var salir = false;
        var sc = new Scanner(System.in);
        // creamos objeto para utilizar servicio de snacks
        IServicioSnacks servicioSnacks = new ServicioSnacksArchivos();
        
        
        List<Snack> productos = new ArrayList<>();
        
        System.out.println("*** Maquina de Snacks ***");
        servicioSnacks.mostrarSnacks();
        
        while(!salir){
            try{
                var opcion = mostrarMenu(sc);
                salir = ejecutarOpciones(opcion, sc, productos, servicioSnacks);
                
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
            }
            finally {
                System.out.println(); // salto de linea por cada iteracion
            }
            
        }
                
    }
    
    private static int mostrarMenu(Scanner sc){
        System.out.println("""
                           Menu:
                           1. Comprar snack
                           2. Mostrar ticket
                           3. Agregar nuevo Snack
                           4. Inventario de Snack
                           5. Salir
                           Elige una opcion:\s""");
        return Integer.parseInt(sc.nextLine());
    }
    
    
    private static boolean ejecutarOpciones(int opcion, Scanner sc, List<Snack> productos, IServicioSnacks servicioSnacks){
        var salir = false;
        
        switch(opcion){
            case 1 -> comprarSnack(sc, productos, servicioSnacks);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(sc, servicioSnacks);
            case 4 -> listarInventario(sc, servicioSnacks);
            case 5 -> {
                System.out.print("Saliendo...");
                salir = true;
            }
            default -> System.out.println("Opción no válida, ingrese un numero del 1 al 4");
        }

        
        return salir;  
    }
    
    private static void listarInventario(Scanner sc, IServicioSnacks servicioSnacks){
        servicioSnacks.mostrarSnacks();
    }
    
    private static void comprarSnack(Scanner sc, List<Snack> productos,IServicioSnacks servicioSnacks){
        System.out.print("Que snack quieres comprar (id)?");
        var idSnack = Integer.parseInt(sc.nextLine());
        //validar
        var snackEncontrado = false;
        for (var snack : servicioSnacks.getSnacks()){
            if(idSnack == snack.getIdSnack()){
                productos.add(snack);
                System.out.println("Snack agregado " + snack);
                snackEncontrado = true;
                break;
            }
        }
        if(!snackEncontrado){
            System.out.println("ID de Snack incorrecto " + idSnack);
        }
        
    }
    
    private static void mostrarTicket(List<Snack> productos){
        var ticket = "*** Ticket de venta ***";
        var total = 0.0;
        
        for (var producto:productos){
            ticket += "\n\t-" + producto.getNombre() + "- $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        ticket += "\n\t Total -> $ " + total;
        System.out.println(ticket);
    }
    
    private static void agregarSnack(Scanner sc, IServicioSnacks servicioSnacks){
        System.out.println("Nombre del snack nuevo: ");
        var nombre = sc.nextLine();
        System.out.println("Precio del snack nuevo: ");
        var precio = Double.parseDouble(sc.nextLine());
        servicioSnacks.agregarSnack(new Snack(nombre, precio));
        System.out.println("Tu snack se ha agregado correctamente");
        servicioSnacks.mostrarSnacks();
    }
    
            
}    

