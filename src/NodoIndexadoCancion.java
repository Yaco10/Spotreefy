class NodoIndexadoCancion {
    private NodoCancion cancion;
    private NodoIndexadoCancion siguiente;

    public NodoIndexadoCancion(NodoCancion cancion) {
        this.cancion = cancion;
        this.siguiente = null; // Innecesario
    }

    public NodoCancion getCancion() {
        return this.cancion;
    }

    public NodoIndexadoCancion getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoIndexadoCancion nodo) {
        this.siguiente = nodo;
    }
}
