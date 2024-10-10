/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.operativosproyecto;

/**
 *
 * @author sisir
 */

/*
Numero de Empleados para HP (Augusto 20221110152): 2 + 12 = 14
Numero de Empleados para Dell (Angel
*/

public class Empleado {

    String nombre;
    private int salarioPorHora; // Salario en dólares por hora trabajada
    private int produccionPorDia; // Cantidad de productos fabricados por día
    private int horasTrabajo; // Horas de trabajo por día (24h en el proyecto)

    public Empleado(String nombre, int salarioPorHora, int produccionPorDia) {
        this.nombre = nombre;
        this.salarioPorHora = salarioPorHora;
        this.produccionPorDia = produccionPorDia;
        this.horasTrabajo = 24; // Los empleados trabajan 24 horas en este proyecto
    }

    public void trabajar(){
        
    };

    // Getters y setters...

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the salarioPorHora
     */
    public int getSalarioPorHora() {
        return salarioPorHora;
    }

    /**
     * @param salarioPorHora the salarioPorHora to set
     */
    public void setSalarioPorHora(int salarioPorHora) {
        this.salarioPorHora = salarioPorHora;
    }

    /**
     * @return the produccionPorDia
     */
    public int getProduccionPorDia() {
        return produccionPorDia;
    }

    /**
     * @param produccionPorDia the produccionPorDia to set
     */
    public void setProduccionPorDia(int produccionPorDia) {
        this.produccionPorDia = produccionPorDia;
    }

    /**
     * @return the horasTrabajo
     */
    public int getHorasTrabajo() {
        return horasTrabajo;
    }

    /**
     * @param horasTrabajo the horasTrabajo to set
     */
    public void setHorasTrabajo(int horasTrabajo) {
        this.horasTrabajo = horasTrabajo;
    }
}
