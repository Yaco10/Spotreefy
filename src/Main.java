public class Main {
    public static void main(String[] args) {
        ArbUsuarios arb = new ArbUsuarios();
        ArbCanciones arbolcanciones = new Ar
        arb.cargarDatos();
        Menu menu = new Menu(arb);
        menu.mostrarMenuPrincipal();
        arb.guardarDatos();

    }
}