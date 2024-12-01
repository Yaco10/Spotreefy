class NodoPlaylistSeguida {
    // NodoIndexadoCancion canciones (borrar)
    String nombre;
    String usuario;
    NodoPlaylistSeguida siguiente;

    public NodoPlaylistSeguida(String usuario, String nombre) {
        this.usuario = nombre;
        this.nombre = nombre;
    }

    public String getUsuario() {
        return this.usuario;
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

    public void insertarNodoCancion() {

    }
}
