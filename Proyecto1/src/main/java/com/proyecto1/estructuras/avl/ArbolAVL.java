/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto1.estructuras.avl;

import java.io.IOException;

import com.proyecto1.archivos.Escritura;
import com.proyecto1.estructuras.pojos.ValorExpresiones;
//import com.proyecto1.arboles.avl.subestructura.cola.ColaAVL;
//import estructuras.arboles.avl.subestructura.cola.NodoColaAVL;
//import pojos.Usuario;

/**
 *
 * @author G
 */
public class ArbolAVL {
    public static String avl_aux;
    private NodoAVL raiz;
//    ColaAVL cola_auxiliar;

    // Constructor
    public ArbolAVL() {
        raiz = null;
        avl_aux = "";
//        cola_auxiliar = null;
    }
    
    // Verifica si esta vacio el arbol.
    public boolean esVacio(){
        return raiz == null;
    }
    
    public void vaciar(){
        raiz = null;
    }
    
    // Inserta la info en los nodos.
    public void insertar(ValorExpresiones nueva_expresion){
        raiz = insertar(nueva_expresion, raiz);
    }
    
    // Obtiene la altura del nodo.
    private int altura(NodoAVL t){
        return t == null? -1 : t.altura;
    }
    
    // Maximo del nodo izq/der.
    private int max(int lhs, int rhs){
        return lhs > rhs ? lhs : rhs;
    }
    
    // Factor de equilibrio
    private int getFactorEquilibrio(NodoAVL t) {
    	if(t == null) {
            return 0;
    	}else {
            return altura(t.getIzquierda())-altura(t.getDerecha());
    	}
    }
    
    // Inserta info en nodos, recursivamente.
    private NodoAVL insertar(ValorExpresiones nuevo_valor, NodoAVL t){
    	System.out.println(nuevo_valor.getId());
    	System.out.println("Insertando");
        if(t == null){
            t = new NodoAVL(nuevo_valor);
//            System.out.println("Arbol vacio, insertando en la raiz.");
        }else if(nuevo_valor.getId() < t.getValor().getId()){
//        	System.out.println("Insertando en la izquierda.");
            t.setIzquierda(insertar(nuevo_valor, t.getIzquierda()));
            if(getFactorEquilibrio(t) == 2){
                if(nuevo_valor.getId()< t.getIzquierda().getValor().getId()){
                    // CASO 1: Izquierda-izquierda
                    t = rotarConHijoIzq(t);
                }else{
                    // CASO 2: Izquierda-derecha
                    t = rotarDobleConHijoIzq(t);
                }
            }
            
        }else if(nuevo_valor.getId() > t.getValor().getId()){
//        	System.out.println("Insertando en la derecha.");
            t.setDerecha(insertar(nuevo_valor, t.getDerecha()));
            if(getFactorEquilibrio(t) == 2){
                if(nuevo_valor.getId() > t.getDerecha().getValor().getId()){
                    // CASO 3: Derecha-derecha
                    t = rotarConHijoDer(t);
                }else{
                    // CASO 4: Derecha-izquierda
                    t = rotarDobleConHijoDer(t);
                }
            }
            
        }else{
            ;   // Duplicado; no hace nada.
            System.out.println("Valor duplicado.");
        }
        t.setAltura(max(altura(t.getIzquierda()), altura(t.getDerecha())) + 1);
        return t;
    }
    
    // Rotacion del arbol binario con un hijo izquierdo.
    private NodoAVL rotarConHijoIzq(NodoAVL k2){
        NodoAVL k1 = k2.getIzquierda();
        k2.setIzquierda(k1.getDerecha());
        k1.setDerecha(k2);
        k2.setAltura(max(altura(k2.getIzquierda()), altura(k2.getDerecha()))+1);
        k1.setAltura(max(altura(k1.getIzquierda()), k2.getAltura())+1);
        
        return k1;
    }
    
