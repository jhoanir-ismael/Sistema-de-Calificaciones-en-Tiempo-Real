/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

/**
 *
 * @author Ismael Flores
 */
public class Nodo {
    String matricula;
    double dato;
    Nodo siguiente;

    public Nodo(String matricula, double dato) {
        this.matricula = matricula;
        this.dato = dato;
        this.siguiente = null;
    }
}
