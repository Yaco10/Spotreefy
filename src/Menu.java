import java.util.*;
import java.util.regex.*;

public class Menu {
    public static final String REGEX_NOMBRE = "^[A-Za-z0-9]{1,8}$";
    private Scanner scanner;
    private ArbolUsuarios arbUsuarios;
    private Interfaz interfaz;
    private NodoUsuario usuario;

    public Menu(ArbolUsuarios arbol, ArbolCanciones arbolCanciones, ListaAutores listaAutores) {
        scanner = new Scanner(System.in);
        arbUsuarios = arbol;
        usuario = null;
        interfaz = new Interfaz(arbol, arbolCanciones, listaAutores);
    }

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("===== Inicio de Sesión =====");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Ingresar");
            System.out.println("2. No tengo usuario, necesito crear uno");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    login();
                    break;
                case 2:
                    nuevoUsuario();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 3);
    }

    private void login() {
        String nombreUsuario;
        String contraseña;
        String opcion;

        while (true) {
            System.out.println("\n--- Login ---");
            System.out.print("Ingrese su usuario: ");
            nombreUsuario = scanner.nextLine();
            System.out.print("Ingrese su contraseña: ");
            contraseña = scanner.nextLine();

            usuario = arbUsuarios.buscarUsuario(nombreUsuario);

            if (usuario == null) {
                System.out.println("Usuario no encontrado. Presione 'm' para volver al menú o Enter para intentar de nuevo.");
            } else if (!contraseña.equals(usuario.getContraseña())) {
                System.out.println("Contraseña incorrecta. Presione 'm' para volver al menú o Enter para intentar de nuevo.");
            } else {
                System.out.println("Ingreso correctamente.");
                interfaz.inicializarUsuario(usuario);
                interfaz.mostrarInterfaz();
                break;
            }

            opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("m")) {
                System.out.println("Inicio de sesión cancelado. Volviendo al menú principal...");
                return;
            }
        }
    }

    private void nuevoUsuario() {
        System.out.println("\n--- Crear Nuevo Usuario ---");
        System.out.print("Ingrese su usuario: ");
        String nuevoUsuario = scanner.nextLine();

        // Validar nombre de usuario
        while (!validarNombre(nuevoUsuario)) {
            System.out.println("Error: El nombre de usuario debe tener máximo 8 caracteres alfanuméricos.");
            System.out.print("Ingrese un nombre de usuario válido: ");
            nuevoUsuario = scanner.nextLine();
        }

        System.out.print("Ingrese su contraseña: ");
        String contraseña = scanner.nextLine();

        // Validar contraseña
        while (contraseña.length() < 6 || !validarNombre(contraseña)) {
            System.out.println("Error: La contraseña debe tener entre 6 y 8 caracteres alfanuméricos.");
            System.out.print("Ingrese una contraseña válida: ");
            contraseña = scanner.nextLine();
        }

        arbUsuarios.insertarUsuario(nuevoUsuario, contraseña);
        System.out.println("Usuario creado exitosamente. Redirigiendo al menú de inicio de sesión...");
    }

    private boolean validarNombre(String texto) {
        Pattern pattern = Pattern.compile(REGEX_NOMBRE);
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }
}
