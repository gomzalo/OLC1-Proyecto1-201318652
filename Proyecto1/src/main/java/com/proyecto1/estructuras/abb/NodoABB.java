/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto1.estructuras.abb;

import com.proyecto1.estructuras.pojos.ValorExpresiones;

/**
 *
 * @author g
 */
public class NodoABB {
    protected NodoABB izquierdo, derecho;
    protected int id;
    private ValorExpresiones valor;

    public NodoABB() {
        this.izquierdo = null;
        this.derecho = null;
        this.id = 0;
    }
    
    public NodoABB(int id) {
        this.izquierdo = null;
        this.derecho = null;
        this.id = id;
    }

    public NodoABB(int id, ValorExpresiones valor) {
        this.izquierdo = null;
        this.derecho = null;
        this.id = id;
        this.valor = valor;
    }
    
    public NodoABB getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoABB getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ValorExpresiones getValorExpresiones() {
        return valor;
    }

    public void setValorExpresiones(ValorExpresiones valor) {
        this.valor = valor;
    }
    
}
