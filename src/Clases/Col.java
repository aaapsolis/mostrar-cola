package Clases;


public class Col<T> {
    
    //Atributos
    private T elemento;
    private Col<T> siguiente; //Apunta al siguiente nodo
    
    //Contructor
    public Col(T elemento, Col<T> siguiente){
        this.elemento = elemento;
        this.siguiente = siguiente;
    }

    //Metodos
    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public Col<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Col<T> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return elemento+"\n";
    }
    
    
    
}
