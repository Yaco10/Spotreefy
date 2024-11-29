// playlist
// lista de canciones

class NodoPlaylistPropia {
    String nombre; // factor de orden
    NodoIndexadoCancion listaCanciones; // (lista sin orden)
    // ListaCanciones listaCanciones // ¿?
    
    NodoPlaylistPropia siguiente;

    public NodoPlaylistPropia(String nombre) {
        this.nombre = nombre;
        this.listaCanciones = null; // Innecesario
        this.siguiente = null; // Innecesario
    }

    public String getNombre() {
        return this.nombre;
    }

    public NodoPlaylistPropia getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoPlaylistPropia nodo) {
        this.siguiente = nodo;
    }

    // TO DO
    // Inserción sin orden.
    
    public void insertarNodoCancion() {
        
    }
}
