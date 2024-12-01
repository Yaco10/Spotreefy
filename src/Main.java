class Main {
    public static void main(String[] args) {
        ArbolUsuarios usuarios = new ArbolUsuarios();
        RegistroUsuario.cargarArbol(usuarios);
        ArbolCanciones canciones = new ArbolCanciones();
        RegistroCancion.cargarArbol(canciones);
        ListaAutores autores = new ListaAutores();
        // autores.cargarArchivo("ArchCanciones", canciones);
        
        // Menu menu = new Menu(usuarios, canciones, autores);
        // menu.mostrarMenuPrincipal();
        Menu.mostrarMenuPrincipal(usuarios, canciones, autores);

        usuarios.guardarArbol("ArchUsuarios");
        autores.guardarArchivo("ArchCanciones");
    }
}