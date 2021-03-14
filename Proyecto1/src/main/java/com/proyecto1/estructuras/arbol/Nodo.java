/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto1.estructuras.arbol;

import java.util.LinkedList;

/**
 *
 * @author Gonzalo
 */
public class Nodo {
    public String valor;
    public LinkedList<Nodo> Hijos;
    
    // B1
    public Nodo izq, der;
    public int id;

    public Nodo(Nodo izq, Nodo der, String valor, int id) {
        this.izq = izq;
        this.der = der;
        this.valor = valor;
        this.id = id;
    }
    
//    public Object getValor(){
//        
//    }
    
    public Nodo(String valor) {
        this.valor = valor;
        this.Hijos = new LinkedList<>();
    }
    
    public void mostrarNodo(){
        System.out.println(this.valor);
        Hijos.forEach(Hijo -> {
            Hijo.mostrarNodo();
        });
    }
    
    public String getCodigoInterno(){
        String etiqueta = "";
        if (izq == null && der == null) {
            etiqueta = "nodo" + id + " [ label =\"" + valor+"\"];\n";
        } else {
            etiqueta = "nodo" + id + " [ label =\"" + valor + "\"];\n";
        }
        if (izq != null) {
            etiqueta = etiqueta + izq.getCodigoInterno()
                    + "nodo" + id + "->nodo" + izq.id + "\n";
        }
        if (der != null) {
            etiqueta = etiqueta + der.getCodigoInterno()
                    + "nodo" + id + "->nodo" + der.id + "\n";
        }
        return etiqueta;
    }
}
