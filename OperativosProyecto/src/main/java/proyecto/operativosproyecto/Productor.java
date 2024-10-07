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
HP Augusto:
Último número de carnet en intervalo 0 <= X < 3
1 CPU cada 2 días

*/
public class Productor extends Thread {

    private Almacen almacen;
    private String tipoProducto;

    public Productor(Almacen almacen, String tipoProducto) {
        this.almacen = almacen;
        this.tipoProducto = tipoProducto;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Simula la producción de un componente
//                almacen.producir(tipoProducto);
                // Simula el tiempo que tarda en producirse (en días)
                Thread.sleep(2000); // Simula 2 días
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
