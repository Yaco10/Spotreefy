class NodoUsuario {
    private String nombre, contraseña;
    private ListaPlaylistsPropias playlistsPropias;
    private ListaPlaylistsSeguidas playlistsSeguidas;
    private NodoUsuario menores, mayores;

    public NodoUsuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.playlistsPropias = new ListaPlaylistsPropias();
        this.playlistsSeguidas = new ListaPlaylistsSeguidas();
        this.menores = null; // Innecesario
        this.mayores = null; // Innecesario
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public ListaPlaylistsPropias getPlaylistsPropias() {
        return this.playlistsPropias;
    }

    public ListaPlaylistsSeguidas getPlaylistsSeguidas() {
        return this.playlistsSeguidas;
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