// playlist
// lista de canciones
class NodoPlaylistPropiaLista {
    String nombre;
    NodoIndexadoCancion canciones; // (lista sin orden)
    NodoPlaylistPropiaLista siguiente;

    public void insertarNodoCancion(NodoCancion cancion) {

    }

    public String getNombre(){
        return nombre;
    }

    public NodoPlaylistPropiaLista getSiguiente(){
        return siguiente;
    }

}


