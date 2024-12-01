import java.io.IOException;
import java.io.ObjectOutputStream;

class ListaPlaylistsSeguidas {
    NodoPlaylistSeguida listaPlaylistsSeguidas;

    // Constructor
    public ListaPlaylistsSeguidas() {
        this.listaPlaylistsSeguidas = null;
    }

    // Inserción con orden alfabético (primero por nombre del dueño, después por nombre de la playlist).
    public void insertarPlaylist(String dueño, String nombre) {
        NodoPlaylistSeguida nueva = new NodoPlaylistSeguida(dueño, nombre);
        NodoPlaylistSeguida anterior = null, actual = this.listaPlaylistsSeguidas;

        // Bucle para encontrar la posición correcta
        while (actual != null && (actual.getUsuario().compareToIgnoreCase(dueño) < 0 ||
                (actual.getUsuario().compareToIgnoreCase(dueño) == 0 &&
                        actual.getNombre().compareToIgnoreCase(nombre) < 0))) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        if (anterior == null) { // Inserción al inicio
            nueva.setSiguiente(this.listaPlaylistsSeguidas);
            this.listaPlaylistsSeguidas = nueva;
        } else { // Inserción en el medio o al final
            nueva.setSiguiente(actual);
            anterior.setSiguiente(nueva);
        }
    }

    // Verifica si existe una playlist con el dueño y nombre especificados
    public boolean existePlaylist(String dueño, String nombre) {
        NodoPlaylistSeguida playlist = this.listaPlaylistsSeguidas;
        while (playlist != null) {
            if (playlist.getUsuario().equalsIgnoreCase(dueño) && playlist.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
            playlist = playlist.getSiguiente();
        }
        return false;
    }

    // Muestra todas las playlists seguidas
    public void mostrarPlaylists() {
        NodoPlaylistSeguida playlist = this.listaPlaylistsSeguidas;
        while (playlist != null) {
            System.out.println("* " + playlist.getNombre() + ", de " + playlist.getUsuario());
            playlist = playlist.getSiguiente();
        }
    }

    // Guarda las playlists en un archivo
    public void guardarPlaylistEnArchivo(String nombreUser, ObjectOutputStream out) throws IOException {
        NodoPlaylistSeguida actual = this.listaPlaylistsSeguidas;
        while (actual != null) {
            ArchListaSeguida registro = new ArchListaSeguida(nombreUser, actual.getUsuario(), actual.getNombre());
            out.writeObject(registro);
            actual = actual.getSiguiente();
        }
    }
}
