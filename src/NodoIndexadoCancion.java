class NodoIndexadoCancion {
    NodoCancion cancion; // conectado a nodos de ArbolCanciones
    NodoIndexadoCancion siguiente;

    public NodoIndexadoCancion(NodoCancion cancion) {
        this.cancion = cancion;
        this.siguiente = null; // Innecesario
    }

    public NodoIndexadoCancion getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoIndexadoCancion nodo) {
        this.siguiente = nodo;
    }
}
