//  ::::::::::::::::::::::::    Codigo de usuario   ::::::::::::::::::::::::

//  ------------------------    Importaciones  ------------------------ 
package com.proyecto1.analizadores;
import java_cup.runtime.*;
import java.util.LinkedList;

//  ::::::::::::::::::::::::    Opciones y declaraciones   ::::::::::::::::::::::::

%%

%{
    // Codigo de usuario en sintaxis java
    //public static LinkedList<TError> TablaEL = new LinkedList<TError>();
%}

//  ------------------------    Directivas  ------------------------    
%public
%class Lexico
%cupsym sym
%cup
%char 
%column
%full
%ignorecase
%line
%unicode

// ------------------------    Expresiones regulares    ------------------------    
digito      = [0-9]
minuscula   = [a-z]
mayuscula   = [A-Z]
numero      = [0-9]+
letras      = [a-zA-Z]
decimal     = [0-9]+("."[ | 0-9]+)?
espacios    = [ " " | \t\r\n\f]
caracteres  = [!\"#\$%&\'()\*\+\,\-\.\/:;<=>\?@\[\]\\\^_`{}]
//Identificadores
identificador      = [[a-zA-Z]+ | [0-9]* | "_"]
comment     = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment      = "<!" [^*] ~"!>" | "<!" "*"+ "!>"
EndOfLineComment        = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment    = "<!" {CommentContent} "*"+ "!>"
CommentContent          = ( [^*] | \*+ [^/*] )*
LineTerminator          = \r|\n|\r\n
InputCharacter          = [^\r\n]

//  ------------------------    Estados ------------------------    

%%

//  ::::::::::::::::::::::::    Reglas lexicas   ::::::::::::::::::::::::
//  ------------------------    Palabras aceptadas    ------------------------    
"CONJ"                      {return new Symbol(sym.conjunto, yycolumn, yyline, yytext());}
//  ------------------------    Simbolos    ------------------------    
"_"     		{  System.out.println("Reconocio: " + yytext() + " guionBajo"); return new Symbol(sym.guionBajo, yycolumn, yyline, yytext());}
// Estructura archivo
"{"     		{  System.out.println("Reconocio: " + yytext() + " llaveA"); return new Symbol(sym.llaveA, yycolumn, yyline, yytext());}
"}"     		{  System.out.println("Reconocio: " + yytext() + " llaveC"); return new Symbol(sym.llaveC, yycolumn, yyline, yytext());}
";"     		{  System.out.println("Reconocio: " + yytext() + " puntoComa"); return new Symbol(sym.puntoComa, yycolumn, yyline, yytext());}
":"     		{  System.out.println("Reconocio: " + yytext() + " dosPuntos"); return new Symbol(sym.dosPuntos, yycolumn, yyline, yytext());}
"("     		{  System.out.println("Reconocio: " + yytext() + " parA"); return new Symbol(sym.parA, yycolumn, yyline, yytext());}
")"     		{  System.out.println("Reconocio: " + yytext() + " parC"); return new Symbol(sym.parC, yycolumn, yyline, yytext());}
"->"     		{  System.out.println("Reconocio: " + yytext() + " equivale"); return new Symbol(sym.equivale, yycolumn, yyline, yytext());}
"%%"     		{  System.out.println("Reconocio: " + yytext() + " porcentajes"); return new Symbol(sym.porcentajes, yycolumn, yyline, yytext());}
// Operaciones
"."     		{  System.out.println("Reconocio: " + yytext() + " concatenacion"); return new Symbol(sym.concatenacion, yycolumn, yyline, yytext());}
"|"     		{  System.out.println("Reconocio: " + yytext() + " orBooleana"); return new Symbol(sym.orBooleana, yycolumn, yyline, yytext());}
"*"     		{  System.out.println("Reconocio: " + yytext() + " asterisco"); return new Symbol(sym.asterisco, yycolumn, yyline, yytext());}
"+"     		{  System.out.println("Reconocio: " + yytext() + " mas"); return new Symbol(sym.mas, yycolumn, yyline, yytext());}
"?"     		{  System.out.println("Reconocio: " + yytext() + " interrogacion"); return new Symbol(sym.interrogacion, yycolumn, yyline, yytext());}
// Conjuntos
","     		{  System.out.println("Reconocio: " + yytext() + " coma"); return new Symbol(sym.coma, yycolumn, yyline, yytext());}
"~"     		{  System.out.println("Reconocio: " + yytext() + " tilde"); return new Symbol(sym.tilde, yycolumn, yyline, yytext());}
// Simbolos especiales
"!"     		{  System.out.println("Reconocio: " + yytext() + " admiracion"); return new Symbol(sym.admiracion, yycolumn, yyline, yytext());}
"#"     		{  System.out.println("Reconocio: " + yytext() + " numeral"); return new Symbol(sym.numeral, yycolumn, yyline, yytext());}
"$"     		{  System.out.println("Reconocio: " + yytext() + " dolar"); return new Symbol(sym.dolar, yycolumn, yyline, yytext());}
"%"     		{  System.out.println("Reconocio: " + yytext() + " porcentaje"); return new Symbol(sym.porcentaje, yycolumn, yyline, yytext());}
"&"     		{  System.out.println("Reconocio: " + yytext() + " ampersand"); return new Symbol(sym.ampersand, yycolumn, yyline, yytext());}
// Caracteres especiales
"\""     		{  System.out.println("Reconocio: " + yytext() + " comillasDobles"); return new Symbol(sym.comillasDobles, yycolumn, yyline, yytext());}
"\'"     		{  System.out.println("Reconocio: " + yytext() + " comillaSimple"); return new Symbol(sym.comillaSimple, yycolumn, yyline, yytext());}
"\\n"     		{  System.out.println("Reconocio: " + yytext() + " saltoLinea"); return new Symbol(sym.saltoLinea, yycolumn, yyline, yytext());}

\n {yycolumn = 1;}
//  ------------------------    Simbolos ER    ------------------------    
{digito}   	  	{ System.out.println("Reconocio: " + yytext() + " digito");  return new Symbol(sym.digito, yycolumn, yyline, yytext());}
{minuscula}	    { System.out.println("Reconocio: " + yytext() + " minuscula");  return new Symbol(sym.minuscula, yycolumn, yyline, yytext());}
{mayuscula}     { System.out.println("Reconocio: " + yytext() + " mayuscula");  return new Symbol(sym.mayuscula, yycolumn, yyline, yytext());}
{numero}   	  	{ System.out.println("Reconocio: " + yytext() + " numero");  return new Symbol(sym.numero, yycolumn, yyline, yytext());}
{letras}   	  	{ System.out.println("Reconocio: " + yytext() + " letras");  return new Symbol(sym.letras, yycolumn, yyline, yytext());}
{decimal}   	{ System.out.println("Reconocio: " + yytext() + " decimal");  return new Symbol(sym.decimal, yycolumn, yyline, yytext());}
{caracteres}   	{ System.out.println("Reconocio: " + yytext() + " caracteres");  return new Symbol(sym.caracteres, yycolumn, yyline, yytext());}
{identificador}        { System.out.println("Reconocio: " + yytext() + " identificador");  return new Symbol(sym.identificador, yycolumn, yyline, yytext());}
//  ------------------------    Espacios    ------------------------    
{espacios}          		{/* Espacios en blanco, se ignoran */}
//  ------------------------    Comentarios    ------------------------    
{comment}				    {/*IGNORE*/}
//  ------------------------    Errores lexicos ------------------------    
.	 			            { System.out.println("Error lexico " +  yytext() + ", linea: " + yyline + ", columna: " + yycolumn); }