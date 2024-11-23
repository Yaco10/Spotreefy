// usuario
// lista de listas propias (playlists propias)
// lista de listas seguidas (playlists seguidas)
class NodoUsuario {
    String nombre; // factor de orden en ArbolUsuario
    String contraseña;
    NodoPlaylistPropiaLista listaPlaylistsPropias; // lista ordenada
    NodoPlaylistSeguida listaPlaylistsSeguidas; // lista ordenada
    NodoUsuario menores, mayores;

    public NodoUsuario (String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.listaPlaylistsPropias = null; // Innecesario
        this.listaPlaylistsSeguidas = null; // Innecesario
        this.menores = null; // Innecesario
        this.mayores = null; // Innecesario
    }

    public void insertarPlaylistPropiaOrdenado() {

    }

    public void insertarPlaylistSeguidaOrdenado() {

    }
}