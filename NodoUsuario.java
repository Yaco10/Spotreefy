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

    public void insertarPlaylistPropiaOrdenado(String nombre) {
        NodoPlaylistPropiaLista nuevo = new NodoPlaylistPropiaLista(nombre);
        NodoPlaylistPropiaLista anterior = null, actual = this.listaPlaylistsPropias;
        while (actual != null && actual.getNombre().compareToIgnoreCase(nuevo.getNombre()) < 0) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        nuevo.setSiguiente(actual);
        if (anterior != null) {
            anterior.setSiguiente(nuevo);
        } else {
            this.listaPlaylistsPropias = nuevo;
        }
    }

    // Orden: primero por nombre de usuario, después por nombre de playlist
    public void insertarPlaylistSeguidaOrdenado() {

    }

    public String getNombre() {
        return this.nombre;
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
}