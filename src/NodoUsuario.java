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

    //Getter y setters

    public String getNombre(){
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public NodoPlaylistPropiaLista getListaPlaylistsPropias() {
        return listaPlaylistsPropias;
    }

    public void setListaPlaylistsPropias(NodoPlaylistPropiaLista listaPlaylistsPropias) {
        this.listaPlaylistsPropias = listaPlaylistsPropias;
    }

    public NodoPlaylistSeguida getListaPlaylistsSeguidas() {
        return listaPlaylistsSeguidas;
    }

    public void setListaPlaylistsSeguidas(NodoPlaylistSeguida listaPlaylistsSeguidas) {
        this.listaPlaylistsSeguidas = listaPlaylistsSeguidas;
    }

    public NodoUsuario getMayores() {
        return mayores;
    }

    public void setMayores(NodoUsuario mayores) {
        this.mayores = mayores;
    }

    public NodoUsuario getMenores() {
        return menores;
    }

    public void setMenores(NodoUsuario menores) {
        this.menores = menores;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}