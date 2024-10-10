/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectooperativos1.Funciones;

/**
 *
 * @author sisir
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import proyecto.operativosproyecto.Company;
import proyecto.operativosproyecto.App;

public class FileFunctions {

    public static String read(File file) {
        String line;
        String data = "";

        try {
            if (!file.exists()) {
                file.createNewFile();

            } else {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                while ((line = br.readLine()) != null) {
                    if (!(line.isEmpty())) {
                        data += line + "\n";
                    }
                }
                br.close();
            }
            return data;
        } catch (Exception e) {
        }
        return data;
    }

    public static int[] getGeneralParams(String fileData) {
        int startIndex = fileData.indexOf("[General Params]");
        if (startIndex == -1) {
            // La sección de General Params no fue encontrada.
            return null;
        }

        // Encuentra el final de la sección de General Params o el final del archivo si no hay más secciones
        int endIndex = fileData.indexOf("[", startIndex + 1);
        if (endIndex == -1) {
            endIndex = fileData.length();
        }

        // Extrae la sección de General Params
        String generalParamsSection = fileData.substring(startIndex, endIndex);

        // Divide la sección en líneas
        String[] lines = generalParamsSection.split("\n");

        // Array para almacenar los valores enteros de la configuración
        int[] generalParams = new int[2]; // [0]: DayDuration, [1]: Deadline

        // Itera sobre las líneas, extrayendo los valores enteros
        int valueIndex = 0;
        for (String line : lines) {
            if (line.contains("=")) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    try {
                        generalParams[valueIndex++] = Integer.parseInt(parts[1].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("El valor no es entero");
                    }
                }
            }
        }
        return generalParams;
    }

    public static int[] getCompanyValues(int company, String fileData) {
        int[] values = new int[9];
        int startIndex = 0;

        if (company == 0) {
            startIndex = fileData.indexOf("[Dell]");
        } else if (company == 1) {
            startIndex = fileData.indexOf("[HP]");
        }

        if (startIndex == -1) {
            // La sección no fue encontrada.
            return null;
        }

        // Encuentra el final de la sección de la compañía o el final del archivo
        int endIndex = fileData.indexOf("[", startIndex + 1);
        if (endIndex == -1) {
            endIndex = fileData.length();
        }

        // Extrae la sección de la compañía
        String companySection = fileData.substring(startIndex, endIndex);

        // Divide la sección en líneas
        String[] lines = companySection.split("\n");

        // Itera sobre las líneas, extrayendo los valores enteros
        int valueIndex = 0;
        for (String line : lines) {
            if (line.contains("=")) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    try {
                        values[valueIndex++] = Integer.parseInt(parts[1].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("El valor no es entero");
                    }
                }
            }
        }
        return values;
    }

    public static void write(File file) {
        String data = getActualParams();
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(file);
            fileWriter.write(data);
            fileWriter.close();
        } catch (Exception e) {

        }
    }

    public static String getActualParams() {
        App app = App.getInstance();
        String data = "[General Params]\n";
        int dayDuration = App.getDayDuration();
        int deadline = App.getDeadline();

        data += "DayDuration=" + dayDuration + "\n" + "Deadline=" + deadline + "\n\n";

        data += "[Dell]\n";
        Company dell = app.getDell();
        int motherboardProducers = dell.countNonNull(dell.getMotherboardProducers());
        int cpuProducers = dell.countNonNull(dell.getCpuProducers());
        int ramProducers = dell.countNonNull(dell.getRamProducers());
        int powerSupplyProducers = dell.countNonNull(dell.getPowerSupplyProducers());
        int graphicsCardProducers = dell.countNonNull(dell.getGraphicsCardProducers());
        int assemblers = dell.countNonNull(dell.getAssemblers());
        int projectManager = dell.getProjectManager();
        int director = dell.getDirector();

        data += "MotherboardProducers=" + motherboardProducers + "\n"
                + "CPUProducers=" + cpuProducers + "\n"
                + "RAMProducers=" + ramProducers + "\n"
                + "PowerSupplyProducers=" + powerSupplyProducers + "\n"
                + "GraphicsCardProducers=" + graphicsCardProducers + "\n"
                + "Assemblers=" + assemblers + "\n"
                + "ProjectManager=" + projectManager + "\n"
                + "Director=" + director + "\n\n";

        data += "[HP]\n";
        Company hp = app.getHP();
        motherboardProducers = hp.countNonNull(hp.getMotherboardProducers());
        cpuProducers = hp.countNonNull(hp.getCpuProducers());
        ramProducers = hp.countNonNull(hp.getRamProducers());
        powerSupplyProducers = hp.countNonNull(hp.getPowerSupplyProducers());
        graphicsCardProducers = hp.countNonNull(hp.getGraphicsCardProducers());
        assemblers = hp.countNonNull(hp.getAssemblers());
        projectManager = hp.getProjectManager();
        director = hp.getDirector();

        data += "MotherboardProducers=" + motherboardProducers + "\n"
                + "CPUProducers=" + cpuProducers + "\n"
                + "RAMProducers=" + ramProducers + "\n"
                + "PowerSupplyProducers=" + powerSupplyProducers + "\n"
                + "GraphicsCardProducers=" + graphicsCardProducers + "\n"
                + "Assemblers=" + assemblers + "\n"
                + "ProjectManager=" + projectManager + "\n"
                + "Director=" + director + "\n\n";

        return data;
    }
}
