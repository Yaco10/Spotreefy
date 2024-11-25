public class Main {
    public static void main(String[] args) {
        ArbolUsuarios arb = new ArbUsuarios();
        ArbolCanciones arbolcanciones = new Ar
        arb.cargarDatos();
        Menu menu = new Menu(arb);
        menu.mostrarMenuPrincipal();
        arb.guardarDatos();

    }
}