    // Rotacion del arbol binario con un hijo derecho.
    private NodoAVL rotarConHijoDer(NodoAVL k1){
        NodoAVL k2 = k1.getDerecha();
        k1.setDerecha(k2.getIzquierda());
        k2.setIzquierda(k1);
        k1.setAltura(max(altura(k1.getIzquierda()), altura(k1.getDerecha()))+1);
        k2.setAltura(max(altura(k2.getDerecha()), k1.getAltura())+1);
        
        return k2;
    }
    
    // Doble rotacion de un nodo del arbo binario: Primero el hijo izquierdo
    // con su hijo derecho; luego el nodo k3 con su nuevo hijo izquierdo.
    private NodoAVL rotarDobleConHijoIzq(NodoAVL k3){
        k3.setIzquierda(rotarConHijoDer(k3.getIzquierda()));
        return rotarConHijoIzq(k3);
    }
    
    // Doble rotacion de un nodo del arbo binario: Primero el hijo derecho
    // con su hijo izquierdo; luego el nodo k1 con su nuevo hijo derecho.
    private NodoAVL rotarDobleConHijoDer(NodoAVL k1){
        k1.setDerecha(rotarConHijoIzq(k1.getDerecha()));
        return rotarConHijoDer(k1);
    }
    
    // Devuelve el numero de nodos
    public int contarNodos(){
        return contarNodos(raiz);
    }
    
    // Contar nodos, recursivamente.
    private int contarNodos(NodoAVL r){
        if(r == null){
            return 0;
        }else{
            int l = 1;
            l += contarNodos(r.getIzquierda());
            l += contarNodos(r.getDerecha());
            return l;
        }
    }
    
    // Buscar un elemento.
    public boolean buscar(int codigo){
        return buscar(raiz, codigo);
    }
    
    // Buscar, recursivamente.
    private boolean buscar(NodoAVL r, int codigo){
        boolean encontrado = false;
        while((r != null) && ! encontrado)        {
            if(r.getValor().getId() < 0){
                r = r.getIzquierda();
            }else if(r.getValor().getId() > 0){
                r = r.getDerecha();
            }else{
                encontrado = true;
                break;
            }
            encontrado = buscar(r, codigo);
        }
        
        return encontrado;
    }
    
    // Eliminar nodo
    public void eliminar(int codigo) {
    	raiz = eliminar(codigo, raiz);
    }
    
    private NodoAVL eliminar(int codigo, NodoAVL t) {
    	if(t == null) {
    		return t;
    	}
    	if(t.getValor().getId() < 0) {
            t.setIzquierda(eliminar(codigo, t.getIzquierda()));
    	} else if(t.getValor().getId() > 0) {
            t.setDerecha(eliminar(codigo, t.getDerecha()));
    	}else {
            if(t.getIzquierda() == null || t.getDerecha() == null) {
                NodoAVL temp = null;
                if(temp == t.getIzquierda()) {
                    temp = t.getDerecha();
                }else {
                    temp = t.getIzquierda();
                }

                if(temp == null) {
                    temp = t;
                    t = null;
                }else {
                    t = temp;
                }
            }else {
                NodoAVL temp = sucesor(t.getDerecha());
                t.setValor(new ValorExpresiones(temp.getValor().getId(), temp.getValor().getValor(), temp.getValor().getTipo()));
                t.setDerecha(eliminar(temp.getValor().getId(), t.getDerecha()));
            }
    	}
    	
    	if(t == null) {
    		return t;
    	}
    	
    	t.setAltura(max(altura(t.getIzquierda()), altura(t.getDerecha())));
    	int balance = getFactorEquilibrio(t);
    	
    	// CASO 1: Izquierda-izquierda
    	if(balance > 1 && getFactorEquilibrio(t.getIzquierda()) >= 0) {
            return rotarConHijoDer(t);
    	}
    	// CASO 2: Izquierda-derecha
    	if(balance > 1 && getFactorEquilibrio(t.getIzquierda()) < 0) {
            t.setIzquierda(rotarConHijoIzq(t.getIzquierda()));
            return rotarConHijoDer(t);
    	}
    	// CASO 3: Derecha-derecha
    	if(balance < -1 && getFactorEquilibrio(t.getDerecha()) <= 0) {
            return rotarConHijoIzq(t);
    	}
    	// CASO 4: Derecha-izquierda
    	if(balance < -1 && getFactorEquilibrio(t.getDerecha()) > 0) {
            t.setDerecha(rotarConHijoDer(t.getDerecha()));
            return rotarConHijoIzq(t);
    	}
    	
    	return t;
    }
    
