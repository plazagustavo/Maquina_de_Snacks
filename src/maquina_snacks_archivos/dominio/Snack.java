
package maquina_snacks_archivos.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Snack implements Serializable {
    private static int ContadorSnacks = 0;
    private int idSnack;
    private String nombre;
    private double precio;
    

    public Snack() {
        this.idSnack = ++Snack.ContadorSnacks;
    }

    public Snack(String nombre, double precio) {
        this(); //Llamamos al constructor vacio para inicializar idSnack
        this.nombre = nombre;
        this.precio = precio;
    }

    public static int getContadorSnacks() {
        return ContadorSnacks;
    }


    public int getIdSnack() {
        return idSnack;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Snack{" + "idSnack=" + idSnack + 
                ", nombre=" + nombre + 
                ", precio=" + precio + '}';
    }
    
    
    public String escribirSnack(){
        return idSnack + "," + nombre + "," + precio;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idSnack;
        hash = 41 * hash + Objects.hashCode(this.nombre);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.precio) ^ (Double.doubleToLongBits(this.precio) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Snack other = (Snack) obj;
        if (this.idSnack != other.idSnack) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }

    
   
    
    
    
    
    
}
