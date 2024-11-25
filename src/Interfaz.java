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

    public Interfaz (NodoUsuario usuario, ArbolCanciones arbolCanciones, ListaAutores listaAutores) {
        this.usuario = usuario;
        this.arbolCanciones = arbolCanciones;
        this.listaAutores = listaAutores;
        scanner = new Scanner(System.in);
    }


    public void mostrarInterfaz(NodoUsuario usuario){
        int opcion;
        System.out.println("Usuario" + usuario.getNombre());
        System.out.println("Listas Propias");
        usuario.mostrarPlaylistPropia();
        System.out.println("Listas Seguidas");
        usuario.mostrarPlaylistPropia();
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
                agregarCancionPorAutor();
                break;
            case 5:
                eliminarListaPropia();
                break;
            case 6:
                seguirLista();
                break;
            case 7:
                System.out.println("Volviendo a menu principal...");
                break;
            default:
                System.out.println("Opcion invalida");

        }
    }

    private void agregarCancion () {
        //ingreso cancion
        System.out.println("Ingrese su Cancion");
        String nombreCancion = scanner.nextLine();
        while(!validarCancion(nombreCancion)){ //valido con regex para cancion
            System.out.println("Ingrese un nombre con menos de 30 caracteres");
            nombreCancion = scanner.nextLine();
        }
        ArbolCanciones.insertarCancion(nombreCancion);
        //Ingreso autor
        System.out.println("Ingrese el nombre del autor");
        String NombreAutor = scanner.nextLine();
        while(!validarNombre(NombreAutor)){
            System.out.println("Ingrese un nombre con menos de 8 caracteres");
            NombreAutor = scanner.nextLine();
        }
        listaAutores.insertarAutor(NombreAutor);
    }

    private void crearListaPropia () {
        System.out.println("Ingrese el nombre de la playlist");
        String nombrePlaylist = scanner.nextLine();
        while(!validarNombre(nombrePlaylist)){
            System.out.println("Ingrese un nombre de la playlist menor a 8 caracteres");
        }
        usuario.insertarPlaylistPropia(nombrePlaylist);
    }

    private void agregarCancionAListaPorTitulo () {
        System.out.println("Ingrese el nombre de la playlist");
        String nombrePlaylist = scanner.nextLine();
        NodoPlaylistPropiaLista Playlist = usuario.buscarPlaylistPropia(nombrePlaylist); //obtengo nodo perteneciente a playlist
        if(Playlist != null){
            System.out.println("Ingrese nombre de la cancion");
            String nombreCancion = scanner.nextLine();
            NodoCancion Cancion = arbolCanciones.buscarCancion(nombreCancion); //obtengo nodo perteneciente a cancion
            if(Cancion != null){
                Playlist.insertarNodoCancion(Cancion); //inserto nodo cancion en la playlist correspondiente
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
        NodoPlaylistPropiaLista Lista = usuario.buscarPlaylistPropia(nombrePlaylist); //obtengo nodo playlist
        if(Lista != null){
            System.out.println("Ingrese nombre del autor");
            String nombreAutor = scanner.nextLine();
            NodoAutor autor = listaAutores.buscarAutor(nombreAutor); //obtengo nodo autor
            if(autor != null){
                autor.mostrarCanciones();
                String nombreCancion = scanner.nextLine();
                NodoCancion cancion = autor.buscarCancion(nombreCancion);
                if(cancion != null){
                    Lista.insertarNodoCancion(cancion);
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

    private void eliminarListaPropia () {
        System.out.println("Ingrese el nombre de la playlist que desea eliminar");
        String nombrePlaylist = scanner.nextLine();
        if(usuario.buscarPlaylistPropia(nombrePlaylist)!=null){
            usuario.borrarPlaylist(nombrePlaylist);
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

    private boolean validarCancion (String texto){
        Pattern pattern = Pattern.compile(REGEX_CANCION);
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }
}


