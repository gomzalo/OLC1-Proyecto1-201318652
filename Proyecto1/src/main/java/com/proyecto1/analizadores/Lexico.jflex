//  ::::::::::::::::::::::::    Codigo de usuario   ::::::::::::::::::::::::

//  ------------------------    Importaciones  ------------------------ 
package com.proyecto1.analizadores;
import java_cup.runtime.*;
import java.util.LinkedList;
import com.proyecto1.estructuras.arbol.*;
import com.proyecto1.estructuras.pojos.*;

//  ::::::::::::::::::::::::    Opciones y declaraciones   ::::::::::::::::::::::::

%%

%{
    // Codigo de usuario en sintaxis java
    public static LinkedList<TError> TablaEL = new LinkedList<TError>();
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
// digito      = [0-9]
// minuscula   = [a-z]
// mayuscula   = [A-Z]
numero                  = [0-9]+
letras                  = [a-zA-Z]
decimal                 = [0-9]+("."[ | 0-9]+)?
//                                                                                     [ digitos 0-9: 48-57 ]                   [minusculas a-z: 65-90 ]        [mayusculas A-Z: 97-122 ]
// ASCII cod:                  33 |  34  |  35 | 36  | 37  | 38  |  39  |  40 | 41  | 42  |  43 |  44 | 45  | 46  |  47 |  58 | 59  |  60 | 61  |  62 |  63 |  64 |  91 |  92  | 93  | 94  | 95  | 96  | 123 | 124 | 125 |
// caracter                = ["!" | "\"" | "#" | "$" | "%" | "&" | "\'" | "(" | ")" | "*" | "+" | "," | "-" | "." | "/" | ":" | ";" | "<" | "=" | ">" | "?" | "@" | "[" | "\\" | "]" | "^" | "_" | "`" | "{" | "|" | "}"]
// ..........   Identificadores     ..........
identificador           = [a-zA-Z_][a-zA-Z0-9_]*
cadena                  = \"(.[^\"]*)\"
valorIDConjunto         = "{" {identificador} "}"
// equivale                = "->"
comillasDoblesEspecial  = "\\""\""
comillaSimpleEspecial   = "\\""\'"
saltoLineaEspecial      = "\\""n"
caracteresEspeciales    = ({comillasDoblesEspecial} | {comillaSimpleEspecial} | {saltoLineaEspecial})
// ..........   Comentarios     ..........
comment                 = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}
TraditionalComment      = "<!" [^*] ~"!>" | "<!" "*"+ "!>"
EndOfLineComment        = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment    = "<!" {CommentContent} "*"+ "!>"
CommentContent          = ( [^*] | \*+ [^/*] )*
LineTerminator          = \r|\n|\r\n
InputCharacter          = [^\r\n]

espacios                = [ " " | \t\r\n\f]
// Expresiones
// operador                = ("." | "|" | "*" | "+" | "?")+
// operador                = [.|*+?]+
// valExpresion            = (("{"{identificador}"}")|("\""{caracter}"\""))+


//  ------------------------    Estados ------------------------    

%%

//  ::::::::::::::::::::::::    Reglas lexicas   ::::::::::::::::::::::::
//  ------------------------    Palabras aceptadas    ------------------------    
"CONJ"          {  System.out.println("Reconocio palabra reservada: " + yytext() + "."); return new Symbol(sym.PR_conjunto, yycolumn, yyline, yytext());}
//  ------------------------    sym    ------------------------    
// ..........   Estructura archivo      ..........
// 59
";"     		{  System.out.println("Reconocio: " + yytext() + " puntoComa"); return new Symbol(sym.puntoComa, yycolumn, yyline, yytext());}
// 58
":"     		{  System.out.println("Reconocio: " + yytext() + " dosPuntos"); return new Symbol(sym.dosPuntos, yycolumn, yyline, yytext());}
// 123
"{"     		{  System.out.println("Reconocio: " + yytext() + " llaveA"); return new Symbol(sym.llaveA, yycolumn, yyline, yytext());}
// 125
"}"     		{  System.out.println("Reconocio: " + yytext() + " llaveC"); return new Symbol(sym.llaveC, yycolumn, yyline, yytext());}
// ..........   Operaciones     ..........
// 42
"*"     		{  System.out.println("Reconocio: " + yytext() + " asterisco"); return new Symbol(sym.asterisco, yycolumn, yyline, yytext());}
// 43
"+"     		{  System.out.println("Reconocio: " + yytext() + " mas"); return new Symbol(sym.mas, yycolumn, yyline, yytext());}
// 46
"."     		{  System.out.println("Reconocio: " + yytext() + " punto"); return new Symbol(sym.punto, yycolumn, yyline, yytext());}
// 63
"?"     		{  System.out.println("Reconocio: " + yytext() + " interrogacion"); return new Symbol(sym.interrogacion, yycolumn, yyline, yytext());}
// 124
"|"     		{  System.out.println("Reconocio: " + yytext() + " orBooleana"); return new Symbol(sym.orBooleana, yycolumn, yyline, yytext());}
// ..........   Conjuntos   ..........
// 44
","     		{  System.out.println("Reconocio: " + yytext() + " coma"); return new Symbol(sym.coma, yycolumn, yyline, yytext());}
// 126
"~"     		{  System.out.println("Reconocio: " + yytext() + " tilde"); return new Symbol(sym.tilde, yycolumn, yyline, yytext());}
// ..........   sym especiales     ..........
// 33
"!"     		{  System.out.println("Reconocio: " + yytext() + " admiracion"); return new Symbol(sym.admiracion, yycolumn, yyline, yytext());}
// 34
"\""     		{  System.out.println("Reconocio: " + yytext() + " comillasDobles"); return new Symbol(sym.comillasDobles, yycolumn, yyline, yytext());}
// 35
"#"     		{  System.out.println("Reconocio: " + yytext() + " numeral"); return new Symbol(sym.numeral, yycolumn, yyline, yytext());}
// 36
"$"     		{  System.out.println("Reconocio: " + yytext() + " dolar"); return new Symbol(sym.dolar, yycolumn, yyline, yytext());}
// 37
"%"     		{  System.out.println("Reconocio: " + yytext() + " porcentaje"); return new Symbol(sym.porcentaje, yycolumn, yyline, yytext());}
// 38
"&"     		{  System.out.println("Reconocio: " + yytext() + " ampersand"); return new Symbol(sym.ampersand, yycolumn, yyline, yytext());}
// 39
"\'"     		{  System.out.println("Reconocio: " + yytext() + " comillaSimple"); return new Symbol(sym.comillaSimple, yycolumn, yyline, yytext());}
// 40
"("     		{  System.out.println("Reconocio: " + yytext() + " parentesisA"); return new Symbol(sym.parentesisA, yycolumn, yyline, yytext());}
// 41
")"     		{  System.out.println("Reconocio: " + yytext() + " parentesisC"); return new Symbol(sym.parentesisC, yycolumn, yyline, yytext());}
// 42 *
// 43 +
// 44 ,
// 45
"-"     		{  System.out.println("Reconocio: " + yytext() + " guion"); return new Symbol(sym.guion, yycolumn, yyline, yytext());}
// 46 .
// 47
"/"     		{  System.out.println("Reconocio: " + yytext() + " diagonal"); return new Symbol(sym.diagonal, yycolumn, yyline, yytext());}
// [ digitos 0-9: 48-57 ]
// 58 ,
// 59 ;
// 60
"<"     		{  System.out.println("Reconocio: " + yytext() + " menorQue"); return new Symbol(sym.menorQue, yycolumn, yyline, yytext());}
// 61
"="     		{  System.out.println("Reconocio: " + yytext() + " igual"); return new Symbol(sym.igual, yycolumn, yyline, yytext());}
// 62
">"     		{  System.out.println("Reconocio: " + yytext() + " mayorQue"); return new Symbol(sym.mayorQue, yycolumn, yyline, yytext());}
// 63 ?
// 64
"@"     		{  System.out.println("Reconocio: " + yytext() + " arroba"); return new Symbol(sym.arroba, yycolumn, yyline, yytext());}
// [mayusculas A-Z: 65-90 ]
// 91
"["     		{  System.out.println("Reconocio: " + yytext() + " corcheteA"); return new Symbol(sym.corcheteA, yycolumn, yyline, yytext());}
// 92
"\\"     		{  System.out.println("Reconocio: " + yytext() + " diagonalInversa"); return new Symbol(sym.diagonalInversa, yycolumn, yyline, yytext());}
// 93
"]"     		{  System.out.println("Reconocio: " + yytext() + " corcheteC"); return new Symbol(sym.corcheteC, yycolumn, yyline, yytext());}
// 94
"^"     		{  System.out.println("Reconocio: " + yytext() + " acentoCircunflejo"); return new Symbol(sym.acentoCircunflejo, yycolumn, yyline, yytext());}
// 95
"_"     		{  System.out.println("Reconocio: " + yytext() + " guionBajo"); return new Symbol(sym.guionBajo, yycolumn, yyline, yytext());}
// 96
"`"     		{  System.out.println("Reconocio: " + yytext() + " acentoGrave"); return new Symbol(sym.acentoGrave, yycolumn, yyline, yytext());}
// [minusculas a-z: 97-122 ]
// 123 {
// 124 |
// 125 }

// 10
// "\n"     		{  System.out.println("Reconocio: " + yytext() + " saltoLinea"); return new Symbol(sym.saltoLinea, yycolumn, yyline, yytext());}

\n {yycolumn = 1;}
//  ------------------------    Espacios    ------------------------    
{espacios}          		{/* Espacios en blanco, se ignoran */}
//  ------------------------    Comentarios    ------------------------    
{comment}				    {/*IGNORE*/}
//  ------------------------    sym ER    ------------------------    
// {digito}   	  	{ System.out.println("Reconocio: " + yytext() + " digito");  return new Symbol(sym.digito, yycolumn, yyline, yytext());}
// {minuscula}	    { System.out.println("Reconocio: " + yytext() + " minuscula");  return new Symbol(sym.minuscula, yycolumn, yyline, yytext());}
// {mayuscula}     { System.out.println("Reconocio: " + yytext() + " mayuscula");  return new Symbol(sym.mayuscula, yycolumn, yyline, yytext());}
{numero}   	  	                { System.out.println("Reconocio: " + yytext() + " numero");  return new Symbol(sym.numero, yycolumn, yyline, yytext());}
{letras}   	            	    { System.out.println("Reconocio: " + yytext() + " letras");  return new Symbol(sym.letras, yycolumn, yyline, yytext());}
{decimal}   	                { System.out.println("Reconocio: " + yytext() + " decimal");  return new Symbol(sym.decimal, yycolumn, yyline, yytext());}
{identificador}                 { System.out.println("Reconocio: " + yytext() + " identificador");  return new Symbol(sym.identificador, yycolumn, yyline, yytext());}
{cadena}                        { System.out.println("Reconocio: " + yytext() + " cadena");  return new Symbol(sym.cadena, yycolumn, yyline, yytext());}
{valorIDConjunto}  	            { System.out.println("Reconocio: " + yytext() + " valorIDConjunto");  return new Symbol(sym.valorIDConjunto, yycolumn, yyline, yytext());}
// Expresiones
// {equivale}  	                { System.out.println("Reconocio: " + yytext() + " equivale");  return new Symbol(sym.equivale, yycolumn, yyline, yytext());}
// Caracteres especiales
{caracteresEspeciales}        {  System.out.println("Reconocio: " + yytext() + " caracteresEspeciales"); return new Symbol(sym.caracteresEspeciales, yycolumn, yyline, yytext());}
// {comillasDoblesEspecial}        {  System.out.println("Reconocio: " + yytext() + " comillasDoblesEspecial"); return new Symbol(sym.comillasDoblesEspecial, yycolumn, yyline, yytext());}
// {comillaSimpleEspecial}         {  System.out.println("Reconocio: " + yytext() + " comillaSimpleEspecial"); return new Symbol(sym.comillaSimpleEspecial, yycolumn, yyline, yytext());}
// {saltoLineaEspecial}     		{  System.out.println("Reconocio: " + yytext() + " saltoLineaEspecial"); return new Symbol(sym.saltoLineaEspecial, yycolumn, yyline, yytext());}
//  ------------------------    Errores lexicos ------------------------    
    .	 			            {   System.out.println("Error lexico " +  yytext() + ", linea: " + yyline + ", columna: " + yycolumn); 
                                    TError datos = new TError(yytext(), yyline, yycolumn, "Error lexico", "Simbolo no reconocido.");
                                    TablaEL.add(datos);
                                }