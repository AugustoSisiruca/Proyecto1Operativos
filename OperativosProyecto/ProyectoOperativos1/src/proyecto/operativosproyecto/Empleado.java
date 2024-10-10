/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.operativosproyecto;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sisir
 */

/*
Numero de Empleados para HP (Augusto 20221110152): 2 + 12 = 14
Numero de Empleados para Dell (Angel
*/

public class Empleado extends Thread{
int company;
    private int workerId;
    private int type;
    private int daysToFinish;
    private int numOfWorkDone;
    App app = App.getInstance();
    private Almacen driveRef;
    Semaphore mutex;

    int hourlyWage;
    float accumulatedSalary;
    private float dailyProgress;
    private float totalWork;
    String nombre;
    private int salarioPorHora; // Salario en dólares por hora trabajada
    private int produccionPorDia; // Cantidad de productos fabricados por día
    private int horasTrabajo; // Horas de trabajo por día (24h en el proyecto)

    public Empleado(int company, int workerId, int type, int daysToFinish, int numOfWorkDone, int hourlyWage,
            Almacen driveRef, Semaphore mutex) {
        this.company = company;
        this.workerId = workerId;
        this.type = type;
        this.daysToFinish = daysToFinish;
        this.numOfWorkDone = numOfWorkDone;
        this.driveRef = driveRef;
        this.mutex = mutex;
        
        this.hourlyWage = constantes.hourlyWages[type];
        this.accumulatedSalary = 0;
        // Cantidad de trabajo realizado por dia
        this.dailyProgress = (float) numOfWorkDone / daysToFinish;
        this.totalWork = 0;
    }

    public void run() {
        while (!Thread.interrupted()) {
            try {
                this.getPaid();
                this.addDailyProduction();
                this.working();
                sleep(App.getDayDuration());
            } catch (InterruptedException ex) {
                // Interrupción manejada, preparándose para terminar el hilo
                Logger.getLogger(Empleado.class.getName()).log(Level.INFO,
                        "Hilo de Empleado interrumpido, terminando...");
                break;
            }
        }
    }
    
    private void getPaid() {
        this.setAccumulatedSalary(this.getAccumulatedSalary() + this.getHourlyWage() * 24);
        // FIXME
    }

    private void addDailyProduction() {
        if (this.type == 5 && !(this.evaluateAssemble())) {
            this.setTotalWork(0);
        }
        this.setTotalWork(this.getTotalWork() + this.getDailyProgress());
    }
    
    
    private void working() {

        if (getTotalWork() >= 1) {
            try {
                this.getMutex().acquire();
                int workToUpload = (int) Math.floor(this.getTotalWork());

                if (this.type != 5) {
                    this.getDriveRef().uploadFile(getType(), workToUpload);
                } else {
                    this.createPc();
                }

                funcionesaux.calculateTotalCost(this.company, this.accumulatedSalary);
                setAccumulatedSalary(0);

                this.setTotalWork(Math.max(0, this.getTotalWork() - workToUpload));
                this.getMutex().release();
            } catch (InterruptedException ex) {
                Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private boolean evaluateAssemble() {

        Company co = funcionesaux.getCompany(this.company);

        // Requisito minimo para un cap (cap regular)
        for (int i = 0; i < co.getDrive().getSections().length - 2; i++) {
            // Si no hay la cantidad minima entonces el assembler no puede tranbajar
            if (co.getDrive().getSections()[i] < constantes.chaptersComposition[this.company][i]) {
                return false;
            }
        }
        // si es plottwist
        boolean isNextPlotTwist = (co.getNumChapters() != 0
                && ((co.getNumChapters()) % constantes.plotTwistFreq[this.company]) == 0);

        if (isNextPlotTwist) {
            // Verifica si NO hay para hacer un plottwist
            if (co.getDrive().getSections()[4] < constantes.chaptersComposition[this.company][4]) {
                return false;
            }
        }
        return true;
    }

    private void createPc() {
        Company co = funcionesaux.getCompany(this.company);

        if (this.evaluateAssemble()) {
            // Evaluar si el siguiente capítulo debe ser un plot twist antes de incrementar
            // numChapters
            boolean isNextPlotTwist = (co.getPlotTwistTrigger() != 0
                    && ((co.getPlotTwistTrigger()) % constantes.plotTwistFreq[this.company]) == 0);

            if (isNextPlotTwist) {
                for (int i = 0; i < co.getDrive().getSections().length - 1; i++) {
                    co.getDrive().getSections()[i] = Math.max(0,
                            co.getDrive().getSections()[i] - constantes.chaptersComposition[this.company][i]);
                }
                co.setNumChaptersWithPlotTwist(co.getNumChaptersWithPlotTwist() + 1);
                co.setActualNumChaptersWithPlotTwist(co.getActualNumChaptersWithPlotTwist() + 1);
                co.setPlotTwistTrigger(0);
            } else {
                // Capitulo regular
                for (int i = 0; i < co.getDrive().getSections().length - 2; i++) {
                    co.getDrive().getSections()[i] = Math.max(0,
                            co.getDrive().getSections()[i] - constantes.chaptersComposition[this.company][i]);
                }
                co.setNumNormalChapters(co.getNumNormalChapters() + 1);
                co.setActualNumNormalChapters(co.getActualNumNormalChapters() + 1);
                co.setPlotTwistTrigger(co.getPlotTwistTrigger() + 1);
            }

            // Incrementa el número de capítulos
            co.setNumChapters(co.getNumChapters() + 1);
            co.setActualNumChapters(co.getActualNumChapters() + 1);
            this.getDriveRef().getSections()[5] += 1;
        } else {
            this.setTotalWork(0);
        }
    }

    private boolean isPlotGPUPC(Company co) {
        return true;
    }

    // Getters y setters...

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public float getTotalWork() {
        return totalWork;
    }

    public void setTotalWork(float totalWork) {
        this.totalWork = totalWork;
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

    public Almacen getDriveRef() {
        return driveRef;
    }

    public void setDriveRef(Almacen driveRef) {
        this.driveRef = driveRef;
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

    public float getDailyProgress() {
        return dailyProgress;
    }

    public void setDailyProgress(float dailyProgress) {
        this.dailyProgress = dailyProgress;
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

    public float getAccumulatedSalary() {
        return accumulatedSalary;
    }

    public void setAccumulatedSalary(float accumulatedSalary) {
        this.accumulatedSalary = accumulatedSalary;
    }

    public int getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(int hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    
}
