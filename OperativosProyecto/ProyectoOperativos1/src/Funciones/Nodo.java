/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

/**
 *
 * @author sisir
 * @param <T>
 */
public class Nodo<T> {
    private int element;
    private T next;

    public Nodo(int element) {
        this.element = element;
        this.next = null;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public T getNext() {
        return next;
    }

    public void setNext(T next) {
        this.next = next;
    }
    
}
