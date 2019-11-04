package Clases;


public class Cola<T> {

    //Atributos
    private Col<T> primero;
    private Col<T> ultimo;
    private int tamanio;

    public Cola() {
        primero = null;
        ultimo = null;
        tamanio = 0;

    }

    /**
     * Indica si la cola esta vacia
     * @return 
     */
    public boolean isEmpty() {
        return primero == null;
    }

    /**
     * Indica el tamaño de la cola
     * @return 
     */
    public int size() {
        return tamanio;
    }

    /**
     * Devuelve el primer elemento en la cola
     * @return 
     */
    public T primero() {
        if (isEmpty()) {
            return null;
        }

        return primero.getElemento();
    }

    /**
     * Elimina y devuelve el primer elemento de la cola
     * @return 
     */
    public T dequeue() {

        if (isEmpty()) {
            return null;
        }

        T elemento = primero.getElemento();
        Col<T> aux = primero.getSiguiente();
        primero = null;

        primero = aux;
        tamanio--;
        if (isEmpty()) {
            ultimo = null;
        }

        return elemento;
    }

    /**
     * Añade un nuevo elemento a la cola
     * @param elemento
     * @return 
     */
    public T enqueue(T elemento) {

        Col<T> aux = new Col(elemento, null);

        if (isEmpty()) {
            primero = aux;
            ultimo = aux;
        } else {
            if (size() == 1) {
                primero.setSiguiente(aux);
            } else {
                ultimo.setSiguiente(aux);
            }
            ultimo = aux;
        }

        tamanio++;
        return aux.getElemento();

    }

    /**
     * Muestra el contenido
     * @return 
     */
    public String toString() {

        if (isEmpty()) {
            return "La lista esta vacia";
        } else {

            String cadena = "";

            Col<T> aux = primero;
            while (aux != null) {
                cadena += aux;
                aux = aux.getSiguiente();
            }

            return cadena;

        }

    }

}
