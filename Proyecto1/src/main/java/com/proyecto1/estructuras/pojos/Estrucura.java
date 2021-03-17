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
public class Estrucura {
    int id;
    String nombre, valor;

    public Estrucura(int id, String nombre, String valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public void mostrar(){
        System.out.println("Id: " + this.id + ", identificador: " + this.nombre + 
        ",\t\t valor: " + this.valor);
    }
    
    public String mostrarStr(){
        String content = "";
        content = "Id: " + this.id + ", identificador: " + this.nombre + 
        ",\t\t valor: " + this.valor;
        return content;
    }
}
