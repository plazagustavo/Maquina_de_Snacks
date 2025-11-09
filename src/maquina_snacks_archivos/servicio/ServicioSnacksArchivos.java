package maquina_snacks_archivos.servicio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import maquina_snacks_archivos.dominio.Snack;

public class ServicioSnacksArchivos implements IServicioSnacks {
    private final String NOMBRE_ARCHIVO = "snacks.txt";

    //lista de snacks
    private List<Snack> snacks = new ArrayList<>();

    public ServicioSnacksArchivos() {
       var archivo = new File(NOMBRE_ARCHIVO);
       var existe = false;
       
       try{
           existe = archivo.exists();
           if (existe){
               this.snacks = obtenerSnacks();
           }
           else{
               var salida = new PrintWriter(new FileWriter(archivo));
               salida.close(); // save
               System.out.println("¡SE HA CREADO UN NUEVO ARCHVIO!");
           }
           
       }catch(IOException e){
           System.out.println("Error al crear el archivo " + e.getMessage());
       }
       // Si no existe, cargamos snacks iniciales.
       if(!existe){
           cargarSnacksIniciales();
       }
    }
    
    private void cargarSnacksIniciales(){
        this.agregarSnack(new Snack("Papas", 70));
        this.agregarSnack(new Snack("Bebida", 200));
        this.agregarSnack(new Snack("Sanguche", 120));
    }
    
    
    private List<Snack> obtenerSnacks(){
        var snacks = new ArrayList<Snack>();
        try{
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for(String linea: lineas){
                String[] lineaSnack = linea.split(",");
                
                var idSnack = lineaSnack[0]; // no lo usamos pero lo recuperamos
                var nombre = lineaSnack[1];
                var precio = Double.parseDouble(lineaSnack[2]);
                
                var snack = new Snack(nombre, precio);
                snacks.add(snack);// snack leido lo agregamos a la lista
                
            }
            
        }catch(Exception e){
            System.out.println("Error al leer archivo de snack " + e.getMessage());
        }
        return snacks;
    }
    
    @Override
    public void agregarSnack(Snack snack) {
        // agregamos el snack a, 1. lista 
        this.snacks.add(snack);
        // 2. al archivo
        this.agregarSnackArchivo(snack);
    }
    private void agregarSnackArchivo(Snack snack){
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            anexar = archivo.exists(); // si el archivo existe el contenido se anexa
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(snack.escribirSnack());
            salida.close(); // save
            
            
        }catch(Exception e){
            System.out.println("Error al agregar snack " + e.getMessage());
        }
    }
    

    @Override
    public void mostrarSnacks() {
        System.out.println("===== Snack en el inventario ====");
        var inventario = "";
        
        for (var snack : this.snacks){
            inventario += snack.toString() + "\n";
        }
        System.out.println(inventario); 
        
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
    
}
