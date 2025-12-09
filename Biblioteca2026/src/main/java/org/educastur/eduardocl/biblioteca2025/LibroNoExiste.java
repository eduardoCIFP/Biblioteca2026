/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.educastur.eduardocl.biblioteca2025;


public class LibroNoExiste extends Exception {
    public LibroNoExiste(String cadena){                                                                        
                 super(cadena); //Llama al constructor de Exception y le pasa el contenido de cadena              
    }
}
