import java.util.*;
import java.util.regex.*;

public class Menu {
    public static final String REGEX_NOMBRE = "^[A-Za-z0-9]{1,8}$";
    public static final String REGEX_CANCION = "^[A-Za-z0-9]{1,30}$";
    private Scanner scanner;
    private ArbolUsuarios arbUsuarios;

    public Menu(ArbolUsuarios arbol, ArbolCanciones arbolCanciones, ListaAutores listaAutores) {
        scanner = new Scanner(System.in);
        arbUsuarios = arbol;
        ArbolCanciones = arbolCanciones;
        ListaAutores = listaAutores
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
        System.out.println("Ingrese su usuario");
        String usuario = scanner.nextLine();
        System.out.println("Ingrese contraseña");
        String contraseña = scanner.nextLine();
        while (arbUsuarios.buscarUsuario(usuario,contraseña) == null) {
                System.out.println("Contraseña incorrecta Ingrese Nuevamente o Presione 0");
                contraseña = scanner.nextLine();
        }
        if(contraseña != 0){
            System.out.println("Ingreso correctamente");
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
            while (!validarNombre(usuario)) {
                System.out.println("Ingrese una contraseña de usuario de maximo 8 caracteres");
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