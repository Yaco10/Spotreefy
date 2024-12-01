class ListaPlaylistsSeguidas {
    private NodoPlaylistSeguida listaPlaylistsSeguidas;

    // Constructor innecesario,
    public ListaPlaylistsSeguidas() {
        this.listaPlaylistsSeguidas = null;
    }

    // Inserción con orden alfabético (primero por nombre del dueño, después por nombre de la playlist).
    public void insertarPlaylist(String dueño, String nombre) {
        NodoPlaylistSeguida nueva = new NodoPlaylistSeguida(dueño, nombre);
        NodoPlaylistSeguida anterior = null, actual = this.listaPlaylistsSeguidas;
        while (actual != null && (actual.getDueño().compareToIgnoreCase(dueño) < 0 ||
        (actual.getDueño().equalsIgnoreCase(dueño) && actual.getNombre().compareToIgnoreCase(nombre) < 0))) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        nueva.setSiguiente(actual);
        if (anterior == null) {
            this.listaPlaylistsSeguidas = nueva;
        } else {
            anterior.setSiguiente(nueva);
        }
    }

    public boolean existePlaylist(String dueño, String nombre) {
        NodoPlaylistSeguida playlist = this.listaPlaylistsSeguidas;
        while (playlist != null && (!playlist.getDueño().equalsIgnoreCase(dueño) || !playlist.getNombre().equalsIgnoreCase(nombre))) {
            playlist = playlist.getSiguiente();
        }
        return playlist != null;
    }

    public void mostrar() {
        NodoPlaylistSeguida playlist = this.listaPlaylistsSeguidas;
        while (playlist != null) {
            System.out.println("* " + playlist.getNombre() + ", de " + playlist.getDueño());
            playlist = playlist.getSiguiente();
        }
    }
}
