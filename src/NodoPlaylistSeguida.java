class NodoPlaylistSeguida {
    private String dueño, nombre;
    private NodoPlaylistSeguida siguiente;

    public NodoPlaylistSeguida(String dueño, String nombre) {
        this.dueño = dueño;
        this.nombre = nombre;
        this.siguiente = null; // Innecesario
    }

    public String getDueño() {
        return this.dueño;
    }

    public String getNombre() {
        return this.nombre;
    }

    public NodoPlaylistSeguida getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoPlaylistSeguida nodo) {
        this.siguiente = nodo;
    }
}
