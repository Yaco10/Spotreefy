// playlist
// lista de canciones
class NodoPlaylistPropiaLista {
    String nombre; // 
    NodoIndexadoCancion canciones; // (lista sin orden)
    NodoPlaylistPropiaLista siguiente;

    public NodoPlaylistPropiaLista(String nombre) {
        this.nombre = nombre;
        this.canciones = null; // Innecesario
        this.siguiente = null; // Innecesario
    }

    public String getNombre() {
        return this.nombre;
    }

    public NodoPlaylistPropiaLista getSiguiente() {
        return this.siguiente;
    }

    public void setSiguiente(NodoPlaylistPropiaLista nodo) {
        this.siguiente = nodo;
    }

    // TO DO
    // Inserción sin orden.
    public void insertarNodoCancion() {

    }
}
