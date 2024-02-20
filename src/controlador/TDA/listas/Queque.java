/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.listas;

import controlador.TDA.listas.exception.VacioException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebastian
 */
public class Queque<E> {

    private Nodo<E> head;
    private Nodo<E> last;
    private Integer size;

    public Queque() {
        this.head = null;
        this.last = null;
        this.size = 0;
    }
    
    public Integer getSize(){
        return size;
    }

    public void queue(E data) {
        addLast(data);
    }

    public E dequeue(E data) {
        return deleteFirst();
    }

    public Boolean isEmpty() {
        return head == null || size == 0;
    }

    private void addFirst(E data) {
        if (isEmpty()) {
            Nodo<E> aux = new Nodo<>(data, null);
            head = aux;
            last = aux;
        } else {
            Nodo<E> headOld = head;
            Nodo<E> aux = new Nodo<>(data, headOld);
            head = aux;
        }
        size++;
    }

    private void addLast(E data) {
        if (isEmpty()) {
            addFirst(data);
        } else {
            Nodo<E> aux = new Nodo<>(data, null);
            last.setNext(aux);
            last = aux;
            size++;
        }

    }

    private E deleteFirst() {
        if (isEmpty()) {
            try {
                throw new VacioException("Lista vacia");
            } catch (VacioException ex) {
                Logger.getLogger(Queque.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        E element = head.getData();
        Nodo<E> aux = head.getNext();
        head = aux;
        if (size == 1) {
            last = null;
        }
        size--;
        return element;
    }

}
