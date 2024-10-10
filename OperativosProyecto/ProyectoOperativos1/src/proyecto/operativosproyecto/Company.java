/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.operativosproyecto;
import Funciones.Lista;
/**
 *
 * @author sisir
 */

public class Company {

    private String name;
    private Lista<Integer> motherboardProducers;  // Productores de Placa Base
    private Lista<Integer> cpuProducers;          // Productores de CPU
    private Lista<Integer> ramProducers;          // Productores de Memoria RAM
    private Lista<Integer> powerSupplyProducers;  // Productores de Fuentes de Alimentación
    private Lista<Integer> graphicsCardProducers; // Productores de Tarjetas Gráficas
    private Lista<Integer> assemblers;            // Ensambladores
    private int projectManager;                   // Project Manager
    private int director;                         // Director
    private int maxEmployeesQuantity;             // Máximo número de empleados

    public Company(String name, int maxEmployeesQuantity) {
        this.name = name;
        this.maxEmployeesQuantity = maxEmployeesQuantity;
        this.motherboardProducers = new Lista<>();
        this.cpuProducers = new Lista<>();
        this.ramProducers = new Lista<>();
        this.powerSupplyProducers = new Lista<>();
        this.graphicsCardProducers = new Lista<>();
        this.assemblers = new Lista<>();
    }

    // Métodos para inicializar la empresa
    public void initializeCompany(int numMotherboardProducers, int numCpuProducers, int numRamProducers,
            int numPowerSupplyProducers, int numGraphicsCardProducers, int numAssemblers,
            int projectManager, int director) {
        // Agregar productores de placa base
        for (int i = 0; i < numMotherboardProducers; i++) {
            motherboardProducers.insertFinal(1); // El "1" indica que es un productor activo
        }

        // Agregar productores de CPU
        for (int i = 0; i < numCpuProducers; i++) {
            cpuProducers.insertFinal(1);
        }

        // Agregar productores de RAM
        for (int i = 0; i < numRamProducers; i++) {
            ramProducers.insertFinal(1);
        }

        // Agregar productores de Fuentes de Alimentación
        for (int i = 0; i < numPowerSupplyProducers; i++) {
            powerSupplyProducers.insertFinal(1);
        }

        // Agregar productores de Tarjetas Gráficas
        for (int i = 0; i < numGraphicsCardProducers; i++) {
            graphicsCardProducers.insertFinal(1);
        }

        // Agregar ensambladores
        for (int i = 0; i < numAssemblers; i++) {
            assemblers.insertFinal(1);
        }

        // Asignar el Project Manager y el Director
        this.projectManager = projectManager;
        this.director = director;
    }

    // Métodos para contar los productores
    public int countNonNull(Lista<Integer> list) {
        return list.getLength();
    }

    public Lista<Integer> getMotherboardProducers() {
        return motherboardProducers;
    }

    public Lista<Integer> getCpuProducers() {
        return cpuProducers;
    }

    public Lista<Integer> getRamProducers() {
        return ramProducers;
    }

    public Lista<Integer> getPowerSupplyProducers() {
        return powerSupplyProducers;
    }

    public Lista<Integer> getGraphicsCardProducers() {
        return graphicsCardProducers;
    }

    public Lista<Integer> getAssemblers() {
        return assemblers;
    }

    public int getProjectManager() {
        return projectManager;
    }

    public int getDirector() {
        return director;
    }

    public int getMaxEmployeesQuantity() {
        return maxEmployeesQuantity;
    }

    public String getName() {
        return name;
    }
}
