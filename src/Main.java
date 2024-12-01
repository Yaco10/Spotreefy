public class Main {
    public static void main(String[] args) {
        ArbolUsuarios arbUs = new ArbolUsuarios();
        ArbolCanciones arbolcanciones = new ArbolCanciones();
        ListaAutores listaAutores = new ListaAutores();
        arbolcanciones.cargarArchivo("ArchCanciones");
        listaAutores.cargarAutores("ArchCanciones",arbolcanciones);
        arbUs.cargarArchivoPlaylist("ArchListasPropia");
        arbUs.cargarArchivoPlaylistSeguida("ArchListasSeguida");
        Menu menu = new Menu(arbUs, arbolcanciones, listaAutores);
        menu.mostrarMenuPrincipal();
        arbUs.guardarEnArchivo("archUsuarios");
        listaAutores.guardarEnArchivoCanciones("ArchCanciones");
        arbUs.guardarEnArchivoPlaylist("ArchListasPropia");
        arbUs.guardarArchivoPlaylistSeguida("ArchListasSeguida");



    }
}