/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calificaciones;

import java.util.Scanner;

/**
 *
 * @author Ismael Flores
 * Integrantes:
 *  Adriana Briseyda Martínez Cabañuelas
 *  Jhoanir Ismael Hidalgo Flores
 */
public class Calificaciones {


    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ListaEnlazada lista = new ListaEnlazada();
        int opcion;
        String matricula;
        double dato;

        do {
            System.out.println("\n SISTEMA DE CALIFICACIONES ");
            System.out.println("1. Registrar");
            System.out.println("2. Mostrar calificaciones");
            System.out.println("3. Consultar por posicion");
            System.out.println("4. Ver minima, maxima y mediana");
            System.out.println("5. Corregir calificacion");
            System.out.println("6. Eliminar calificacion");
            System.out.println("7. Buscar calificacion");
            System.out.println("8. Ver total de alumnos");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    matricula = leerMatricula(teclado);
                    dato = leerCalificacion(teclado);
                    if (lista.registrar(matricula, dato)) {
                        System.out.println("Entrega registrada.");
                    } else {
                        System.out.println("La matricula ya tiene una calificacion.");
                    }
                    break;
                case 2:
                    lista.mostrar();
                    break;
                case 3:
                    System.out.print("Posicion (desde 1): ");
                    int posicion = leerEntero(teclado);
                    Nodo nodo = lista.obtenerPorPosicion(posicion);
                    if (nodo == null) {
                        System.out.println("Posicion fuera de la lista.");
                    } else {
                        System.out.println(nodo.matricula + " -> " + nodo.dato);
                    }
                    break;
                case 4:
                    mostrarResumen(lista);
                    break;
                case 5:
                    matricula = leerMatricula(teclado);
                    dato = leerCalificacion(teclado);
                    if (lista.corregir(matricula, dato)) {
                        System.out.println("Calificacion corregida.");
                    } else {
                        System.out.println("Matricula no encontrada.");
                    }
                    break;
                case 6:
                    matricula = leerMatricula(teclado);
                    if (lista.eliminar(matricula)) {
                        System.out.println("Calificacion eliminada.");
                    } else {
                        System.out.println("Matricula no encontrada.");
                    }
                    break;
                case 7:
                    dato = leerCalificacion(teclado);
                    if (lista.buscar(dato)) {
                        System.out.println("Si existe esa calificacion.");
                    } else {
                        System.out.println("No existe esa calificacion.");
                    }
                    break;
                case 8:
                    System.out.println("Total de alumnos registrados: " + lista.getTamanio());
                    break;
                case 0:
                    System.out.println("Fin del programa.");
                    break;
                default:
                    System.out.println("Selecciona una opcion del menu.");
            }
        } while (opcion != 0);

        teclado.close();
    }

    private static int leerEntero(Scanner teclado) {
        while (!teclado.hasNextInt()) {
            System.out.print("Escribe un numero entero: ");
            teclado.nextLine();
        }
        int numero = teclado.nextInt();
        teclado.nextLine();
        return numero;
    }

    private static String leerMatricula(Scanner teclado) {
        String matricula;
        do {
            System.out.print("Matricula: ");
            matricula = teclado.nextLine().trim();
            if (matricula.isEmpty()) {
                System.out.println("Escribe una matricula.");
            }
        } while (matricula.isEmpty());
        return matricula;
    }

    private static double leerCalificacion(Scanner teclado) {
        double dato = 0;
        boolean valida;
        do {
            System.out.print("Calificacion (0 a 10): ");
            if (teclado.hasNextDouble()) {
                dato = teclado.nextDouble();
                valida = dato >= 0 && dato <= 10;
            } else {
                valida = false;
            }
            teclado.nextLine();
            if (!valida) {
                System.out.println("Escribe un numero entre 0 y 10.");
            }
        } while (!valida);
        return dato;
    }

    private static void mostrarResumen(ListaEnlazada lista) {
        if (lista.estaVacia()) {
            System.out.println("No hay calificaciones vigentes.");
            return;
        }

        Nodo primero = lista.obtenerPorPosicion(1);
        Nodo ultimo = lista.obtenerPorPosicion(lista.getTamanio());
        System.out.println("Minima: " + primero.dato);
        System.out.println("Maxima: " + ultimo.dato);
        System.out.println("Mediana: " + lista.obtenerMediana());
    }    
}
