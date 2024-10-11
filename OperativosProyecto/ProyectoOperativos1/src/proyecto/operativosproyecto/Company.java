/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.operativosproyecto;
import Funciones.Lista;
import java.util.concurrent.Semaphore;
/**
 *
 * @author sisir
 */

public class Company {

    private String name;
    private int maxEmployeesQuantity;             // Máximo número de empleados
    private int actualEmployeesQuantity = 0;
    private Empleado[] motherboardProducers;  // Productores de Placa Base
    private Empleado[] cpuProducers;          // Productores de CPU
    private Empleado[] ramProducers;          // Productores de Memoria RAM
    private Empleado[] powerSupplyProducers;  // Productores de Fuentes de Alimentación
    private Empleado[] graphicsCardProducers; // Productores de Tarjetas Gráficas
    private Empleado[] assemblers;            // Ensambladores
    private int projectManager;
    private ProyectManager projectManagerInstance;
    private int director;
    private Director directorInstance;
    private static Almacen drive;
    private Semaphore mutex;                       // Director
    private float totalCost = 0;
    private float earning = 0;
    private float profit = 0;
    private float lastOpsCost = 0;
    private float batchLastProfit = 0;
    private int totalDays = 0;
    private int remainingDays = App.getInstance().getDeadline();
    private int numChapters = 0;
    private int numNormalChapters = 0;
    private int numChaptersWithPlotTwist = 0;
    private int actualNumChapters = 0;
    private int actualNumNormalChapters = 0;
    private int actualNumChaptersWithPlotTwist = 0;
    private int lastNumNormalChapters = 0;
    private int lastNumChaptersWithPlotTwist = 0;
    private int plotTwistTrigger = 0;
    
    
    public Company(String name, int maxEmployeesQuantity, Empleado[] motherboardProducers, Empleado[] cpuProducers,
            Empleado[] ramProducers, Empleado[] powerSupplyProducers, Empleado[] graphicsCardProducers,
            Empleado[] Assemblers, int projectManager, int director, Almacen drive, Semaphore mutex) {
        this.name = name;
        this.maxEmployeesQuantity = maxEmployeesQuantity;
        this.motherboardProducers = motherboardProducers;
        this.cpuProducers = cpuProducers;
        this.ramProducers = ramProducers;
        this.powerSupplyProducers = powerSupplyProducers;
        this.graphicsCardProducers = graphicsCardProducers;
        this.assemblers = Assemblers;
        this.projectManager = projectManager;
        this.director = director;
        this.drive = drive;
        this.mutex = mutex;
        this.actualEmployeesQuantity();
    }

    // Métodos para inicializar la empresa
   public void start() {

        for (int i = 0; i < this.getMotherboardProducers().length; i++) {
            if (this.getMotherboardProducers()[i] != null) {
                this.getMotherboardProducers()[i].start();
            }
        }
        for (int i = 0; i < this.getCpuProducers().length; i++) {
            if (this.getCpuProducers()[i] != null) {
                this.getCpuProducers()[i].start();
            }
        }
        for (int i = 0; i < this.getRamProducers().length; i++) {
            if (this.getRamProducers()[i] != null) {
                this.getRamProducers()[i].start();
            }
        }
        for (int i = 0; i < this.getPowerSupplyProducers().length; i++) {
            if (this.getPowerSupplyProducers()[i] != null) {
                this.getPowerSupplyProducers()[i].start();
            }
        }
        for (int i = 0; i < this.getGraphicsCardProducers().length; i++) {
            if (this.getGraphicsCardProducers()[i] != null) {
                this.getGraphicsCardProducers()[i].start();
            }
        }
        for (int i = 0; i < this.getAssemblers().length; i++) {
            if (this.getAssemblers()[i] != null) {
                this.getAssemblers()[i].start();
            }
        }
        this.getProjectManagerInstance().start();
        this.getDirectorInstance().start();

    }

    // Métodos para contar los productores
    public int countNonNull(Lista<Integer> list) {
        return list.getLength();
    }

    public Empleado[] getMotherboardProducers() {
        return motherboardProducers;
    }

    public void setMotherboardProducers(Empleado[] motherboardProducers) {
        this.motherboardProducers = motherboardProducers;
    }

    public Empleado[] getCpuProducers() {
        return cpuProducers;
    }

    public void setCpuProducers(Empleado[] cpuProducers) {
        this.cpuProducers = cpuProducers;
    }

    public Empleado[] getRamProducers() {
        return ramProducers;
    }

    public void setRamProducers(Empleado[] ramProducers) {
        this.ramProducers = ramProducers;
    }

    public Empleado[] getPowerSupplyProducers() {
        return powerSupplyProducers;
    }

