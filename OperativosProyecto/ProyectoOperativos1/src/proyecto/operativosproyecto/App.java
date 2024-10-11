/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.operativosproyecto;

/**
 *
 * @author sisir
 */
import GUI.MenuInicio;
import Funciones.FileFunctions;
import java.io.File;
import proyecto.operativosproyecto.funcionesaux;
import proyecto.operativosproyecto.Company;
public class App {

    // File params
    private static String selectedPath = "test//params.txt";
    private static File selectedFile = new File(selectedPath);
    private static FileFunctions fileFunctions = new FileFunctions();

    // General params
    private static int dayDuration;
    private static int deadline;

    // General variables
    private Company dell;
    private Company hp;
    //private static ChartManager chartManager;

    private static App app;

    /**
     * Devuelve una instancia única de la aplicación.
     *
     * @return La instancia única de la aplicación.
     */
    public static synchronized App getInstance() {
        if (getApp() == null) {
            setApp(new App());
        }
        return getApp();
    }

    public void start() {
        
        
        funcionesaux.loadParams();
        // Inicia la simulación
        getDell().start();
        getHP().start();
       // chartManager = new ChartManager();
        MenuInicio home = new MenuInicio();
        home.setVisible(true);
    }

    /**
     * @return the selectedPath
     */
    public static String getSelectedPath() {
        return selectedPath;
    }

    /**
     * @param aSelectedPath the selectedPath to set
     */
    public static void setSelectedPath(String aSelectedPath) {
        selectedPath = aSelectedPath;
    }

    /**
     * @return the selectedFile
     */
    public static File getSelectedFile() {
        return selectedFile;
    }

    /**
     * @param aSelectedFile the selectedFile to set
     */
    public static void setSelectedFile(File aSelectedFile) {
        selectedFile = aSelectedFile;
    }

    /**
     * @return the fileFunctions
     */
    public static FileFunctions getFileFunctions() {
        return fileFunctions;
    }

    /**
     * @param aFileFunctions the fileFunctions to set
     */
    public static void setFileFunctions(FileFunctions aFileFunctions) {
        fileFunctions = aFileFunctions;
    }

    /**
     * @return the dayDuration
     */
    public static int getDayDuration() {
        return dayDuration;
    }

    /**
     * @param aDayDuration the dayDuration to set
     */
    public static void setDayDuration(int aDayDuration) {
        dayDuration = aDayDuration;
    }

    /**
     * @return the deadline
     */
    public static int getDeadline() {
        return deadline;
    }

    /**
     * @param aDeadline the deadline to set
     */
    public static void setDeadline(int aDeadline) {
        deadline = aDeadline;
    }

    /**
     * @return the dell company
     */
    public Company getDell() {
        return dell;
    }

    /**
     * @param dell the dell company to set
     */
    public void setDell(Company dell) {
        this.dell = dell;
    }

    /**
     * @return the hp company
     */
    public Company getHP() {
        return hp;
    }

    /**
     * @param hp the hp company to set
     */
    public void setHP(Company hp) {
        this.hp = hp;
    }


    /**
     * @return the app
     */
    public static App getApp() {
        return app;
    }

    /**
     * @param aApp the app to set
     */
    public static void setApp(App aApp) {
        app = aApp;
    }
}