    // Nodo sucesor
    private NodoAVL sucesor(NodoAVL t) {
    	NodoAVL aux = t;
    	while(aux.getIzquierda() != null) {
            aux = aux.getIzquierda();
    	}
    	return aux;
    }
    
    // Editar
//    public void editar(String codigo_, Usuario usuario_nuevo) {
//    	editar(codigo_, usuario_nuevo, raiz);
//    }
//    
//    private void editar(String codigo_, Usuario usuario_nuevo, NodoAVL t) {
//    	if(t != null) {
//            if(t.getValor().getId().compareTo(codigo_) == 0) {
//                t.setUsuario(null);
//                t.setUsuario(usuario_nuevo);
//                return;
//            }
//            editar(codigo_, usuario_nuevo, t.getIzquierda());
//            editar(codigo_, usuario_nuevo, t.getDerecha());
//    	}
//    }

    boolean esRaiz, esIzquierdo;
	
    // Buscar valor
//    public Usuario buscarUsuario(String codigo) {
//        Usuario user_temp = null;
//        user_temp = buscarNodo(raiz, codigo).getValor();
//        return user_temp;
//    }
	
    // Buscar nodo
    public NodoAVL buscarNodo(String codigo) {
        return buscarNodo(raiz, codigo);
    }
	
	// Buscar nodo recursivo
    private NodoAVL buscarNodo(NodoAVL r, String codigo){
        if(r == null){
            return null;
        }
        if(r.getValor().getId() == 0){
            System.out.println("Se encontro usuario con ID: " + codigo);
            return r;
        }else if(r.getValor().getId() < 0){
//            System.out.println("No se encontro valor con ID: "+codigo);
            return buscarNodo(r.getIzquierda(), codigo);
        }else{
            return buscarNodo(r.getDerecha(), codigo);
//            System.out.println("Se encontro valor con ID: "+aux.getValor().getId());
        }
    }
    
//    public Usuario verificarContraseña(String codigo, String contrasena){
////        System.out.println("Pass a verificar: "+contrasena);
////        System.out.println("Nodo encontrado: "+buscarNodo(raiz, nickname).getNickname());
////        System.out.println("Pass: "+buscarNodo(raiz, nickname).getContraseña());
//    	Usuario user_temp = null;
//        if(buscarNodo(raiz, codigo).getValor().getContrasena().equals(contrasena)){
//            user_temp = buscarNodo(raiz, codigo).getValor();
//            return user_temp;
//        }
//        return null;
//    }
    
    // Recorrer inorder.
    public void inorder(){
        inorder(raiz);
    }
        
