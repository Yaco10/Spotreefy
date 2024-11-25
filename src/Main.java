public class Main {
    public static void main(String[] args) {
        ArbolUsuarios arbUs = new ArbolUsuarios();
        ArbolCanciones arbolcanciones = new ArbolCanciones();
        ListaAutores listaAutores = new ListaAutores();
        Menu menu = new Menu(arbUs, arbolcanciones, listaAutores);
        menu.mostrarMenuPrincipal();


    }
}