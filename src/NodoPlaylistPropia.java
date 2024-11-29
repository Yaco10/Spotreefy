class NodoPlaylistPropia {
    String nombre;
    ListaIndexadaCanciones playlist;
    NodoPlaylistPropia siguiente;

    public NodoPlaylistPropia(String nombre) {
        this.nombre = nombre;
        this.playlist = new ListaIndexadaCanciones();
        this.siguiente = null; // Innecesario
    }

    public String getNombre() {
        return this.nombre;
    }

    public ListaIndexadaCanciones getPlaylist() {
        return this.playlist;
    }

    public NodoPlaylistPropia getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoPlaylistPropia nodo) {
        this.siguiente = nodo;
    }
}