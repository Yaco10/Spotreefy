import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interfaz {
    public static final String REGEX_NOMBRE = "^[A-Za-z0-9]{1,8}$";
    public static final String REGEX_CANCION = "^[A-Za-z0-9]{1,30}$";
    private NodoUsuario usuario;
    private Scanner scanner;
    private ArbolCanciones arbolCanciones;
    private ListaAutores listaAutores;

    public Interfaz(NodoUsuario usuario, ArbolCanciones arbolCanciones, ListaAutores listaAutores) {
        this.usuario = usuario;
        this.arbolCanciones = arbolCanciones;
        this.listaAutores = listaAutores;
        scanner = new Scanner(System.in);
    }


    public void mostrarInterfaz(NodoUsuario usuario){
        int opcion;
        System.out.println("Usuario" + usuario.getNombreUsuario());
        System.out.println("Listas Propias");
        usuario.getListasPropias().MostrarLista();
        System.out.println("Listas Seguidas");
        usuario.getListasSeguidos().MostrarLista();
        opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                agregarCancion();
                break;
            case 2:
                crearListaPropia();
                break;
            case 3:
                agregarCancionAListaPorTitulo();
                break;
            case 4:
                agregarCancionPorAutor()
                break;
            case 5:
                eliminarListaPropia();
                break;
            case 6:
                seguirLista();
                break;
            case 7:
                "Volviendo a menu principal...";
                break;
            default:
                System.out.println("Opcion invalida");

        }
    }

    private void agregarCancion () {
        //ingreso cancion
        System.out.println("Ingrese su Cancion");
        String nombreCancion = scanner.nextLine();
        while(!validarCancion(nombreCancion)){
            System.out.println("Ingrese un nombre con menos de 30 caracteres");
            nombreCancion = scanner.nextLine();
        }
        arbCanciones.insertarCancion(nombreCancion);
        //Ingreso autor
        System.out.println("Ingrese el nombre del autor");
        String autor = scanner.nextLine();
        while(!validarNombre(autor)){
            System.out.println("Ingrese un nombre con menos de 8 caracteres");
            autor = scanner.nextLine();
        }
        listaAutores.insertarAutor(autor);
    }

    private void crearListaPropia () {
        System.out.println("Ingrese el nombre de la playlist");
        String nombre = scanner.nextLine();
        while(!validarNombre(nombre)){
            System.out.println("Ingrese un nombre de la playlist menor a 8 caracteres");
        }
        usuario.getListasPropias().insertarPlaylist();
    }

    private void agregarCancionAListaPorTitulo () {
        System.out.println("Ingrese el nombre de la playlist");
        String nombrePlaylist = scanner.nextLine();
        NodoListaPropia Lista = usuario.getListasPropias.buscarLista(nombrePlaylist)
        if(Lista != null){
            System.out.println("Ingrese nombre de la cancion");
            String nombreCancion = scanner.nextLine();
            NodoCancion Cancion = arbolCanciones.buscarCancion(nombreCancion)
            if(Cancion != null){
               Lista.insertarCancion(Cancion)
            }
            else{
                System.out.println("No se encontro el nombre de la cancion");
            }
        }
        else{
            System.out.println("La playlist no existe");
        }
    }

    private void agregarCancionPorAutor () {
        System.out.println("Ingrese el nombre de la playlist");
        String nombrePlaylist = scanner.nextLine();
        NodoListaPropia Lista = usuario.getListasPropias.buscarLista(nombrePlaylist)
        if(Lista != null){
            System.out.println("Ingrese nombre del autor");
            String nombreAutor = scanner.nextLine();
            NodoAutor autor = listaAutores.buscarAutor(nombreAutor);
            if(autor != null){
                autor.mostrarCanciones();
                String nombreCancion = scanner.nextLine();
                NodoCancion cancion = autor.ListaCanciones.buscarCancion(nombreCancion);
                if(cancion != null){
                    Lista.insertarCancion(cancion);
                }
                else{
                    System.out.println("No se encontro el nombre de la cancion");
                }
            }
            else{
                System.out.println("No se encontro el nombre del autor");
            }
        }
        else{
            System.out.println("La playlist no existe");
        }
    }

    private void eliminarLista () {
        System.out.println("Ingrese el nombre de la playlist que desea eliminar");
        String nombrePlaylist = scanner.nextLine();
        if(usuario.getListasPropias().buscarPlaylist(nombrePlaylist)!=null){
            usuario.getListasPropias().borrarPlaylist(nombrePlaylist);
        }
        else{
            System.out.println("No se encontro el nombre de la playlist");
        }
    }

    private void seguirLista () {

    }

    //VALIDACIONES
    private boolean validarNombre (String texto){
        Pattern pattern = Pattern.compile(REGEX_NOMBRE);
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }
}


