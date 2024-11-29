// playlist
// lista de canciones

import java.io.Serializable;

class NodoPlaylistPropia implements Serializable {
    String nombre; // factor de orden
    SubListaCanciones sublistaCanciones;// (lista sin orden)
    NodoPlaylistPropia siguiente;

    public NodoPlaylistPropia(String nombre) {
        this.nombre = nombre;
        this.sublistaCanciones = new SubListaCanciones(); // Innecesario
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
    
    public void insertarNodoCancion(NodoCancion cancion) {
        sublistaCanciones.insertarCancion(cancion);
    }
}