    private void inorder(NodoAVL r){
        if(r != null){
            inorder(r.getIzquierda());
            System.out.print(r.getValor().getId()+", ");
            inorder(r.getDerecha());
        }
    }
    
//    public ColaAVL listar(){
//        cola_auxiliar = new ColaAVL();
//        listar(raiz);
//        return cola_auxiliar;
//    }
//    
//    private void listar(NodoAVL nodo){
//        if(nodo == null){
//            System.out.println("Arbol vacio.");
//        }
//        if(nodo != null){
//            listar(nodo.getIzquierda());
//            cola_auxiliar.encolar(new NodoColaAVL(nodo.getValor()));
//            listar(nodo.getDerecha());
//        }
//    }
//    
    public String graficar(String opcion) throws IOException, InterruptedException{
        String nombre = "arbolAVL";
        
        if(opcion.equals("grafo")) {
            String dot_grafo_arbol_avl = "";
            dot_grafo_arbol_avl =  
            "digraph avl"
            +   "\n{"
                +   "\n\tgraph[color = \"lightcyan\", fontcolor = \"steelblue4\", fontname = serif, style = filled, label = \"Usuarios\"];"
                +   "\n\tnode[shape = egg, style = filled, color = navyblue, fontcolor = white, peripheries = 2];"
                +   "\n\tedge[color = deeppink];"
                +   "\n"
//                +   "\n\tsubgraph cluster_avl"
//                +   "\n\t{"
                +   "\n"
                    +   generarDot(raiz)
//                +   "\n\t}"
            +   "\n}";
            Escritura.escribirArchivoDot(nombre, dot_grafo_arbol_avl);
            Escritura.generarImagenDot(nombre);
            avl_aux = "";
            dot_grafo_arbol_avl = "";
            return "";
        }else if(opcion.equals("subgrafo")) {
            String dot_subgrafo_arbol_avl = "";
            dot_subgrafo_arbol_avl =
            "\n\tsubgraph cluster_avl"
            + 	"\n\t{"
            +   "\n\tgraph[color = \"lightcyan\", fontcolor = \"steelblue4\", fontname = serif, style = filled, label = \"Usuarios\"];"
            + 	"\n\tnode[shape = egg, style = filled, color = navyblue, fontcolor = white, peripheries = 2];"
            +   "\n\tedge[color = deeppink];"
            + 	"\n"
            + 	"\n"
                +   generarDot(raiz)
            + 	"\n\t}";
            return dot_subgrafo_arbol_avl;
        }
        return "";
    }
    
