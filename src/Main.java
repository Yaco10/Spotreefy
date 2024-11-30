public class Main {
    public static void main(String[] args) {
        ArbolUsuarios arbUs = new ArbolUsuarios();
        ArbolCanciones arbolcanciones = new ArbolCanciones();
        ListaAutores listaAutores = new ListaAutores();
        arbolcanciones.cargarArchivo("ArchCanciones");
        listaAutores.cargarArchivo("ArchCanciones",arbolcanciones);
        Menu menu = new Menu(arbUs, arbolcanciones, listaAutores);
        menu.mostrarMenuPrincipal();
        arbUs.guardarArbol("archUsuarios");
        listaAutores.guardarArchivo("ArchCanciones");


    }
}