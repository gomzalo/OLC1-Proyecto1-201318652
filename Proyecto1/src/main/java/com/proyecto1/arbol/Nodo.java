/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto1.arbol;

import java.util.LinkedList;

/**
 *
 * @author Gonzalo
 */
public class Nodo {
    public String nombre;
    public LinkedList<Nodo> Hijos;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.Hijos = new LinkedList<>();
    }
    
    public void mostrarNodo(){
        System.out.println(this.nombre);
        for (Nodo Hijo : Hijos) {
            Hijo.mostrarNodo();
        }
    }
}
