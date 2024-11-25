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

    // Inserción con orden alfabético.
    public void insertarPlaylistPropia(String nombre) {
        NodoPlaylistPropiaLista nuevo = new NodoPlaylistPropiaLista(nombre);
        NodoPlaylistPropiaLista anterior = null, actual = this.listaPlaylistsPropias;
        // while (actual != null && actual.getNombre().compareToIgnoreCase(nuevo.getNombre()) < 0) {
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
    // Inserción con orden alfabético.
    public void insertarPlaylistSeguida(String usuario, String nombre) {
        NodoPlaylistSeguida nuevo = new NodoPlaylistSeguida(usuario, nombre);
        NodoPlaylistSeguida anterior = null, actual = this.listaPlaylistsSeguidas;
        // ¿temporal?
        while (actual != null &&
            actual.getUsuario().compareToIgnoreCase(nuevo.getUsuario()) < 0 ||
            actual.getUsuario().compareToIgnoreCase(nuevo.getUsuario()) == 0 &&
            actual.getNombre().compareToIgnoreCase(nuevo.getNombre()) < 0) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        nuevo.setSiguiente(actual);
        if (anterior != null) {
            anterior.setSiguiente(nuevo);
        } else {
            this.listaPlaylistsSeguidas = nuevo;
        }
    }
}