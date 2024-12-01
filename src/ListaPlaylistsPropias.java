import java.io.*;

public class ListaPlaylistsPropias {
    private NodoPlaylistPropia listaPlaylistPropias;

    public ListaPlaylistsPropias() {
        this.listaPlaylistPropias = null;
    }

    public void insertarPlaylist(String nombrePlaylist) {
        NodoPlaylistPropia nuevo = new NodoPlaylistPropia(nombrePlaylist);

        // Caso 1: Lista vacía o el nuevo nodo debe ir al principio
        if (listaPlaylistPropias == null || nombrePlaylist.compareTo(listaPlaylistPropias.getNombre()) < 0) {
            // Verificar si el nombre ya existe en la lista
            if (listaPlaylistPropias != null && listaPlaylistPropias.getNombre().equalsIgnoreCase(nombrePlaylist)) {
                return; // No se inserta si el nombre ya existe
            }
            nuevo.setSiguiente(listaPlaylistPropias);
            this.listaPlaylistPropias = nuevo;
        } else {
            // Caso 2: Inserción en el medio o al final
            NodoPlaylistPropia actual = listaPlaylistPropias;
            NodoPlaylistPropia anterior = null;

            while (actual != null && nombrePlaylist.compareTo(actual.getNombre()) > 0) {
                anterior = actual;
                actual = actual.getSiguiente();
            }

            // Verificar si el nombre ya existe en la lista antes de la inserción
            if (actual != null && actual.getNombre().equalsIgnoreCase(nombrePlaylist)) {
                return; // No se inserta si el nombre ya existe
            }

            nuevo.setSiguiente(actual);
            if (anterior != null) {
                anterior.setSiguiente(nuevo);
            }
        }
    }

    public NodoPlaylistPropia buscarPlaylist(String nombrePlaylist) {
        NodoPlaylistPropia actual = listaPlaylistPropias;
        while (actual != null) {
            if (nombrePlaylist.equalsIgnoreCase(actual.getNombre())) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null; // No se encontró la playlist
    }

    public void eliminarPlaylist(String nombrePlaylist) {
        if (listaPlaylistPropias == null) {
            return; // Lista vacía
        }

        if (listaPlaylistPropias.getNombre().equalsIgnoreCase(nombrePlaylist)) {
            listaPlaylistPropias = listaPlaylistPropias.getSiguiente();
            return;
        }

        NodoPlaylistPropia actual = listaPlaylistPropias;
        NodoPlaylistPropia anterior = null;
        while (actual != null && !actual.getNombre().equalsIgnoreCase(nombrePlaylist)) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (actual != null && anterior != null) {
            anterior.setSiguiente(actual.getSiguiente());
        }
    }

    public void mostrarPlaylistPropia() {
        if (listaPlaylistPropias == null) {
            System.out.println("No hay playlists disponibles.");
            return;
        }

        NodoPlaylistPropia actual = listaPlaylistPropias;
        while (actual != null) {
            System.out.println(actual.getNombre());
            actual = actual.getSiguiente();
        }
        System.out.println();
    }

    // Métodos relacionados con archivos

    public void guardarEnArchivoPlaylist(String nombreUsuario, ObjectOutputStream out) throws IOException {
        NodoPlaylistPropia actual = listaPlaylistPropias;
        while (actual != null) {
            actual.getSublistaCanciones().guardarCancionesDeLista(nombreUsuario, actual.getNombre(), out);
            actual = actual.getSiguiente();
        }
    }
}
