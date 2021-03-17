/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto1.archivos;


import com.proyecto1.interfaz.Pantalla_principal;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import com.proyecto1.estructuras.*;

/**
 *
 * @author g
 */
public class Lectura {
        
    public static String leer(String ruta) throws FileNotFoundException, IOException, InterruptedException{
//        Matriz capa_auxiliar = null;
        String contenido = "";
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(ruta);
            sc = new Scanner(inputStream, "UTF-8");
            String id_capa_aux = "0";
// ..................... Contenido archivo ..................... 
            while (sc.hasNextLine()) {
                /*
                id_capa {
                   x, y, color;
                }
                */
                String linea = sc.nextLine();
//                System.out.println(linea);
                contenido += "\n" + linea;
            }
// ..................... Fin contenido archivo ..................... 
//            VentanaPrincipal.arbol_binario_capas.graficar("grafo");
            
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }
        return contenido;
    }
    
}
