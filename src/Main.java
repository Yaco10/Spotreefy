public class Main {
    public static void main(String[] args) {
        ArbolUsuarios arbUs = new ArbolUsuarios();
        ArbolCanciones arbolcanciones = new ArbolCanciones();
        ListaAutores listaAutores = new ListaAutores();
        arbolcanciones.cargarCanciones("ArchCanciones");
        listaAutores.cargarAutores("ArchCanciones",arbolcanciones);
        arbUs.cargarPlaylistsPropias("ArchListasPropia");
        arbUs.cargarPlaylistsSeguidas("ArchListasSeguida");
        Menu menu = new Menu(arbUs, arbolcanciones, listaAutores);
        menu.mostrarMenuPrincipal();
        arbUs.guardarEnArchivo("archUsuarios");
        listaAutores.guardarAutores("ArchCanciones");
        arbUs.guardarPlaylistsPropias("ArchListasPropia");
        arbUs.guardarPlaylistsSeguidas("ArchListasSeguida");

    }
}