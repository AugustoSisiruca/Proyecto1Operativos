/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.operativosproyecto;

/**
 *
 * @author sisir
 */
public class Ensamblador extends Empleado {

    private int tiempoParaEnsamblar; // Tiempo para ensamblar una computadora (en horas)

    public Ensamblador(String nombre, int salarioPorHora, int tiempoParaEnsamblar) {
        super(nombre, salarioPorHora, 1); // Ensambladores ensamblan 1 computadora a la vez
        this.tiempoParaEnsamblar = tiempoParaEnsamblar;
    }

    @Override
    public void trabajar() {
        System.out.println(nombre + " está ensamblando una computadora");
        // Lógica para ensamblar una computadora
    }

    // Getters y setters...
}