    public void setPowerSupplyProducers(Empleado[] powerSupplyProducers) {
        this.powerSupplyProducers = powerSupplyProducers;
    }

    public Empleado[] getGraphicsCardProducers() {
        return graphicsCardProducers;
    }

    public void setGraphicsCardProducers(Empleado[] graphicsCardProducers) {
        this.graphicsCardProducers = graphicsCardProducers;
    }

    public Empleado[] getAssemblers() {
        return assemblers;
    }

    public void setAssemblers(Empleado[] assemblers) {
        this.assemblers = assemblers;
    }

    public ProyectManager getProjectManagerInstance() {
        return projectManagerInstance;
    }

    public void setProjectManagerInstance(ProyectManager projectManagerInstance) {
        this.projectManagerInstance = projectManagerInstance;
    }


    public int getProjectManager() {
        return projectManager;
    }

    public Director getDirectorInstance() {
        return directorInstance;
    }

    public void setDirectorInstance(Director directorInstance) {
        this.directorInstance = directorInstance;
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

    public static Almacen getDrive() {
        return drive;
    }

    public static void setDrive(Almacen drive) {
        Company.drive = drive;
    }

    public int getNumPC() {
        return numChapters;
    }

    public void setNumChapters(int numChapters) {
        this.numChapters = numChapters;
    }

    public int getGPUTrigger() {
        return plotTwistTrigger;
    }

    public void setGPUTrigger(int plotTwistTrigger) {
        this.plotTwistTrigger = plotTwistTrigger;
    }

    public int getNumPCWithGPU() {
        return numChaptersWithPlotTwist;
    }

    public void setNumChaptersWithPlotTwist(int numChaptersWithPlotTwist) {
        this.numChaptersWithPlotTwist = numChaptersWithPlotTwist;
    }

    public int getActualNumGPUPC() {
        return actualNumChaptersWithPlotTwist;
    }

    public void setActualNumChaptersWithPlotTwist(int actualNumChaptersWithPlotTwist) {
        this.actualNumChaptersWithPlotTwist = actualNumChaptersWithPlotTwist;
    }

    public int getActualNumPC() {
        return actualNumChapters;
    }

    public void setActualNumChapters(int actualNumChapters) {
        this.actualNumChapters = actualNumChapters;
    }

    public int getActualNumNormalPC() {
        return actualNumNormalChapters;
    }

    public void setActualNumNormalChapters(int actualNumNormalChapters) {
        this.actualNumNormalChapters = actualNumNormalChapters;
    }

    public int getNumNormalChapters() {
        return numNormalChapters;
    }

    public void setNumNormalChapters(int numNormalChapters) {
        this.numNormalChapters = numNormalChapters;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }
    
    public void decreaceRemainingDays() {
        this.remainingDays--;
    }

    public int getLastNumNormalPC() {
        return lastNumNormalChapters;
    }

    public void setLastNumNormalChapters(int lastNumNormalChapters) {
        this.lastNumNormalChapters = lastNumNormalChapters;
    }

    public int getLastNumGPUPC() {
        return lastNumChaptersWithPlotTwist;
    }

    public void setLastNumChaptersWithPlotTwist(int lastNumChaptersWithPlotTwist) {
        this.lastNumChaptersWithPlotTwist = lastNumChaptersWithPlotTwist;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public float getLastOpsCost() {
        return lastOpsCost;
    }

    public void setLastOpsCost(float lastOpsCost) {
        this.lastOpsCost = lastOpsCost;
    }

    public float getBatchLastProfit() {
        return batchLastProfit;
    }

    public void setBatchLastProfit(float batchLastProfit) {
        this.batchLastProfit = batchLastProfit;
    }

    public int getActualEmployeesQuantity() {
        return actualEmployeesQuantity;
    }

    public void setActualEmployeesQuantity(int actualEmployeesQuantity) {
        this.actualEmployeesQuantity = actualEmployeesQuantity;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public float getEarning() {
        return earning;
    }

    public void setEarning(float earning) {
        this.earning = earning;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }
    
    public void actualEmployeesQuantity() {
        int totalEmployees = 0;

        // Contar empleados no nulos en cada arreglo
        totalEmployees += countNonNull(motherboardProducers);
        totalEmployees += countNonNull(cpuProducers);
        totalEmployees += countNonNull(ramProducers);
        totalEmployees += countNonNull(powerSupplyProducers);
        totalEmployees += countNonNull(graphicsCardProducers);
        totalEmployees += countNonNull(assemblers);

        this.setActualEmployeesQuantity(totalEmployees);
    }

    // Método auxiliar para contar los elementos no nulos en un arreglo de Employee
    public int countNonNull(Empleado[] employees) {
        int count = 0;
        for (Empleado employee : employees) {
            if (employee != null) {
                count++;
            }
        }
        return count;
    }
    
}
