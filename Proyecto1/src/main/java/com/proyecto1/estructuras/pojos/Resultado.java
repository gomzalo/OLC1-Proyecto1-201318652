/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto1.estructuras.pojos;

import com.proyecto1.estructuras.arbol.Nodo;

/**
 *
 * @author Gonzalo
 */
public class Resultado {
    Nodo nodoTemporal;
    String contenido;

    public Resultado(Nodo nodoTemporal, String contenido) {
        this.nodoTemporal = nodoTemporal;
        this.contenido = contenido;
    }

    public Nodo getNodoTemporal() {
        return nodoTemporal;
    }

    public void setNodoTemporal(Nodo nodoTemporal) {
        this.nodoTemporal = nodoTemporal;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
    
}
