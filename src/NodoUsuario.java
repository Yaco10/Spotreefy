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

    //crear meotod que inserte ordenado
    public void insertarPlaylistPropia(String nombrePlaylist) {

    }

    public void insertarPlaylistSeguida() {

    }
    //crear metodo que borre playlist
    public void borrarPlaylist(String nombrePlaylist) {

    }

    public NodoPlaylistPropiaLista buscarPlaylistPropia(String nombre) {
        NodoPlaylistPropiaLista actual = listaPlaylistsPropias;
        while (actual != null && actual.getNombre() != nombre) {
            actual = actual.getSiguiente();
        }
        if(actual != null){
            return actual;
        }
        return null;
    }
    //mostrar todas las playlist seguidas
    public void mostrarPlaylistSeguida() {}

    //mostrar todas las playlist propias
    public void mostrarPlaylistPropia() {}

    public String getNombre(){
        return nombre;
    }


}