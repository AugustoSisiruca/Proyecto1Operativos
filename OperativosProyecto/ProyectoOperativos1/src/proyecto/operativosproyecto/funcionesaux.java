/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.operativosproyecto;

import java.util.concurrent.Semaphore;
import Funciones.FileFunctions;
/**
 *
 * @author acarr
 */
public class funcionesaux {
    public static Company getCompany(int company) {
        return company == 0 ? App.getInstance().getDell() : App.getInstance().getHP();
    }

    public static void loadParams() {
        String fileData = FileFunctions.read(App.getSelectedFile());

        // Se obtiene los datos del TXT
        int[] params = FileFunctions.getGeneralParams(fileData);

        if (params != null && params.length >= 2) {
            App.setDayDuration(params[0]);
            App.setDeadline(params[1]);
        }

        App app = App.getInstance();
        app.setDell(funcionesaux.createTelevisionNetwork(0));
        app.setHP(funcionesaux.createTelevisionNetwork(1));

    }

    // NOTE - 0 para dell y 1 para hp
    public static Company createTelevisionNetwork(int company) {
        String fileData = FileFunctions.read(App.getSelectedFile());

        // Se obtiene los datos del TXT
        int[] televisionNetworkValues = FileFunctions.getCompanyValues(company, fileData);

        if (televisionNetworkValues != null && televisionNetworkValues.length >= 9) {

            String name = constantes.companies[company];
            Empleado[][] workers = new Empleado[6][];
            Semaphore mutex = new Semaphore(1);
            Almacen drive = new Almacen(25, 20, 55, 35, 10);
            int projectManager = 1;
            int director = 1;
            int maxEmployees = televisionNetworkValues[7];

            // Se crean los empleados de cada sección
            for (int type = 0; type <= 5; type++) {
                Empleado[] employees = new Empleado[maxEmployees];

                for (int j = 0; j < televisionNetworkValues[type]; j++) {
                    int workerId = j + 1;
                    int numOfWorkDone = constantes.productionTimes[company][type][0];
                    int daysToFinish = constantes.productionTimes[company][type][1];
                    int hourlyWage = constantes.hourlyWages[type];
                    employees[j] = new Empleado(company, workerId, type, daysToFinish, numOfWorkDone, hourlyWage,
                            drive, mutex);
                }
                workers[type] = employees;
            }
            Company compania = new Company(name, maxEmployees, workers[0], workers[1], workers[2],
                    workers[3], workers[4],
                    workers[5], projectManager, director, drive, mutex);

            // Se crea al projectManager y al director, se les pasa la cadena televisiva.
            ProyectManager projectManagerInstance = new ProyectManager(company, 1, 5, 1, 1,
                    constantes.hourlyWages[5], drive, mutex);
            compania.setProjectManagerInstance(projectManagerInstance);
            Director directorInstance = new Director(company, 1, 6, 2, 1, constantes.hourlyWages[6], drive,
                    mutex);
            compania.setDirectorInstance(directorInstance);
            return compania;

        }
        return null;
    }

    public void addWorker(int company, int workerType) {

        Company network = company == 0 ? App.getInstance().getDell()
                : App.getInstance().getHP();

        // Se verifica si la cantidad actual de empleados es menor que la cantidad
        // máxima permitida
        if (network.getActualEmployeesQuantity() < network.getMaxEmployeesQuantity()) {
            Empleado[] employees = getEmployeesArrayByType(network, workerType);

            // Se crea nuevo empleado
            int workerId = network.getActualEmployeesQuantity() + 1;
            int daysToFinish = constantes.productionTimes[company][workerType][1];
            int numOfWorkDone = constantes.productionTimes[company][workerType][0];
            int hourlyWage = constantes.hourlyWages[workerType];
            Empleado newEmployee = new Empleado(company, workerId, workerType, daysToFinish, numOfWorkDone, hourlyWage,
                    network.getDrive(), network.getMutex());

            // Se inicia el hilo del nuevo empleado
            newEmployee.start();

            // Se buscar la primera posición vacía en el arreglo y añadir allí el nuevo
            // empleado
            for (int i = 0; i < employees.length; i++) {
                if (employees[i] == null) {
                    employees[i] = newEmployee;
                    network.setActualEmployeesQuantity(network.getActualEmployeesQuantity() + 1);
                    // Actualizar la cantidad de empleados
                    break;
                }
            }
        } else {
            System.out.println("Se ha alcanzado el número máximo de empleados.");
        }
    }

    public void deleteWorker(int company, int workerType) {
        Company network = funcionesaux.getCompany(company);

        // Verifica si hay empleados para eliminar
        if (network.getActualEmployeesQuantity() > 0) {
            Empleado[] employees = getEmployeesArrayByType(network, workerType);

            if (employees != null) {
                // Buscar el último empleado no nulo
                for (int i = employees.length - 1; i >= 0; i--) {
                    if (employees[i] != null) {
                        // Interrumpe el hilo del empleado
                        employees[i].interrupt();

                        // Elimina el empleado del arreglo
                        employees[i] = null;

                        // Actualiza la cantidad de empleados
                        network.setActualEmployeesQuantity(network.getActualEmployeesQuantity() - 1);
                        break;
                    }
                }
            }
        } else {
            System.out.println("No hay empleados para eliminar.");
        }
    }

    private Empleado[] getEmployeesArrayByType(Company network, int workerType) {
        switch (workerType) {
            case 0:
                return network.getMotherboardProducers();
            case 1:
                return network.getCpuProducers();
            case 2:
                return network.getRamProducers();
            case 3:
                return network.getPowerSupplyProducers();
            case 4:
                return network.getGraphicsCardProducers();
            case 5:
                return network.getAssemblers();
            default:
                return null;
        }
    }

    private void setEmployeesArrayByType(Company network, int workerType, Empleado[] newEmployees) {
        switch (workerType) {
            case 0:
                network.setMotherboardProducers(newEmployees);
                break;
            case 1:
                network.setCpuProducers(newEmployees);
                break;
            case 2:
                network.setRamProducers(newEmployees);
                break;
            case 3:
                network.setPowerSupplyProducers(newEmployees);
                break;
            case 4:
                network.setGraphicsCardProducers(newEmployees);
                break;
        }
    }

    public static void calculateTotalCost(int company, float accumulatedSalary) {
        Company tv = getCompany(company);
        tv.setTotalCost(tv.getTotalCost() + accumulatedSalary);
    }

    public static void calculateTotalEarnings(int company) {
        Company tv = getCompany(company);
        float earning = (tv.getNumNormalChapters() * constantes.profitPerChapter[company][0])
                + (tv.getNumChaptersWithPlotTwist() * constantes.profitPerChapter[company][1]);
        tv.setEarning(earning);
    }

    public static void calculateProfit(int company) {
        Company tv = getCompany(company);
        float profit = tv.getEarning() - tv.getTotalCost();
        tv.setProfit(profit);
    }
}
