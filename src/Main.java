public class Main {
    public static void main(String[] args) {
        ArbolUsuarios arbUs = ArbolUsuarios.cargarArchivo("pepe");
        ArbolCanciones arbolcanciones = new ArbolCanciones();
        ListaAutores listaAutores = new ListaAutores();
        Menu menu = new Menu(arbUs, arbolcanciones, listaAutores);
        menu.mostrarMenuPrincipal();
        arbUs.guardarArchivo("pepe");


    }
}