    private String generarDot(NodoAVL r) throws IOException{
        if(r != null)
        {
            generarDot(r.getIzquierda());
            // ................ Inicia codigo para graficar ................
            if(r.getIzquierda() != null && r.getDerecha() != null)
            {
                if(r.getIzquierda() != null)
                {
                    // Datos del nodo
                    avl_aux += 	
                    "\t\t" + r.getValor().getId()
                    +	"[label = <"
                    +   "<FONT POINT-SIZE = \"11\">"
                    +   " ID: " + r.getValor().getId()
                    +   " </FONT>"
                    +   ">"
                    +   "]"
                    +	"\n";
                        // Lista de imagenes, del valor
//                        if(!r.getValor().getImagenes().esVacia() || r.getValor().getImagenes().getTamano() == 0){
//                        if(r.getValor().getImagenes() != null){
//                            // Obteniendo subgrafo
//                            avl_aux += r.getValor().getImagenes().
//                            graficar("_usuario_" + r.getValor().getId(),
//                            "Imagenes del usuario " + r.getValor().getId());
//                            // Creando enlace
//                            avl_aux += r.getValor().getId() + "->"
//                            + "_usuario_" + r.getValor().getId() 
//                            + r.getValor().getImagenes().getInicio().getId();
//                        }else{
//                            System.out.println("Lista vacia");
//                        }
                        
                    // Datos del nodo izquierdo
                    avl_aux += 	
                    "\t\t" + r.getIzquierda().getValor().getId()
                    +	"[label = <"
                    +   "<FONT POINT-SIZE = \"11\">"
                    +   " ID: " + r.getIzquierda().getValor().getId()
                    +   " </FONT>"
                    +   ">"
                    +   "]"
                    +	"\n";
                        // Lista de imagenes, del valor (izquierda)
//                        if(!r.getIzquierda().getValor().getImagenes().esVacia() || r.getIzquierda().getValor().getImagenes().getTamano() == 0){
//                        if(r.getIzquierda().getValor().getImagenes() != null){
//                            // Obteniendo subgrafo (izquierda)
//                            avl_aux += r.getIzquierda().getValor().getImagenes().
//                            graficar("_usuario_" + r.getIzquierda().getValor().getId(), 
//                            "Imagenes del usuario " + r.getIzquierda().getValor().getId());
//                            // Creando enlace (izquierda)
//                            avl_aux += "\t\t" + r.getIzquierda().getValor().getId() + "->"
//                            + "_usuario_" + r.getIzquierda().getValor().getId() 
//                            + r.getIzquierda().getValor().getImagenes().getInicio().getId() + "\n";
//                        }else{
//                            System.out.println("Lista vacia");
//                        }
                    // Enlaces a los nodos
                    avl_aux += 	
                    "\t\t" + r.getValor().getId() + "->" + r.getIzquierda().getValor().getId()
                    +   "\n";
                }
                if(r.getDerecha() != null)
                {
                    // Datos del nodo
                    avl_aux += 	
                    "\t\t" + r.getValor().getId()
                    +	"[label = <"
                    +   "<FONT POINT-SIZE = \"11\">"
                    +   " ID: " + r.getValor().getId()
                    +   " </FONT>"
                    +    ">"
                    +   "]"
                    +	"\n";
                        // Lista de imagenes, del valor
//                        if(!r.getValor().getImagenes().esVacia()){
//                        if(r.getValor().getImagenes() != null){
//                            // Obteniendo subgrafo
//                            avl_aux += r.getValor().getImagenes().
//                            graficar("_usuario_" + r.getValor().getId(), 
//                            "Imagenes del usuario " + r.getValor().getId());
//                            // Creando enlace
//                            avl_aux += "\t\t" + r.getValor().getId() + "->"
//                            + "_usuario_" + r.getValor().getId() 
//                            + r.getValor().getImagenes().getInicio().getId() + "\n";
//                        }

                    avl_aux +=
                    "\t\t" + r.getDerecha().getValor().getId()
                    +	"[label = <"
                    +   "<FONT POINT-SIZE = \"11\">"
                    +   " ID: " + r.getDerecha().getValor().getId()
                    +   " </FONT>"
                    +   ">"
                    +   "]"
                    + 	"\n";
                        // Lista de imagenes, del valor (derecha)
//                        if(!r.getDerecha().getValor().getImagenes().esVacia() || r.getDerecha().getValor().getImagenes().getTamano() == 0){
//                        if(r.getDerecha().getValor().getImagenes() != null){
//                            // Obteniendo subgrafo (derecha)
//                            avl_aux += r.getDerecha().getValor().getImagenes().
//                            graficar("_usuario_" + r.getDerecha().getValor().getId(), 
//                            "Imagenes del usuario " + r.getDerecha().getValor().getId());
//                            // Creando enlace (derecha)
//                            avl_aux += "\t\t" + r.getDerecha().getValor().getId() + "->"
//                            + "_usuario_" + r.getDerecha().getValor().getId() 
//                            + r.getDerecha().getValor().getImagenes().getInicio().getId() + "\n";
//                        }else{
//                            System.out.println("Lista vacia");
//                        }

                    // Enlaces a los nodos
                    avl_aux += 	
                    "\t\t" + r.getValor().getId() + "->" + r.getDerecha().getValor().getId() 
                    +   "\n";
                }
            }
            else
            {
            	// Datos del nodo
            	avl_aux += 	
                "\t\t" + r.getValor().getId()
                +   "[label = <"
                +   "<FONT POINT-SIZE = \"11\">"
                +   " ID: " + r.getValor().getId()
                +   " </FONT>"
                +   ">"
                +   "]"
                +   "\n";
                    // Lista de imagenes, del valor
//                    if(!r.getValor().getImagenes().esVacia() || r.getValor().getImagenes().getTamano() == 0){
//                    if(r.getValor().getImagenes() != null){
//                        // Obteniendo subgrafo
//                        avl_aux += r.getValor().getImagenes().
//                        graficar("_usuario_" + r.getValor().getId(), 
//                        "Imagenes del usuario " + r.getValor().getId());
//                        // Creando enlace
//                        avl_aux += "\t\t" + r.getValor().getId() + "->"
//                        + "_usuario_" + r.getValor().getId() 
//                        + r.getValor().getImagenes().getInicio().getId() + "\n";
//                    }else{
//                            System.out.println("Lista vacia");
//                    }

            	// Enlaces a los nodos
                avl_aux += "\t\t" + r.getValor().getId();
                
                if(r.getIzquierda() != null && r.getDerecha() == null)
                {
                    // Enlaces a los nodos
                    avl_aux += 	
                    "->" + r.getIzquierda().getValor().getId() 
                    +   "\n";
                    
                    // Datos del nodo izquierdo
                    avl_aux +=
                    "\t\t" + r.getIzquierda().getValor().getId()
                    +	"[label = <"
                    +   "<FONT POINT-SIZE = \"11\">"
                    +   " ID: " + r.getIzquierda().getValor().getId()
                    +   " </FONT>"
                    +   ">"
                    +   "]"
                    +	"\n";
                        // Lista de imagenes, del valor (izquierda)
//                        if(!r.getIzquierda().getValor().getImagenes().esVacia() || r.getIzquierda().getValor().getImagenes().getTamano() == 0){
//                        if(r.getIzquierda().getValor().getImagenes() != null){
//                            // Obteniendo subgrafo (izquierda)
//                            avl_aux += r.getIzquierda().getValor().getImagenes().
//                            graficar("_usuario_" + r.getIzquierda().getValor().getId(), 
//                            "Imagenes del usuario " + r.getIzquierda().getValor().getId());
//                            // Creando enlace (izquierda)
//                            avl_aux += "\t\t" + r.getIzquierda().getValor().getId() + "->"
//                            + "_usuario_" + r.getIzquierda().getValor().getId() 
//                            + r.getIzquierda().getValor().getImagenes().getInicio().getId() + "\n";
//                        }else{
//                            System.out.println("Lista vacia");
//                        }
                }
                if(r.getIzquierda() == null && r.getDerecha() != null)
                {
                    // Enlaces a los nodos
                    avl_aux += 	
                    "->" + r.getDerecha().getValor().getId()
                    + 	"\n";

                    // Datos del nodo derecho
                    avl_aux += 	
                    "\t\t" + r.getDerecha().getValor().getId()
                    +	"[label = <"
                    +   "<FONT POINT-SIZE = \"11\">"
                    +   " ID: " + r.getDerecha().getValor().getId()
                    +   " </FONT>"
                    +   ">"
                    +   "]"
                    +	"\n";
                        // Lista de imagenes, del valor (derecha)
//                        if(!r.getDerecha().getValor().getImagenes().esVacia() || r.getDerecha().getValor().getImagenes().getTamano() == 0){
//                        if(r.getDerecha().getValor().getImagenes() != null){
//                            // Obteniendo subgrafo (derecha)
//                            avl_aux += r.getDerecha().getValor().getImagenes().
//                            graficar("_usuario_" + r.getDerecha().getValor().getId(), 
//                            "Imagenes del usuario " + r.getDerecha().getValor().getId());
//                            // Creando enlace (derecha)
//                            avl_aux += "\t\t" + r.getDerecha().getValor().getId() + "->"
//                            + "_usuario_" + r.getDerecha().getValor().getId() 
//                            + r.getDerecha().getValor().getImagenes().getInicio().getId() + "\n";
//                        }else{
//                            System.out.println("Lista vacia");
//                        }
                }
            }
            
            avl_aux += "\n";
            // ................ Finaliza codigo para graficar ................
            generarDot(r.getDerecha());
        }
        return avl_aux;
    }
}
