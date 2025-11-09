package maquina_snacks_archivos.servicio;

import java.util.ArrayList;
import java.util.List;
import maquina_snacks_archivos.dominio.Snack;

public class ServicioSnacksLista implements IServicioSnacks{
    private static final List<Snack> snacks;
    
    // bloque static inicializador 
    static{
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas", 70));
        snacks.add(new Snack("Bebida", 200));
        snacks.add(new Snack("Sanguche", 120));
    }
    
    @Override
    public void agregarSnack(Snack snack){
        snacks.add(snack);
    }
    
    @Override
    public void mostrarSnacks(){
        var inventarioSnacks = "";
        
        for(var snack : snacks){
            inventarioSnacks += snack.toString() + "\n";
        }
        System.out.println("==== Inventario de Snacks ====");
        System.out.println(inventarioSnacks);
    }

    @Override
    public List<Snack> getSnacks() {
        return snacks;
    }
    

            
    
}
