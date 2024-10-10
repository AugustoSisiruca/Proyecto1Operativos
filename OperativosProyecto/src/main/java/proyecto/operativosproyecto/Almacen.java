/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.operativosproyecto;

/**
 *
 * @author sisir
 */
import java.util.HashMap;

public class Almacen {

    private HashMap<String, Integer> inventario; // Mapa para almacenar el tipo de producto y su cantidad
    private HashMap<String, Integer> capacidadMaxima; // Capacidad máxima para cada tipo de producto

    public Almacen() {
        this.inventario = new HashMap<>();
        this.capacidadMaxima = new HashMap<>();
    }

    public void agregarProducto(String tipoProducto, int cantidad) {
        int actual = inventario.getOrDefault(tipoProducto, 0);
        int max = capacidadMaxima.getOrDefault(tipoProducto, Integer.MAX_VALUE);

        if (actual + cantidad <= max) {
            inventario.put(tipoProducto, actual + cantidad);
        } else {
            System.out.println("No hay espacio suficiente en el almacén para " + tipoProducto);
        }
    }

    public void establecerCapacidad(String tipoProducto, int capacidad) {
        capacidadMaxima.put(tipoProducto, capacidad);
    }

    public int obtenerCantidad(String tipoProducto) {
        return inventario.getOrDefault(tipoProducto, 0);
    }

    // Getters y setters...
}
