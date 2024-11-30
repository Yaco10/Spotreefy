public class NodoSublistaCanciones {
    NodoCancion cancion;
    NodoSublistaCanciones siguiente;

    public NodoSublistaCanciones(NodoCancion cancion) {
        this.cancion = cancion;
        siguiente = null;
    }

    public NodoCancion getCancion() {
        return cancion;
    }

    public void setCancion(NodoCancion cancion) {
        this.cancion = cancion;
    }

    public NodoSublistaCanciones getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSublistaCanciones siguiente) {
        this.siguiente = siguiente;
    }
}
