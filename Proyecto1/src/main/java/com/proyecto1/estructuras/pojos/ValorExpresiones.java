/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto1.estructuras.pojos;

/**
 *
 * @author Gonzalo
 */
public class ValorExpresiones {
    int id;
    String valor, tipo;

    public ValorExpresiones(int id, String valor, String tipo) {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void mostrar(){
        System.out.println("Id: " + this.id + "\t\tvalor: " + this.valor + 
        "\t\ttipo: " + this.tipo);
    }
}
