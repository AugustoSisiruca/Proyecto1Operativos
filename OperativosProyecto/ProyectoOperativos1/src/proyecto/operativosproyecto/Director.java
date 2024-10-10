/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.operativosproyecto;

/**
 *
 * @author sisir
 */
public class Director extends Empleado {

    private boolean listoParaEntregar; // Verifica si quedan días para la entrega

    public Director(String nombre, int salarioPorHora) {
        super(nombre, salarioPorHora, 0); // No produce, solo supervisa
        this.listoParaEntregar = false;
    }

    @Override
    public void trabajar() {
        if (isListoParaEntregar()) {
            System.out.println(nombre + " está enviando computadoras a las distribuidoras.");
        } else {
            System.out.println(nombre + " está realizando labores administrativas.");
        }
    }

    public void revisarEntrega(int diasRestantes) {
        setListoParaEntregar(diasRestantes <= 0);
    }

    // Getters y setters...

    /**
     * @return the listoParaEntregar
     */
    public boolean isListoParaEntregar() {
        return listoParaEntregar;
    }

    /**
     * @param listoParaEntregar the listoParaEntregar to set
     */
    public void setListoParaEntregar(boolean listoParaEntregar) {
        this.listoParaEntregar = listoParaEntregar;
    }
}
