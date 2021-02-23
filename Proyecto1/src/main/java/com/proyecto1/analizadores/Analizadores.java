/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto1.analizadores;

/**
 *
 * @author Gonzalo
 */
public class Analizadores {
    public static void main(String[] args){
        generarCompilador();
    }

    private static void generarCompilador() {
        try{
            String ruta = "src/main/java/com/proyecto1/analizadores/";
            String opcFlex[] = {ruta + "Lexico.jflex", "-d", ruta};
            jflex.Main.generate(opcFlex);
            
            String opcCUP[] = {"-destdir", ruta, "-parser", "Parser", ruta + "Sintactico.cup"};
            java_cup.Main.main(opcCUP);
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
        
}
