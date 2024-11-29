// usuario
// lista de listas propias (playlists propias)
// lista de listas seguidas (playlists seguidas)

import java.io.Serializable;

class NodoUsuario implements Serializable {
    String nombre; // factor de orden en ArbolUsuario
    String contraseña;
    ListaPlaylistsPropias playlistsPropias;
    ListaPlaylistsSeguidas playlistsSeguidas;
    NodoUsuario menores, mayores;

    public NodoUsuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        // this.listaPlaylistsPropias = null; // Innecesario
        // this.listaPlaylistsSeguidas = null; // Innecesario
        this.playlistsPropias = new ListaPlaylistsPropias(); // Innecesario
        this.playlistsSeguidas = new ListaPlaylistsSeguidas(); // Innecesario
        this.menores = null; // Innecesario
        this.mayores = null; // Innecesario
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public NodoUsuario getMenores() {
        return this.menores;
    }

    public void setMenores(NodoUsuario nodo) {
        this.menores = nodo;
    }

    public NodoUsuario getMayores() {
        return this.mayores;
    }

    public void setMayores(NodoUsuario nodo) {
        this.mayores = nodo;
    }

    public ListaPlaylistsPropias getPlaylistsPropias() {
        return this.playlistsPropias;
    }
}


