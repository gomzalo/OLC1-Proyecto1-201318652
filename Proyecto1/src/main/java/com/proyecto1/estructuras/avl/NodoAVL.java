/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto1.estructuras.avl;

import com.proyecto1.estructuras.pojos.ValorExpresiones;

/**
 *
 * @author G
 */
public class NodoAVL {
    NodoAVL izquierda;
    NodoAVL derecha;
    int altura;
    ValorExpresiones valor;

    public NodoAVL(ValorExpresiones valor) {
        izquierda = null;
        derecha = null;
        altura = 0;
        this.valor = valor;
    }

    public NodoAVL() {
        izquierda = null;
        derecha = null;
        altura = 0;
        valor = null;
    }

    public NodoAVL getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(NodoAVL izquierda) {
        this.izquierda = izquierda;
    }

    public NodoAVL getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoAVL derecha) {
        this.derecha = derecha;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public ValorExpresiones getValor() {
        return valor;
    }

    public void setValor(ValorExpresiones valor) {
        this.valor = valor;
    }

    
    
}
