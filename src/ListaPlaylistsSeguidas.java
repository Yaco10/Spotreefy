import java.io.IOException;
import java.io.ObjectOutputStream;

class ListaPlaylistsSeguidas {
    NodoPlaylistSeguida listaPlaylistsSeguidas;

    // Constructor innecesario,
    public ListaPlaylistsSeguidas() {
        this.listaPlaylistsSeguidas = null;
    }

    // REVISAR
    // Inserción con orden alfabético (primero por nombre del dueño, después por nombre de la playlist).
    public void insertarPlaylist(String dueño, String nombre) {
        NodoPlaylistSeguida nueva = new NodoPlaylistSeguida(dueño, nombre);
        NodoPlaylistSeguida anterior = null, actual = this.listaPlaylistsSeguidas;
        // ¿temporal?
        while (actual != null && (actual.getNombre().compareToIgnoreCase(dueño) < 0 ||
                (actual.getNombre().compareToIgnoreCase(dueño) == 0 &&
                        actual.getNombre().compareToIgnoreCase(nombre) < 0))) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        if (anterior == null) {
            this.listaPlaylistsSeguidas = nueva;
        } else {
            nueva.setSiguiente(actual);
            anterior.setSiguiente(nueva);
        }
    }

    public boolean existePlaylist(String dueño, String nombre) {
        NodoPlaylistSeguida playlist = this.listaPlaylistsSeguidas;
        while (playlist != null && (!playlist.getNombre().equals(dueño) || !playlist.getNombre().equals(nombre))) {
            playlist = playlist.getSiguiente();
        }
        return playlist != null;
    }

    public void mostrarPlaylists() {
        NodoPlaylistSeguida playlist = this.listaPlaylistsSeguidas;
        while (playlist != null) {
            System.out.println("*" + playlist.getNombre() + ", de " + playlist.getNombre());
            playlist = playlist.getSiguiente();
        }
    }

    //Archivos

    public void guardarPlaylistEnArchivo(String nombreUser, ObjectOutputStream out) throws IOException {
        NodoPlaylistSeguida actual = this.listaPlaylistsSeguidas;
        while (actual != null) {
            ArchListaSeguida registro = new ArchListaSeguida(nombreUser, actual.getUsuario(), actual.getNombre());
            out.writeObject(registro);
            actual = actual.getSiguiente();
        }
    }
}