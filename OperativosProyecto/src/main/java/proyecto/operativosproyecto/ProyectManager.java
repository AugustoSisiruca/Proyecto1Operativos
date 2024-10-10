/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.operativosproyecto;

/**
 *
 * @author sisir
 */
public class ProyectManager extends Empleado {

    private int faltas; // Número de veces que ha sido descubierto viendo anime

    public ProyectManager(String nombre, int salarioPorHora) {
        super(nombre, salarioPorHora, 0); // No produce, solo gestiona
        this.faltas = 0;
    }

    @Override
    public void trabajar() {
        System.out.println(nombre + " está gestionando el proyecto (o viendo anime)");
        // Lógica de revisión y anime
    }

    public void agregarFalta() {
        faltas++;
    }

    public int getFaltas() {
        return faltas;
    }

    // Getters y setters...
}
