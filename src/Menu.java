import javax.swing.*;
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
        interfaz = new Interfaz(arbol,arbolCanciones,listaAutores);
    }

    public void mostrarMenuPrincipal() {
        int opcion;
            System.out.println("Inicio de Sesion");
            System.out.println("Seleccione una opción: ");
            System.out.println("1.Ingresar");
            System.out.println("2. No tengo usuario, necesito crear uno");
            System.out.println("3. Salir");
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
                    break;
            }

    }


    private void login () {
        String nombreUsuario;
        String contraseña;
        String opcion = "";
        while (usuario == null && !"M".equalsIgnoreCase(opcion)) {
                System.out.println("Ingrese su usuario");
                nombreUsuario = scanner.nextLine();
                System.out.println("Ingrese contraseña");
                contraseña = scanner.nextLine();
                usuario = arbUsuarios.buscarUsuario(nombreUsuario,contraseña);
                if (usuario == null) {
                    System.out.println("Usuario no encontrado, presione enter para volver a intentar o M para volver al menu");
                    opcion = scanner.nextLine();
                }
        }
        if(opcion.equalsIgnoreCase("M")) {
            mostrarMenuPrincipal();
        }
        else if(usuario != null) {
            System.out.println("Ingreso correctamente");
            interfaz.setUsuario(usuario);
            interfaz.mostrarInterfaz();
        }
    }

        private void nuevoUsuario () {
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
            arbUsuarios.insertarUsuario(usuario, contraseña);
            System.out.println("Redirigiendo al Menu de Inicio Sesion");
            mostrarMenuPrincipal();
        }

        private boolean validarNombre (String texto){
            Pattern pattern = Pattern.compile(REGEX_NOMBRE);
            Matcher matcher = pattern.matcher(texto);
            return matcher.matches();
        }

}