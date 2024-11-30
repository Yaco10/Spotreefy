class NodoAutor {
    private String nombre;
    private ListaCanciones canciones;
    private NodoAutor siguiente;

    public NodoAutor(String nombre) {
        this.nombre = nombre;
        this.canciones = new ListaCanciones();
        this.siguiente = null; // Innecesario
    }

    public String getNombre() {
        return this.nombre;
    }

    public ListaCanciones getCanciones() {
        return this.canciones;
    }

    public NodoAutor getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoAutor nodo) {
        this.siguiente = nodo;
    }
}