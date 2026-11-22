/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calificaciones;

/**
 *
 * @author Ismael Flores
 */
public class ListaEnlazada {
    private Nodo cabeza;
    private int tamanio;

    public ListaEnlazada() {
        cabeza = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean registrar(String matricula, double dato) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.matricula.equals(matricula)) {
                return false;
            }
            actual = actual.siguiente;
        }

        insertarOrdenado(matricula, dato);
        return true;
    }

    private void insertarOrdenado(String matricula, double dato) {
        Nodo nuevo = new Nodo(matricula, dato);

        if (estaVacia() || dato < cabeza.dato) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null && actual.siguiente.dato <= dato) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamanio++;
    }

    public boolean buscar(double dato) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato == dato) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean eliminar(String matricula) {
        if (estaVacia()) {
            return false;
        }

        if (cabeza.matricula.equals(matricula)) {
            cabeza = cabeza.siguiente;
            tamanio--;
            return true;
        }

        Nodo anterior = cabeza;
        Nodo actual = cabeza.siguiente;
        while (actual != null) {
            if (actual.matricula.equals(matricula)) {
                anterior.siguiente = actual.siguiente;
                tamanio--;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    public boolean corregir(String matricula, double dato) {
        if (!eliminar(matricula)) {
            return false;
        }
        insertarOrdenado(matricula, dato);
        return true;
    }

    public Nodo obtenerPorPosicion(int posicion) {
        if (posicion < 1 || posicion > tamanio) {
            return null;
        }

        Nodo actual = cabeza;
        for (int i = 1; i < posicion; i++) {
            actual = actual.siguiente;
        }
        return actual;
    }

    public double obtenerMediana() {
        if (estaVacia()) {
            return 0;
        }

        Nodo centro = obtenerPorPosicion((tamanio + 1) / 2);
        if (tamanio % 2 == 0) {
            return (centro.dato + centro.siguiente.dato) / 2.0;
        }
        return centro.dato;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("No hay calificaciones vigentes.");
            return;
        }

        int posicion = 1;
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(posicion + ". " + actual.matricula + " -- " + actual.dato);
            posicion++;
            actual = actual.siguiente;
        }
    }
}
