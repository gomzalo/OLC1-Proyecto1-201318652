/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto1.estructuras;

import com.proyecto1.estructuras.pojos.*;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * @author Gonzalo
 */
public class Instancias {
//     ::::::::::::::::::::::::::::     CONJUNTOS       ::::::::::::::::::::::::::::
    public static LinkedList<Conjunto> listaConjuntos = new LinkedList<Conjunto>();
//    public static LinkedList<Conjunto> listaConjuntos;
//    public static LinkedList<String> listaValoresConjuntos = new LinkedList<String>();
//    public static Stack<String> identificadoresConjuntos = new Stack<String>();
//    public static Conjunto conjunto_temporal;
    public static int contador_conjuntos = 0;
    // ::::::::::::::::::::::::::::     EXPRESIONES       ::::::::::::::::::::::::::::
    public static LinkedList<Expresion> listaExpresiones = new LinkedList<Expresion>();
//    public static LinkedList<Expresion> listaExpresiones;
    public static int contador_expresiones = 0;
    // ::::::::::::::::::::::::::::     LEXEMAS       ::::::::::::::::::::::::::::
    public static LinkedList<Lexema> listaLexemas = new LinkedList<Lexema>();
//    public static LinkedList<Lexema> listaLexemas;    
    public static int contador_lexemas = 0;
    
    public static void limpiar(){
        listaConjuntos.clear();
        contador_conjuntos = 0;
        listaExpresiones.clear();
        contador_expresiones = 0;
        listaLexemas.clear();
        contador_lexemas = 0;
    }
}
    