class ListaPlaylistsPropias {
    NodoPlaylistPropia listaPlaylistsPropias;

    // Constructor innecesario.
    public ListaPlaylistsPropias() {
        this.listaPlaylistsPropias = null;
    }

    // Inserción con orden alfabético.
    public void insertarPlaylist(String nombre) {
        NodoPlaylistPropia nueva = new NodoPlaylistPropia(nombre);
        NodoPlaylistPropia anterior = null, actual = this.listaPlaylistsPropias;
        while (actual != null && actual.getNombre().compareToIgnoreCase(nombre) < 0) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        nueva.setSiguiente(actual);
        if (anterior == null) {
            this.listaPlaylistsPropias = nueva;
        } else {
            anterior.setSiguiente(nueva);
        }
    }

    public NodoPlaylistPropia buscarPlaylist(String nombre) {
        NodoPlaylistPropia playlist = this.listaPlaylistsPropias;
        while (playlist != null && !playlist.getNombre().equalsIgnoreCase(nombre)) {
            playlist = playlist.getSiguiente();
        }
        return playlist;
    }

    public void eliminarPlaylist(String nombre) {
        if (this.listaPlaylistsPropias != null ) {
            if (this.listaPlaylistsPropias.getNombre().equalsIgnoreCase(nombre)) {
                this.listaPlaylistsPropias = this.listaPlaylistsPropias.getSiguiente();
            } else {
                NodoPlaylistPropia anterior = null, actual = this.listaPlaylistsPropias;
                while (actual != null && !actual.getNombre().equalsIgnoreCase(nombre)) {
                    anterior = actual;
                    actual = actual.getSiguiente();
                }
                anterior.setSiguiente(actual.getSiguiente());
            }
        }
    }

    public void mostrar() {
        NodoPlaylistPropia playlist = this.listaPlaylistsPropias;
        while (playlist != null) {
            System.out.println("* " + playlist.getNombre());
            playlist = playlist.getSiguiente();
        }
    }
}