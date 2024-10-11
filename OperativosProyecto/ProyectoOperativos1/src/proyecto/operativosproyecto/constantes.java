/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.operativosproyecto;

/**
 *
 * @author acarr
 */
public class constantes {
    public final static String[] companies = {
        "Dell",
        "Hp"
    };

    public final static String[] workesType = {
        "Motherboard Producer",
        "Cpu Producer",
        "Ram Producer",
        "Power Supply Producer",
        "GraphicsCard Producer",
        "Assembler",
        "Project Manager",
        "Director"
    };

    public final static int[] hourlyWages = {
        20,
        26,
        40,
        16,
        34,
        50,
        40,
        60
    };

    // ANCHOR - El primer array es de Dell y el segundo de HP
    // FIXME - Revisar los tiempos de producción (El carnet)
    // NOTE - Estoy asumiendo que Dell es con el carnet mio (ultimo digito =
    // 1). El primer numero es la cantidad de trabajo que termina y el segundo es
    // cuantos dia le toma en terminarlo
    public final static int[][][] productionTimes = {
        {{1, 2}, {1, 2}, {3, 1}, {3, 1}, {1, 3}, {1, 2}},
        {{1, 2}, {1, 2}, {3, 1}, {3, 1}, {1, 3}, {1, 2}}
    };

    public final static int[][] PcComposition = {
        {1, 5, 6, 5, 1},
        {1, 1, 2, 4, 3}
    };

    public final static int[] GpuFreq = {3, 2};

    public final static int[][] profitPerPc = {
        {80000, 120000},
        {90000, 140000}
    };
}
