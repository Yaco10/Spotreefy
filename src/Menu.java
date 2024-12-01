import java.util.*;
import java.util.regex.*;

public class Menu {
    private static final String REGEX_NOMBRE = "^[A-Za-z0-9]{1,8}$";
    // private ArbolUsuarios usuarios;
    // private Interfaz interfaz;
    // private NodoUsuario usuario;
    private static Scanner scanner; // cambiado a static

    /*
    public Menu(ArbolUsuarios usuarios, ArbolCanciones canciones, ListaAutores autores) {
        scanner = new Scanner(System.in);
        this.usuarios = usuarios;
        // usuario = null;
        // FETCH USER
        // interfaz = new Interfaz(usuario, canciones, autores);
    }
    */

    // revisar
    public static void mostrarMenuPrincipal(ArbolUsuarios usuarios, ArbolCanciones canciones, ListaAutores autores) {
        int opcion;
        do {
            System.out.println("Inicio de Sesión");
            System.out.println("Seleccione una opción (número):");
            System.out.println("1. Ingresar");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    login(usuarios, canciones, autores);
                    break;
                case 2:
                    register(usuarios);
                    break;
                case 3:
                    System.out.println("Cerrando programa...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
                    break;
            }
        } while (opcion != 3);
    }

    // revisar
    private static void login(ArbolUsuarios usuarios, ArbolCanciones canciones, ListaAutores autores) {
        NodoUsuario usuario = null;
        String nombre, contraseña;
        String cancelar = "";
        while (usuario == null && !cancelar.equalsIgnoreCase("M")) {
            System.out.print("Usuario: ");
            nombre = scanner.nextLine();
            System.out.print("Contraseña: ");
            contraseña = scanner.nextLine();
            usuario = usuarios.buscarUsuario(nombre,contraseña);
            if (usuario == null) {
                System.out.println("Usuario no encontrado, presione Enter para volver a intentar o M para volver al menú principal");
                cancelar = scanner.nextLine();
            }
        }
        if (usuario != null && !cancelar.equalsIgnoreCase("M")) {
            System.out.println("Inicio de sesión exitoso\n");
            Interfaz.mostrarInterfazDeUsuario(usuario, canciones);
        }
    }

    private static void register(ArbolUsuarios usuarios) {
        System.out.println("Ingrese su usuario");
        String usuario = scanner.nextLine();
        //Valida usuario
        while (!validarNombre(usuario)) {
            System.out.println("Ingrese un nombre de usuario de maximo 8 caracteres");
            usuario = scanner.nextLine();
        }
        System.out.println("Ingrese su contraseña");
        String contraseña = scanner.nextLine();
        //Valida contraseña
        while (!validarNombre(contraseña) && contraseña.length() < 6) {
            System.out.println("Ingrese una contraseña entre 6 y 8 caracteres");
            contraseña = scanner.nextLine();
        }
        usuarios.insertarUsuario(usuario, contraseña);
        System.out.println("Redirigiendo al Menu de Inicio Sesion\n");
    }

    private static boolean validarNombre (String texto) {
        Pattern pattern = Pattern.compile(REGEX_NOMBRE);
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }
}